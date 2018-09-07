package com.yxf.oa.actionprocess;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.bean.Process;
import com.yxf.oa.service.ProcessService;

/**
*
* @author yxf
* @time 2018年9月1日下午3:45:09
*
*/
@Controller("processApproveAction")
public class ProcessApproveAction {
	@Resource
	private ProcessService processService;
	
	public String processApprove(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		String empId = emp.getEmpId();
		
		List<Process> processes = processService.findProcessByNext(empId);
		session.setAttribute("nextProcesses", processes);
		
		return "success";
	}
	
}
