package com.yxf.oa.actionturnpage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Role;
import com.yxf.oa.service.RoleAndPermissionService;

/**
*
* @author yxf
* @time 2018年8月28日下午9:40:20
*
*/
@Controller("turnToRole")
public class TurnToRole {
	
	@Resource
	RoleAndPermissionService roleAndPermissionService;
	
	public String turnToRole(){
		List<Role> roles = roleAndPermissionService.findAllRoles();
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("roles", roles);
		return "success";
	}
	
	
}
