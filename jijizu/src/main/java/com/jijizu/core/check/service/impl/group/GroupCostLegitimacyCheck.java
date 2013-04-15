package com.jijizu.core.check.service.impl.group;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : GroupCostLegitimacyCheck
 * @function : 人均费用合法性检查-不合法则返回错误
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

public class GroupCostLegitimacyCheck extends CheckCommonField
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		String cost = (String)para.get(CheckParam.COST);
		String fee = (String)para.get(CheckParam.FEE);
		
		if(StringUtil.isNotNullOrEmpty(fee)){
			if("0".equals(fee)){
				para.put(CheckParam.COST, "0");
			}else{
				String regu = "^(-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$";
				Pattern p = Pattern.compile(regu);
				Matcher matcher = p.matcher(cost);
				if(!matcher.find()){
					return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
				}
			}
		}
		
		return null;
	}

}
