package com.yxf.oa.utils;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.struts2.ServletActionContext;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.bean.Permission;
import com.yxf.oa.dao.EmpDao;
import com.yxf.oa.service.EmpService;

/**
*
* @author yxf
* @time 2018年9月3日下午5:53:20
*
*/

public class UserRealm extends AuthorizingRealm{
	
	@Resource
	private EmpDao empDao;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();//得到用户名
		String password = new String((char[])token.getCredentials()); //得到密码 
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		
		Emp emp = empDao.loginCheck(username, password);
		
		session.setAttribute("emp", emp);
		
		session.setAttribute("permissions", emp.getRole().getPermissions());
		
		if (!emp.getE_code().equals(username)) {
			throw new UnknownAccountException(); //如果用户名错误 
		}
		
		if (!emp.getPsw().equals(password)) {
			throw new IncorrectCredentialsException(); //如果密码错误
		}
		
		//如果身份认证验证成功，返回一个AuthenticationInfo实现；  
        return new SimpleAuthenticationInfo(username, password, getName());  
	}
	
}
