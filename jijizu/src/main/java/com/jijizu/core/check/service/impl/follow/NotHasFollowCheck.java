package com.jijizu.core.check.service.impl.follow;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.follow.dao.FollowDAO;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : NotHasFollowCheck
 * @function : 未关注某人检查-若已关注某人则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-3-19   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class NotHasFollowCheck extends CheckCommonField
	implements CheckService{
	
	private FollowDAO followDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		Object followUserId = para.get(CheckParam.USERID);
		if(sessionUserInfo != null && followUserId != null){
			if(followDAO.hasFollowUser(sessionUserInfo.getUserId(), Long.parseLong(followUserId.toString()))){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setFollowDAO(FollowDAO followDAO) {
		this.followDAO = followDAO;
	}
	
}
