package com.jijizu.core.check.service.impl;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 也买网   
 * @type : ParamsNotNullCheck
 * @function : 参数不为空检查-有为空的参数则返回错误
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

public class ParamsNotNullCheck extends CheckCommonField
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		
		for(String key : para.keySet()){
			if(!CheckParam.SESSIONUSERINFO.equals(key)){ //排除用户session为空检查
				Object result = para.get(key);
				if(para.get(key+CheckParam.IS_NEED_NULL_CHECK)==null){ //排除不需要为空检查的元素
					if(result == null || "".equals(result.toString().trim())){
						return new JsonResult(OperateConstanct.OPERATE_ERROR,errorMsg);
					}
				}
			}
		}
		
		return null;
	}

}

