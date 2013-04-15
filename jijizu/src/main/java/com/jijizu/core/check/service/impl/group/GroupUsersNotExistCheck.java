package com.jijizu.core.check.service.impl.group;

import java.util.Map;

import com.jijizu.base.util.ObjectUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.group.dao.GroupDAO;
import com.jijizu.core.group.dto.GroupUsers;

/**   
 *******************************************************************************
 * @project : 集集组
 * @type : GroupUsersNotExistCheck
 * @function : 用户未参加过集集组检查-如果以参加过则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-2-1   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class GroupUsersNotExistCheck extends CheckCommonField
	implements CheckService{
	
	private GroupDAO groupDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Object groupId = para.get(CheckParam.GROUPID);
		Object userId = para.get(CheckParam.USERID);
		if(!ObjectUtil.isEmptyObject(groupId) && !ObjectUtil.isEmptyObject(userId)){
			GroupUsers groupUser = groupDAO.getGroupUsersByGroupIdAndUserId(Long.parseLong(userId.toString()), Long.parseLong(groupId.toString()));
			if(groupUser != null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

}
