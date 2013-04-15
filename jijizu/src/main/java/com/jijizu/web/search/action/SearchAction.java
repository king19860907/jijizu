package com.jijizu.web.search.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.search.dto.UserSearch;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class SearchAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2463473898225180912L;
	
	private UserInfoService userInfoService;
	
	private UserInfo userInfo;
	
	private PaginationDTO<UserInfo> userPage;
	
	private String name;
	
	private Long companyId;
	
	private Long schoolId;
	
	private String nickName;
	
	private String schoolName;
	
	private String childSchoolName;
	
	private String companyName;
	
	private Long province;
	
	private Long city;
	
	private Long district;
	
	private String gender;
	
	private long page;
	
	private Long ageType;
	 
	public String user(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo == null){
			return NOT_LOGIN;
		}else{
			userInfo = userInfoService.getUserInfoById(sessionUserInfo.getUserId());
		}
		
		userPage = userInfoService.findUserListPage(new UserSearch(name, companyId, schoolId, nickName, schoolName, companyName, province, city, district,gender,ageType,childSchoolName,sessionUserInfo.getUserId()), page, 10L);
		
		return SUCCESS;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public PaginationDTO<UserInfo> getUserPage() {
		return userPage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getAgeType() {
		return ageType;
	}

	public void setAgeType(Long ageType) {
		this.ageType = ageType;
	}

	public String getChildSchoolName() {
		return childSchoolName;
	}

	public void setChildSchoolName(String childSchoolName) {
		this.childSchoolName = childSchoolName;
	}
	
}
