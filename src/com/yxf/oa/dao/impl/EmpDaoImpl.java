package com.yxf.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.dao.EmpDao;

/**
*
* @author yxf
* @time 2018年8月27日下午8:07:22
*
*/
@Repository("empDao")
public class EmpDaoImpl implements EmpDao {
	@Resource
	HibernateTemplate hibernateTemplate;
	
	@Override
	public Emp loginCheck(String username, String password) {
		//查询是否存在该emp对象
		String hql = "from Emp where e_code=? and psw=?";
		//调用API
		List<Emp> ownEmp = (List<Emp>) hibernateTemplate.find(hql, username, password);
		
		
		return ownEmp.get(0);
	}

	@Override
	public Emp checkEmail(Emp emp) {
		
		String hql = "from Emp where email=?";
		System.out.println(emp);
		List<Emp> emps = (List<Emp>) hibernateTemplate.find(hql,emp.getEmail());
		System.out.println(emps.size() + ":" + emps.get(0).getEmail());
		emp = emps.get(0);
		
		return emp;
	}

	@Override
	public Emp setNewPsw(Emp emp) {
		//生成默认密码赋值给emp
		emp.setPsw("abcd@1234");
		System.out.println(emp);
		hibernateTemplate.update(emp);
		return emp;
	}

	@Override
	public Emp findEmpInfo(Emp emp) {
		String hql = "from Emp where e_code=? and psw=?";
		List<?> emps = hibernateTemplate.find(hql, emp.getE_code(),emp.getPsw());
		return (Emp) emps.get(0);
	}

	@Override
	public Emp updatePsw(Emp emp) {
		Emp newEmp = findEmpById(emp.getId());
		newEmp.setPsw(emp.getPsw());
		hibernateTemplate.saveOrUpdate(newEmp);
		System.out.println(newEmp);
		return newEmp;
	}

	@Override
	public Emp findEmpById(int id) {
		String hql = "from Emp where id=?";
		List<?> emps = hibernateTemplate.find(hql, id);
		Emp empById = (Emp) emps.get(0);
		return empById;
	}

}
