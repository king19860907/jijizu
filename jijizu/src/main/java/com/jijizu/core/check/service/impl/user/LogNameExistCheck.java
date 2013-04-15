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
 * @project : 集集组   
 * @type : LogNameExistCheck
 * @function : 用户名存在检查-不存在则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-4-12   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class LogNameExistCheck extends CheckCommonField
	implements CheckService{
	
	private UserInfoDAO userInfoDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		String logName = (String)para.get(CheckParam.LOGNAME);
		UserInfo userInfo = null;
		
		if(StringUtil.isNotNullOrEmpty(logName)){
			userInfo = userInfoDAO.getUserInfoByLogName(logName);
			if(userInfo == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

}
