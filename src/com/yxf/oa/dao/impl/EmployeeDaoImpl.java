package com.yxf.oa.dao.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.yxf.oa.bean.Dept;
import com.yxf.oa.bean.Emp;
import com.yxf.oa.bean.Notice;
import com.yxf.oa.bean.Role;
import com.yxf.oa.dao.EmployeeDao;

/**
*
* @author yxf
* @time 2018年8月29日下午9:29:30
*
*/
@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Resource
	HibernateTemplate hibernateTemplate;
	
	
	@Override
	public List<Emp> findAllEmps(int page) {
		String hql = "from Emp order by dept.id ";
		int pageCount = 5;
		int pageNum = (page - 1 ) * pageCount;
		List<Emp> emps = (List<Emp>) hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);

	            query.setFirstResult(pageNum);

	            query.setMaxResults(pageCount);

	            List list = query.list();

	            return list;
			}
		});
		return emps;
	}

	@Override
	public boolean addEmp(Emp emp,int rid, int deptId) {
		try {
			Role role = hibernateTemplate.get(Role.class, rid);
			Dept dept = hibernateTemplate.get(Dept.class, deptId);
			Set<Emp> emps1 = role.getEmps();
			emps1.add(emp);
			role.setEmps(emps1);
			
			Set<Emp> emps2 = dept.getEmps();
			emps2.add(emp);
			dept.setEmps(emps2);
			emp.setRole(role);
			emp.setDept(dept);
			
			hibernateTemplate.update(role);
			hibernateTemplate.update(dept);
			
			
		} catch (Exception e) {
			return false;
		}
		
		
		return true;
	}

	@Override
	public Dept findDeptById(int id) {
		Dept dept = hibernateTemplate.get(Dept.class, id);
		return dept;
	}

	@Override
	public Emp findEmpById(int id) {
		Emp emp = hibernateTemplate.get(Emp.class, id);
		return emp;
	}

	@Override
	public Emp updateEmpInfo(Emp emp) {
		hibernateTemplate.update(emp);
		return emp;
	}

	@Override
	public Emp findEmpByEmpid(String empId) {
		String hql = "from Emp where empId=?";
		List<Emp> emps = (List<Emp>) hibernateTemplate.find(hql, empId);
		Emp emp = emps.get(0);
		return emp;
	}

	@Override
	public void deleteEmp(Emp emp) {
		hibernateTemplate.delete(emp);
		
	}

	@Override
	public void updateEmp(Emp emp, int rid, int deptId) {
		try {
			emp.setRole(null);
			emp.setDept(null);
			
			Role role = hibernateTemplate.get(Role.class, rid);
			Dept dept = hibernateTemplate.get(Dept.class, deptId);
			
			Set<Emp> emps1 = role.getEmps();
			for (Emp emp2 : emps1) {
				if (emp2.getEmpId().equals(emp.getEmpId())) {
					emps1.remove(emp);
					break;
				}
			}
			System.out.println(emps1);
			Set<Emp> emps2 = dept.getEmps();
			for (Emp emp3 : emps1) {
				if (emp3.getEmpId().equals(emp.getEmpId())) {
					emps1.remove(emp);
					break;
				}
			}
			
			hibernateTemplate.update(emp);
			hibernateTemplate.delete(emp);
			
			System.out.println(emp + "-------------------");
			emps1.add(emp);
			emps2.add(emp);
			
			role.setEmps(emps1);
			System.out.println(role.getEmps());
			dept.setEmps(emps2);
			
			emp.setRole(role);
			emp.setDept(dept);
			
			
			
			
			hibernateTemplate.saveOrUpdate(emp);
			
			
		} catch (Exception e) {
		}
		
	}

	@Override
	public List<Emp> findAllEmps(int deptId,int page) {
		String hql = "from Emp where dept.id=?";
		
		int pageCount = 5;
		int pageNum = (page - 1 ) * pageCount;
		
		List<Emp> emps = (List<Emp>) hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				
				query.setInteger(0, deptId);
				
	            query.setFirstResult(pageNum);

	            query.setMaxResults(pageCount);

	            List list = query.list();

	            return list;
			}
		});
		
		return emps;
	}

	@Override
	public int findCount(int deptId) {
		String hql = "";
		int count = 0;
		if (deptId == 6) {
			hql = "from Emp";
			count = hibernateTemplate.find(hql).size();
		}else {
			hql = "from Emp where dept.id=?";
			count = hibernateTemplate.find(hql, deptId).size();
		}
		return count;
	}

	@Override
	public boolean addNotice(Notice notice) {
		String hql = "from Notice where deptId=?";
		List<Notice> notices = (List<Notice>) hibernateTemplate.find(hql, notice.getDeptId());
		Notice pNotice;
		try {
			if (hibernateTemplate.find(hql, notice.getDeptId()).isEmpty()) {
				hibernateTemplate.save(notice);
			}else {
				pNotice = notices.get(0);
				pNotice.setContent(notice.getContent());
				pNotice.setDeptId(notice.getDeptId());
				pNotice.setTitle(notice.getTitle());
				hibernateTemplate.update(pNotice);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public Notice findNoticeByDeptId(int deptId) {
		String hql = "from Notice where deptId=?";
		
		List<Notice> notices = (List<Notice>) hibernateTemplate.find(hql, deptId);
		
		if (hibernateTemplate.find(hql, deptId).isEmpty()) {
			return null;
		}
		
		return notices.get(0);
	}

	@Override
	public List<Emp> findAllEmps() {
		String hql = "from Emp";
		
		List<Emp> emps = (List<Emp>) hibernateTemplate.find(hql);
		
		return emps;
	}

}
