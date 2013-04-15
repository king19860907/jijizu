package com.jijizu.core.check.service.impl.user;

import java.util.Map;

import com.jijizu.base.util.CommonValidate;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 也买网   
 * @type : EmailCheck
 * @function : email合法性验证，不合法则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-20   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class EmailCheck extends CheckCommonField
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		
		String email = (String)para.get(CheckParam.LOGNAME);
		
		if(!StringUtil.isNotNullOrEmpty(email)){
			email = (String)para.get(CheckParam.EMAIL);
		}
		
		if(StringUtil.isNotNullOrEmpty(email)){
			if(!CommonValidate.isEmail(email)){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		
		return null;
	}

}
