package com.jijizu.core.check.service.impl.user;

import java.util.Map;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.cache.service.CacheService;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 集集组 
 * @type : FindPwdUrlNotExpireCheck
 * @function : 找回密码未过期检查-已过期则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-4-14   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class FindPwdUrlNotExpireCheck extends CheckCommonField
	implements CheckService{
	
	private CacheService cacheService;
	
	@Override
	public JsonResult check(Map<String, Object> para) {
		String random = (String)para.get(CheckParam.RANDOM);
		String logName = (String)para.get(CheckParam.LOGNAME);
		if(StringUtil.isNotNullOrEmpty(random) && StringUtil.isNotNullOrEmpty(logName)){
			try {
				String cacheRandom = (String)cacheService.getSnaInfo(CacheConstant.FIND_PWD_USER_KEY_NAME+logName);
				if(!StringUtil.isNotNullOrEmpty(cacheRandom) || !cacheRandom.equals(random)){
					return new JsonResult(OperateConstanct.OPERATE_FIND_PWD_URL_EXPIRE, errorMsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult(OperateConstanct.OPERATE_FIND_PWD_URL_EXPIRE, errorMsg);
			}
		}
		return null;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

}
