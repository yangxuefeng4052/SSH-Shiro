package com.yxf.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.yxf.oa.bean.Process;
import com.yxf.oa.dao.ProcessDao;

/**
*
* @author yxf
* @time 2018年8月31日下午7:14:49
*
*/
@Repository("processDao")
public class ProcessDaoImpl implements ProcessDao{

	@Resource
	HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addProcess(Process process) {
		try {
			hibernateTemplate.getSessionFactory().getCurrentSession().flush();
			hibernateTemplate.saveOrUpdate(process);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Process> findProcessByCreater(String creater) {
		String hql = "from Process where creater=? order by processId desc";
		List<Process> processes = (List<Process>) hibernateTemplate.find(hql, creater);
		return processes;
	}

	@Override
	public Process findProcessInfoByPid(String processId) {
		
		Process process = hibernateTemplate.get(Process.class, processId);
		
		return process;
	}

	@Override
	public List<Process> findProcessByNext(String next) {
		String hql = "from Process where next=?";
		
		List<Process> processes = (List<Process>) hibernateTemplate.find(hql, next);
		
		return processes;
	}

}
