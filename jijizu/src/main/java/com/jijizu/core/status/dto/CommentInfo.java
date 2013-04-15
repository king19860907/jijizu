package com.jijizu.core.status.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.CommonConstant;

public class CommentInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5995940905034251802L;
	
	private Long commentId;
	
	private Long userId;
	
	private Long statusId;
	
	private String content;
	
	private String sourceType;
	
	private String sourceTypeDetail;
	
	private String sourceText;
	
	private String sourceUrl;
	
	private Date postTime;
	
	private String postIp;
	
	private String sinaCommentId;
	
	private Long replyId;
	
	private Long replyUserId;
	
	private String sinaUserId;
	
	private String sinaHeadImgUrl;
	
	private String sinaNickName;
	
	private String cancelFlag;
	
	private String name;
	
	private String nickName;
	
	private String headImgUrl;
	
	private Long vFlag;
	
	private Long enterpriseFlag;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getContent() {
		return JijizuUtil.formatContent(content);
	}
	
	public String getContentWithOutHtmlTag(){
		return JijizuUtil.removeHtmlTag(content);
	}
	
	public String getContentWithOutHtmlTag(int len,String subffix){
		return StringUtil.getStrByLen(JijizuUtil.removeHtmlTag(content), len, subffix);
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceTypeDetail() {
		return sourceTypeDetail;
	}

	public void setSourceTypeDetail(String sourceTypeDetail) {
		this.sourceTypeDetail = sourceTypeDetail;
	}

	public String getSourceText() {
		return sourceText;
	}

	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getPostIp() {
		return postIp;
	}

	public void setPostIp(String postIp) {
		this.postIp = postIp;
	}

	public String getSinaCommentId() {
		return sinaCommentId;
	}

	public void setSinaCommentId(String sinaCommentId) {
		this.sinaCommentId = sinaCommentId;
	}

	public Long getReplyId() {
		return replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public Long getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}

	public String getSinaUserId() {
		return sinaUserId;
	}

	public void setSinaUserId(String sinaUserId) {
		this.sinaUserId = sinaUserId;
	}

	public String getSinaHeadImgUrl() {
		return sinaHeadImgUrl;
	}

	public void setSinaHeadImgUrl(String sinaHeadImgUrl) {
		this.sinaHeadImgUrl = sinaHeadImgUrl;
	}

	public String getSinaNickName() {
		return sinaNickName;
	}

	public void setSinaNickName(String sinaNickName) {
		this.sinaNickName = sinaNickName;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getVFlag() {
		return vFlag;
	}

	public void setVFlag(Long vFlag) {
		this.vFlag = vFlag;
	}

	public Long getEnterpriseFlag() {
		return enterpriseFlag;
	}

	public void setEnterpriseFlag(Long enterpriseFlag) {
		this.enterpriseFlag = enterpriseFlag;
	}

}
