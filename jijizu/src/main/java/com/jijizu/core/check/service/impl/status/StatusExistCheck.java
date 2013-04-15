package com.jijizu.core.check.service.impl.status;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.status.dao.StatusDAO;
import com.jijizu.core.status.dto.StatusInfo;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : StatusExistCheck
 * @function : 微博存在检查-不存在则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-28   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class StatusExistCheck extends CheckCommonField
	implements CheckService{
	
	private StatusDAO statusDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Long statusId = (Long)para.get(CheckParam.STATUSID);
		StatusInfo status = null;
		if(statusId != null){
			status = statusDAO.getStatusById(statusId);
			if(status == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		
		para.put(CheckParam.TEMP_STATUSINFO, status);
		return null;
	}

	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

}
