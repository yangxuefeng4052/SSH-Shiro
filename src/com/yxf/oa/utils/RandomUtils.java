package com.yxf.oa.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
*
* @author yxf
* @time 2018年8月27日下午10:17:22
*
*/
public class RandomUtils {
	
	
	public static String buildEmpId(int size){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		
		int count = 10000;
		count += size + 1;
		String num = String.valueOf(count);
		
		num = num.substring(1);
		
		
		return year + num;
	}
	
	public static String buildProcessId(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String processID = sdf.format(new Date());
		return processID;
	}
	
	public static String buildCheckCode(){
		Random random = new Random();
		String checkCode = "";
		for (int i = 0; i < 4; i++) {
			int nextInt = random.nextInt(10);
			checkCode += nextInt;
		}
		return checkCode;
	}
	
}
