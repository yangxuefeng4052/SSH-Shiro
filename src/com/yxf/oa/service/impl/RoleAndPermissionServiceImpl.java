package com.yxf.oa.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxf.oa.bean.Permission;
import com.yxf.oa.bean.Role;
import com.yxf.oa.dao.RoleAndPermissionDao;
import com.yxf.oa.service.RoleAndPermissionService;

/**
*
* @author yxf
* @time 2018年8月28日下午10:15:27
*
*/
@Service("roleAndPermissionService")
public class RoleAndPermissionServiceImpl implements RoleAndPermissionService {
	
	@Resource
	RoleAndPermissionDao roleAndPermissionDao; 
	
	
	@Override
	public List<Role> findAllRoles() {
		
		return roleAndPermissionDao.findAllRoles();
	}

	@Override
	public List<Permission> findAllPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addRole(Role role) {
		
		return roleAndPermissionDao.addRole(role);
	}

	@Override
	public Role findRoleById(int id) {
		
		return roleAndPermissionDao.findRoleById(id);
	}

	@Override
	public boolean deleteRole(Role role) {
		
		return roleAndPermissionDao.deleteRole(findRoleById(role.getId()));
	}

	@Override
	public boolean updateRole(Role role) {
		Role oldRole = findRoleById(role.getId());
		// 得到旧权限
		Set<Permission> pers = oldRole.getPermissions();
		//删除旧权限
		roleAndPermissionDao.deleteOldPers(pers);
		//添加新权限
		oldRole.setPermissions(role.getPermissions());
		//更新权限
		boolean updateRole = roleAndPermissionDao.updateRole(oldRole);
		
		
		return true;
	}

}
