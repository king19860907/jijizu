package com.jijizu.core.check.service.impl.status;

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
 * @type : StatusLessThanLengthCheck
 * @function : 微博内容长度小于限定长度检查，大于限定长度则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-24   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class StatusContentLessThanLengthCheck extends CheckCommonField
	implements CheckService{
	
	private int maxLength;

	@Override
	public JsonResult check(Map<String, Object> para) {
		String content = (String)para.get(CheckParam.CONTENT);
		if(StringUtil.isNotNullOrEmpty(content)){
			if(StringUtil.calStringLength(content) > maxLength){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

}
