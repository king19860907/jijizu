package com.jijizu.core.check.service.impl.user;

import java.util.Map;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.ChildInfo;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : ChildNickNameNotExistCheck
 * @function : 孩子昵称不存在验证-如果存在则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-1-24   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class ChildNickNameNotExistCheck extends CheckCommonField
	implements CheckService{

	private UserInfoDAO userInfoDAO;
	
	@Override
	public JsonResult check(Map<String, Object> para) {
		String nickName = (String)para.get(CheckParam.NICKNAME);
		String childIdStr = (String)(para.get(CheckParam.CHILDID));
		Long childId = null;
		if(StringUtil.isNotNullOrEmpty(childIdStr)){
			childId = Long.parseLong(childIdStr);
		}
		if(StringUtil.isNotNullOrEmpty(nickName)){
			ChildInfo childInfo = userInfoDAO.getChildInfoByNickName(nickName);
			if(childInfo != null && childId == null){	//添加孩子时验证
				return new JsonResult(OperateConstanct.OPERATE_ERROR,errorMsg);
			}else if(childInfo != null && childId != null && childId.longValue() != childInfo.getChildId().longValue()){ //编辑孩子时验证
				return new JsonResult(OperateConstanct.OPERATE_ERROR,errorMsg);
			}
		}
		return null;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
	
}
