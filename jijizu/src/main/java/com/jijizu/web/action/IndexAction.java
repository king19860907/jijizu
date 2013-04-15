package com.jijizu.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.group.dto.GroupInfo;
import com.jijizu.core.group.service.GroupService;
import com.jijizu.core.user.dto.UserInfo;

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction{

	private List<GroupInfo> groupList;
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4385802930399421209L;
	
	private UserInfo userInfo;
	
	private GroupService groupService;
	
	public String index(){

		UserInfo userInfo = getSessionUserInfo();
		
		if(userInfo != null){
			return "profile";
		}
		
		groupList = groupService.findConveneGroupFromCache(6L);
		
		return SUCCESS;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<GroupInfo> getGroupList() {
		return groupList;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

}
