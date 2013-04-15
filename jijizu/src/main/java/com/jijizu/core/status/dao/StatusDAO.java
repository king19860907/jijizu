package com.jijizu.core.status.dao;

import java.util.Date;
import java.util.List;

import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.status.dto.CommentInfo;
import com.jijizu.core.status.dto.CommentInfoWithStatus;
import com.jijizu.core.status.dto.IllegalWords;
import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.status.dto.StatusInfo;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;

public interface StatusDAO {

	/**   
	 *******************************************************************************
	 * @function : 获取敏感词
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	List<IllegalWords> getIllegalwords();
	
	/**   
	 *******************************************************************************
	 * @function : 新增一条status
	 * @param userId
	 * @param content
	 * @param mediaType
	 * @param mediaUrl
	 * @param sourceType
	 * @param sourceTypeDetail
	 * @param sourceText
	 * @param postTime
	 * @param postIp
	 * @param forwardId
	 * @param forwardSrcId
	 * @param sinaStatusId
	 * @param groupId
	 * @param sourceUrl
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
	public Long insertStatus(Long userId,String content,String mediaType,String mediaUrl,
			String sourceType,String sourceTypeDetail,String sourceText,Date postTime,
			String postIp,Long forwardId,Long forwardSrcId,Long sinaStatusId,Long groupId,
			String sourceUrl,Long notShowFlag);
	
	/**   
	 *******************************************************************************
	 * @function : 根据类型查找表情
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
	List<StatusFace> findFaceByType(String faceType);
	
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
	 * @function : 根据id查询一条微博
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
	StatusInfo getStatusById(Long statusId);
	
	/**   
	 *******************************************************************************
	 * @function : 保存评论
	 * @param userId
	 * @param statusId
	 * @param content
	 * @param sourceType
	 * @param sourceTypeDetail
	 * @param sourceText
	 * @param sourceUrl
	 * @param postTime
	 * @param postIp
	 * @param replyId
	 * @param replyUserId
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
	Long insertComment(Long userId,Long statusId,String content,	String sourceType,
			String sourceTypeDetail,String sourceText,String sourceUrl,Date postTime,String postIp,
			Long replyId,Long replyUserId);
	
	/**   
	 *******************************************************************************
	 * @function : 更新微博的被评论数
	 * @param statusId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	void updateStatusCommentNumByStatusId(Long statusId);
	
	/**   
	 *******************************************************************************
	 * @function : 更具评论ID获取评论
	 * @param commentId
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
	CommentInfo getCommentByCommentId(Long commentId);
	
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
	 * @param statusId
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-30   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	int deleteStatusById(Long statusId,Long userId);
	
	/**   
	 *******************************************************************************
	 * @function : 根据微薄ID和用户ID统计微薄数量
	 * @param statusId
	 * @param userId
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
	long countStatusByStatusIdAndUserId(Long statusId,Long userId);
	
	/**   
	 *******************************************************************************
	 * @function : 保存at记录
	 * @param userId
	 * @param refId
	 * @param type
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
	Long saveAtRecord(Long userId,Long refId,String type);
	
	/**   
	 *******************************************************************************
	 * @function : 删除评论
	 * @param commentId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	void deleteCommentById(Long commentId);
	
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
	 * @function : 统计新评论的数量
	 * @param userId
	 * @param readCommentTime
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	Long countNewCommentNumByUserId(Long userId,Date readCommentTime);
	
	/**   
	 *******************************************************************************
	 * @function : 统计新at我的数量
	 * @param userId
	 * @param readAtMeTime
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	Long countNewAtMeNumByUserId(Long userId,Date readAtMeTime);
	
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
	 * @function : 更新微博转发数
	 * @param statusId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-6   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	void updateStatusForwardNumByStatusId(Long statusId);
	
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
