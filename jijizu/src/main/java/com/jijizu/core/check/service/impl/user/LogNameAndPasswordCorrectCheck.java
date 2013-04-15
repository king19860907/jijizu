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
 * @type : LogNameAndPasswordCorrectCheck
 * @function : 用户名及密码检查，用户不存在则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-21   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class LogNameAndPasswordCorrectCheck extends CheckCommonField
	implements CheckService{
	
	private UserInfoDAO userInfoDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {

		String logName = (String)para.get(CheckParam.LOGNAME);
		String password = (String)para.get(CheckParam.PASSWORD);
		UserInfo userInfo = null;
			
		if(StringUtil.isNotNullOrEmpty(logName) && StringUtil.isNotNullOrEmpty(password)){
		    userInfo = userInfoDAO.getUserInfoByLogNameAndPassword(logName, password);
			if(userInfo == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		
		para.put(CheckParam.TMEP_USERINFO, userInfo);
		return null;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

}
