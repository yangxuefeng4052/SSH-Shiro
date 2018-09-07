package com.yxf.oa.actionreportform;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.service.ReportFormService;

/**
*
* @author yxf
* @time 2018年9月3日上午10:36:27
*
*/
@Controller("projectDeclare")
public class ProjectDeclareAction {
	@Resource
	ReportFormService reportFormService;
	
	private String page;
	
	public String reportForm(){
		if (page == null) {
			page = "1";
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		List<Process> allProcesses = reportFormService.findAllProcesses(Integer.parseInt(page));
		session.setAttribute("allProcesses", allProcesses);
		
		int count = reportFormService.findAllCount();
		session.setAttribute("count", count);
		
		return "success";
		
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	
	
	
}
