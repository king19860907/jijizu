package com.jijizu.core.check.service.impl.group;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.group.dao.GroupDAO;
import com.jijizu.core.group.dto.GroupInfo;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : GroupBelongToUserCheck
 * @function : 集集组属于某个用户检查-不属于则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-2-5   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class GroupBelongToUserCheck extends CheckCommonField
	implements CheckService{
	
	private GroupDAO groupDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		Object groupId = para.get(CheckParam.GROUPID);
		if(sessionUserInfo != null && groupId != null){
			GroupInfo groupInfo = groupDAO.getGroupInfoById(Long.parseLong(groupId.toString()), para);
			if(groupInfo.getUserId().longValue() != sessionUserInfo.getUserId().longValue()){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

}
