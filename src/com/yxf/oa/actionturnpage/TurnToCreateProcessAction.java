package com.yxf.oa.actionturnpage;

import org.springframework.stereotype.Controller;

/**
*
* @author yxf
* @time 2018年8月31日下午5:21:27
*
*/
@Controller("turnToCreateProcessAction")
public class TurnToCreateProcessAction {
	
	public String type;
	
	public String turnToCreateProcess(){
		
		return "success";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
