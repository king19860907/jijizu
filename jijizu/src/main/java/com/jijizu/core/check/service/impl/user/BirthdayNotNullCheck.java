package com.jijizu.core.check.service.impl.user;

import java.util.Map;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : BirthdayNotNullCheck
 * @function : 生日不为空检查-为空则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-1-25   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class BirthdayNotNullCheck extends CheckCommonField
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		
		String birthday = (String)para.get(CheckParam.BIRTHDAY);
		if(!StringUtil.isNotNullOrEmpty(birthday)){
			return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
		}
		
		return null;
	}

}
