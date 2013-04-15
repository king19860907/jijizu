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

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : NickNameNotExistCheck
 * @function : 昵称不重复检查-重复则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-1-7   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 
public class NickNameNotExistCheck extends CheckCommonField
	implements CheckService{
	
	private UserInfoDAO userInfoDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		String nickName = (String)para.get(CheckParam.NICKNAME);
		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		if(StringUtil.isNotNullOrEmpty(nickName)){
			UserInfo userInfo = userInfoDAO.getUserInfoByNickName(nickName);
			if(userInfo != null && sessionUserInfo == null){	//用户未登录且用户名已存在
				return new JsonResult(OperateConstanct.OPERATE_ERROR,errorMsg);
			}else if(userInfo != null && userInfo.getUserId().longValue() != sessionUserInfo.getUserId().longValue()){ //用户已登录且昵称不为自己的昵称且已存在
				return new JsonResult(OperateConstanct.OPERATE_ERROR,errorMsg);
			}
		}
		return null;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

}
