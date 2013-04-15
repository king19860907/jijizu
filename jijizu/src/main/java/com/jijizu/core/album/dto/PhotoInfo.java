package com.jijizu.core.album.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.user.dto.UserInfo;

public class PhotoInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6561065799314634111L;
	
	private Long photoId;
	
	private String name;
	
	private String content;
	
	private Long albumId;
	
	private Date createDate;
	
	private Date updateDate;
	
	private Long cancelFlag;
	
	private String imgUrl;
	
	private Long statusId;
	
	private Long groupId;
	
	private Long createUserId;
	
	private StatusInfoWithFwdSrc status;

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public String getName() {
		if(!StringUtil.isNotNullOrEmpty(name) && StringUtil.isNotNullOrEmpty(imgUrl)){
			return imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
		}
		return name;
	}
	
	public String getSmallImgUrl(){
		if(StringUtil.isNotNullOrEmpty(imgUrl)){
			String imgNameWithoutExt = imgUrl.substring(0,
					imgUrl.lastIndexOf('.'));

			return imgNameWithoutExt + "_t"
					+ imgUrl.substring(imgUrl.lastIndexOf('.'));
		}
		return imgUrl;
	}
	
	public String getOriginalImgUrl(){
		if(StringUtil.isNotNullOrEmpty(imgUrl)){
			String imgNameWithoutExt = imgUrl.substring(0,
					imgUrl.lastIndexOf('.'));

			return imgNameWithoutExt + "_o"
					+ imgUrl.substring(imgUrl.lastIndexOf('.'));
		}
		return imgUrl;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Long cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
	public boolean isOverOneDay(){
		boolean isOverOneDay = false;
		if(createDate != null){
			long time = createDate.getTime();
			long nowTimes = new Date().getTime();
			if((nowTimes - time)>24*60*60*1000){
				isOverOneDay = true;
			}
		}
		return isOverOneDay;

	}
	
	public boolean canEdit(UserInfo sessionUserInfo){
		boolean canEdit = false;
		if(isCreaterUser(sessionUserInfo)){
				canEdit = true;
		}
		return canEdit;
	}
	
	public boolean canDelete(UserInfo sessionUserInfo){
		boolean canDelete = false;
		if(isCreaterUser(sessionUserInfo)){
			canDelete = true;
		}
		return canDelete;
	}
	
	
	private boolean isCreaterUser(UserInfo sessionUserInfo){
		if(sessionUserInfo != null && createUserId != null ){
			if(sessionUserInfo.getUserId().longValue() == createUserId.longValue()){
				return true;
			}
		}
		return false;
	}

	public StatusInfoWithFwdSrc getStatus() {
		return status;
	}

	public void setStatus(StatusInfoWithFwdSrc status) {
		this.status = status;
	}
	
}
