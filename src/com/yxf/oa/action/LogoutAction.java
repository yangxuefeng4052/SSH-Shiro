package com.yxf.oa.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

/**
*
* @author yxf
* @time 2018年9月3日下午7:23:14
*
*/
@Controller("logoutAction")
public class LogoutAction {
	
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
	
}
