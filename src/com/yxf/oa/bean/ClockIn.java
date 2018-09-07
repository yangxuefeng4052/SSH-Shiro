package com.yxf.oa.bean;

import java.io.Serializable;
import java.util.Date;

/**
*
* @author yxf
* @time 2018年8月31日下午1:48:49
*
*/
public class ClockIn implements Serializable{
	private int id;
	private Date time;
	private String empId;
	private int deptId;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
}
