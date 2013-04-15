package com.jijizu.core.group.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.user.dto.UserInfo;

public class GroupUsers implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4940401213426783606L;
	
	private Long id;
	
	private Long userId;
	
	private Long groupId;
	
	private Date createDate;
	
	private String joinStatus;
	
	private String name;
	
	private String childName;
	
	private String mobile;
	
	private String address;
	
	private String headImgUrl;
	
	private Long friendNum;
	
	private Long statusNum;

	private Long adultNum;

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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getJoinStatus() {
		return joinStatus;
	}
	
	public void setJoinStatus(String joinStatus) {
		this.joinStatus = joinStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHeadImgUrl() {
		return getHeadImgUrl(CommonConstant.HEAD_IMG_SIZE_50);
	}
	
	public String getHeadImgUrl(String imgSize){
		return JijizuUtil.getHeadImgUrl(headImgUrl, imgSize);
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Long getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(Long friendNum) {
		this.friendNum = friendNum;
	}

	public Long getStatusNum() {
		return statusNum;
	}

	public void setStatusNum(Long statusNum) {
		this.statusNum = statusNum;
	}

	public Long getAdultNum() {
		return adultNum;
}

	public void setAdultNum(Long adultNum) {
		this.adultNum = adultNum;
	}

}
