package com.yxf.oa.actionemps;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Notice;
import com.yxf.oa.service.EmployeeService;

/**
 *
 * @author yxf
 * @time 2018年9月4日上午9:00:01
 *
 */
@Controller("noticeActoin")
public class NoticeAction {

	private String title;
	private String content;
	private String deptId;

	@Resource
	EmployeeService employeeService;

	public String notice() {

		Notice notice = new Notice();
		notice.setContent(content);
		notice.setTitle(title);
		notice.setDeptId(Integer.parseInt(deptId));

		boolean addNotice = employeeService.addNotice(notice);
		try {
			if (addNotice) {
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("notice", notice);
				ServletActionContext.getResponse().getWriter().println(1);
			}else {
				ServletActionContext.getResponse().getWriter().println(2);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "none";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}
