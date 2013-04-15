package com.jijizu.core.email.dto;

import java.io.Serializable;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : EmailTemplate
 * @function : email模板类
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-3-18   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class EmailTemplate implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4554996913255698652L;
	
	/**
	 * 标题
	 */
	private String subject;
	
	/**
	 * 模板所在路径
	 */
	private String location;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
