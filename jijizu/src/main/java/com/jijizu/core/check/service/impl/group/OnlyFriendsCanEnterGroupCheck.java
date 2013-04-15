package com.jijizu.core.check.service.impl.group;

import java.util.Map;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.group.dao.GroupDAO;
import com.jijizu.core.group.dto.GroupInfo;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 集集组 
 * @type : OnlyFriendsCanEnterGroupCheck
 * @function : 只有好友能参加集集集检查-不为好友则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-3-21   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class OnlyFriendsCanEnterGroupCheck extends CheckCommonField
	implements CheckService{
	
	private GroupDAO groupDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		Object groupId = para.get(CheckParam.GROUPID);
		
		if(sessionUserInfo != null && groupId != null) {
			GroupInfo group = groupDAO.getGroupInfoById(null, para);
			if(SysDataDicConstant.GROUP_ENTER_TYPE_FRIENDS.equals(group.getEnterType())){
				if(!JijizuUtil.isFollowEachOther(sessionUserInfo.getUserId(), group.getUserId())) {
					return new JsonResult(OperateConstanct.OPERATE_ERROR,errorMsg);
				}
			}
		}
		
		return null;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

}
