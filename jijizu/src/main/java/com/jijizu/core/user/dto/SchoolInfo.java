package com.jijizu.core.user.dto;

import java.io.Serializable;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.InitData;

public class SchoolInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2100050403960647000L;
	
	private Long schoolId;
	
	private String schoolName;
	
	private String type;
	
	private String areaLevel;
	
	private String areaName;

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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

	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}
