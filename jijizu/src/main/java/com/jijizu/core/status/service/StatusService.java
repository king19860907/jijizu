package com.jijizu.core.status.service;

import java.util.Date;
import java.util.List;

import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.status.dto.CommentInfo;
import com.jijizu.core.status.dto.CommentInfoWithStatus;
import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.user.dto.UserInfo;

public interface StatusService {

	/**   
	 *******************************************************************************
	 * @function : 发送微博
	 * @param userInfo
	 * @param content
	 * @param mediaType
	 * @param mediaUrl
	 * @param sourceType
	 * @param sourceTypeDetail
	 * @param postTime
	 * @param postIp
	 * @param isPostSina
	 * @param groupId
	 * @param sinaStatusId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-24   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	JsonResult postStatus(UserInfo userInfo, String content,
            String mediaType, String mediaUrl, String sourceType,String sourceTypeDetail,
            Date postTime, String postIp, boolean isPostSina,Long groupId,Long sinaStatusId,Long notShowFlag);
	
	/**   
	 *******************************************************************************
	 * @function : 查询表情
	 * @param faceType
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-25   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	List<StatusFace> findStatusFace(String faceType);
	
	/**   
	 *******************************************************************************
	 * @function : 查询好友发送的微博
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-27   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	PaginationDTO<StatusInfoWithFwdSrc> findFriendsStatusPage(long pageNum, long pageSize,Long userId);
	
	/**   
	 *******************************************************************************
	 * @function : 转发微博
	 * @param sessionUserInfo
	 * @param content
	 * @param sourceType
	 * @param sourceTypeDetail
	 * @param postTime
	 * @param postIp
	 * @param isPostSina
	 * @param groupId
	 * @param sinaStatusId
	 * @param forwardId
	 * @param isComment  是否同时评论
	 * @param isCommentOriginalAuthor	  是否评论给原作者
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult forwardStatus(UserInfo sessionUserInfo,String content,String sourceType,
			String sourceText,Date postTime,String postIp,boolean isPostSina,Long groupId,
			Long sinaStatusId,Long forwardId,boolean isComment,boolean isCommentOriginalAuthor);
	
	/**   
	 *******************************************************************************
	 * @function : 发表评论
	 * @param sessionUserInfo
	 * @param statusId
	 * @param content
	 * @param sourceType
	 * @param sourceTypeDetail
	 * @param postTime
	 * @param postIp
	 * @param replyId
	 * @param replyUserId
	 * @param isForward					是否转发至我的微薄
	 * @param isCommentOriginalAuthor	是否评论给原作者
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult postComment(UserInfo sessionUserInfo,Long statusId,String content,String sourceType,
			String sourceTypeDetail,Date postTime,String postIp,
			Long replyId,Long replyUserId,boolean isForward,boolean isCommentOriginalAuthor,Long groupId);
	
	/**   
	 *******************************************************************************
	 * @function : 根据微博ID查询评论
	 * @param pageNum
	 * @param pageSize
	 * @param statusId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	PaginationDTO<CommentInfo> findCommentsByStatusIdPage(long pageNum, long pageSize,Long statusId);
	
	/**   
	 *******************************************************************************
	 * @function : 根据id查询一条微博(带转发)
	 * @param statusId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-30   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	StatusInfoWithFwdSrc getStatusWithFwdSrc(Long statusId);
	
	/**   
	 *******************************************************************************
	 * @function : 删除微薄
	 * @param sessionUserInfo
	 * @param statusId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-30   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	JsonResult deleteStatus(UserInfo sessionUserInfo,Long statusId);
	
	/**   
	 *******************************************************************************
	 * @function : 删除评论
	 * @param sessionUserInfo
	 * @param commentId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	JsonResult deleteComment(UserInfo sessionUserInfo,Long commentId);
	
	/**   
	 *******************************************************************************
	 * @function : 查询用户发送的微博
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	PaginationDTO<StatusInfoWithFwdSrc> findUserStatusPage(long pageNum, long pageSize,Long userId);
	
	/**   
	 *******************************************************************************
	 * @function : 获取收到的评论
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-11   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	PaginationDTO<CommentInfoWithStatus> findCommentInPage(long pageNum, long pageSize,Long userId);
	
	/**   
	 *******************************************************************************
	 * @function : 获取发出的评论
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-11   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	PaginationDTO<CommentInfoWithStatus> findCommentOutPage(long pageNum, long pageSize,Long userId);
	
	/**   
	 *******************************************************************************
	 * @function : 查询at我的微博
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	PaginationDTO<StatusInfoWithFwdSrc> findAtMeStatusPage(long pageNum, long pageSize,Long userId);
	
	/**   
	 *******************************************************************************
	 * @function : 查询at我的评论
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	PaginationDTO<CommentInfoWithStatus> findAtMeCommentPage(long pageNum, long pageSize,Long userId);
	
	/**   
	 *******************************************************************************
	 * @function : 查询某条微博之前的微博
	 * @param userId
	 * @param statusId
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-21   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	List<StatusInfoWithFwdSrc> findBeforeStatusList(Long userId,Long statusId,Long rownum);
	
	/**   
	 *******************************************************************************
	 * @function : 查询某条微博之后的微博
	 * @param userId
	 * @param statusId
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-21   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	List<StatusInfoWithFwdSrc> findAfterStatusList(Long userId,Long statusId,Long rownum);
	
	/**   
	 *******************************************************************************
	 * @function : 统计某条微博之后的新微博数量
	 * @param userId
	 * @param statusId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-21   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	Long countAfterStatus(Long userId,Long statusId);
	
	/**   
	 *******************************************************************************
	 * @function : 修改微博内容及媒体地址
	 * @param statusId
	 * @param userId
	 * @param content
	 * @param mediaType
	 * @param mediaUrl
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-7   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	void updateStatusContentAndMediaUrlById(Long statusId,Long userId,String content,String mediaType,String mediaUrl);
}
