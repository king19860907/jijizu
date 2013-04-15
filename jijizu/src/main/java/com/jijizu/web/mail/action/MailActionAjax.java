package com.jijizu.web.mail.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.mail.service.MailService;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class MailActionAjax extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 871037399272471741L;
	
	private MailService mailService;
	
	private UserInfoService userInfoService;
	
	private String content;
	
	private Long userId;
	
	private String nickName;
	
	private Long mailShowId;
	
	private Long mailDetailId;
	
	public String updateMailContentAfterFollow(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(userId != null){
			UserInfo userInfo = userInfoService.getUserInfoById(userId);
			if(userInfo != null){
				content = "您已添加@"+userInfo.getAtName()+" 为好友";
				
				JsonResult result = mailService.updateMailContent(sessionUserInfo, mailShowId, mailDetailId, content);
				
				this.outObjectToJson(result);
			}
		}
		
		return NONE;
		
	}
	
	public String sendMail(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = mailService.sendMail(sessionUserInfo, nickName, content);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String deleteMailShow(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = mailService.deleteShowMail(sessionUserInfo, mailShowId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String deleteMailDetail(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = mailService.deleteDetailMail(sessionUserInfo, mailDetailId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setMailShowId(Long mailShowId) {
		this.mailShowId = mailShowId;
	}

	public void setMailDetailId(Long mailDetailId) {
		this.mailDetailId = mailDetailId;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

}
