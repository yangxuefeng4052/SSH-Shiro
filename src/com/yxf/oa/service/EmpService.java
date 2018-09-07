package com.yxf.oa.service;

import java.util.List;

import com.yxf.oa.bean.Emp;

/**
*
* @author yxf
* @time 2018年8月27日下午8:11:34
*
*/
public interface EmpService {
	/**
	 * 验证用户登录
	 * 
	 * @param emp
	 *            用户信息
	 * @return 是否登录成功
	 */
	Emp loginCheck(String username, String password) throws Exception;
	
	/**
	 * 验证输入的邮箱是否有与之对应的用户信息
	 * 
	 * @param emp
	 *            用户信息
	 * @return 验证成功返回持久化对象
	 */
	Emp checkEmail(Emp emp);
	
	
	/**
	 * 修改密码
	 * @param emp 用户信息
	 * @return
	 */
	Emp updatePsw(Emp emp);
	
	
	
}
