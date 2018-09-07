package com.yxf.oa.actionprocess;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Process;
import com.yxf.oa.bean.Task;
import com.yxf.oa.service.ProcessService;

/**
 *
 * @author yxf
 * @time 2018年9月1日下午4:35:47
 *
 */
@Controller("approveAction")
public class ApproveAction {

	private String processId;
	private String approve;
	private String remark;

	@Resource
	ProcessService processService;

	public String approve() {
		Process process = processService.findProcessInfoByPid(processId);
		System.out.println(processId + ":" + process.getProcessId());
		
		process.setProcessId(processId);
		
		Task task = new Task();
		task.setApprover(approve);
		task.setRemark(remark);

		Set<Task> tasks = process.getTasks();
		tasks.add(task);
		process.setTasks(tasks);
		if (approve.equals("同意")) {
			// 同意则状态为2-完结
			process.setState(2);
		} else {
			// 同意则状态为3-终止
			process.setState(3);
		}

		// 下一节点执行人置空
		String next = process.getNext();
		process.setNext("");
		process.setPrevious(next);
		
		task.setProcess(process);
		
		boolean addProcess = processService.addProcess(process);
		try {
			PrintWriter w = ServletActionContext.getResponse().getWriter();
			if (addProcess) {
				w.println(1);
			} else {
				w.println(2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "none";
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
