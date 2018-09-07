package com.yxf.oa.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.service.EmpService;

/**
*
* @author yxf
* @time 2018年9月4日下午5:14:10
*
*/
@Controller("checkCodeAction")
public class CheckCodeAction {
	
	@Resource
	EmpService empService;
	
	private String checkCode;
	private String email;
	
	public String checkCode(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		String perMail = (String) session.getAttribute("email");
		String perCode = (String) session.getAttribute("checkCode");
		int id = (int) session.getAttribute("id");
		
		Emp emp = new Emp();
		emp.setEmail(email);
		emp.setId(id);
		emp.setPsw("abcd@1234");
		try {
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			if (perCode.equals(checkCode) && email.equals(perMail)) {
				//修改密码
				empService.updatePsw(emp);
				writer.println(1);
			}else {
				writer.println(2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "none";
		
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
