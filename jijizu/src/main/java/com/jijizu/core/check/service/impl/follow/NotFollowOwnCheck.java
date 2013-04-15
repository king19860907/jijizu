package com.jijizu.core.check.service.impl.follow;

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
 * @type : NotFollowOwnCheck
 * @function : 未关注自己检查-如关注自己则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-31   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class NotFollowOwnCheck extends CheckCommonField 
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		Long followUserId = (Long)para.get(CheckParam.FOLLOWUSERID);
		if(sessionUserInfo != null && followUserId != null){
			if(sessionUserInfo.getUserId().longValue() == followUserId.longValue()){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

}
