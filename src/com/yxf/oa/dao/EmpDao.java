package com.yxf.oa.dao;

import com.yxf.oa.bean.Emp;

/**
 *
 * @author yxf
 * @time 2018年8月27日下午8:05:25
 *
 */
public interface EmpDao {
	/**
	 * 验证用户登录
	 * 
	 * @param emp
	 *            用户信息
	 * @return 是否登录成功
	 */
	Emp loginCheck(String username, String password);

	/**
	 * 获取登录的员工的所有信息
	 * 
	 * @param emp
	 *            员工对象
	 * @return
	 */
	Emp findEmpInfo(Emp emp);

	/**
	 * 验证输入的邮箱是否有与之对应的用户信息
	 * 
	 * @param emp
	 *            用户信息
	 * @return 验证成功返回持久化对象
	 */
	Emp checkEmail(Emp emp);

	/**
	 * 生成随机密码写入库表，执行前提：验证邮箱成功
	 * 
	 * @param emp
	 * @return
	 */
	Emp setNewPsw(Emp emp);
	
	/**
	 * 修改密码
	 * @param emp 用户信息
	 * @return
	 */
	Emp updatePsw(Emp emp);
	
	/**
	 * 根据id查询用户
	 * @param emp
	 * @return
	 */
	Emp findEmpById(int id);
	
	
}
