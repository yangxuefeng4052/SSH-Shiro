package com.yxf.oa.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Role;
import com.yxf.oa.service.RoleAndPermissionService;

/**
*
* @author yxf
* @time 2018年8月29日下午1:59:01
*
*/
@Controller
public class FindRoleInfoAction {
	@Resource
	RoleAndPermissionService roleAndPermissionService;
	
	public String findRoleInfo(){
		String roleId = ServletActionContext.getRequest().getParameter("roleId");
		Role role = roleAndPermissionService.findRoleById(Integer.parseInt(roleId));
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("roleInfo", role);
		
		return "success";
		
	}
	
	
}
