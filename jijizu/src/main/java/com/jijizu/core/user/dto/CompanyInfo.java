package com.jijizu.core.user.dto;

import java.io.Serializable;
import java.util.Date;

public class CompanyInfo implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 800524091047240830L;

	private Long companyId;
	
	private String companyName;
	
	private Date createDate;
	
	private Long cancelFlag;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
