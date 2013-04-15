package com.jijizu.core.mail.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.core.user.dto.UserInfo;


public class MailShow implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2462743455723594010L;

	private Long mailShowId;
	
	private Long userId;
	
	private Long mailUserId;
	
	private Date lastMailDate;
	
	private Long lastDirection;
	
	private String lastMailContent;
	
	private Long newMailNum;
	
	private Long mailNum;
	
	private UserInfo userInfo;
	
	private UserInfo mailUserInfo;

	public Long getMailShowId() {
		return mailShowId;
	}

	public void setMailShowId(Long mailShowId) {
		this.mailShowId = mailShowId;
	}

	public Long getUserId() {
		if(userInfo != null){
			userId = userInfo.getUserId();
		}
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMailUserId() {
		if(mailUserInfo != null){
			mailUserId = mailUserInfo.getUserId();
		}
		return mailUserId;
	}

	public void setMailUserId(Long mailUserId) {
		this.mailUserId = mailUserId;
	}

	public Date getLastMailDate() {
		return lastMailDate;
	}

	public void setLastMailDate(Date lastMailDate) {
		this.lastMailDate = lastMailDate;
	}

	public Long getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(Long lastDirection) {
		this.lastDirection = lastDirection;
	}
	
	public String getLastMailContent() {
		return JijizuUtil.formatContent(lastMailContent);
	}

	public void setLastMailContent(String lastMailContent) {
		this.lastMailContent = lastMailContent;
	}

	public Long getNewMailNum() {
		return newMailNum;
	}

	public void setNewMailNum(Long newMailNum) {
		this.newMailNum = newMailNum;
	}

	public Long getMailNum() {
		return mailNum;
	}

	public void setMailNum(Long mailNum) {
		this.mailNum = mailNum;
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
		if(lastMailDate != null){
			long time = lastMailDate.getTime();
			long nowTimes = new Date().getTime();
			if((nowTimes - time)>24*60*60*1000){
				isOverOneDay = true;
			}
		}
		return isOverOneDay;

	}
	
}
