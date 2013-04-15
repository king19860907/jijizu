package com.jijizu.web.auth.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class AuthAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8907744410202712391L;
	
	private UserInfoService userInfoService;
	
	private UserInfo userInfo;
	
	public String index(){
		
		
		
		return SUCCESS;
	}
	
	public String authPersonal(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		if(!StringUtil.isNotNullOrEmpty(userInfo.getHeadImgDefault())){
			return "no-head";
		}
		
		return SUCCESS;
	}
	
	public String authEnterprise(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		if(!StringUtil.isNotNullOrEmpty(userInfo.getHeadImgDefault())){
			return "no-head";
		}
		
		return SUCCESS;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
}
