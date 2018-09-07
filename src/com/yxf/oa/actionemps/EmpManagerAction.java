package com.yxf.oa.actionemps;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.service.EmpService;
import com.yxf.oa.service.EmployeeService;

/**
*
* @author yxf
* @time 2018年8月29日下午9:14:10
*
*/
@Controller("empManagerAction")
public class EmpManagerAction {
	
	@Resource
	EmployeeService employeeService;
	
	private String page;
	
	public String showEmps(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		if (page == null) {
			page = "1";
		}
		List<Emp> emps = new ArrayList<>();
		if (emp.getDept().getId() == 6) {
			emps = employeeService.findAllEmps(Integer.parseInt(page));
		}else {
			emps = employeeService.findAllEmps(emp.getDept().getId(), Integer.parseInt(page));
		}
		int count = employeeService.findCount(emp.getDept().getId());
		
		session.setAttribute("newEmps", emps);
		session.setAttribute("empCount", count);
		
		return "success";
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	
	
}
