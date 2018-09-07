package com.yxf.oa.actionprocess;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Process;
import com.yxf.oa.service.ProcessService;

/**
*
* @author yxf
* @time 2018年9月1日上午10:37:50
*
*/
@Controller("findProcessInfoAction")
public class FindProcessInfoAction {
	
	private String processId;
	
	@Resource
	ProcessService processService;
	
	public String findProcessInfo(){
		
		Process process = processService.findProcessInfoByPid(processId);
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("processInfo", process);
		
		return "success";
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
}
