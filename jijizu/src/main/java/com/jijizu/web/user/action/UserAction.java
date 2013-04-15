package com.jijizu.web.user.action;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.base.util.IdentifyingCode;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.album.service.AlbumService;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.dto.SysDataDic;
import com.jijizu.core.follow.service.FollowService;
import com.jijizu.core.search.dto.UserSearch;
import com.jijizu.core.service.SysDataDicService;
import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.status.service.StatusService;
import com.jijizu.core.user.dto.ChildInfo;
import com.jijizu.core.user.dto.RecommendUser;
import com.jijizu.core.user.dto.SchoolInfo;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.dto.UserJob;
import com.jijizu.core.user.dto.UserSchool;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2853432826456830563L;
	
	private SysDataDicService sysDataDicService;
	
	private UserInfoService userInfoService;
	
	private FollowService followService;
	
	private StatusService statusService;
	
	private AlbumService albumService;
	
	private List<SysDataDic> familyStatus;
	
	private UserInfo userInfo;
	
	private Long userId;
	
	private Long followUserId;
	
	private PaginationDTO<UserInfo> commonFriendsPage;
	
	private PaginationDTO<UserInfo> friendsPage;
	
	private long page;
	
	private long pageSize;
	
	private boolean isFollowEachOther;
	
	private String type;
	
	private String name;
	
	private PaginationDTO<StatusInfoWithFwdSrc> statusPage;
	
	private List<StatusFace> faces;
	
	private String img = "";
	
	private int x;
	private int y;
	private int w;
	private int h;
	private float t;
	
	private List<UserJob> jobs;
	
	private List<SchoolInfo> schoolList;
	
	private String areaName;
	
	private String schoolName;
	
	private List<SysDataDic> schoolTypes;
	
	private String firstLetter;
	
	private List<UserSchool> userSchoolList;
	
	private List<ChildInfo> children;
	
	private PaginationDTO<PhotoInfo> photoPage;
	
	private List<RecommendUser> userList;
	
	private PaginationDTO<UserInfo> userPage;
	
	private PaginationDTO<UserInfo> otherUserPage;
	
	private String random;
	
	private String logName;
	
	private String uid;
	
	public String editPwd(){
		
		if(!StringUtil.isNotNullOrEmpty(random) || 
				!StringUtil.isNotNullOrEmpty(logName) ||
				!StringUtil.isNotNullOrEmpty(uid)){
			return INDEX;
		}
		
		return SUCCESS;
	}
	
	public String forget(){
		
		return SUCCESS;
	}
	
	public String recommend(){

		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		UserSearch us = new UserSearch();
		us.setCity(Long.parseLong(sessionUserInfo.getCity()));
		us.setUserId(sessionUserInfo.getUserId());
		userPage = userInfoService.findUserListPage(us,0, 16);
		otherUserPage = userInfoService.findUserOrderByStatusNumPage(sessionUserInfo.getUserId(), 0, 16);
		
		return SUCCESS;
		
	}
	
	public String recommendPerson(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null){
			userList = userInfoService.findRecommendUser(sessionUserInfo.getUserId(), 4L);
		}
		
		return SUCCESS;
	}
	
	public String sign(){
		
		familyStatus = sysDataDicService.findSysDataDicByParentId(SysDataDicConstant.FAMILY_STATUS, CommonConstant.CANCEL_FLAG_NO);
		
		return SUCCESS;
	}
	
	public String getUserCard(){
		
		if(userId != null){
			UserInfo sessionUserInfo = getSessionUserInfo();
			userInfo = userInfoService.getUserInfoById(userId);
			if(sessionUserInfo != null && sessionUserInfo.getUserId().longValue() != userId.longValue()){
				commonFriendsPage = followService.findCommonFriendsPage(page, 8, sessionUserInfo.getUserId(), userId);
				isFollowEachOther = followService.isFollowEachOther(sessionUserInfo.getUserId(), userId);
			}
			return SUCCESS;
		}
		
		return NONE;
	}
	
	public String friends(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null && CommonConstant.FRIENDS_TYPE_COMMON_FRIENDS.equals(type)){
			return NOT_LOGIN;
		}
		if(userId == null){
			return INDEX;
		}
		userInfo = userInfoService.getUserInfoById(userId);
		if(userInfo == null){
			return INDEX;
		}
		
		friendsPage = (CommonConstant.FRIENDS_TYPE_COMMON_FRIENDS.equals(type) && userId != null) ?
						followService.findCommonFriendsPage(page, 12, sessionUserInfo.getUserId(), userId):
							followService.findFriendsPage(page, 12, userId, name);
		
		return SUCCESS;
	}
	
	public String info(){
		
		if(userId == null){
			return INDEX;
		}
		
		userInfo = userInfoService.getUserInfoById(userId);
		if(userInfo == null){
			return INDEX;
		}
		
		userSchoolList = userInfoService.findUserSchool(userId, 10L);
		
		jobs = userInfoService.findUserJob(userId, 8L);
		
		children = userInfoService.findChildInfoByUserId(userId);
		
		return SUCCESS;
	}
	
	public String view(){
		
		if(userId == null){
			return INDEX;
		}
		
		userInfo = userInfoService.getUserInfoById(userId,true);
		if(userInfo == null){
			return INDEX;
		}
		statusPage = statusService.findUserStatusPage(page, 20, userId);
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS);
		photoPage = albumService.findPhotoByUserIdPage(0, 9, userId);
		
		return SUCCESS;
	}
	
	public String base(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		schoolTypes = sysDataDicService.findSysDataDicByParentId(SysDataDicConstant.SCHOOL_TYPE, CommonConstant.CANCEL_FLAG_NO);
		
		return SUCCESS;
	}
	
	public String photo(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		return SUCCESS;
	}
	
	public String updateHeadImage(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		userInfoService.updateHeadImage(sessionUserInfo, img, (int)(x/t), (int)(y/t), (int)(h/t), (int)(w/t));
		
		if(StringUtil.isNotNullOrEmpty(target)){
			return TARGET;
		}
		
		return SUCCESS;
	}
	
	public String photoCut(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		return SUCCESS;
	}
	
	public String job(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		return SUCCESS;
	}
	
	public String findUserJob(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NONE;
		}
		
		jobs = userInfoService.findUserJob(sessionUserInfo.getUserId(), 8L);
		
		return SUCCESS;
	}
	
	public String edu(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		schoolTypes = sysDataDicService.findSysDataDicByParentId(SysDataDicConstant.SCHOOL_TYPE, CommonConstant.CANCEL_FLAG_NO);
		
		return SUCCESS;
	}
	
	public String findSchoolInfo(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NONE;
		}
		
		schoolList = userInfoService.findSchoolInfo(type, areaName, schoolName,firstLetter);
		
		return SUCCESS;
	}
	
	public String findUserSchool(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NONE;
		}
		
		userSchoolList = userInfoService.findUserSchool(sessionUserInfo.getUserId(), 10L);
		
		return SUCCESS;
	}
	
	public String findChildInfo(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null){
			return NONE;
		}
		
		children = userInfoService.findChildInfoByUserId(sessionUserInfo.getUserId());
		
		return SUCCESS;
		
	}
	
	public boolean canEdit(UserInfo sessionUserInfo,Long userId){
		boolean canEdit = false;
		if(sessionUserInfo != null && userId != null){
			if(sessionUserInfo.getUserId().longValue() == userId.longValue()){
				canEdit = true;
			}
		}
		
		return canEdit;
	}
	
	public void setSysDataDicService(SysDataDicService sysDataDicService) {
		this.sysDataDicService = sysDataDicService;
	}

	public List<SysDataDic> getFamilyStatus() {
		return familyStatus;
	}

	public void setFamilyStatus(List<SysDataDic> familyStatus) {
		this.familyStatus = familyStatus;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public PaginationDTO<UserInfo> getCommonFriendsPage() {
		return commonFriendsPage;
	}

	public void setFollowService(FollowService followService) {
		this.followService = followService;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public boolean isFollowEachOther() {
		return isFollowEachOther;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(Long followUserId) {
		this.followUserId = followUserId;
	}

	public PaginationDTO<UserInfo> getFriendsPage() {
		return friendsPage;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public PaginationDTO<StatusInfoWithFwdSrc> getStatusPage() {
		return statusPage;
	}

	public List<StatusFace> getFaces() {
		return faces;
	}

	public String getImg() {
		return img;
	}
	
	public String getOriginalImgUrl(){
		if(StringUtil.isNotNullOrEmpty(img)){
			String imgNameWithoutExt = img.substring(0,
					img.lastIndexOf('.'));

			return imgNameWithoutExt + "_o"
					+ img.substring(img.lastIndexOf('.'));
		}
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public float getT() {
		return t;
	}

	public void setT(float t) {
		this.t = t;
	}

	public List<UserJob> getJobs() {
		return jobs;
	}

	public List<SchoolInfo> getSchoolList() {
		return schoolList;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public List<SysDataDic> getSchoolTypes() {
		return schoolTypes;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public List<UserSchool> getUserSchoolList() {
		return userSchoolList;
	}

	public List<ChildInfo> getChildren() {
		return children;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	public PaginationDTO<PhotoInfo> getPhotoPage() {
		return photoPage;
	}

	public List<RecommendUser> getUserList() {
		return userList;
	}

	public PaginationDTO<UserInfo> getUserPage() {
		return userPage;
	}

	public PaginationDTO<UserInfo> getOtherUserPage() {
		return otherUserPage;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
