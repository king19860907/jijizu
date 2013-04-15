package com.jijizu.core.check.service.impl.group;

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
 * @type : GroupApplyNumLegitimacyCheck
 * @function : 参加人数合法性检查-不合法则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-3-25   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class GroupApplyNumLegitimacyCheck extends CheckCommonField
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		String people = (String)para.get(CheckParam.PEOPLE);
		String applyNum = (String)para.get(CheckParam.APPLYNUM);
		
		if(StringUtil.isNotNullOrEmpty(people)) {
			if("0".equals(people)) {
				para.put(CheckParam.APPLYNUM, "-1");
			}else {
				if(!CommonValidate.isSignlessInteger(applyNum)) {
					return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
				}
			}
		}
		
		return null;
	}

}
