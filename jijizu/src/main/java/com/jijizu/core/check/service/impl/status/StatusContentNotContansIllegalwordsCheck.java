package com.jijizu.core.check.service.impl.status;

import java.util.Map;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.dto.IllegalWordsCheck;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : StatusContentNotContansIllegalwordsCheck
 * @function : 微博内容不包含敏感词检查-包含则返回错误
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

public class StatusContentNotContansIllegalwordsCheck extends CheckCommonField
	implements CheckService{
	
	private IllegalWordsCheck illegalWordsCheck;

	@Override
	public JsonResult check(Map<String, Object> para) {
		String content = (String)para.get(CheckParam.CONTENT);
		if(StringUtil.isNotNullOrEmpty(content)){
			if(illegalWordsCheck.checkIllegalword(content) == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setIllegalWordsCheck(IllegalWordsCheck illegalWordsCheck) {
		this.illegalWordsCheck = illegalWordsCheck;
	}

}
