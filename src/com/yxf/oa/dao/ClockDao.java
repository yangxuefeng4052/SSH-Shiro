package com.yxf.oa.dao;

import com.yxf.oa.bean.ClockIn;
import com.yxf.oa.bean.ClockOut;

/**
*
* @author yxf
* @time 2018年8月31日下午2:26:39
*
*/
public interface ClockDao {
	/**
	 * 上班打卡
	 * @param in
	 * @return
	 */
	boolean addClockIn(ClockIn in);
	
	/**
	 * 下班打卡
	 * @param out
	 * @return
	 */
	boolean addClockOut(ClockOut out);
	
}
