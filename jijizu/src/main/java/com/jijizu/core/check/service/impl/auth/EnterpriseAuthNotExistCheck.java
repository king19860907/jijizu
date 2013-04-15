package com.jijizu.core.check.service.impl.auth;

import java.util.Map;

import com.jijizu.base.util.ObjectUtil;
import com.jijizu.core.auth.dao.AuthDAO;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 集集组 
 * @type : PersoanalAuthNotExistCheck
 * @function : 企业认证不存在检查-如存在则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-2-1   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class EnterpriseAuthNotExistCheck extends CheckCommonField
	implements CheckService{
	
	private AuthDAO authDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Object userId = para.get(CheckParam.USERID);
		if(!ObjectUtil.isEmptyObject(userId)){
			if(authDAO.isHavePostEnterpriseAuth(Long.parseLong(userId.toString()))){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setAuthDAO(AuthDAO authDAO) {
		this.authDAO = authDAO;
	}

}
