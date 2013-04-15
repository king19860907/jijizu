package com.jijizu.web.group.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.album.dto.AlbumInfo;
import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.album.service.AlbumService;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.group.dto.GroupInfo;
import com.jijizu.core.group.dto.GroupUsers;
import com.jijizu.core.group.service.GroupService;
import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.status.service.StatusService;
import com.jijizu.core.user.dto.ChildInfo;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class GroupAction extends BaseAction{
	
	private UserInfoService userInfoService;
	
	private GroupService groupService;
	
	private StatusService statusService;
	
	private AlbumService albumService;
	
	private UserInfo userInfo;
	
	private Long groupId;
	
	private GroupInfo groupInfo;
	
	private PaginationDTO<GroupInfo> groupPage;
	
	private PaginationDTO<GroupUsers> userPage;

	private List<GroupInfo> groupList;
	
	private long page;
	
	private UserInfo createUser;
	
	private List<StatusFace> faces;
	
	private PaginationDTO<StatusInfoWithFwdSrc> statusPage;
	
	private PaginationDTO<AlbumInfo> albumPage;
	
	private PaginationDTO<PhotoInfo> photoPage;
	
	private Long albumId;
	
	private PhotoInfo nextPhotoInfo;
	
	private PhotoInfo prePhotoInfo;
	
	private PhotoInfo photoInfo;
	
	private Long photoId;
	
	private Long userId;

	private List<ChildInfo> childList;
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5830228979781753658L;
	
	public String photoDetail(){
		if(photoId == null || groupId == null){
			return INDEX;
		}
		
		nextPhotoInfo = albumService.getGroupNextPhotoById(photoId, groupId);
		prePhotoInfo = albumService.getGroupPrePhotoById(photoId, groupId);
		photoInfo = albumService.getPhotoInfoById(photoId);
		groupInfo = groupService.getGroupInfoById(groupId);
		
		return SUCCESS;
	}
	
	public String photo(){
		if(groupId == null){
			return INDEX;
		}
		photoPage = albumService.findPhotoByGroupIdPage(page, 16, groupId);
		groupInfo = groupService.getGroupInfoById(groupId);
		return SUCCESS;
	}
	
	public String getPhoto(){
		
		if(albumId != null){
			photoPage = albumService.findPhotoPage(page, 12, albumId);
		}
		
		return SUCCESS;
	}
	
	public String getUserAlbum(){

		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null){
			albumPage = albumService.findUserAlbumPage(page, 3, sessionUserInfo.getUserId());
		}
		
		return SUCCESS;
	}
	
	public String index(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null){
			userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		}
		
		groupPage = groupService.findGroupInfoPage(page, 10);
		
		return SUCCESS;
	}
	
	public String create(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		return SUCCESS;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-31   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public String view(){
		
		if(groupId == null){
			return INDEX;
		}
		
		Long uid = null;
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo != null){
			userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
			uid = userInfo.getUserId();
		}
		
		groupInfo = groupService.getGroupInfoById(groupId,uid,true);
		
		if(groupInfo == null){
			return INDEX;
		}
		
		createUser = userInfoService.getUserInfoById(groupInfo.getUserId());
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS); 
		statusPage = groupService.findGroupStatusPage(groupId, page, 20);
		
		childList = userInfoService.findChildInfoByUserId(uid);
		
		photoPage = albumService.findPhotoByGroupIdPage(0, 6, groupId);
		
		return SUCCESS;
	}
	
	public String myCreate(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}else{
			userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		}
		
		groupPage=groupService.findMyCreateGroupPage(page, 10, sessionUserInfo.getUserId());
		
		return SUCCESS;
		
	}
	
	public String manageUser(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		userPage = groupService.findGroupUsersAllPage(groupId, page, 10);
		
		return SUCCESS;
	}
	
	public String myEnter(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}else{
			userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		}
		
		groupPage=groupService.findMyEnterGroupPage(page, 10, sessionUserInfo.getUserId());
		
		return SUCCESS;
	}
	
	public String findGroupUsers(){
		
		if(groupId == null){
			return NONE;
		}
		
		userPage = groupService.findGroupUsersPage(groupId, page, 12);
		
		return SUCCESS;
	}
	
	public String findRecommendGroup(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		groupList = groupService.findRecommendGroup(sessionUserInfo, 3);
		
		return SUCCESS;
	}
	
	public String findHotGrup(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		groupList = groupService.findHotGroup(sessionUserInfo, 6);
		
		return SUCCESS;
	}
	
	public String update(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		if(groupId == null){
			return INDEX;
		}
		
		groupInfo = groupService.getGroupInfoById(groupId);
		
		if(groupInfo == null){
			return INDEX;
		}
		
		return SUCCESS;
	}
	
	
	public String findUserGroup(){
		
		groupPage = groupService.findMyCreateGroupPage(page, 3, userId);
		
		return SUCCESS;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public GroupInfo getGroupInfo() {
		return groupInfo;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public PaginationDTO<GroupInfo> getGroupPage() {
		return groupPage;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public UserInfo getCreateUser() {
		return createUser;
	}

	public PaginationDTO<GroupUsers> getUserPage() {
		return userPage;
	}

	public List<GroupInfo> getGroupList() {
		return groupList;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public List<StatusFace> getFaces() {
		return faces;
	}

	public PaginationDTO<StatusInfoWithFwdSrc> getStatusPage() {
		return statusPage;
	}

	public PaginationDTO<AlbumInfo> getAlbumPage() {
		return albumPage;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	public PaginationDTO<PhotoInfo> getPhotoPage() {
		return photoPage;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public PhotoInfo getNextPhotoInfo() {
		return nextPhotoInfo;
	}

	public PhotoInfo getPrePhotoInfo() {
		return prePhotoInfo;
	}

	public PhotoInfo getPhotoInfo() {
		return photoInfo;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<ChildInfo> getChildList() {
		return childList;
	}

}
