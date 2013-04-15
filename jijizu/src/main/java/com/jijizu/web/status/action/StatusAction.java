package com.jijizu.web.status.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.group.dto.GroupInfo;
import com.jijizu.core.group.service.GroupService;
import com.jijizu.core.service.MessageService;
import com.jijizu.core.status.dto.CommentInfo;
import com.jijizu.core.status.dto.CommentInfoWithStatus;
import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.status.dto.StatusInfo;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.status.service.StatusService;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class StatusAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6229370601005411183L;
	
	private StatusService statusService;
	
	private List<StatusFace> faces;
	
	private PaginationDTO<StatusInfoWithFwdSrc> statusPage;
	
	private List<StatusInfoWithFwdSrc> statusList;
	
	private int pageNum;
	
	private Long statusId;
	
	private PaginationDTO<CommentInfo> commentsPage;
	
	private PaginationDTO<CommentInfoWithStatus> commentsWithStatusPage;
	
	private StatusInfoWithFwdSrc statusFwdSrc;
	
	private StatusInfo status;
	
	private UserInfo userInfo;
	
	private UserInfoService userInfoService;
	
	private GroupService groupService;
	
	private MessageService messageService;
	
	private boolean isShowPage;
	
	private long page;
	
	private List<GroupInfo> groupList;
	
	public String findNewStatus(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null && statusId != null){
			statusList = statusService.findAfterStatusList(sessionUserInfo.getUserId(), statusId, null);
		}
		
		return SUCCESS;
	}
	
	public String home(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		Long userId = sessionUserInfo.getUserId();
		
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS); 
		
		statusPage = statusService.findFriendsStatusPage(pageNum, 20, userId);
		
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		groupList = groupService.findConveneGroup(6L);
		
		return SUCCESS;
	}
	
	public String statusDetail(){
		
		status = statusService.getStatusWithFwdSrc(statusId);
		
		if(status == null){
			return INDEX;
		}
		
		userInfo = userInfoService.getUserInfoById(status.getUserId(),true);
		
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS); 
		
		return SUCCESS;
	}
	
	public String showMoreStatus(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null || statusId == null){
			return SUCCESS;
		}
		
		Long userId = sessionUserInfo.getUserId();
		
		statusList = statusService.findBeforeStatusList(userId, statusId, 20L);
		
		return SUCCESS;
		
	}
	
	public String loadComment(){
		
		commentsPage = statusService.findCommentsByStatusIdPage(pageNum, 10, statusId);
		
		statusFwdSrc = statusService.getStatusWithFwdSrc(statusId);
		
		return SUCCESS;
	}
	
	public String getNewestStatus(){
		
		statusFwdSrc = statusService.getStatusWithFwdSrc(statusId);
		
		return SUCCESS;
	}
	
	public String commentIn(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		commentsWithStatusPage = statusService.findCommentInPage(page, 10, sessionUserInfo.getUserId());
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS);
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		userInfoService.updateUserReadCommentTime(sessionUserInfo.getUserId());
		messageService.clearNewMessage(CacheConstant.MESSAGE_COMMENT_NEW_COUNT, userInfo.getUserId());
		
		return SUCCESS;
	}
	
	public String commentOut(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		commentsWithStatusPage = statusService.findCommentOutPage(page, 10, sessionUserInfo.getUserId());
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS); 
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		return SUCCESS;
	}
	
	public String atMeStatus(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		statusPage = statusService.findAtMeStatusPage(page, 10, sessionUserInfo.getUserId());
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS); 
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		userInfoService.updateUserReadAtTime(sessionUserInfo.getUserId());
		messageService.clearNewMessage(CacheConstant.MESSAGE_AT_NEW_COUNT, userInfo.getUserId());
		
		return SUCCESS;
	}
	
	public String atMeComment(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		commentsWithStatusPage = statusService.findAtMeCommentPage(page, 10, sessionUserInfo.getUserId());
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS); 
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		userInfoService.updateUserReadAtTime(sessionUserInfo.getUserId());
		messageService.clearNewMessage(CacheConstant.MESSAGE_AT_NEW_COUNT, userInfo.getUserId());
		
		return SUCCESS;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public List<StatusFace> getFaces() {
		return faces;
	}

	public void setFaces(List<StatusFace> faces) {
		this.faces = faces;
	}

	public PaginationDTO<StatusInfoWithFwdSrc> getStatusPage() {
		return statusPage;
	}

	public void setStatusPage(PaginationDTO<StatusInfoWithFwdSrc> statusPage) {
		this.statusPage = statusPage;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public PaginationDTO<CommentInfo> getCommentsPage() {
		return commentsPage;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusFwdSrc(StatusInfoWithFwdSrc statusFwdSrc) {
		this.statusFwdSrc = statusFwdSrc;
	}

	public StatusInfoWithFwdSrc getStatusFwdSrc() {
		return statusFwdSrc;
	}

	public int getPageNum() {
		return pageNum;
	}

	public StatusInfo getStatus() {
		return status;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public boolean isShowPage() {
		return isShowPage;
	}

	public void setShowPage(boolean isShowPage) {
		this.isShowPage = isShowPage;
	}

	public PaginationDTO<CommentInfoWithStatus> getCommentsWithStatusPage() {
		return commentsWithStatusPage;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public List<GroupInfo> getGroupList() {
		return groupList;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public List<StatusInfoWithFwdSrc> getStatusList() {
		return statusList;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

}
