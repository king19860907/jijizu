package com.jijizu.core.user.dto;

import java.io.Serializable;

public class PostArea implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7496322773924411379L;

	private Long areaId;
	
	private String areaName;
	
	private Long seq;
	
    private Long fatherId;
    
    private String classType;

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
	
}
