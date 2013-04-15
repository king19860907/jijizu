package com.jijizu.core.check.service.impl.user;

import java.util.Map;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

public class PasswordLegitimacyCheck extends CheckCommonField 
	implements CheckService{
	
	/**
	 * 名字最短长度
	 */
	private int passwordMinLength;
	
	/**
	 * 名字最长长度
	 */
	private int passwordMaxLength;

	@Override
	public JsonResult check(Map<String, Object> para) {

		String password = (String)para.get(CheckParam.PASSWORD);
		if(StringUtil.isNotNullOrEmpty(password)){
			if(password.length()<passwordMinLength || password.length() > passwordMaxLength){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setPasswordMinLength(int passwordMinLength) {
		this.passwordMinLength = passwordMinLength;
	}

	public void setPasswordMaxLength(int passwordMaxLength) {
		this.passwordMaxLength = passwordMaxLength;
	}
	
}
