package com.yxf.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxf.oa.bean.Process;
import com.yxf.oa.dao.ProcessDao;
import com.yxf.oa.service.ProcessService;

/**
*
* @author yxf
* @time 2018年8月31日下午7:17:24
*
*/
@Service("processService")
public class ProcessServiceImpl implements ProcessService{
	@Resource
	ProcessDao processDao;

	@Override
	public boolean addProcess(Process process) {
		
		return processDao.addProcess(process);
	}

	@Override
	public List<Process> findProcessByCreater(String creater) {
		
		return processDao.findProcessByCreater(creater);
	}

	@Override
	public Process findProcessInfoByPid(String processId) {
		
		return processDao.findProcessInfoByPid(processId);
	}

	@Override
	public List<Process> findProcessByNext(String next) {
		
		return processDao.findProcessByNext(next);
	}
	
	
	
}
