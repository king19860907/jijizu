package com.jijizu.core.user.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.InitData;

public class UserJob implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9040634341322340762L;
	
	private Long jobId;
	
	private String startYear;
	
	private String endYear;
	
	private Long companyId;
	
	private String companyName;
	
	private Long userId;
	
	private Date createDate;
	
	private Long cancelFlag;
	
	private String department;
	
	private String province;
	
	private String city;
	
	private List<UserInfo> colleagues;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

	public Long getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Long cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProvince() {
		return province;
	}
	
	public String getProviceStr(){
		if(StringUtil.isNotNullOrEmpty(province)){
			PostArea postArea = InitData.areaProvince.get(Long.parseLong(province));
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}
	
	public String getCityStr(){
		if(StringUtil.isNotNullOrEmpty(city)){
			PostArea postArea = InitData.areaCity.get(Long.parseLong(city));
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<UserInfo> getColleagues() {
		return colleagues;
	}

	public void setColleagues(List<UserInfo> colleagues) {
		this.colleagues = colleagues;
	}

}
