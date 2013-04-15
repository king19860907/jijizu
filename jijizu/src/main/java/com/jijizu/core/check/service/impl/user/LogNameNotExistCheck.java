package com.jijizu.core.check.service.impl.user;

import java.util.Map;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 也买网   
 * @type : LogNameNotRepeatCheck
 * @function : 登录名不存在检查-如果存在则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-20   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class LogNameNotExistCheck extends CheckCommonField
	implements CheckService{
	
	private UserInfoDAO userInfoDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		
		String logName = (String)para.get(CheckParam.LOGNAME);
		UserInfo userInfo = null;
		
		if(StringUtil.isNotNullOrEmpty(logName)){
			userInfo = userInfoDAO.getUserInfoByLogName(logName);
			if(userInfo != null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

}
