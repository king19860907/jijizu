package com.jijizu.core.status.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.InitData;
import com.jijizu.core.constant.SysDataDicConstant;

public class StatusInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5368083027863440426L;
	
	private Long statusId;
	
	private Long userId;
	
	private String name;
	
	private String nickName;
	
	private String headImgUrl;
	
	protected String content;
	
	private String mediaType;
	
	private String mediaUrl;
	
	private String sourceType;
	
	private String sourceTypeDetail;
	
	private String sourceText;
	
	private Date postTime;
	
	private String postIp;
	
	private Long forwardId;
	
	private Long forwardSrcId;
	
	private Long sinaStatusId;
	
	private Long isRecommend;
	
	private Long forwardNum;
	
	private Long commentNum;
	
	private Long cancelFlag;
	
	private Long groupId;
	
	private String sourceUrl;
	
	private Long vFlag;
	
	private Long enterpriseFlag;

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getContent() {
		return JijizuUtil.formatContent(content);
	}
	
	public String getContentWithOutHtmlTag(){
		return JijizuUtil.removeHtmlTag(content);
	}
	
	public String getContentWithOutHtmlTag(int len,String subffix){
		return StringUtil.getStrByLen(JijizuUtil.removeHtmlTag(content), len, subffix);
	}
	
	public String getContentWithOutHtmlTag(boolean isGetHref) {
		return JijizuUtil.removeHtmlTag(content, isGetHref);
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}
	
	public String getSmallImgUrl(){
		if(StringUtil.isNotNullOrEmpty(mediaUrl)){
			String imgNameWithoutExt = mediaUrl.substring(0,
					mediaUrl.lastIndexOf('.'));

			return imgNameWithoutExt + "_t"
					+ mediaUrl.substring(mediaUrl.lastIndexOf('.'));
		}
		return mediaUrl;
	}
	
	public String getOriginalImgUrl(){
		if(StringUtil.isNotNullOrEmpty(mediaUrl)){
			String imgNameWithoutExt = mediaUrl.substring(0,
					mediaUrl.lastIndexOf('.'));

			return imgNameWithoutExt + "_o"
					+ mediaUrl.substring(mediaUrl.lastIndexOf('.'));
		}
		return mediaUrl;
	}
	
	public boolean isOverOneDay(){
		boolean isOverOneDay = false;
		if(postTime != null){
			long time = postTime.getTime();
			long nowTimes = new Date().getTime();
			if((nowTimes - time)>24*60*60*1000){
				isOverOneDay = true;
			}
		}
		return isOverOneDay;

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

	public Long getForwardId() {
		return forwardId;
	}

	public void setForwardId(Long forwardId) {
		this.forwardId = forwardId;
	}

	public Long getForwardSrcId() {
		return forwardSrcId;
	}

	public void setForwardSrcId(Long forwardSrcId) {
		this.forwardSrcId = forwardSrcId;
	}

	public Long getSinaStatusId() {
		return sinaStatusId;
	}

	public void setSinaStatusId(Long sinaStatusId) {
		this.sinaStatusId = sinaStatusId;
	}

	public Long getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Long isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Long getForwardNum() {
		return forwardNum;
	}

	public void setForwardNum(Long forwardNum) {
		this.forwardNum = forwardNum;
	}

	public Long getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Long commentNum) {
		this.commentNum = commentNum;
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

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
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
	
	/**   
	 *******************************************************************************
	 * @function : 是否含有图片
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-7   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean isHaveImage() {
		if(StringUtil.isNotNullOrEmpty(mediaType) && SysDataDicConstant.MEDIA_TYPE_IMAGE.equals(mediaType)
				&& StringUtil.isNotNullOrEmpty(mediaUrl)) {
			return true;
		}
		return false;
	}

}
