package com.jijizu.core.check.service.impl.mail;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.mail.dao.MailDAO;
import com.jijizu.core.mail.dto.MailDetail;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : MailDetailExistCheck
 * @function : 私信详细记录存在检查-不存在则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-1-15   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class MailDetailExistCheck extends CheckCommonField
	implements CheckService{
	
	private MailDAO mailDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Long mailDetailId = (Long)para.get(CheckParam.MAILDETAILID);
		if(mailDetailId != null){
			MailDetail mailDetail = mailDAO.getMailDetailById(mailDetailId);
			if(mailDetail == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
			para.put(CheckParam.MAILSHOWID, mailDetail.getMailShowId());
		}
		return null;
	}

	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
	}
	
}
