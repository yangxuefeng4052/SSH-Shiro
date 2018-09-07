package com.yxf.oa.dao;

import java.util.List;
import java.util.Set;

import com.yxf.oa.bean.Permission;
import com.yxf.oa.bean.Role;

/**
 *
 * @author yxf
 * @time 2018年8月28日下午10:04:08
 *
 */
public interface RoleAndPermissionDao {
	/**
	 * 查询所有岗位角色
	 * 
	 * @return 岗位角色集合
	 */
	List<Role> findAllRoles();
	
	/**
	 * 查询所有权限
	 * @return 权限集合
	 */
	List<Permission> findAllPermissions();
	
	/**
	 * 添加岗位角色
	 * @param role
	 * @return
	 */
	boolean addRole(Role role);
	/**
	 * 根据id查询role
	 * @param role
	 * @return
	 */
	Role findRoleById(int id);
	
	/**
	 * 删除岗位角色
	 * @param role
	 * @return
	 */
	boolean deleteRole(Role role);
	
	/**
	 * 修改岗位角色对应的权限
	 * @param role
	 * @return
	 */
	boolean updateRole(Role role);
	
	/**
	 * 删除原来具有的权限
	 * @param role
	 */
	void deletePermission(Role role);
	
	/**
	 * 删除旧权限
	 * @param pers
	 */
	void deleteOldPers(Set<Permission> pers);
	
}
