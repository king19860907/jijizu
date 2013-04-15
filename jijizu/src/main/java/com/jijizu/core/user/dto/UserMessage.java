package com.jijizu.core.user.dto;

import java.io.Serializable;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : UserMessage
 * @function : 用户消息提示
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-2-18   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class UserMessage implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2799493020646911472L;
	
	/**
	 * 新评论数量
	 */
	private Long commentNum;
	
	/**
	 * 新at我的数量
	 */
	private Long atMeNum;
	
	/**
	 * 新私信数量
	 */
	private Long mailNum;
	
	/**
	 * 系统通知数量
	 */
	private Long noticeNum;

	public Long getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Long commentNum) {
		this.commentNum = commentNum;
	}
	
	public Long getAtMeNum() {
		return atMeNum;
	}

	public void setAtMeNum(Long atMeNum) {
		this.atMeNum = atMeNum;
	}

	public boolean isShow(){
		boolean isShow = false;
		if((commentNum != null && commentNum.longValue() > 0) ||
				(atMeNum != null && atMeNum.longValue() > 0) ||
				(mailNum != null && mailNum.longValue() > 0) ||
				(noticeNum != null && noticeNum.longValue() > 0)){
			isShow = true;
		}
		return isShow;
	}

	public Long getMailNum() {
		return mailNum;
	}

	public void setMailNum(Long mailNum) {
		this.mailNum = mailNum;
	}

	public Long getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(Long noticeNum) {
		this.noticeNum = noticeNum;
	}

}
