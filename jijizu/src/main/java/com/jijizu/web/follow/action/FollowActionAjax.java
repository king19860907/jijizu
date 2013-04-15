package com.jijizu.web.follow.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.follow.service.FollowService;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class FollowActionAjax extends BaseAction{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1136534317558746945L;
	
	private Long userId;
	
	private FollowService followService;
	
	private String q;
	
	private Long mailId;
	
	private String userIds;
	
	public String followCancelBatch(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = followService.followCancelBatch(sessionUserInfo, userIds);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String followUserBatch(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = null;
		
		if(StringUtil.isNotNullOrEmpty(userIds)){
			String [] ids = userIds.split("-");
			for(String id:ids){
				result = followService.followUser(sessionUserInfo, Long.parseLong(id));
			}
		}
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String followUser(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = followService.followUser(sessionUserInfo, userId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String refuseFollow(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = followService.refuseFollow(sessionUserInfo, userId);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String followCancel(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = followService.followCancel(sessionUserInfo, userId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String findAtFirendsJson(){
		
		UserInfo sessionUserinfo = getSessionUserInfo();
		
		if(sessionUserinfo != null){
			PaginationDTO<UserInfo> friendsPage = followService.findFriendsPage(1, 10, sessionUserinfo.getUserId(),q);
			List<String> namelist = new ArrayList<String>();
			List<UserInfo> userList = friendsPage.getResult();
			for(UserInfo user: userList){
				namelist.add(user.getName()+"("+user.getNickName()+")"+"|"+user.getHeadImgUrl());
			}
			this.outListToJson(namelist);
		}
		
		return NONE;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setFollowService(FollowService followService) {
		this.followService = followService;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

}

