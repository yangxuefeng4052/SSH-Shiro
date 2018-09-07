package com.yxf.oa.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.yxf.oa.bean.Permission;
import com.yxf.oa.bean.Role;
import com.yxf.oa.dao.RoleAndPermissionDao;

/**
*
* @author yxf
* @time 2018年8月28日下午10:12:04
*
*/
@Repository("roleAndPermissionDao")
public class RoleAndPermissionDaoImpl implements RoleAndPermissionDao {
	
	@Resource
	HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Role> findAllRoles() {
		List<Role> roles = new ArrayList<>();
		
		String hql = "from Role";
		roles = (List<Role>) hibernateTemplate.find(hql, null);
		
		return roles;
	}

	@Override
	public List<Permission> findAllPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addRole(Role role) {
		try {
			//hibernateTemplate.getSessionFactory().openSession().clear();
			hibernateTemplate.update(findRoleById(role.getId()));
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public Role findRoleById(int id) {
		String hql = "from Role where id=?";
		List<Role> roles = (List<Role>) hibernateTemplate.find(hql, id);
		Role role2 = roles.get(0);
		return role2;
	}

	@Override
	public boolean deleteRole(Role role) {
		try {
			hibernateTemplate.delete(role);
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateRole(Role role) {
		try {
			hibernateTemplate.update(role);
			//更新后删除旧权限
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void deletePermission(Role role) {
		
	}

	@Override
	public void deleteOldPers(Set<Permission> pers) {
		for (Permission permission : pers) {
			hibernateTemplate.delete(permission);
		}
		
	}

}
