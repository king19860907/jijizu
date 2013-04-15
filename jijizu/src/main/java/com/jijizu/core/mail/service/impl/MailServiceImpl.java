package com.jijizu.core.mail.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckContext;
import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.email.dto.EmailTemplate;
import com.jijizu.core.email.service.EmailService;
import com.jijizu.core.mail.dao.MailDAO;
import com.jijizu.core.mail.dto.MailDetail;
import com.jijizu.core.mail.dto.MailShow;
import com.jijizu.core.mail.service.MailService;
import com.jijizu.core.service.MessageService;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.UserInfo;

public class MailServiceImpl implements MailService {
	
	private MailDAO mailDAO;
	
	private UserInfoDAO userInfoDAO;
	
	private MessageService messageService;
	
	private EmailService emailService;
	
	/**
	 * 私信邮件模板
	 */
	private EmailTemplate mailEmailTemplate;
	
	/**
	 * 发送私信验证
	 */
	private CheckContext sendMailCheckContext;
	
	/**
	 * 删除显示私信验证
	 */
	private CheckContext deleteShowMailCheckContext;
	
	/**
	 * 删除详细私信验证
	 */
	private CheckContext deleteDetailMailCheckContext;
	
	/**
	 * 更新私信内容验证
	 */
	private CheckContext updateMailContentCheckContext;
	
	/**   
	 *******************************************************************************
	 * @function : 更新私信内容
	 * @param sessionUserInfo
	 * @param mailShowId
	 * @param mailDetailId
	 * @param content
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult updateMailContent(UserInfo sessionUserInfo,Long mailShowId,Long mailDetailId,String content){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		JsonResult result = updateMailContentCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		if(mailShowId != null){
			//修改 mailShow中的内容
			mailDAO.updateMailShowContent(content, mailShowId);
			
			//修改maildetail最后一条的内容
			mailDAO.updateLastMailDetailContentByMailShowId(content, mailShowId);
		}
		if(mailDetailId != null){
			//修改mailDetail中的内容
			mailDAO.updateMailDetailByMailDetailId(content, mailDetailId);
			
			//如果mailDetial为mailshow的最后一条内容，则修改mailshow的内容
			if(mailDAO.isLastMailDetail(mailDetailId)){
				mailDAO.updateMailShowContentByMailDetailId(content, mailDetailId);
			}
		}
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}

	/**   
	 *******************************************************************************
	 * @function : 发送私信
	 * @param sessionUserInfo
	 * @param toNickName
	 * @param content
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult sendMail(UserInfo sessionUserInfo,String toNickName,String content){
		
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.NICKNAME, toNickName);
		para.put(CheckParam.CONTENT, content);
		JsonResult result = sendMailCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		
		UserInfo toUser = (UserInfo)para.get(CheckParam.TMEP_USERINFO);
		Long mailId = this.sendMail(sessionUserInfo.getUserId(), toUser.getUserId(), content,true);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,mailId);
	}
	
	
	/**   
	 *******************************************************************************
	 * @function : 发送私信
	 * @param fromUserid
	 * @param toUserId
	 * @param content
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long sendMail(Long fromUserId,Long toUserId,String content,boolean replaceHtmlTag){
		//处理超链接
		content = JijizuUtil.convertURL(content,replaceHtmlTag);
		Long mailId = mailDAO.saveMail(fromUserId, toUserId, content);
		
		messageService.setNewMessage(CacheConstant.MESSAGE_MAIL_NEW_COUNT, toUserId);
		
		//发送邮件
		UserInfo toUser = userInfoDAO.getUserInfoById(toUserId);
		UserInfo fromUser = userInfoDAO.getUserInfoById(fromUserId);
		String toMail=toUser.getEmail();
		Map<String,String> model = new HashMap<String,String>();
		model.put("toUser", toUser.getName());
		model.put("fromUser", fromUser.getName());
		model.put("content", content);
		String emailContent = emailService.getContent(mailEmailTemplate.getLocation(), model);
		emailService.sendEmail(toMail, StringUtil.formatString(mailEmailTemplate.getSubject(), fromUser.getName()), emailContent, true);
		
		return mailId;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查看私信
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<MailShow> findMailShowPage(long pageNum, long pageSize,Long userId){
		PaginationDTO<MailShow> page = mailDAO.findMailShowPage(pageNum, pageSize, userId);
		mailDAO.updateNewMailNumByShowMail(userId, page.getEndRecordNum(), page.getBegRecordNum());
		mailDAO.updateSnsUserNewMailByUserId(userId);
		return page;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除私信显示记录
	 * @param sessionUserInfo
	 * @param mailShowId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult deleteShowMail(UserInfo sessionUserInfo,Long mailShowId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.MAILSHOWID, mailShowId);
		
		JsonResult result = deleteShowMailCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		mailDAO.deleteMailDetailByShowId(mailShowId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除私信详细记录
	 * @param sessionUserInfo
	 * @param mailDetailId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult deleteDetailMail(UserInfo sessionUserInfo,Long mailDetailId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.MAILDETAILID, mailDetailId);
		
		JsonResult result = deleteDetailMailCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		mailDAO.deleteMailDetailByDetailId(mailDetailId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查看我与某人的私信对话
	 * @param mailShowId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<MailDetail> findMailDetailPage(long mailShowId,long pageNum,Long pageSize){
    	PaginationDTO<MailDetail> page = mailDAO.findMailDetailPage(mailShowId, pageNum, pageSize);
    	mailDAO.updateNewMailNumByMailShowId(mailShowId);
    	return page;
    }
	
	/**   
	 *******************************************************************************
	 * @function : 获取私信显示记录
	 * @param mailShowId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public MailShow getMailShowById(Long mailShowId){
		return mailDAO.getMailShowById(mailShowId);
	}

	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
	}

	public void setSendMailCheckContext(CheckContext sendMailCheckContext) {
		this.sendMailCheckContext = sendMailCheckContext;
	}

	public void setDeleteShowMailCheckContext(
			CheckContext deleteShowMailCheckContext) {
		this.deleteShowMailCheckContext = deleteShowMailCheckContext;
	}

	public void setDeleteDetailMailCheckContext(
			CheckContext deleteDetailMailCheckContext) {
		this.deleteDetailMailCheckContext = deleteDetailMailCheckContext;
	}

	public void setUpdateMailContentCheckContext(
			CheckContext updateMailContentCheckContext) {
		this.updateMailContentCheckContext = updateMailContentCheckContext;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setMailEmailTemplate(EmailTemplate mailEmailTemplate) {
		this.mailEmailTemplate = mailEmailTemplate;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
	
}
