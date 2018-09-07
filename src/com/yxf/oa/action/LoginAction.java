package com.yxf.oa.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.ws.message.StringHeader;
import com.yxf.oa.bean.Emp;
import com.yxf.oa.bean.Permission;
import com.yxf.oa.service.EmpService;
import com.yxf.oa.utils.UserRealm;

/**
*
* @author yxf
* @time 2018年8月27日下午8:48:11
*
*/
@Controller("loginAction")
public class LoginAction extends ActionSupport{
	@Resource
	private EmpService empService;
	
	private String code;
	private String psw;
	
	private String rememberMe;
	
	public String login(){
		
		return "login";
	}
	
	
	public String loginCheck(){
		String returnType = "";
		
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		try {
			
			DefaultSecurityManager securityManager = new DefaultSecurityManager();
			securityManager.setRealm(new UserRealm());
			SecurityUtils.setSecurityManager(securityManager);
			// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(code, psw);
			if (rememberMe != null && rememberMe.equals("rememberMe")) {
				token.setRememberMe(true);
			}
			// 4、登录，即身份验证
			subject.login(token);
			
			System.out.println("登录成功 ");
			returnType = "success";
			
		} catch (IncorrectCredentialsException e) {
			returnType = LOGIN;
			System.out.println("登录密码错误.");
			session.setAttribute("msg", "登录密码错误");
		} catch (ExcessiveAttemptsException e) {
			returnType = LOGIN;
			System.out.println("登录失败次数过多");
			session.setAttribute("msg", "登录失败次数过多");
		} catch (LockedAccountException e) {
			returnType = LOGIN;
			System.out.println("帐号已被锁定.");
			session.setAttribute("msg", "帐号已被锁定");
		} catch (DisabledAccountException e) {
			returnType = LOGIN;
			System.out.println("帐号已被禁用. ");
			session.setAttribute("msg", "帐号已被禁用");
		} catch (ExpiredCredentialsException e) {
			returnType = LOGIN;
			System.out.println("帐号已过期. ");
			session.setAttribute("msg", "帐号已过期");
		} catch (UnknownAccountException e) {
			returnType = LOGIN;
			System.out.println("帐号不存在.");
			session.setAttribute("msg", "帐号不存在");
		} catch (UnauthorizedException e) {
			returnType = LOGIN;
			System.out.println("您没有得到相应的授权！" + e.getMessage());
			session.setAttribute("msg", "您没有得到相应的授权！");
		} catch (Exception e) {
			returnType = LOGIN;
			e.printStackTrace();
		}
	
		return returnType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}


	public String getRememberMe() {
		return rememberMe;
	}


	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	
	
	
}
