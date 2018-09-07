package com.yxf.oa.bean;

import java.io.Serializable;
import java.util.Date;

/**
*
* @author yxf
* @time 2018年9月3日下午9:28:24
*
*/
public class Notice implements Serializable{
	private int id;
	private String content;
	private String title;
	private Date createTime;
	private int deptId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
}
