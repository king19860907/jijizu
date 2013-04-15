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
 * @type : GroupStartDateAfterNowDateCheck
 * @function : 开始时间在当前时间之后检查-反之则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-1-29   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class GroupStartDateAfterNowDateCheck extends CheckCommonField
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		String startDay = (String)para.get(CheckParam.STARTDAY);
		String startMinute = (String)para.get(CheckParam.STARTMINUTE);
		String startHour = (String)para.get(CheckParam.STARTHOUR);
		
		Date startDate = DateUtil.parseDate(startDay, startHour, startMinute);
		if(startDate.getTime() < new Date().getTime()){
			return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
		}
		
		return null;
	}

}
