package com.jijizu.core.notice.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.core.check.CheckContext;
import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.notice.dao.NoticeDAO;
import com.jijizu.core.notice.dto.NoticeInfo;
import com.jijizu.core.notice.service.NoticeService;
import com.jijizu.core.service.MessageService;
import com.jijizu.core.user.dto.UserInfo;

public class NoticeServiceImpl implements NoticeService {
	
	private NoticeDAO noticeDAO;
	
	private MessageService messageService;
	
	/**
	 * 删除系统通知验证
	 */
	private CheckContext deleteNoticeCheckContext;
	
	/**
	 * 更新系统通知内容验证
	 */
	private CheckContext updateNoticeContentCheckContext;
	
	/**   
	 *******************************************************************************
	 * @function : 更新系统通知内容
	 * @param sessionUserInfo
	 * @param noticeId
	 * @param content
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult updateNoticeContent(UserInfo sessionUserInfo,Long noticeId,String content){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.NOTICEID, noticeId);
		para.put(CheckParam.CONTENT, content);
		JsonResult result = updateNoticeContentCheckContext.check(para);
		if(result != null){
			return result;
		}

		noticeDAO.updateNoticeContent(noticeId, sessionUserInfo.getUserId(), content);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除系统通知
	 * @param sessionUserInfo
	 * @param noticeId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult deleteNotice(UserInfo sessionUserInfo,Long noticeId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.NOTICEID, noticeId);
		JsonResult result = deleteNoticeCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		noticeDAO.deleteNotice(noticeId, sessionUserInfo.getUserId());
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}

	/**   
	 *******************************************************************************
	 * @function : 发送通知（用官方账号）
	 * @param toUserId
	 * @param content
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long sendNotice(Long toUserId,String content){
		messageService.setNewMessage(CacheConstant.MESSAGE_NOTICE_NEW_COUNT, toUserId);
		Long noticeId = noticeDAO.saveNotice(CommonConstant.OFFICAL_ACCOUNT_USER_ID, toUserId, content);
		return noticeId;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 
	 * @param toUserId
	 * @param content
	 * @param replaceHtmlTag
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long sendNotice(Long toUserId,String content,boolean replaceHtmlTag){
		//处理超链接
		content = JijizuUtil.convertURL(content,replaceHtmlTag);
		messageService.setNewMessage(CacheConstant.MESSAGE_NOTICE_NEW_COUNT, toUserId);
		Long noticeId = noticeDAO.saveNotice(CommonConstant.OFFICAL_ACCOUNT_USER_ID, toUserId, content);
		return noticeId;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询系统通知
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<NoticeInfo> findNoticePage(long pageNum, long pageSize,Long userId){
		return noticeDAO.findNoticePage(pageNum, pageSize, userId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据用户更新通知为已读
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateNoticeHasReadByUserId(Long userId){
		noticeDAO.updateNoticeHasReadByUserId(userId);
	}

	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	public void setDeleteNoticeCheckContext(CheckContext deleteNoticeCheckContext) {
		this.deleteNoticeCheckContext = deleteNoticeCheckContext;
	}

	public void setUpdateNoticeContentCheckContext(
			CheckContext updateNoticeContentCheckContext) {
		this.updateNoticeContentCheckContext = updateNoticeContentCheckContext;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
}
