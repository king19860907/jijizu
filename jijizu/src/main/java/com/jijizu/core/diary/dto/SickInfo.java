package com.jijizu.core.diary.dto;

import java.io.Serializable;
import java.util.Date;

public class SickInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -729865439352991246L;
	
	private Long sickId;
	
	private	String sickName;
	
	private String sickType;
	
	private Long relateSickId;
	
	private Date createDate;
	
	private Long cancelFlag;

	public Long getSickId() {
		return sickId;
	}

	public void setSickId(Long sickId) {
		this.sickId = sickId;
	}

	public String getSickName() {
		return sickName;
	}

	public void setSickName(String sickName) {
		this.sickName = sickName;
	}

	public String getSickType() {
		return sickType;
	}

	public void setSickType(String sickType) {
		this.sickType = sickType;
	}

	public Long getRelateSickId() {
		return relateSickId;
	}

	public void setRelateSickId(Long relateSickId) {
		this.relateSickId = relateSickId;
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

}
