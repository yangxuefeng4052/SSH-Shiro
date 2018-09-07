package com.yxf.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxf.oa.bean.ClockIn;
import com.yxf.oa.bean.ClockOut;
import com.yxf.oa.dao.ClockDao;
import com.yxf.oa.service.ClockService;

/**
*
* @author yxf
* @time 2018年8月31日下午2:29:13
*
*/
@Service("clockService")
public class ClockServiceImpl implements ClockService{
	
	@Resource
	ClockDao clockDao;
	
	@Override
	public boolean addClockIn(ClockIn in) {
		
		return clockDao.addClockIn(in);
	}

	@Override
	public boolean addClockOut(ClockOut out) {
		return clockDao.addClockOut(out);
	}
	
}
