package com.jijizu.core.status.dto;

import java.io.Serializable;

import com.jijizu.base.util.JijizuUtil;

public class StatusInfoWithFwdSrc extends StatusInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1272970900884601768L;
	
	private StatusInfo forwardSourceStatus;

	public StatusInfo getForwardSourceStatus() {
		return forwardSourceStatus;
	}

	public void setForwardSourceStatus(StatusInfo forwardSourceStatus) {
		this.forwardSourceStatus = forwardSourceStatus;
	}
	
	public String getForwardContent(){
		
		if(forwardSourceStatus != null){
			return "//@"+this.getName()+"("+this.getNickName()+")："+JijizuUtil.removeHtmlTag(content);
		}
		return "";
	}
	
}
