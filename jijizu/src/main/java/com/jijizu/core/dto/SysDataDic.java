package com.jijizu.core.dto;

import java.io.Serializable;

public class SysDataDic implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1166097341120963318L;
	
	private String dictCode;
	
	private String name;
	
	private String parentId;
	
	private String description;
	
	private String itemIndex;
	
	private String cancelFlag;

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(String itemIndex) {
		this.itemIndex = itemIndex;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

}
