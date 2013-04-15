package com.jijizu.core.user.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.DateUtil;

public class ChildInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5857036761736021457L;
	
	private Long childId;
	
	private String name;
	
	private String nickName;
	
	private String gender;
	
	private Date birthday;
	
	private Long schoolId;
	
	private String department;
	
	private Date createDate;
	
	private Long cancelFlag;
	
	private Long userId;
	
	private SchoolInfo schoolInfo;

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Integer getYear(){
		if(birthday != null){
			return DateUtil.getYearByDate(birthday);
		}
		return null;
	}
	
	public Integer getMonth(){
		if(birthday != null){
			return DateUtil.getMonthByDate(birthday);
		}
		return null;
	}
	
	public Integer getDay(){
		if(birthday != null){
			return DateUtil.getDayByDate(birthday);
		}
		return null;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Long cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public SchoolInfo getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(SchoolInfo schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

}
