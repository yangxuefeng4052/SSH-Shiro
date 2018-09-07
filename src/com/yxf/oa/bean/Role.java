package com.yxf.oa.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author yxf
 * @time 2018年8月27日下午5:34:31
 *
 */
public class Role implements Serializable {
	private int id;
	private String name;
	Set<Permission> permissions = new HashSet<>();
	Set<Emp> emps = new HashSet<>();
	
	public Role() {
		super();
	}

	public Role(int id, String name) {
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

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Emp> getEmps() {
		return emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", emps=" + emps + "]";
	}

	

	
	

}
