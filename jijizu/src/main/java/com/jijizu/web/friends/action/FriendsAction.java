package com.jijizu.web.friends.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.follow.service.FollowService;
import com.jijizu.core.user.dto.ChildInfo;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.dto.UserJob;
import com.jijizu.core.user.dto.UserSchool;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

public class FriendsAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6011241906040663780L;
	
	private UserInfoService userInfoService;
	
	private FollowService followService;
	
	private UserInfo userInfo;
	
	private long page;
	
	private String name;
	
	private PaginationDTO<UserInfo> friendsPage;
	
	private PaginationDTO<UserInfo> companyUserPage;
	
	private PaginationDTO<UserInfo> livingUserPage;
	
	private PaginationDTO<UserInfo> schoolUserPage;
	
	private PaginationDTO<UserInfo> childUserPage;
	
	private List<UserJob> jobs;
	
	private List<UserSchool> schools;
	
	private List<ChildInfo> children;
	
	private Long companyId;
	
	private Long livingCommunityId;
	
	private Long schoolId;
	
	public String fndUserByChildSchool(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null){
			childUserPage = userInfoService.findUserByChildSchoolPage(sessionUserInfo.getUserId(), schoolId, page, 6);
			children = userInfoService.findChildInfoByUserId(sessionUserInfo.getUserId());
			removeRepeatSchool();
		}
		
		return SUCCESS;
	}
	
	public String findUserBySchool(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null){
			schoolUserPage = userInfoService.findUserBySchoolPage(sessionUserInfo.getUserId(), schoolId, page, 6);
			schools = userInfoService.findUserSchool(sessionUserInfo.getUserId(), 3l,false);
		}
		
		return SUCCESS;
	}
	
	public String findUserByLivingCommunity(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo != null){
			userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
			livingUserPage = userInfoService.findUserByLivingCommunityPage(sessionUserInfo.getUserId(), livingCommunityId,Long.parseLong(sessionUserInfo.getDistrict()), page, 6);
		}
		return SUCCESS;
	}
	
	public String findUserByCompany(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null){
			companyUserPage = userInfoService.findUserByCompanyPage(sessionUserInfo.getUserId(),companyId, page, 6);
			jobs = userInfoService.findUserJob(sessionUserInfo.getUserId(), 3L,false);
		}
		
		return SUCCESS;
	}
	
	public String search(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return INDEX;
		}
		
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		
		jobs = userInfoService.findUserJob(sessionUserInfo.getUserId(), 3L,false);
		
		if(jobs != null && jobs.size() > 0){
			companyId = jobs.get(0).getCompanyId();
			companyUserPage = userInfoService.findUserByCompanyPage(sessionUserInfo.getUserId(),companyId, 0, 6);
		}
		
		if(userInfo.getLivingCommunity() != null){
			livingCommunityId = userInfo.getLivingCommunity().getId();
			livingUserPage = userInfoService.findUserByLivingCommunityPage(sessionUserInfo.getUserId(), livingCommunityId,Long.parseLong(sessionUserInfo.getDistrict()), 0, 6);
		}
		
		schools = userInfoService.findUserSchool(sessionUserInfo.getUserId(), 3l,false);
		
		if(schools != null && schools.size() > 0){
			schoolId = schools.get(0).getSchoolId();
			schoolUserPage = userInfoService.findUserBySchoolPage(sessionUserInfo.getUserId(), schoolId, 0, 6);
		}
		
		children = userInfoService.findChildInfoByUserId(sessionUserInfo.getUserId());
		if(children != null && children.size() > 0){
			schoolId = children.get(0).getSchoolId();
			removeRepeatSchool();
			childUserPage = userInfoService.findUserByChildSchoolPage(sessionUserInfo.getUserId(), schoolId, 0, 6);
		}
		
		return SUCCESS;
	}

	/**   
	 *******************************************************************************
	 * @function : 去除孩子间重复的学校
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-13   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void removeRepeatSchool() {
		Map<Long,String> map = new HashMap<Long,String>();
		Iterator<ChildInfo> it = children.iterator();
		while(it.hasNext()){
			ChildInfo child = it.next();
			if(child.getSchoolInfo() != null){
				if(map.get(child.getSchoolId()) == null){
					map.put(child.getSchoolInfo().getSchoolId(), child.getSchoolInfo().getSchoolName());
				}else{
					it.remove();
				}
			}
		}
	}
	
	public String manage(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		friendsPage	 = followService.findFriendsPage(page, 15, sessionUserInfo.getUserId(), name);
		
		return SUCCESS;
	}
	
	public String apply(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}
		
		userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		friendsPage	 = followService.findSendApplyFriendsPage(page, 15, sessionUserInfo.getUserId());
		
		return SUCCESS;
		
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public UserInfo getUserInfo() {
		return userInfo;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PaginationDTO<UserInfo> getFriendsPage() {
		return friendsPage;
	}

	public List<UserJob> getJobs() {
		return jobs;
	}

	public PaginationDTO<UserInfo> getCompanyUserPage() {
		return companyUserPage;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getLivingCommunityId() {
		return livingCommunityId;
	}

	public void setLivingCommunityId(Long livingCommunityId) {
		this.livingCommunityId = livingCommunityId;
	}

	public PaginationDTO<UserInfo> getLivingUserPage() {
		return livingUserPage;
	}

	public List<UserSchool> getSchools() {
		return schools;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public PaginationDTO<UserInfo> getSchoolUserPage() {
		return schoolUserPage;
	}

	public List<ChildInfo> getChildren() {
		return children;
	}

	public PaginationDTO<UserInfo> getChildUserPage() {
		return childUserPage;
	}

}
