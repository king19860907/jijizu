package com.jijizu.core.check.service.impl.user;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.jijizu.base.util.CookieUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.cache.service.CacheService;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.CookieConstant;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : ICodeCheck
 * @function : 验证码正确性检查-不正确则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-4-13   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class ICodeCheck	extends CheckCommonField 
	implements CheckService{
	
	private CacheService cacheService;

	@Override
	public JsonResult check(Map<String, Object> para) {
		
		String icode = (String)para.get(CheckParam.ICODE);
		
		String uid = CookieUtil.getCookieValue(ServletActionContext.getRequest(), CookieConstant.IDENTIFYING_CODE, null);
		if(!StringUtil.isNotNullOrEmpty(uid)){
			return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
		}else{
			try {
				String memICode = (String)cacheService.getSnaInfo(uid);
				if(!StringUtil.isNotNullOrEmpty(memICode) || !icode.toLowerCase().equals(memICode.toLowerCase())){
					return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		try {
			cacheService.deleteSnaInfo(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}
	
}
