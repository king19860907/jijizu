package com.jijizu.core.search.dto;

import java.io.Serializable;

public class UserSearch implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6077344251821787010L;
	
	public UserSearch(){}
	
	public UserSearch(String name, Long companyId, Long schoolId,
			String nickName, String schoolName, String companyName,
			Long province, Long city, Long district,String gender,
			Long ageType,String childSchoolName,Long userId) {
		super();
		this.name = name;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.nickName = nickName;
		this.schoolName = schoolName;
		this.companyName = companyName;
		this.province = province;
		this.city = city;
		this.district = district;
		this.gender = gender;
		this.childSchoolName = childSchoolName;
		this.userId = userId;
		this.setAgeType(ageType);
	}

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
	
	private Long ageType;
	
	private Long minAge;
	
	private Long maxAge;
	
	private Long userId;
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
		if(ageType != null){
			if(ageType.longValue() == 1){
				this.maxAge = 18L;
			}else if(ageType.longValue() == 2){
				this.minAge = 19L;
				this.maxAge = 22L;
			}else if(ageType.longValue() == 3){
				this.minAge = 23L;
				this.maxAge = 29L;
			}else if(ageType.longValue() == 4){
				this.minAge = 30L;
				this.maxAge = 39L;
			}else if(ageType.longValue() == 5){
				this.minAge = 40L;
			}
		}
	}

	public Long getMinAge() {
		return minAge;
	}

	public void setMinAge(Long minAge) {
		this.minAge = minAge;
	}

	public Long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Long maxAge) {
		this.maxAge = maxAge;
	}

	public String getChildSchoolName() {
		return childSchoolName;
	}

	public void setChildSchoolName(String childSchoolName) {
		this.childSchoolName = childSchoolName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
