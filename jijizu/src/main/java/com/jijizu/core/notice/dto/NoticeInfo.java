package com.jijizu.core.notice.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.core.constant.CommonConstant;

public class NoticeInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3262401452628824367L;
	
	private Long noticeId;
	
	private Long fromUserId;
	
	private String fromUserName;
	
	private String fromUserHeadImgUrl;
	
	private Long fromUserEnterpriseFlag;
	
	private Long fromUserVFlag;
	
	private Long toUserId;
	
	private String content;
	
	private Date createDate;
	
	private Long cancelFlag;
	
	private Long readFlag;

	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public String getContent() {
		return JijizuUtil.formatContent(content);
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

	public Long getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Long cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public Long getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Long readFlag) {
		this.readFlag = readFlag;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getFromUserHeadImgUrl() {
		return getFromUserHeadImgUrl(CommonConstant.HEAD_IMG_SIZE_50);
	}
	
	public String getFromUserHeadImgUrl(String imgSize){
		return JijizuUtil.getHeadImgUrl(fromUserHeadImgUrl, imgSize);
	}

	public void setFromUserHeadImgUrl(String fromUserHeadImgUrl) {
		this.fromUserHeadImgUrl = fromUserHeadImgUrl;
	}

	public Long getFromUserEnterpriseFlag() {
		return fromUserEnterpriseFlag;
	}

	public void setFromUserEnterpriseFlag(Long fromUserEnterpriseFlag) {
		this.fromUserEnterpriseFlag = fromUserEnterpriseFlag;
	}

	public Long getFromUserVFlag() {
		return fromUserVFlag;
	}

	public void setFromUserVFlag(Long fromUserVFlag) {
		this.fromUserVFlag = fromUserVFlag;
	}

}
