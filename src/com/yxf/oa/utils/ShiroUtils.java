package com.yxf.oa.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import com.yxf.oa.bean.Emp;

/**
* shiro工具类
* @author yxf
* @time 2018年8月27日下午8:15:10
*
*/
public class ShiroUtils {
	//SecurityManager对象
	private static SecurityManager sm;
	//初始化sm
	private static void iniSecurityManager(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		sm = factory.getInstance();
		SecurityUtils.setSecurityManager(sm);
	}
	
	public static AuthenticationToken getToken(Emp emp){
		iniSecurityManager();
		System.out.println(emp.getPsw());
		UsernamePasswordToken token = new UsernamePasswordToken(emp.getE_code(), emp.getPsw().toCharArray());
		
		return token;
		
	}
	
	
}
