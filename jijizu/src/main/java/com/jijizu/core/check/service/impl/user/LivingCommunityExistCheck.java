package com.jijizu.core.check.service.impl.user;

import java.util.Map;

import com.jijizu.base.util.ObjectUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.LivingCommunity;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : HometownLivingCommunityExistCheck
 * @function : 居住地生活小区存在检查-不存在则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-1-27   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class LivingCommunityExistCheck extends CheckCommonField
	implements CheckService{
	
	private UserInfoDAO userInfoDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Object livingCommunityId = para.get(CheckParam.HOMETOWNLIVINGCOMMUNITY);
		
		if(!ObjectUtil.isEmptyObject(livingCommunityId)){
			LivingCommunity livingCommunity = userInfoDAO.getLivingCommunityById(Long.parseLong(livingCommunityId.toString()));
			if(livingCommunity == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		
		return null;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
	
}
