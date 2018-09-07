package com.yxf.oa.bean;

import java.io.Serializable;

/**
*
* @author yxf
* @time 2018年8月31日下午4:02:29
*
*/
public class Task implements Serializable{
	private int id;
	private String approver;
	private String remark;
	
	private Process process;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}
	
	
}
