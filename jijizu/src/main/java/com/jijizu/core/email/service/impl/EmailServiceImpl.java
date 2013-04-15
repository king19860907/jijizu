package com.jijizu.core.email.service.impl;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.jijizu.core.email.service.EmailService;

public class EmailServiceImpl implements EmailService {
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	private JavaMailSender javaMailSender;
	
	private VelocityEngine velocityEngine;
	
	private String from;
	
	private String encoding;
	
	private String sendName;
	
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
	public String getContent(String location,Map<String,String> model){
		String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, location, encoding, model);
		return content;
	}
	
	/**
	 * 是否需要发送邮件开关
	 */
	private boolean isSender;
	
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
	public void sendEmail(String toEmail,String subject,String content,boolean html){
		if(!isSender){
			return;
		}
		try{
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(msg);
			message.setFrom(from, sendName);
			message.setSubject(subject);
			message.setTo(toEmail);
			message.setText(content,html);
			javaMailSender.send(msg);
		}catch(Exception e){
			log.error(e);
		}
	}
	
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
	public void sendEmail(String [] toEmails,String subject,String content,boolean html){
		if(!isSender){
			return;
		}
		try{
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(msg);
			message.setFrom(from, sendName);
			message.setSubject(subject);
			message.setTo(toEmails);
			message.setText(content,html);
			javaMailSender.send(msg);
		}catch(Exception e){
			log.error(e);
		}
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setSender(boolean isSender) {
		this.isSender = isSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

}
