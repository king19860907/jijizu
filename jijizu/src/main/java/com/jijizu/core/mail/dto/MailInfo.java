package com.jijizu.core.mail.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.JijizuUtil;

public class MailInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6220747840179428215L;
	
	private Long mailId;
	
	private Long fromUserId;
	
	private Long toUserId;
	
	private Date sendDate;
	
	private String mailContent;

	public Long getMailId() {
		return mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getMailContent() {
		return JijizuUtil.formatContent(mailContent);
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

}
