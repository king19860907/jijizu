package com.jijizu.core.auth.service.impl;

import java.util.Map;

import com.jijizu.core.auth.dao.AuthDAO;
import com.jijizu.core.auth.service.AuthService;
import com.jijizu.core.check.CheckContext;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dto.UserInfo;

public class AuthServiceImpl implements AuthService {
	
	/**
	 * 个人认证申请验证
	 */
	private CheckContext addPersonAuthCheckContext;
	
	/**
	 * 企业认证申请验证
	 */
	private CheckContext addEnterpriseAuthCheckContext;
	
	private AuthDAO authDAO;
	
	/**   
	 *******************************************************************************
	 * @function : 个人认证申请
	 * @param sessionUserInfo
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-29   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult addPersonAuth(UserInfo sessionUserInfo,Map<String,Object> para){
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		if(sessionUserInfo != null){
			para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		}
		JsonResult result = addPersonAuthCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		authDAO.saveAuthPersonal(para);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 企业认证申请
	 * @param sessionUserInfo
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult addEnterpriseAuth(UserInfo sessionUserInfo,Map<String,Object> para){
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		if(sessionUserInfo != null){
			para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		}
		para.put(CheckParam.MYFILE+CheckParam.IS_NEED_NULL_CHECK, false);
		JsonResult result = addEnterpriseAuthCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		authDAO.saveAuthEnterprise(para);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}

	public void setAddPersonAuthCheckContext(CheckContext addPersonAuthCheckContext) {
		this.addPersonAuthCheckContext = addPersonAuthCheckContext;
	}

	public void setAuthDAO(AuthDAO authDAO) {
		this.authDAO = authDAO;
	}

	public void setAddEnterpriseAuthCheckContext(
			CheckContext addEnterpriseAuthCheckContext) {
		this.addEnterpriseAuthCheckContext = addEnterpriseAuthCheckContext;
	}
	
}
