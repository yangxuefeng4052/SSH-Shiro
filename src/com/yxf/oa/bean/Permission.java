package com.yxf.oa.bean;

import java.io.Serializable;

/**
 *
 * @author yxf
 * @time 2018年8月28日下午7:06:11
 *
 */
public class Permission implements Serializable {
	private int id;
	private String name;
	private String url;
	private Role role;

	public Permission() {
		super();
	}
//
	public Permission(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", url=" + url + "]";
	}
	

}
