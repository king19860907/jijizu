package com.jijizu.core.follow.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckContext;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.email.dto.EmailTemplate;
import com.jijizu.core.email.service.EmailService;
import com.jijizu.core.follow.dao.FollowDAO;
import com.jijizu.core.follow.service.FollowService;
import com.jijizu.core.mail.service.MailService;
import com.jijizu.core.notice.service.NoticeService;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.UserInfo;

public class FollowServiceImpl implements FollowService {

	private FollowDAO followDAO;
	
	private UserInfoDAO userInfoDAO;
	
	private MailService mailService;
	
	private NoticeService noticeService;
	
	private EmailService emailService;
	
	/**
	 * 关注用户验证
	 */
	private CheckContext followUserCheckContext;
	
	/**
	 * 取消关注验证
	 */
	private CheckContext followCancelCheckContext;
	
	/**
	 * 拒绝关注验证
	 */
	private CheckContext refuseFollowCheckContext;
	
	/**
	 * 批量取消关注验证
	 */
	private CheckContext followCancelBatchCheckContext;
	
	/**
	 * 关注Email模板
	 */
	private EmailTemplate followEmailTemplate;
	
	/**
	 * 互相关注Email模板
	 */
	private EmailTemplate followEachOtherEmailTemplate;
	
	
	/**   
	 *******************************************************************************
	 * @function : 批量取消关注
	 * @param sessionUserInfo
	 * @param userIds
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-8   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult followCancelBatch(UserInfo sessionUserInfo,String userIds){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		JsonResult result = followCancelBatchCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		if(StringUtil.isNotNullOrEmpty(userIds)){
			String [] ids = userIds.split("-");
			for(String id : ids){
				followDAO.followCancel(sessionUserInfo.getUserId(), Long.parseLong(id));
				this.updateUserFriendNumEachOther(sessionUserInfo.getUserId(), Long.parseLong(id));
			}
		}
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 取消关注
	 * @param sessionUserInfo
	 * @param followUserId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult followCancel(UserInfo sessionUserInfo,Long followUserId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.FOLLOWUSERID, followUserId);
		JsonResult result = followCancelCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		followDAO.followCancel(sessionUserInfo.getUserId(), followUserId);
		
		this.updateUserFriendNumEachOther(sessionUserInfo.getUserId(), followUserId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**
	 * 
	 *******************************************************************************
	 * @function : 关注官方账号
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void followOfficeAccount(Long userId){
		UserInfo userInfo = userInfoDAO.getUserInfoById(userId);
		if(userInfo != null){
			this.followUser(userInfo, CommonConstant.OFFICAL_ACCOUNT_USER_ID);
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 关注用户
	 * @param sessionUserInfo
	 * @param followUserId		被关注人
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-31   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult followUser(UserInfo sessionUserInfo,Long followUserId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.USERID, followUserId);
		JsonResult result = followUserCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		followDAO.followOne(sessionUserInfo.getUserId(), followUserId);
		
		//关注人
		UserInfo fromUser = userInfoDAO.getUserInfoById(sessionUserInfo.getUserId());
		
		//如果双方互相关注，则更新双方的user表中的friend_num字段
		boolean isEachOther = followDAO.isFollowEachOther(sessionUserInfo.getUserId(), followUserId);
		
		//发送系统通知内容
		String noticeContent = getFollowContent(fromUser, followUserId,isEachOther);
		
		//被关注人
		UserInfo toUser = userInfoDAO.getUserInfoById(followUserId);
		noticeService.sendNotice(toUser.getUserId(), noticeContent);
		
		//发送邮件
		this.sendEmail(fromUser, toUser, isEachOther);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, isEachOther?"加对方好友成功":"发送好友申请成功");
	}
	
	private void sendEmail(UserInfo fromUser,UserInfo toUser,boolean isEachOther){
		String content = null;
		String toMail=toUser.getEmail();
		Map<String,String> para = new HashMap<String,String>();
		if(isEachOther){
			para.put("fromUser", fromUser.getName());
			para.put("toUser", toUser.getName());
			content = emailService.getContent(followEachOtherEmailTemplate.getLocation(), para);
			emailService.sendEmail(toMail, StringUtil.formatString(followEachOtherEmailTemplate.getSubject(), fromUser.getName()), content, true);
		}else{
			para.put("fromUser", fromUser.getName());
			para.put("toUser", toUser.getName());
			content = emailService.getContent(followEmailTemplate.getLocation(), para);
			emailService.sendEmail(toMail, StringUtil.formatString(followEmailTemplate.getSubject(), fromUser.getName()), content, true);
		}
	}

	private String getFollowContent(UserInfo fromUser,Long followUserId,  boolean isEachOther) {
		String content;
		if(isEachOther){
			this.updateUserFriendNumEachOther(fromUser.getUserId(), followUserId);
			content = "@"+fromUser.getAtName()+" 已经加您为好友";
		}else{
			content = "@"+fromUser.getAtName()+" 申请加您为好友  <a name='followUserMail' userid="+fromUser.getUserId()+" href='javascript:void(0);'>同意</a>&nbsp;&nbsp;&nbsp;<a href='javascript:refuseFollow("+fromUser.getUserId()+")'>拒绝</a>";
		}
		return content;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 拒绝关注
	 * @param sessionUserInfo
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult refuseFollow(UserInfo sessionUserInfo,Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.USERID, userId);
		JsonResult result = refuseFollowCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		UserInfo fromUser = userInfoDAO.getUserInfoById(sessionUserInfo.getUserId());
		UserInfo toUser = userInfoDAO.getUserInfoById(userId);
		String content = "@"+fromUser.getAtName()+" 拒绝了您的好友申请";
		
		noticeService.sendNotice(toUser.getUserId(), content);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, "已拒绝该用户的好友申请");
	}
	
	/**   
	 *******************************************************************************
	 * @function : 关注好友双方各自的好友数
	 * @param userId
	 * @param followUserId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void updateUserFriendNumEachOther(Long userId,Long followUserId){
		userInfoDAO.updateUserFriendNumByUserId(userId);
		userInfoDAO.updateUserFriendNumByUserId(followUserId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取好友
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @param nickName
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-4   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findFriendsPage(long pageNum, long pageSize,Long userId,String nickName){
		return followDAO.findFriendsPage(pageNum, pageSize, userId,nickName);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取共同好友
	 * @param pageNum
	 * @param pageSize
	 * @param userId1
	 * @param userId2
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findCommonFriendsPage(long pageNum,long pageSize,Long userId1,Long userId2){
		return followDAO.findCommonFriendsPage(pageNum, pageSize, userId1, userId2);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 判断两个用户是否互相关注
	 * @param userId1
	 * @param userId2
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-31   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean isFollowEachOther(Long userId1,Long userId2){
		return followDAO.isFollowEachOther(userId1, userId2);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取发送申请好友
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-8   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findSendApplyFriendsPage(long pageNum,long pageSize,Long userId){
		return followDAO.findSendApplyFriendsPage(pageNum, pageSize, userId);
	}

	public void setFollowDAO(FollowDAO followDAO) {
		this.followDAO = followDAO;
	}

	public void setFollowUserCheckContext(CheckContext followUserCheckContext) {
		this.followUserCheckContext = followUserCheckContext;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	public void setFollowCancelCheckContext(CheckContext followCancelCheckContext) {
		this.followCancelCheckContext = followCancelCheckContext;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setRefuseFollowCheckContext(CheckContext refuseFollowCheckContext) {
		this.refuseFollowCheckContext = refuseFollowCheckContext;
	}

	public void setFollowCancelBatchCheckContext(
			CheckContext followCancelBatchCheckContext) {
		this.followCancelBatchCheckContext = followCancelBatchCheckContext;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setFollowEmailTemplate(EmailTemplate followEmailTemplate) {
		this.followEmailTemplate = followEmailTemplate;
	}

	public void setFollowEachOtherEmailTemplate(
			EmailTemplate followEachOtherEmailTemplate) {
		this.followEachOtherEmailTemplate = followEachOtherEmailTemplate;
	}
	
}
