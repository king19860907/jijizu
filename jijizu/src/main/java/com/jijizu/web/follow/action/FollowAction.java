package com.jijizu.web.follow.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.follow.service.FollowService;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class FollowAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -819409306465655058L;
	
	private PaginationDTO<UserInfo> friendsPage;
	
	private FollowService followService;
	
	private int page;
	
	private String attr;
	
	private String q;
	
	private Long userId;
	
	private long pageSize;
	
	public String findFirends(){
		
		UserInfo sessionUserinfo = getSessionUserInfo();
		
		if(sessionUserinfo != null && userId != null){
			friendsPage = followService.findFriendsPage(page, pageSize, userId,q);
		}
		
		return SUCCESS;
	}
	
	public String findCommonFriends(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null && userId != null && sessionUserInfo.getUserId().longValue() != userId){
			friendsPage = followService.findCommonFriendsPage(page, pageSize, sessionUserInfo.getUserId(), userId);
		}
		
		
		return SUCCESS;
	}
	
	public PaginationDTO<UserInfo> getFriendsPage() {
		return friendsPage;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setFollowService(FollowService followService) {
		this.followService = followService;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

}
