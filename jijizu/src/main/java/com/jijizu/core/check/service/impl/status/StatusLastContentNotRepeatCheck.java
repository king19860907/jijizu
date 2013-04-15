package com.jijizu.core.check.service.impl.status;

import java.util.Map;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : StatusLastContentNotRepeatCheck
 * @function : 用户最后发送的微博不重复检查-重复则返回错误
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

public class StatusLastContentNotRepeatCheck extends CheckCommonField
	implements CheckService{

	@Override
	public JsonResult check(Map<String, Object> para) {
		String content = (String)para.get(CheckParam.CONTENT);
		if(StringUtil.isNotNullOrEmpty(content)){
			if(content.equals(JijizuUtil.getLastContent())){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

}
