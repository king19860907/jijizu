package com.jijizu.core.mail.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.core.user.dto.UserInfo;


public class MailDetail implements Serializable {

/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8323078305473497840L;

	private Long mailDetailId;
	
	private Long mailShowId;
	
	private Long mailId;
	
	private Date mailDate;
	
	private Long direction;
	
	private String mailContent;
	
	private Long deleteFlag;
	
	private UserInfo userInfo;
	
	private UserInfo mailUserInfo;

	public Long getMailDetailId() {
		return mailDetailId;
	}

	public void setMailDetailId(Long mailDetailId) {
		this.mailDetailId = mailDetailId;
	}

	public Long getMailShowId() {
		return mailShowId;
	}

	public void setMailShowId(Long mailShowId) {
		this.mailShowId = mailShowId;
	}

	public Long getMailId() {
		return mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	public Date getMailDate() {
		return mailDate;
	}

	public void setMailDate(Date mailDate) {
		this.mailDate = mailDate;
	}

	public Long getDirection() {
		return direction;
	}

	public void setDirection(Long direction) {
		this.direction = direction;
	}

	public String getMailContent() {
		return JijizuUtil.formatContent(mailContent);
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public Long getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Long deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserInfo getMailUserInfo() {
		return mailUserInfo;
	}

	public void setMailUserInfo(UserInfo mailUserInfo) {
		this.mailUserInfo = mailUserInfo;
	}
	
	public boolean isOverOneDay(){
		boolean isOverOneDay = false;
		if(mailDate != null){
			long time = mailDate.getTime();
			long nowTimes = new Date().getTime();
			if((nowTimes - time)>24*60*60*1000){
				isOverOneDay = true;
			}
		}
		return isOverOneDay;

	}
	
}
