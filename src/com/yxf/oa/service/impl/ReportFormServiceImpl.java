package com.yxf.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxf.oa.dao.ReportFormDao;
import com.yxf.oa.service.ReportFormService;

/**
*
* @author yxf
* @time 2018年9月3日上午10:45:44
*
*/
@Service("reportFormService")
public class ReportFormServiceImpl implements ReportFormService{
	@Resource
	ReportFormDao reportformDao;
	
	@Override
	public List<Process> findAllProcesses(int page) {
		
		return reportformDao.findAllProcesses(page);
	}

	@Override
	public List<Process> findAllEndProcesses() {
		
		return reportformDao.findAllEndProcesses();
	}

	@Override
	public int findAllCount() {
		
		return reportformDao.findAllCount();
	}

}
