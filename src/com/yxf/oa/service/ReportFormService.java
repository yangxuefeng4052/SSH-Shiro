package com.yxf.oa.service;

import java.util.List;

/**
*
* @author yxf
* @time 2018年9月3日上午10:45:22
*
*/
public interface ReportFormService {
	/**
	 * 获取所有流程信息
	 * @return
	 */
	List<Process> findAllProcesses(int page);
	/**
	 * 获取所有已完结流程信息
	 * @return
	 */
	List<Process> findAllEndProcesses();
	
	//获取总页数
		int findAllCount();
	
}
