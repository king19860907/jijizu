package com.jijizu.core.service.impl;

import com.jijizu.core.cache.service.CacheService;
import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.notice.dao.NoticeDAO;
import com.jijizu.core.service.MessageService;
import com.jijizu.core.status.dao.StatusDAO;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.UserInfo;

public class MessageServiceImpl implements MessageService {
	
	private StatusDAO statusDAO;
	
	private NoticeDAO noticeDAO;
	
	private UserInfoDAO userInfoDAO;
	
	private CacheService cacheService;

	/**   
	 *******************************************************************************
	 * @function : 从缓存中获取新消息提示
	 * @param key
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long getNewMessage(String key,Long userId){
		
		Long count = null;
		try {
			count = (Long)cacheService.getSnaInfo(key+userId);
			if(count == null){
				count = this.getNewMessageFromDataBase(key, userId);
			}
		} catch (Exception e) {
			count = this.getNewMessageFromDataBase(key, userId);
		}
		return count;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 设置新消息提示数据进入缓存
	 * @param key
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-11   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void setNewMessage(String key,Long userId){
		Long count = null;
		count = this.getNewMessage(key, userId);
		try {
			cacheService.setSnaInfo(key+userId, count+1, CacheConstant.CACHE_EXPRIATION_ONE_HOUR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 清除新消息提示缓存数据
	 * @param key
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-11   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void clearNewMessage(String key,Long userId){
		try {
			cacheService.setSnaInfo(key+userId, 0, CacheConstant.CACHE_EXPRIATION_ONE_HOUR);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 从数据库中获取新消息提示
	 * @param key
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private Long getNewMessageFromDataBase(String key,Long userId){
		Long count = 0L;
		UserInfo userInfo = userInfoDAO.getUserInfoById(userId);
		if(userInfo != null){
			if(CacheConstant.MESSAGE_COMMENT_NEW_COUNT.equals(key)){
				count = statusDAO.countNewCommentNumByUserId(userInfo.getUserId(), userInfo.getReadCommentTime());
			}else if(CacheConstant.MESSAGE_AT_NEW_COUNT.equals(key)){
				count = statusDAO.countNewAtMeNumByUserId(userInfo.getUserId(), userInfo.getReadAtTime());
			}else if(CacheConstant.MESSAGE_NOTICE_NEW_COUNT.equals(key)){
				count = noticeDAO.countNewNoticeNumByUserId(userInfo.getUserId());
			}else if(CacheConstant.MESSAGE_MAIL_NEW_COUNT.equals(key)){
				count = userInfo.getNewMailNum();
			}
			try {
				cacheService.setSnaInfo(key+userInfo.getUserId(), count==null?0:count, CacheConstant.CACHE_EXPRIATION_ONE_HOUR);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
	
}
