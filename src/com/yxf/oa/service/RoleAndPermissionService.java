package com.yxf.oa.service;

import java.util.List;

import com.yxf.oa.bean.Permission;
import com.yxf.oa.bean.Role;

/**
*
* @author yxf
* @time 2018年8月28日下午10:14:41
*
*/
public interface RoleAndPermissionService {
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
	
	
	
}
