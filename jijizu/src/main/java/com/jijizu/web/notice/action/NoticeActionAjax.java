package com.jijizu.web.notice.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.notice.service.NoticeService;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class NoticeActionAjax extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 72787685092537148L;
	
	private NoticeService noticeService;
	
	private UserInfoService userInfoService;
	
	private Long noticeId;
	
	private Long userId;
	
	public String updateNoticeContentAfterFollow(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(userId != null){
			UserInfo userInfo = userInfoService.getUserInfoById(userId);
			if(userInfo != null){
				String content = "您已添加@"+userInfo.getAtName()+" 为好友";
				
				JsonResult result = noticeService.updateNoticeContent(sessionUserInfo, noticeId, content);
				
				this.outObjectToJson(result);
			}
		}
		
		return NONE;
	}
	
	public String deleteNotice(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = noticeService.deleteNotice(sessionUserInfo, noticeId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

}
