package com.jijizu.web.auth.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.auth.service.AuthService;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class AuthActionAjax extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6174560054010446820L;
	
	private AuthService authService;

	public String addPersonalAuth(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		Map<String, Object> para = getParams();
		
		JsonResult result = authService.addPersonAuth(sessionUserInfo, para);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String addEnterpriseAuth(){
	
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		Map<String, Object> para = getParams();
		
		JsonResult result = authService.addEnterpriseAuth(sessionUserInfo, para);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
}
