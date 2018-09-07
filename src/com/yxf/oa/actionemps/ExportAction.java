package com.yxf.oa.actionemps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.yxf.oa.bean.Emp;
import com.yxf.oa.service.EmployeeService;
import com.yxf.oa.utils.ExportUtil;

/**
*
* @author yxf
* @time 2018年9月4日上午10:36:27
*
*/
@Controller("exportAction")
public class ExportAction {
	
	@Resource
	EmployeeService employeeService;
	
	public String export(){
		
		List<Emp> emps = employeeService.findAllEmps();
		ExportUtil.export(emps);
		
		return "none";
	}
	
}
