package com.yxf.oa.actionprocess;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Process;
import com.yxf.oa.bean.Task;
import com.yxf.oa.service.ProcessService;
import com.yxf.oa.utils.RandomUtils;

/**
*
* @author yxf
* @time 2018年8月31日下午7:04:05
*
*/
@Controller("createProcess")
public class CreateProcessAction {
	
	private String processName;//流程名称
	private String clockDate;//补签日期
	private String clock;//补签时间
	private String content;//内容
	private String next;//下一节点
	private String deptId;//部门
	private String roleId;//岗位角色
	
	private String beginDate;
	private String endDate;
	
	
	@Resource
	ProcessService processService;
	
	//private String processId;//流程编号
	//private String previous;//上一节点
	//private String state; //状态  1-进行中 2-完结 3-终止
	private String creater;//发起人
	
	
	public String createProcess(){
		//生成流程实例
		Process process = new Process();
		//新发起的流程，创建流程编号
		process.setProcessId(RandomUtils.buildProcessId());
		process.setProcessName(processName);
		System.out.println(processName);
		//新发起的流程，状态为 1-进行中
		process.setState(1);
		//上一节点执行人
		process.setPrevious(creater);
		//下一节点执行人
		
		System.out.println("-------------" + next);
		
		System.out.println(beginDate + ":" + endDate);
		
		process.setNext(next);
		process.setDeptId(Integer.parseInt(deptId));
		process.setRoleId(Integer.parseInt(roleId));
		process.setCreater(creater);
		process.setContent(content);
		
		//字符串转日期
		try {
			if (beginDate != null && endDate != null) {
				process.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate));
				process.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		//生成任务实例
		Task task = new Task();
		task.setProcess(process);
		
		process.getTasks().add(task);
		
		System.out.println(clockDate + " " + clock);
		
		boolean addProcess = processService.addProcess(process);
		try {
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			if (addProcess) {
				
				writer.println(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "none";
	}


	public String getProcessName() {
		return processName;
	}


	public void setProcessName(String processName) {
		this.processName = processName;
	}


	public String getClockDate() {
		return clockDate;
	}


	public void setClockDate(String clockDate) {
		this.clockDate = clockDate;
	}


	public String getClock() {
		return clock;
	}


	public void setClock(String clock) {
		this.clock = clock;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getNext() {
		return next;
	}


	public void setNext(String next) {
		this.next = next;
	}


	public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public String getCreater() {
		return creater;
	}


	public void setCreater(String creater) {
		this.creater = creater;
	}


	public String getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	
}
