package com.yxf.oa.service.impl;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.dao.EmpDao;
import com.yxf.oa.service.EmpService;
import com.yxf.oa.utils.EmailCheckUtils;
import com.yxf.oa.utils.ShiroUtils;

/**
 *
 * @author yxf
 * @time 2018年8月27日下午8:13:06
 *
 */
@Service("empService")
public class EmpServiceImpl implements EmpService {
	@Resource
	EmpDao empDao;

	@Override
	public Emp loginCheck(String username, String password) throws Exception {

		return null;
	}

	@Override
	public Emp checkEmail(Emp emp) {
		if (emp != null) {
			emp = empDao.checkEmail(emp);
			// 如果邮箱验证成功，调用方法生成随机密码设置到表中，并发送邮件
			// emp = empDao.setNewPsw(emp);
		}
		return emp;
	}

	@Override
	public Emp updatePsw(Emp emp) {
		if (emp.getPsw() != null) {
			emp = empDao.updatePsw(emp);
			return emp;
		}
		return null;
	}

}
