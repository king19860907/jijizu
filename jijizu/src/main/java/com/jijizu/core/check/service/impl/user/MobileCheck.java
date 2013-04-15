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
 * @project : 集集组   
 * @type : MobileCheck
 * @function : 手机号码验证-不为手机号码则返回错误
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

public class MobileCheck extends CheckCommonField
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		
		String mobile = (String)para.get(CheckParam.MOBILE);
		
		if(StringUtil.isNotNullOrEmpty(mobile)){
			if(!CommonValidate.isMobile(mobile)){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		
		return null;
	}

}
