package com.jijizu.core.status.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jijizu.core.dao.IbatisBaseDAO;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.status.dao.StatusDAO;
import com.jijizu.core.status.dto.CommentInfo;
import com.jijizu.core.status.dto.CommentInfoWithStatus;
import com.jijizu.core.status.dto.IllegalWords;
import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.status.dto.StatusInfo;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;

public class StatusDAOImpl extends IbatisBaseDAO
	implements StatusDAO{

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
	public List<IllegalWords> getIllegalwords(){
		return (List<IllegalWords>)getSqlMapClientTemplate().queryForList("getIllegalwords");
	}
	
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
			String sourceUrl,Long notShowFlag){
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("userId", userId);
	    para.put("content", content);
	    para.put("mediaType", mediaType);
	    para.put("mediaUrl", mediaUrl);
	    para.put("sourceType", sourceType);
	    para.put("sourceTypeDetail", sourceTypeDetail);
	    para.put("sourceText", sourceText);
	    para.put("postTime", postTime);
	    para.put("postIp", postIp);
	    para.put("forwardId", forwardId);
	    para.put("forwardSrcId", forwardSrcId);
	    para.put("sinaStatusId", sinaStatusId);
	    para.put("groupId", groupId);
	    para.put("sourceUrl", sourceUrl);
	    para.put("notShowFlag", notShowFlag);
		
		return (Long)this.insert("insertStatus", para);
	}
	
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
	public List<StatusFace> findFaceByType(String faceType){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("faceType", faceType);
		return this.select("findFaceByType", para);
	}
	
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
	public PaginationDTO<StatusInfoWithFwdSrc> findFriendsStatusPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<StatusInfoWithFwdSrc> pagination = new PaginationDTO<StatusInfoWithFwdSrc>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findFriendsStatusPage", pagination);
	}
	
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
	public StatusInfo getStatusById(Long statusId){
		return (StatusInfo)this.selectOneObject("getStatusById", statusId);
	}
	
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
	public Long insertComment(Long userId,Long statusId,String content,	String sourceType,
			String sourceTypeDetail,String sourceText,String sourceUrl,Date postTime,String postIp,
			Long replyId,Long replyUserId){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("userId", userId);
		para.put("statusId", statusId);
		para.put("content", content);
		para.put("sourceType", sourceType);
		para.put("sourceTypeDetail", sourceTypeDetail);
		para.put("sourceText", sourceText);
		para.put("sourceUrl", sourceUrl);
		para.put("postTime", postTime);
		para.put("postIp", postIp);
		para.put("replyId", replyId);
		para.put("replyUserId", replyUserId);
		return (Long) this.insert("insertComment", para);
	}
	
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
	public void updateStatusCommentNumByStatusId(Long statusId){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("statusId", statusId);
		this.update("updateStatusCommentNumByStatusId",para);
	}
	
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
	public CommentInfo getCommentByCommentId(Long commentId){
		return (CommentInfo)this.selectOneObject("getCommentByCommentId", commentId);
	}
	
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
	public PaginationDTO<CommentInfo> findCommentsByStatusIdPage(long pageNum, long pageSize,Long statusId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("statusId", statusId);
		PaginationDTO<CommentInfo> pagination = new PaginationDTO<CommentInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findCommentsByStatusIdPage", pagination);
	}
	
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
	public StatusInfoWithFwdSrc getStatusWithFwdSrc(Long statusId){
		return (StatusInfoWithFwdSrc)this.selectOneObject("getStatusWithFwdSrc", statusId);
	}
	
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
	public int deleteStatusById(Long statusId,Long userId){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("statusId", statusId);
		para.put("userId", userId);
		return this.update("deleteStatusById",para);
	}
	
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
	public long countStatusByStatusIdAndUserId(Long statusId,Long userId){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("statusId", statusId);
		para.put("userId", userId);
		return (Long)this.selectOneObject("countStatusByStatusIdAndUserId", para);
	}
	
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
	public Long saveAtRecord(Long userId,Long refId,String type){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("refId", refId);
		para.put("type", type);
		return (Long)this.insert("saveAtRecord", para);
	}
	
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
	public void deleteCommentById(Long commentId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("commentId", commentId);
		this.update("deleteCommentById",para);
	}
	
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
	public PaginationDTO<StatusInfoWithFwdSrc> findUserStatusPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<StatusInfoWithFwdSrc> pagination = new PaginationDTO<StatusInfoWithFwdSrc>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findUserStatusPage", pagination);
	}
	
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
	public PaginationDTO<CommentInfoWithStatus> findCommentInPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<CommentInfoWithStatus> pagination = new PaginationDTO<CommentInfoWithStatus>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findCommentInPage", pagination);
	}
	
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
	public PaginationDTO<CommentInfoWithStatus> findCommentOutPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<CommentInfoWithStatus> pagination = new PaginationDTO<CommentInfoWithStatus>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findCommentOutPage", pagination);
	}
	
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
	public PaginationDTO<StatusInfoWithFwdSrc> findAtMeStatusPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<StatusInfoWithFwdSrc> pagination = new PaginationDTO<StatusInfoWithFwdSrc>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findAtMeStatusPage", pagination);
	}
	
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
	public PaginationDTO<CommentInfoWithStatus> findAtMeCommentPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<CommentInfoWithStatus> pagination = new PaginationDTO<CommentInfoWithStatus>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findAtMeCommentPage", pagination);
	}
	
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
	public Long countNewCommentNumByUserId(Long userId,Date readCommentTime){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("readCommentTime", readCommentTime);
		return (Long)this.selectOneObject("countNewCommentNumByUserId", para);
	}
	
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
	public Long countNewAtMeNumByUserId(Long userId,Date readAtMeTime){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("readAtMeTime", readAtMeTime);
		return (Long)this.selectOneObject("countNewAtMeNumByUserId", para);
	}
	
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
	public List<StatusInfoWithFwdSrc> findBeforeStatusList(Long userId,Long statusId,Long rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("statusId", statusId);
		para.put("rownum", rownum);
		return (List<StatusInfoWithFwdSrc>)this.select("findBeforeStatusList", para);
	}
	
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
	public List<StatusInfoWithFwdSrc> findAfterStatusList(Long userId,Long statusId,Long rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("statusId", statusId);
		para.put("rownum", rownum);
		return (List<StatusInfoWithFwdSrc>)this.select("findAfterStatusList", para);
	}
	
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
	public Long countAfterStatus(Long userId,Long statusId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("statusId", statusId);
		return (Long)this.selectOneObject("countAfterStatus", para);
	}
	
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
	public void updateStatusForwardNumByStatusId(Long statusId){
		this.update("updateStatusForwardNumByStatusId",statusId);
	}
	
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
	public void updateStatusContentAndMediaUrlById(Long statusId,Long userId,String content,String mediaType,String mediaUrl) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("statusId", statusId);
		para.put("content", content);
		para.put("mediaType", mediaType);
		para.put("mediaUrl", mediaUrl);
		this.update("updateStatusContentAndMediaUrlById",para);
	}
}
