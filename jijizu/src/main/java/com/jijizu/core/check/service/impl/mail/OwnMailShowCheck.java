package com.jijizu.core.check.service.impl.mail;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.mail.dao.MailDAO;
import com.jijizu.core.mail.dto.MailShow;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : OwnMailShowCheck
 * @function : 为自己的私信显示记录检查-不为自己的则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-1-14   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class OwnMailShowCheck extends CheckCommonField
	implements CheckService{
	
	private MailDAO mailDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Long mailShowId = (Long)para.get(CheckParam.MAILSHOWID);
		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		MailShow mailShow = null;
		if(mailShowId != null && sessionUserInfo != null){
			mailShow = (MailShow)para.get(CheckParam.TEMP_MAILSHOW);
			if(mailShow == null){
				mailShow = mailDAO.getMailShowById(mailShowId);
				if(sessionUserInfo.getUserId().longValue() == mailShow.getUserId().longValue()){
					return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
				}
			}
		}
		return null;
	}

	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
	}

}
