package com.yxf.oa.actionemps;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.service.EmployeeService;

/**
*
* @author yxf
* @time 2018年8月30日下午5:35:03
*
*/
@Controller("updateEmpInfo")
public class UpdateEmpInfoAction {
	private String empId;
	private String username;
	private String dept;
	private String job;
	private String age;
	private String sex;
	private String ismarry;
	private String phone;
	private String email;
	
	@Resource
	EmployeeService employeeService;
	
	public String updateEmpInfo(){
		System.out.println(empId);
		Emp emp = employeeService.findEmpByEmpid(empId);
		emp.setAge(Integer.parseInt(age));
		emp.setUsername(username);
		emp.setSex(sex);
		emp.setIsmarry(Integer.parseInt(ismarry));
		emp.setPhone(phone);
		emp.setE_code(phone);
		emp.setEmail(email);
		emp.setEmpId(empId);
		employeeService.updateEmp(emp, Integer.parseInt(job), Integer.parseInt(dept));
		
		
		return "success";
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
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
