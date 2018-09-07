package com.yxf.oa.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.yxf.oa.bean.ClockIn;
import com.yxf.oa.bean.ClockOut;
import com.yxf.oa.dao.ClockDao;

/**
*
* @author yxf
* @time 2018年8月31日下午2:27:30
*
*/
@Repository("clockDao")
public class ClockInImpl implements ClockDao{

	@Resource
	HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addClockIn(ClockIn in) {
		try {
			hibernateTemplate.save(in);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addClockOut(ClockOut out) {
		try {
			hibernateTemplate.save(out);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
