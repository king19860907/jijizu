package com.jijizu.core.user.dto;

import java.io.Serializable;
import java.util.Date;

public class LivingCommunity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8394314038966253913L;

	private Long id;
	
	private String name;
	
	private String areaName;
	
	private String areaLevel;
	
	private Date createDate;
	
	private Long cancelFlag;
	
	private String areaNameFather;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
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

	public String getAreaNameFather() {
		return areaNameFather;
	}

	public void setAreaNameFather(String areaNameFather) {
		this.areaNameFather = areaNameFather;
	}
	
}
