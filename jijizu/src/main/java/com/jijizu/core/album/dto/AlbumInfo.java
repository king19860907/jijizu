package com.jijizu.core.album.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.user.dto.UserInfo;

public class AlbumInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4914776617078332858L;
	
	private Long albumId;
	
	private String name;
	
	private String content;
	
	private Date createDate;
	
	private Date updateDate;
	
	private Long cancelFlag;
	
	private Long groupId;
	
	private Long defaultPhotoId;
	
	private String defaultPhotoImgUrl;
	
	private Long userId;
	
	private Long createUserId;
	
	private Long photoNum;

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getName() {
		return name;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getDefaultPhotoId() {
		return defaultPhotoId;
	}

	public void setDefaultPhotoId(Long defaultPhotoId) {
		this.defaultPhotoId = defaultPhotoId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getPhotoNum() {
		return photoNum;
	}

	public void setPhotoNum(Long photoNum) {
		this.photoNum = photoNum;
	}

	public String getDefaultPhotoImgUrl() {
		if(!StringUtil.isNotNullOrEmpty(defaultPhotoImgUrl)){
			defaultPhotoImgUrl = JijizuUtil.getAbsolutePath()+CommonConstant.DEFAULT_HEAD_IMG_URL+CommonConstant.HEAD_IMG_SIZE_150+".jpg";
			return defaultPhotoImgUrl;
		}else{
			String imgNameWithoutExt = defaultPhotoImgUrl.substring(0,
					defaultPhotoImgUrl.lastIndexOf('.'));

			return imgNameWithoutExt + "_t"
					+ defaultPhotoImgUrl.substring(defaultPhotoImgUrl.lastIndexOf('.'));
		}
	}

	public void setDefaultPhotoImgUrl(String defaultPhotoImgUrl) {
		this.defaultPhotoImgUrl = defaultPhotoImgUrl;
	}
	
	public boolean canEdit(UserInfo sessionUserInfo){
		boolean canEdit = false;
		if(!isDefaultAlbum() && isCreaterUser(sessionUserInfo)){
				canEdit = true;
		}
		return canEdit;
	}
	
	public boolean canDelete(UserInfo sessionUserInfo){
		boolean canDelete = false;
		if(!isDefaultAlbum() && isCreaterUser(sessionUserInfo)){
			canDelete = true;
		}
		return canDelete;
	}
	
	public boolean canUpload(UserInfo sessionUserInfo){
		if(isCreaterUser(sessionUserInfo)){
			return true;
		}
		return false;
	}
	
	public boolean canEditBatch(UserInfo sessionUserInfo){
		if(isCreaterUser(sessionUserInfo)){
			return true;
		}
		return false;
	}
	
	private boolean isDefaultAlbum(){
		if(CommonConstant.ALBUM_NAME_DEFAULT.equals(name) || CommonConstant.ALBUM_NAME_STATUS.equals(name)){
			return true;
		}
		return false;
	}
	
	private boolean isCreaterUser(UserInfo sessionUserInfo){
		if(sessionUserInfo != null && createUserId != null && userId != null){
			if(sessionUserInfo.getUserId().longValue() == createUserId.longValue()
					|| sessionUserInfo.getUserId().longValue() == userId.longValue()){
				return true;
			}
		}
		return false;
	}
	
	
}
