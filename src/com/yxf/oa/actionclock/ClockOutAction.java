package com.yxf.oa.actionclock;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.ClockIn;
import com.yxf.oa.bean.ClockOut;
import com.yxf.oa.service.ClockService;

/**
*
* @author yxf
* @time 2018年8月31日下午2:25:42
*
*/
@Controller("clockOutAction")
public class ClockOutAction {
	
	private String empId;
	private String deptId;
	private String roleId;
	
	@Resource
	ClockService clockService;
	
	public String clockOut(){
		
		ClockOut out = new ClockOut();
		out.setEmpId(empId);
		out.setRoleId(Integer.parseInt(roleId));
		out.setDeptId(Integer.parseInt(deptId));
		
		boolean clockIn = clockService.addClockOut(out);
		
		try {
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			if (clockIn) {
				writer.println(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "none";
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	
}
