package com.jijizu.core.check.service.impl.user;

import java.util.Map;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.UserInfo;

public class UserExistByNickNameCheck extends CheckCommonField
	implements CheckService{

	private UserInfoDAO userInfoDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		String nickName = (String)para.get(CheckParam.NICKNAME);
		UserInfo userInfo = null;
		if(StringUtil.isNotNullOrEmpty(nickName)){
			int startIndex = StringUtil.indexOf(nickName, "(");
			int endIndex = StringUtil.indexOf(nickName, ")");
			if(startIndex == -1 || endIndex == -1){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
			nickName = nickName.substring(startIndex+1,endIndex);
			userInfo = userInfoDAO.getUserInfoByNickName(nickName);
			if(userInfo == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		para.put(CheckParam.TMEP_USERINFO, userInfo);
		return null;
	}
	
	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
	
}
