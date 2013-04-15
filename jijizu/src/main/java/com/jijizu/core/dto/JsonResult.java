package com.jijizu.core.dto;

import java.io.Serializable;

public class JsonResult implements Serializable {

	private static final long serialVersionUID = 4041625283963532102L;
	
	private String flag;
	private String msg;
	private Object data;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public JsonResult(String flag, String msg, Object data){
		this.flag = flag;
		this.msg = msg;
		this.data = data;
	}
	
	public JsonResult(String flag, String msg){
		this.flag = flag;
		this.msg = msg;
	}

}
