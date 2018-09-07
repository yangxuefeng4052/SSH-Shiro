package com.yxf.oa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Permission;
import com.yxf.oa.bean.Role;
import com.yxf.oa.service.RoleAndPermissionService;
import com.yxf.oa.utils.PermissionsUtil;

/**
*
* @author yxf
* @time 2018年8月29日上午10:58:05
*
*/
@Controller("addRoleAction")
public class AddRoleAction {
	
	private String roleName;
	private String permission;
	
	@Resource
	private RoleAndPermissionService roleAndPermissionService;
	
	public String addRole(){
		String[] permissions = permission.split("\\|");
		Set<Permission> pers = new HashSet<>();
		for (String string : permissions) {
			Permission per = new Permission();
			System.out.print(string);
			per.setName(string);
			switch (string) {
				case "角色管理":
					per.setUrl(PermissionsUtil.ROLE);
					break;
				case "人员管理":
					per.setUrl(PermissionsUtil.EMPLOYEE);
					break;
				case "我的考勤":
					per.setUrl(PermissionsUtil.LEGAL);
					break;
				case "我的工作计划":
					per.setUrl(PermissionsUtil.JOBPLAN);
					break;
				case "流程审批":
					per.setUrl(PermissionsUtil.PROCESS);
					break;
				case "项目申报":
					per.setUrl(PermissionsUtil.PROJECT);
					break;
				default:
					break;
				}
			pers.add(per);
		}
		Role role = new Role();
		role.setName(roleName);
		role.setPermissions(pers);
		
		boolean addRole = roleAndPermissionService.addRole(role);
		try {
			PrintWriter w = ServletActionContext.getResponse().getWriter();
			if (addRole) {
				w.println(1);
			}else {
				w.println(2);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return "none";
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	
	
	
	
}
