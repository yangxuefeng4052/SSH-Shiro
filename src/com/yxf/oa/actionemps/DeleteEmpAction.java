package com.yxf.oa.actionemps;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.service.EmployeeService;

/**
*
* @author yxf
* @time 2018年8月30日上午11:09:56
*
*/
@Controller("deleteEmpAction")
public class DeleteEmpAction {
	
	private String empId;
	@Resource
	EmployeeService employeeService;
	
	public String deleteEmp(){
		Emp emp  = employeeService.findEmpByEmpid(empId);
		employeeService.deleteEmp(emp);
		
		return "success";
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
	
}
