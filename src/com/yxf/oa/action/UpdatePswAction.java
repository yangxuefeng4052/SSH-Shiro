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
 * @time 2018年8月28日下午4:23:12
 *
 */
@Controller("updatePswAction")
public class UpdatePswAction {

	private int id;
	private String psw;
	@Resource
	private EmpService empService;
	
	public String updatePsw(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		emp.setPsw(psw);
		PrintWriter writer;
		try {
			empService.updatePsw(emp);
			writer = ServletActionContext.getResponse().getWriter();
			//修改成功返回1
			session.setAttribute("emp", emp);
			writer.println(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "none";
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

}
