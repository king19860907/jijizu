package com.jijizu.core.status.dto;

import java.io.Serializable;

public class StatusFace implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6975958477831854962L;
	
	private Long faceId;
	
	private String name;
	
	private String imgUrl;
	
	private String faceType;
	
	private String defaultContent;
	
	private String cancelFlag;

	public Long getFaceId() {
		return faceId;
	}

	public void setFaceId(Long faceId) {
		this.faceId = faceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getFaceType() {
		return faceType;
	}

	public void setFaceType(String faceType) {
		this.faceType = faceType;
	}

	public String getDefaultContent() {
		return defaultContent;
	}

	public void setDefaultContent(String defaultContent) {
		this.defaultContent = defaultContent;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

}
