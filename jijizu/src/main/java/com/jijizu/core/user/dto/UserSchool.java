package com.jijizu.core.user.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.InitData;

public class UserSchool implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1460526256772817825L;
	
	private Long id;
	
	private Long schoolId;
	
	private String department;
	
	private String type;
	
	private String startYear;
	
	private Long userId;
	
	private Date createDate;
	
	private String schoolName;
	
	private List<UserInfo> classmates;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getType() {
		return type;
	}
	
	public String getTypeStr(){
		if(StringUtil.isNotNullOrEmpty(type)){
			return InitData.sysDictCodeAndNameRelationShip.get(type);
		}
		return null;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartYear() {
		return startYear;
	}
	

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public List<UserInfo> getClassmates() {
		return classmates;
	}

	public void setClassmates(List<UserInfo> classmates) {
		this.classmates = classmates;
	}

}
