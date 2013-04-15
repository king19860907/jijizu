package com.jijizu.core.check.service.impl.mail;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : MailNotSendSelfCheck
 * @function : 不为给自己发送私信验证-如果为给自己发送私信则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-1-14   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class MailNotSendSelfCheck extends CheckCommonField
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		Long toUserId = (Long)para.get(CheckParam.USERID);
		if(sessionUserInfo != null && toUserId != null){
			if(sessionUserInfo.getUserId().longValue() == toUserId.longValue()){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

}
