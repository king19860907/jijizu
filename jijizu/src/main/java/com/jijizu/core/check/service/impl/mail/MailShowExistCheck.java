package com.jijizu.core.check.service.impl.mail;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.mail.dao.MailDAO;
import com.jijizu.core.mail.dto.MailShow;

/**   
 *******************************************************************************
 * @project : 集集组  
 * @type : MailShowExistCheck
 * @function : 私信显示记录存在检查-不存在则返回错误
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

public class MailShowExistCheck extends CheckCommonField
	implements CheckService{
	
	private MailDAO mailDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Long mailShowId = (Long)para.get(CheckParam.MAILSHOWID);
		MailShow mailShow = null;
		if(mailShowId != null){
			mailShow = mailDAO.getMailShowById(mailShowId);
			if(mailShow == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		para.put(CheckParam.TEMP_MAILSHOW, mailShow);
		return null;
	}

	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
	}

}
