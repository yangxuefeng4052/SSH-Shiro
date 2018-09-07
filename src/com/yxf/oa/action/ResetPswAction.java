package com.yxf.oa.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.service.EmpService;
import com.yxf.oa.utils.EmailCheckUtils;
import com.yxf.oa.utils.RandomUtils;

/**
 *
 * @author yxf
 * @time 2018年8月27日下午9:47:38
 *
 */
@Controller("resetPswAction")
public class ResetPswAction {
	@Resource
	EmpService empService;

	private String email;

	public String checkEmail() {
		Emp emp = new Emp();
		emp.setEmail(email);
		emp = empService.checkEmail(emp);
		try {
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			String checkCode = RandomUtils.buildCheckCode();
			boolean send = EmailCheckUtils.sendEmail(emp.getEmail(), checkCode + ",有效时间为1分钟，请尽快处理");
			if (send) {
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("checkCode", checkCode);
				session.setAttribute("email", email);
				session.setAttribute("id", emp.getId());
				writer.println(1);
			} else {
				writer.println(2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "none";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
