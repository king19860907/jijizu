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
 * @type : NameNotEqualOfficialNameCheck
 * @function : 姓名不为集集组官方名称检查-如果为官方名称则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-2-28   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class NameNotEqualOfficialNameCheck extends CheckCommonField
	implements CheckService{
	
	/**
	 * 官方名称
	 */
	private String officalName;

	@Override
	public JsonResult check(Map<String, Object> para) {
		
		String name = (String)para.get(CheckParam.NAME);
		
		if(StringUtil.isNotNullOrEmpty(name)){
			if(name.trim().equals(officalName)){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		
		return null;
	}

	public void setOfficalName(String officalName) {
		this.officalName = officalName;
	}

}
