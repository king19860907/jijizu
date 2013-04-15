package com.jijizu.core.check.service.impl.group;

import java.util.Map;

import com.jijizu.base.util.ObjectUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.group.dao.GroupDAO;
import com.jijizu.core.group.dto.GroupInfo;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : NotGroupCreatorCheck
 * @function : 不为集集组创建者验证-如果为集集组创建这则返回错误
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

public class NotGroupCreatorCheck extends CheckCommonField
	implements CheckService{

	private GroupDAO groupDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Object groupId = para.get(CheckParam.GROUPID);
		Object userId = para.get(CheckParam.USERID);
		if(!ObjectUtil.isEmptyObject(groupId)&&!ObjectUtil.isEmptyObject(userId)){
			GroupInfo groupInfo = groupDAO.getGroupInfoById(Long.parseLong(groupId.toString()), para);
			if(groupInfo.getUserId().longValue()==Long.parseLong(userId.toString())){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
}
