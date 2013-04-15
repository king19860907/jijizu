package com.jijizu.core.status.dto;

public class CommentInfoWithStatus extends CommentInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3398317540250358034L;
	
	private StatusInfo status;
	
	private CommentInfo comment;

	public StatusInfo getStatus() {
		return status;
	}

	public void setStatus(StatusInfo status) {
		this.status = status;
	}

	public CommentInfo getComment() {
		return comment;
	}

	public void setComment(CommentInfo comment) {
		this.comment = comment;
	}
	
}
