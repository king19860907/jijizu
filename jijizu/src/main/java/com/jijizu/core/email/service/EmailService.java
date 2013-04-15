package com.jijizu.core.email.service;

import java.util.Map;

public interface EmailService {
	
	/**   
	 *******************************************************************************
	 * @function : 获取email模板内容
	 * @param location   模板所在路径
	 * @param model		 模板替换内容
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	String getContent(String location,Map<String,String> model);
	
	/**   
	 *******************************************************************************
	 * @function : 发送邮件
	 * @param toEmail
	 * @param subject
	 * @param content
	 * @param html
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	void sendEmail(String toEmail,String subject,String content,boolean html);
	
	/**   
	 *******************************************************************************
	 * @function : 发送邮件（多人）
	 * @param toEmails
	 * @param subject
	 * @param content
	 * @param html
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	void sendEmail(String [] toEmails,String subject,String content,boolean html);

}
