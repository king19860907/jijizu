package com.jijizu.core.check.service.impl.user;

import java.util.Map;
import java.util.regex.Pattern;

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
 * @type : NameLegitimacyCheck
 * @function : 昵称合法性检查-不合法则返回错误
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

public class NickNameLegitimacyCheck extends CheckCommonField
	implements CheckService{
	
	private IllegalWordsCheck illegalWordsCheck;
	
	/**
	 * 名字最短长度
	 */
	private int nameMinLength;
	
	/**
	 * 名字最长长度
	 */
	private int nameMaxLength;

	@Override
	public JsonResult check(Map<String, Object> para) {

		String nickName = (String)para.get(CheckParam.NICKNAME);
		
		if(StringUtil.isNotNullOrEmpty(nickName)){
			if(Pattern.matches("^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5_]+$", nickName)==false || illegalWordsCheck.checkIllegalword(nickName) == null
					|| nickName.length() < nameMinLength || nickName.length() > nameMaxLength){
			        //昵称含有非法字符
				return new JsonResult(OperateConstanct.OPERATE_ERROR,errorMsg);
			}
		}
		return null;
	}

	public void setIllegalWordsCheck(IllegalWordsCheck illegalWordsCheck) {
		this.illegalWordsCheck = illegalWordsCheck;
	}

	public void setNameMinLength(int nameMinLength) {
		this.nameMinLength = nameMinLength;
	}

	public void setNameMaxLength(int nameMaxLength) {
		this.nameMaxLength = nameMaxLength;
	}

}
