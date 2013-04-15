package com.jijizu.core.check.service.impl.group;

import java.util.Map;

import com.jijizu.base.util.ObjectUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.group.dao.GroupDAO;
import com.jijizu.core.group.dto.GroupInfo;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : GroupNotBeginCheck
 * @function : 集集组尚未开始验证-若已开始则返回错误
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

public class GroupNotBeginCheck extends CheckCommonField
	implements CheckService{
	
	private GroupDAO groupDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Object groupId = para.get(CheckParam.GROUPID);
		if(!ObjectUtil.isEmptyObject(groupId)){
			GroupInfo groupInfo = groupDAO.getGroupInfoById(Long.parseLong(groupId.toString()), para);
			if(!CommonConstant.GROUP_STATUS_APPLY_ING.equals(groupInfo.getGroupStatus())){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

}
