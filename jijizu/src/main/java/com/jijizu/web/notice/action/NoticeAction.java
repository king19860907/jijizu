package com.jijizu.web.notice.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.notice.dto.NoticeInfo;
import com.jijizu.core.notice.service.NoticeService;
import com.jijizu.core.service.MessageService;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class NoticeAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7567080889314869557L;
	
	private NoticeService noticeService;
	
	private UserInfoService userInfoService;
	
	private MessageService messageService;
	
	private UserInfo userInfo;
	
	private PaginationDTO<NoticeInfo> noticePage;
	
	private long page;
	
	public String notice(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		noticePage = noticeService.findNoticePage(page, 10, sessionUserInfo.getUserId());
		
		noticeService.updateNoticeHasReadByUserId(sessionUserInfo.getUserId());
		
		messageService.clearNewMessage(CacheConstant.MESSAGE_NOTICE_NEW_COUNT, userInfo.getUserId());
		
		return SUCCESS;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public PaginationDTO<NoticeInfo> getNoticePage() {
		return noticePage;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

}
