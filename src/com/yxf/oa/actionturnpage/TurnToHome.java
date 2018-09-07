package com.yxf.oa.actionturnpage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.bean.Notice;
import com.yxf.oa.bean.Process;
import com.yxf.oa.service.EmployeeService;
import com.yxf.oa.service.ProcessService;

/**
*
* @author yxf
* @time 2018年8月29日下午5:01:49
*
*/
@Controller("turnToHome")
public class TurnToHome {
	
	@Resource
	ProcessService processService;
	@Resource
	EmployeeService employeeService;
	
	public String turnToHome(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		String creater = emp.getEmpId();
		List<Process> processes = processService.findProcessByCreater(creater);
		session.setAttribute("processes", processes);
		
		Notice notice = employeeService.findNoticeByDeptId(emp.getDept().getId());
		session.setAttribute("notice", notice);
		return "success";
	}
	
	
}
