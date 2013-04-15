package com.jijizu.core.check.service.impl.status;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.status.dao.StatusDAO;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : StatusCanDeleteCheck
 * @function : 微薄是否属于某用户检查-不属于则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-30   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class StatusBelongToUserCheck extends CheckCommonField
	implements CheckService{
	
	private StatusDAO statusDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		Long statusId = (Long)para.get(CheckParam.STATUSID);
		
		if(sessionUserInfo != null && statusId != null){
			if(statusDAO.countStatusByStatusIdAndUserId(statusId, sessionUserInfo.getUserId()) <= 0){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		
		return null;
	}

	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}
	
}
