package com.yxf.oa.service;

import java.util.List;

import com.yxf.oa.bean.Process;

/**
*
* @author yxf
* @time 2018年8月31日下午7:17:09
*
*/
public interface ProcessService {
	
	/**
	 * 发起流程
	 * @param process 
	 * @return
	 */
	boolean addProcess(Process process);
	
	/**
	 * 根据发起人查询流程信息
	 * @param creater
	 * @return
	 */
	List<Process> findProcessByCreater(String creater);
	
	/**
	 * 根据流程编号查询流程信息
	 * @param processId
	 * @return
	 */
	Process findProcessInfoByPid(String processId);
	
	/**
	 * 根据下一节点执行人编号查询流程信息
	 * @param next
	 * @return
	 */
	List<Process> findProcessByNext(String next);
	
}
