package com.yxf.oa.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
*
* @author yxf
* @time 2018年8月29日下午5:18:27
*
*/
public class Dept implements Serializable{
	private int id;
	private String name;
	private Set<Emp> emps = new HashSet<>();
	public Dept() {
		super();
	}
	public Dept(int id, String name, Set<Emp> emps) {
		super();
		this.id = id;
		this.name = name;
		this.emps = emps;
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
	public Set<Emp> getEmps() {
		return emps;
	}
	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}
	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name +  "]";
	}
	
	
	
}
