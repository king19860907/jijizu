package com.jijizu.core.check.service.impl.group;

import java.util.Date;
import java.util.Map;

import com.jijizu.base.util.DateUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 集集组
 * @type : ApplyEndDateAfterNowDateCheck
 * @function : 报名截止时间晚于点钱时间检查-反之则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-3-22   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class ApplyEndDateAfterNowDateCheck extends CheckCommonField
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		
		String applyEndDay = (String)para.get(CheckParam.APPLYENDDAY);
		String applyEndMinute = (String)para.get(CheckParam.APPLYENDMINUTE);
		String applyEndHour = (String)para.get(CheckParam.APPLYENDHOUR);
		Date applyEndDate = DateUtil.parseDate(applyEndDay, applyEndHour, applyEndMinute);
		
		if(applyEndDate != null && 
				applyEndDate.getTime() < new Date().getTime()) {
			return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
		}
		
		return null;
	}
	

}
