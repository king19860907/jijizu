package com.jijizu.web.follow.action.json;

import java.io.Serializable;

public class AtFriendsJsonResult implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1151552799617605653L;

	public AtFriendsJsonResult(String name, String headImgUrl) {
		super();
		this.name = name;
		this.headImgUrl = headImgUrl;
	}

	private String name;
	
	private String headImgUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
}
