package com.yxf.oa.actionemps;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yxf.oa.bean.Dept;
import com.yxf.oa.bean.Emp;
import com.yxf.oa.bean.Role;
import com.yxf.oa.service.EmployeeService;
import com.yxf.oa.service.RoleAndPermissionService;
import com.yxf.oa.utils.RandomUtils;

/**
 *
 * @author yxf
 * @time 2018年8月29日下午9:24:51
 *
 */
@Controller("addEmpAction")
public class AddEmpAction {
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
	@Resource
	RoleAndPermissionService roleAndPermissionService;
	

	public String addEmp(){
		Emp emp = new Emp();
		
		emp.setAge(Integer.parseInt(age));
		emp.setEmail(email);
		emp.setIsmarry(Integer.parseInt(ismarry));
		emp.setPhone(phone);
		emp.setSex(sex);
		emp.setUsername(username);
		
		
//		生成工号
			//对于新创建的用户，生成工号，初始账号为手机号，密码为abcd@1234
			emp.setE_code(emp.getPhone());
			emp.setPsw("abcd@1234");
//			List<Emp> emps = employeeService.findAllEmps(1);
			//获取所有员工个数
			String empid = RandomUtils.buildEmpId(employeeService.findCount(6));
			emp.setEmpId(empid);
		System.out.println(emp);
		
		employeeService.addEmp(emp, Integer.parseInt(job), Integer.parseInt(dept));
		
		
			return "success";
	}
	
	//
	
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	

}
