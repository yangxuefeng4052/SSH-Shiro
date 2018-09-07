package com.yxf.oa.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工实体类
 * 
 * @author yxf
 * @time 2018年8月27日下午5:25:47
 *
 */

public class Emp implements Serializable {
	private int id;
	private String e_code;
	private String psw;
	private String username;
	private int age;
	private String sex;
	private int ismarry;
	private String phone;
	private String email;
	private String empId;
	private Date createTime;
	
	private Dept dept;
	
	private Role role;

	public Emp() {
		super();
	}

	public Emp(String e_code, String psw) {
		super();
		this.e_code = e_code;
		this.psw = psw;
	}

	public Emp(int id, String e_code, String psw, String username, int age, String sex, int ismarry, String phone,
			String email, String empId, Date createTime) {
		super();
		this.id = id;
		this.e_code = e_code;
		this.psw = psw;
		this.username = username;
		this.age = age;
		this.sex = sex;
		this.ismarry = ismarry;
		this.phone = phone;
		this.email = email;
		this.empId = empId;
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getE_code() {
		return e_code;
	}

	public void setE_code(String e_code) {
		this.e_code = e_code;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getIsmarry() {
		return ismarry;
	}

	public void setIsmarry(int ismarry) {
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


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Emp [id=" + id + ", code=" + e_code + ", psw=" + psw + ", username=" + username + ", age=" + age
				+ ", sex=" + sex + ", ismarry=" + ismarry + ", phone=" + phone + ", email=" + email + ", empId=" + empId
				+  ", createTime=" + createTime + "]";
	}

}
