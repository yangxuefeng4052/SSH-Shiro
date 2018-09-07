package com.yxf.oa.actionturnpage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.service.EmployeeService;

/**
*
* @author yxf
* @time 2018年8月30日下午4:56:06
*
*/
@Controller("turnToUpdateEmpAction")
public class TurnToUpdateEmpAction {
	
	private String empId;
	@Resource
	EmployeeService employeeService;
	
	
	public String turnToUpdateEmp(){
		
		Emp updateEmp = employeeService.findEmpByEmpid(empId);
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("updateEmp", updateEmp);
		
		return "success";
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
	
	
}
