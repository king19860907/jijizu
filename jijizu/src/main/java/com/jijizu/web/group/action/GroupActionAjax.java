package com.jijizu.web.group.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.check.CheckContext;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.group.service.GroupService;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class GroupActionAjax extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7444245684104952545L;
	
	private GroupService groupService;
	
	private Long groupId;
	
	private Long userId;
	
	private String photoIds;
	
	private List<PhotoInfo> photoList;
	
	private CheckContext enterGroupLayerCheckContext;
	
	public String enterGroupCheck() {
		UserInfo sessionUserInfo = getSessionUserInfo();
		Map<String,Object> para = new HashMap<String,Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.GROUPID, groupId);
		
		JsonResult result = enterGroupLayerCheckContext.check(para);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String uploadPhotoBatch(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = groupService.uploadPhotoBatch(sessionUserInfo, photoList, groupId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String copyPhotoToGroup(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = groupService.copyPhotoToGroup(sessionUserInfo, photoIds, groupId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String addGroup(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		Map<String, Object> para = getParams();
		
		JsonResult result = groupService.addGroup(sessionUserInfo, para);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String enterGroup(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		Map<String,Object> para = getParams();
		
		JsonResult result = groupService.enterGroup(sessionUserInfo, para);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String enterWaitList() {
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		Map<String,Object> para = getParams();
		
		JsonResult result = groupService.enterWaitingList(sessionUserInfo, para);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String cancelEnterGroup(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = groupService.cancelEnterGroup(sessionUserInfo, groupId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String deleteGroup(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = groupService.deleteGroup(sessionUserInfo, groupId);
		
		this.outObjectToJson(result);
		
		return NONE;

	}
	
	public String passGroupUser(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = groupService.passGroupUser(sessionUserInfo, userId, groupId);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String refuseGroupUser(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = groupService.refuseGroupUser(sessionUserInfo, userId, groupId);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String updateGroup(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		Map<String,Object> para = getParams();
		
		JsonResult result = groupService.updateGroup(sessionUserInfo, para);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setPhotoIds(String photoIds) {
		this.photoIds = photoIds;
	}

	public void setPhotoList(List<PhotoInfo> photoList) {
		this.photoList = photoList;
	}

	public List<PhotoInfo> getPhotoList() {
		return photoList;
	}
	
	public void setEnterGroupLayerCheckContext(
			CheckContext enterGroupLayerCheckContext) {
		this.enterGroupLayerCheckContext = enterGroupLayerCheckContext;
}

}
