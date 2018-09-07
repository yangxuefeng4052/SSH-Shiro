package com.yxf.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxf.oa.bean.Dept;
import com.yxf.oa.bean.Emp;
import com.yxf.oa.bean.Notice;
import com.yxf.oa.dao.EmployeeDao;
import com.yxf.oa.service.EmployeeService;
import com.yxf.oa.utils.RandomUtils;

/**
*
* @author yxf
* @time 2018年8月29日下午9:36:08
*
*/
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Resource
	EmployeeDao employeeDao;
	
	
	@Override
	public List<Emp> findAllEmps(int page) {
		return employeeDao.findAllEmps(page);
	}

	@Override
	public boolean addEmp(Emp emp,int rid, int deptId) {
		
		return employeeDao.addEmp(emp, rid, deptId);
	}

	@Override
	public Dept findDeptById(int id) {
		
		return employeeDao.findDeptById(id);
	}

	@Override
	public Emp findEmpById(int id) {
		
		return employeeDao.findEmpById(id);
	}

	@Override
	public Emp updateEmpInfo(Emp emp) {
		return employeeDao.updateEmpInfo(emp);
	}

	@Override
	public Emp findEmpByEmpid(String empId) {
		
		return employeeDao.findEmpByEmpid(empId);
	}

	@Override
	public void deleteEmp(Emp emp) {
		employeeDao.deleteEmp(emp);
		
	}

	@Override
	public void updateEmp(Emp emp, int rid, int deptId) {
		employeeDao.updateEmp(emp, rid, deptId);
		
	}

	@Override
	public List<Emp> findAllEmps(int deptId,int page) {
		
		return employeeDao.findAllEmps(deptId,page);
	}

	@Override
	public int findCount(int deptId) {
		
		return employeeDao.findCount(deptId);
	}

	@Override
	public boolean addNotice(Notice notice) {
		
		return employeeDao.addNotice(notice);
	}

	@Override
	public Notice findNoticeByDeptId(int deptId) {
		
		return employeeDao.findNoticeByDeptId(deptId);
	}

	@Override
	public List<Emp> findAllEmps() {
		return employeeDao.findAllEmps();
	}

}
