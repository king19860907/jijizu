package com.jijizu.web.status.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.InitData;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.status.service.StatusService;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class StatusActionAjax extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1971161703142265172L;

	private StatusService statusService;

	private String content;

	private String mediaType;

	private String mediaUrl;
	
	private String sourceType;
	
	private String sourceTypeDetail;
	
	private String sourceUrl;
	
	private Long groupId;
	
	private Long forwardId;
	
	private Long statusId;
	
	private Long replyId;
	
	private Long replyUserId;
	
	private boolean isComment;
	
	private boolean isCommentOriginalAuthor;
	
	private boolean isForward;
	
	private Long commentId;
	
	public String countNewStatus(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo != null && statusId != null){
			Long count = statusService.countAfterStatus(sessionUserInfo.getUserId(), statusId);
			this.outString(count.toString());
		}
		
		return NONE;
	}
	
	public String postStatus() {

		UserInfo sessionUserInfo = getSessionUserInfo();

		JsonResult result = statusService.postStatus(sessionUserInfo, content,
				mediaType, mediaUrl, sourceType, sourceTypeDetail, null, getIp(request), false, groupId,null,CommonConstant.NOT_SHOW_FLAG_NO);
		
		this.outObjectToJson(result);

		return NONE;
	}
	
	public String forwardStatus(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = statusService.forwardStatus(sessionUserInfo, content, sourceType, sourceTypeDetail,
				null, getIp(request), false, groupId, 
				null, forwardId,isComment,isCommentOriginalAuthor);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String postComment(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = statusService.postComment(sessionUserInfo, statusId, content, sourceType, 
				sourceTypeDetail, null,
				getIp(request), replyId, replyUserId,isForward,isCommentOriginalAuthor,groupId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String deleteStatus(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = statusService.deleteStatus(sessionUserInfo, statusId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String deleteComment(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = statusService.deleteComment(sessionUserInfo, commentId);
		
		this.outObjectToJson(result);
		
		return NONE;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public String getContent() {
		return content;
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

	public StatusService getStatusService() {
		return statusService;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getForwardId() {
		return forwardId;
	}

	public void setForwardId(Long forwardId) {
		this.forwardId = forwardId;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
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

	public void setComment(boolean isComment) {
		this.isComment = isComment;
	}

	public void setCommentOriginalAuthor(boolean isCommentOriginalAuthor) {
		this.isCommentOriginalAuthor = isCommentOriginalAuthor;
	}

	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

}
