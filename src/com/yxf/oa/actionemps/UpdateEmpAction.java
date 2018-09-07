package com.yxf.oa.actionemps;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.service.EmployeeService;

/**
*
* @author yxf
* @time 2018年8月30日上午9:40:33
*
*/
@Controller("updateEmpAction")
public class UpdateEmpAction {
	@Resource
	EmployeeService employeeService;
	
	private String age;
	private String sex;
	private String ismarry;
	private String phone;
	private String email;
	
	
	public String updateEmpInfo(){
		//先将对象持久化，再修改
		HttpSession session = ServletActionContext.getRequest().getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		emp = employeeService.findEmpById(emp.getId());
		emp.setAge(Integer.parseInt(age));
		emp.setSex(sex);
		emp.setIsmarry(Integer.parseInt(ismarry));
		emp.setPhone(phone);
		emp.setE_code(phone);
		emp.setEmail(email);
		
		Emp newEmp = employeeService.updateEmpInfo(emp);
		session.setAttribute("emp", newEmp);
		
		
		
		return "success";
		
		
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getIsmarry() {
		return ismarry;
	}


	public void setIsmarry(String ismarry) {
		this.ismarry = ismarry;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
