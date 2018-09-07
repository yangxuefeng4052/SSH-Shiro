package com.yxf.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.yxf.oa.dao.ReportFormDao;

/**
*
* @author yxf
* @time 2018年9月3日上午10:41:06
*
*/
@Repository("reportFormDao")
public class ReprotFormDaoImpl implements ReportFormDao{
	@Resource
	HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Process> findAllProcesses(int page) {
		String hql = "from Process order by processId desc";
		
		
		
		List<Process> processes =  (List<Process>) hibernateTemplate.execute(new HibernateCallback() {
			int pageCount = 5;
			int pageNum = (page - 1 ) * pageCount;
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);

	            query.setFirstResult(pageNum);

	            query.setMaxResults(pageCount);

	            List list = query.list();

	            return list;
			}
		});
		
		return processes;
	}

	@Override
	public List<Process> findAllEndProcesses() {
		String hql = "from Process where state=? and processName=?";
		List<Process> processes = (List<Process>) hibernateTemplate.find(hql,2,"病事假申请");
		return processes;
	}

	@Override
	public int findAllCount() {
		String hql = "from Process order by processId desc";
		int count = hibernateTemplate.find(hql).size();
		return count;
	}

}
