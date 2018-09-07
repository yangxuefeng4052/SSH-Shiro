package com.yxf.oa.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
*
* @author yxf
* @time 2018年8月27日下午9:44:54
*
*/
public class EmailCheckUtils {
	/**
	 * 发送邮件方法
	 * 
	 * @param to
	 *            接收邮件的邮箱账号
	 * @param code
	 *            需要发送的验证码
	 */
	public static boolean sendEmail(String to, String code) {
		// 创建一个邮件对象
		HtmlEmail email = new HtmlEmail();
		// 指定邮箱服务器地址
		email.setHostName("smtp.163.com");
		// 指定邮箱服务器账号和客户端授权码
		email.setAuthentication("18518184052@163.com", "yxf494510211");
		// 统一设置邮件内容编码,避免内容乱码
		email.setCharset("UTF-8");

		// 设置发件人地址和名称
		try {
			email.setFrom("18518184052@163.com", "yxf");
			// 设置收件人地址
			email.addTo(to);
			// 设置标题
			email.setSubject("验证码");
			// 设置内容
			email.setMsg("验证码为：" + code);
			// 发送
			email.send();
			System.out.println("邮件发送成功");

		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
