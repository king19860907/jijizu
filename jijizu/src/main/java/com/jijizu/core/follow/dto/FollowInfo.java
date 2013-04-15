package com.jijizu.core.follow.dto;

import java.io.Serializable;
import java.util.Date;

public class FollowInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3904008094221252577L;
	
	private Long id;
	
	private Long userId;
	
	private Long followUserId;
	
	private Date postTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(Long followUserId) {
		this.followUserId = followUserId;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	
}
