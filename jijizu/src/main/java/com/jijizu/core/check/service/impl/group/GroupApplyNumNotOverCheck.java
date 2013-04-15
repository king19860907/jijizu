package com.jijizu.core.check.service.impl.group;

import java.util.Map;

import com.jijizu.base.util.StringUtil;
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
 * @type : GroupApplyNumNotOverCheck
 * @function : 报名人数未超过限制检查-如超过则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-3-26   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class GroupApplyNumNotOverCheck extends CheckCommonField
	implements CheckService{
	
	private GroupDAO groupDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Object groupId = para.get(CheckParam.GROUPID);
		String childIds = (String)para.get(CheckParam.CHILDIDS);
		Object adultNum = para.get(CheckParam.ADULTNUM);
		if(groupId != null && adultNum != null) {
			GroupInfo group = groupDAO.getGroupInfoById(Long.parseLong(groupId.toString()), para);
			if(group.getApplyNum() != null && group.getApplyNum().longValue() > -1) {
				//已参加集集组的人数
				Long hasEnterNum = group.getEnterNum();
				//本次要报名参加的人数
				Long enterNumThisTime = Long.parseLong(adultNum.toString());
				if(StringUtil.isNotNullOrEmpty(childIds)) {
					enterNumThisTime += childIds.split(",").length;
				}
				//本次可以报名参加的人数
				Long canEnterNum = group.getApplyNum()-hasEnterNum;
				if(enterNumThisTime.longValue() > canEnterNum.longValue()) {
					return new JsonResult(OperateConstanct.OPERATE_GROUP_OVER_ENTER_LIMIT, errorMsg);
				}
			}
		}
		return null;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
}
