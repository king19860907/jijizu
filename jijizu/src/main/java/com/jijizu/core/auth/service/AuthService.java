package com.jijizu.core.auth.service;

import java.util.Map;

import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dto.UserInfo;

public interface AuthService {

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
	JsonResult addPersonAuth(UserInfo sessionUserInfo,Map<String,Object> para);
	
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
	JsonResult addEnterpriseAuth(UserInfo sessionUserInfo,Map<String,Object> para);
	
}
