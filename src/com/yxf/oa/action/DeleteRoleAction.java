package com.yxf.oa.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Role;
import com.yxf.oa.service.RoleAndPermissionService;

/**
*
* @author yxf
* @time 2018年8月29日下午1:33:14
*
*/
@Controller("deleteRoleAction")
public class DeleteRoleAction {
	
	private String roleId;
	@Resource
	RoleAndPermissionService roleAndPermissionService;
	
	public String deleteRole(){
		
		Role role = new Role();
		role.setId(Integer.parseInt(roleId));
		boolean deleteRole = roleAndPermissionService.deleteRole(role);
		try {
			PrintWriter w = ServletActionContext.getResponse().getWriter();
			if (deleteRole) {
				w.println(1);
			}else{
				w.println(2);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return "none";
	}
	
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	
}
