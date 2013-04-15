package com.jijizu.core.status.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jijizu.base.util.CollectionUtil;
import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.ObjectUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.album.service.AlbumService;
import com.jijizu.core.cache.service.CacheService;
import com.jijizu.core.check.CheckContext;
import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.InitData;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.group.dao.GroupDAO;
import com.jijizu.core.group.dto.GroupInfo;
import com.jijizu.core.group.service.GroupService;
import com.jijizu.core.service.MessageService;
import com.jijizu.core.status.dao.StatusDAO;
import com.jijizu.core.status.dto.CommentInfo;
import com.jijizu.core.status.dto.CommentInfoWithStatus;
import com.jijizu.core.status.dto.IllegalWords;
import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.status.dto.StatusInfo;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.status.service.StatusService;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.UserInfo;

public class StatusServiceImpl implements StatusService {
	
	private StatusDAO statusDAO;
	
	private UserInfoDAO userInfoDAO;
	
	private GroupDAO groupDAO;
	
	private CacheService cacheService;
	
	private AlbumService albumService;
	
	private GroupService groupService;
	
	private MessageService messageService;
	
	/**
	 * 发布微博验证
	 */
	private CheckContext postStatusCheckContext;
	
	/**
	 * 转发微博验证
	 */
	private CheckContext forwardStatusCheckContext;
	
	/**
	 * 发送评论验证
	 */
	private CheckContext postCommentCheckContext;
	
	/**
	 * 删除微薄验证
	 */
	private CheckContext deleteStatusCheckContext;
	
	/**
	 * 删除评论验证
	 */
	private CheckContext deleteCommentCheckContext;
	
	/**   
	 *******************************************************************************
	 * @function : 初始化微博相关内容 
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void init(){
		
		initIllegalWords();
		
		initFace();
		
	}
	
	/**   
	 *******************************************************************************
	 * @function : 初始化表情
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void initFace(){
		List<StatusFace> list =  this.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS);
		for(StatusFace face : list){
			InitData.statusFaceMap.put("["+face.getName()+"]", face);
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 初始化敏感词
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void initIllegalWords(){
		List<IllegalWords> list = statusDAO.getIllegalwords();
		List<String> l = new ArrayList<String>();
		for(IllegalWords s:list){
			l.add(s.getWord());
		}
		this.wordListToMap(l);
	}
	
	private Map<Character, List<String>> wordListToMap(List<String> wordList) {
		for (String s : wordList) {
			char c = s.charAt(0);
			List<String> strs = InitData.illegalwordMap.get(c);
			if (strs == null) {
				strs = new ArrayList<String>();
				InitData.illegalwordMap.put(c, strs);
			}
			strs.add(s);
		}
		return InitData.illegalwordMap;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 发送微博
	 * @param userInfo
	 * @param content
	 * @param mediaType
	 * @param mediaUrl
	 * @param sourceType
	 * @param sourceTypeDetail
	 * @param sourceText
	 * @param postTime
	 * @param postIp
	 * @param isPostSina
	 * @param groupId
	 * @param sourceUrl
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
	public JsonResult postStatus(UserInfo userInfo, String content,
            String mediaType, String mediaUrl, String sourceType,String sourceTypeDetail, Date postTime, String postIp, boolean isPostSina,Long groupId,
            Long sinaStatusId,Long notShowFlag){
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put(CheckParam.CONTENT, content);
		para.put(CheckParam.SESSIONUSERINFO, userInfo);
		JsonResult result = postStatusCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		//处理超链接
		content = JijizuUtil.convertURL(content);
		
		para.put(CheckParam.GROUPID, groupId);
		Long statusId = statusDAO.insertStatus(userInfo.getUserId(), content, mediaType, mediaUrl, 
				sourceType, sourceTypeDetail, getSourceText(sourceTypeDetail, para), postTime, 
				postIp, null, null, sinaStatusId, groupId,getSourceUrl(sourceTypeDetail, para),notShowFlag);

		//TODO 将图片设置到微博配图中
		if(StringUtil.isNotNullOrEmpty(mediaUrl)){
			if(groupId == null){
				albumService.postStatusPhoto(userInfo.getUserId(), mediaUrl, statusId);
			}else{
				groupService.postGroupPhoto(userInfo.getUserId(), mediaUrl, statusId, groupId, content);
			}
		}
		
		//设置最后一条发送微博的内容
		JijizuUtil.setLastContent(content);
		
		//处理at记录
		this.handleAtRecord(userInfo.getUserId(),content, statusId, SysDataDicConstant.AT_RECORD_TYPE_STATUS);
		
		//更新用户微博数
		userInfoDAO.updateUserStatusNumByUserId(userInfo.getUserId());
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,statusId);
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
		//处理超链接
		content = JijizuUtil.convertURL(content);
		//TODO 将图片设置到微博配图中
		if(StringUtil.isNotNullOrEmpty(mediaUrl)){
			albumService.postStatusPhoto(userId, mediaUrl, statusId);
		}
		
		//设置最后一条发送微博的内容
		JijizuUtil.setLastContent(content);
		
		statusDAO.updateStatusContentAndMediaUrlById(statusId, userId, content, mediaType, mediaUrl);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取来自链接
	 * @param sourceTypeDetail
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-4   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private String getSourceUrl(String sourceTypeDetail,Map<String,Object> para){
		String sourceUrl = null;
		Object groupId = para.get(CheckParam.GROUPID);
		if(!ObjectUtil.isEmptyObject(groupId)&&SysDataDicConstant.SOURCE_TYPE_DETAIL_GROUP.equals(sourceTypeDetail)){
			GroupInfo groupInfo = groupDAO.getGroupInfoById(Long.parseLong(groupId.toString()), para);
			if(groupInfo != null){
				sourceUrl = JijizuUtil.getAbsolutePath()+"/group/"+groupInfo.getGroupId();
			}
		}
		
		if(SysDataDicConstant.SOURCE_TYPE_DETAIL_DIARY.equals(sourceTypeDetail)) {
			sourceUrl = JijizuUtil.getAbsolutePath()+"/diary/";
		}
		
		return sourceUrl;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取来自内容
	 * @param sourceTypeDetail
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-4   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private String getSourceText(String sourceTypeDetail,Map<String,Object> para){
		String sourceText = InitData.sysDictCodeAndNameRelationShip.get(sourceTypeDetail);
		Object groupId = para.get(CheckParam.GROUPID);
		if(!ObjectUtil.isEmptyObject(groupId)&&SysDataDicConstant.SOURCE_TYPE_DETAIL_GROUP.equals(sourceTypeDetail)){
			GroupInfo groupInfo = groupDAO.getGroupInfoById(Long.parseLong(groupId.toString()), para);
			if(groupInfo != null){
				sourceText = groupInfo.getTitle();
			}
		}
		
		return sourceText;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 处理at记录
	 * @param loginUserId
	 * @param content
	 * @param refId
	 * @param type
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void handleAtRecord(Long loginUserId,String content,Long refId,String type){
		Set<String> nameList = JijizuUtil.formatAtNickName(content);
		Iterator<String> it = nameList.iterator();
		while(it.hasNext()){
			String name = it.next();
			UserInfo user = userInfoDAO.getUserInfoByNickName(name);
			if(user != null){
				this.saveAtRecord(loginUserId,user.getUserId(), refId, type);
			}
		}
	}
	
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
	public JsonResult forwardStatus(UserInfo sessionUserInfo,String content,String sourceType,String sourceTypeDetail,
			Date postTime,String postIp,boolean isPostSina,Long groupId,
			Long sinaStatusId,Long forwardId,boolean isComment,boolean isCommentOriginalAuthor){
		
		if(!StringUtil.isNotNullOrEmpty(content)){
			content="转发微博";
		}
		Map<String,Object> para = new HashMap<String,Object>();
		para.put(CheckParam.CONTENT, content);
		para.put(CheckParam.STATUSID, forwardId);
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		JsonResult result = forwardStatusCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		//该微博在前面应该已经在forwardStatusCheckContext中已经验证过其存在性，故无需在查询
		StatusInfo sourceStatus = (StatusInfo)para.get(CheckParam.TEMP_STATUSINFO);
		
		para.put(CheckParam.GROUPID, groupId);
		Long statusId = forwardStatusWithOutCheck(sessionUserInfo.getUserId(), content,
				sourceType, sourceTypeDetail, getSourceText(sourceTypeDetail, para), postTime, postIp,
				groupId, getSourceUrl(sourceTypeDetail, para), sinaStatusId, sourceStatus);
		
		//TODO 同时发表评论
		if(isComment){
			this.postCommentWithOutCheck(sessionUserInfo.getUserId(), sourceStatus.getStatusId(), content, 
					sourceType, sourceTypeDetail, getSourceText(sourceTypeDetail, para), 
					getSourceUrl(sourceTypeDetail, para), postTime, postIp, null, null);
		}
		
		//TODO 评论给原作者
		if(isCommentOriginalAuthor && sourceStatus.getForwardSrcId() != null){
			this.postCommentWithOutCheck(sessionUserInfo.getUserId(), sourceStatus.getForwardSrcId(), content, sourceType, 
					sourceTypeDetail, getSourceText(sourceTypeDetail, para), getSourceUrl(sourceTypeDetail, para), postTime, 
					postIp, null, null);
		}
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,statusId);
	}

	
	/**   
	 *******************************************************************************
	 * @function : 
	 * @param userId
	 * @param content
	 * @param sourceType
	 * @param sourceTypeDetail
	 * @param sourceText
	 * @param postTime
	 * @param postIp
	 * @param groupId
	 * @param sourceUrl
	 * @param sinaStatusId
	 * @param forwardId
	 * @param forwardSrcId
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
	private Long forwardStatusWithOutCheck(Long userId,
			String content, String sourceType, String sourceTypeDetail,
			String sourceText, Date postTime, String postIp, Long groupId,
			String sourceUrl, Long sinaStatusId,StatusInfo sourceStatus) {
		
		//处理超链接
		content = JijizuUtil.convertURL(content);
		
		Long statusId = statusDAO.insertStatus(userId, content, null, null, 
				sourceType, sourceTypeDetail, sourceText, postTime, 
				postIp, sourceStatus.getStatusId(), (sourceStatus.getForwardSrcId()!=null) ? sourceStatus.getForwardSrcId() : sourceStatus.getStatusId(),
						sinaStatusId, groupId, sourceUrl,CommonConstant.NOT_SHOW_FLAG_NO);
		
		//设置最后一条发送微博的内容
		JijizuUtil.setLastContent(content);
		
		//同步at record
		this.handleAtRecord(userId,content, statusId, SysDataDicConstant.AT_RECORD_TYPE_STATUS);
		
		Long toUserId = null;
		if(sourceStatus.getForwardSrcId() == null){
			toUserId = sourceStatus.getUserId();
		}else{
			StatusInfoWithFwdSrc forwardSrcStatus = statusDAO.getStatusWithFwdSrc(sourceStatus.getForwardSrcId());
			if(forwardSrcStatus != null){
				toUserId = forwardSrcStatus.getUserId();
			}
		}
		this.saveAtRecord(userId,toUserId, statusId, SysDataDicConstant.AT_RECORD_TYPE_STATUS);
		
		//更新微博数
		userInfoDAO.updateUserStatusNumByUserId(userId);
		//更新转发数
		if(sourceStatus.getForwardSrcId() != null){
			statusDAO.updateStatusForwardNumByStatusId(sourceStatus.getForwardSrcId());
			statusDAO.updateStatusForwardNumByStatusId(sourceStatus.getStatusId());
		}else{
			statusDAO.updateStatusForwardNumByStatusId(sourceStatus.getStatusId());
		}
		return statusId;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存at记录
	 * @param loginUserId
	 * @param toUserId
	 * @param refId
	 * @param type
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-11   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private Long saveAtRecord(Long loginUserId,Long toUserId,Long refId,String type){
		Long recordId = statusDAO.saveAtRecord(toUserId, refId, type);
		if(loginUserId.longValue() != toUserId.longValue()){
			messageService.setNewMessage(CacheConstant.MESSAGE_AT_NEW_COUNT, toUserId);
		}
		return recordId;
	}
	
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
			Long replyId,Long replyUserId,boolean isForward,boolean isCommentOriginalAuthor,Long groupId){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.STATUSID, statusId);
		para.put(CheckParam.CONTENT, content);
		para.put(CheckParam.COMMENTID, replyId);
		para.put(CheckParam.COMMENTID+CheckParam.IS_NEED_NULL_CHECK, false); //commentId不需要为空检查
		JsonResult result = postCommentCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		StatusInfo sourceStatus = (StatusInfo)para.get(CheckParam.TEMP_STATUSINFO);
		
		para.put(CheckParam.GROUPID, groupId);
		Long commentId = postCommentWithOutCheck(sessionUserInfo.getUserId(), statusId,
				content, sourceType, sourceTypeDetail, getSourceText(sourceTypeDetail, para), getSourceUrl(sourceTypeDetail, para),
				postTime, postIp, replyId, replyUserId);
		
		//转发到我的微博
		if(isForward){
			if(sourceStatus.getForwardSrcId() != null){
				content = content+"//@"+sourceStatus.getName()+"("+sourceStatus.getNickName()+")："+sourceStatus.getContentWithOutHtmlTag();
			}
			this.forwardStatusWithOutCheck(sessionUserInfo.getUserId(), content, sourceType, sourceTypeDetail, 
					getSourceText(sourceTypeDetail, para), postTime, postIp, groupId,  getSourceUrl(sourceTypeDetail, para), 
					null,sourceStatus);
		}
		
		//评论给原作者
		if(isCommentOriginalAuthor && sourceStatus.getForwardSrcId() != null){
			this.postCommentWithOutCheck(sessionUserInfo.getUserId(), sourceStatus.getForwardSrcId(), content, sourceType,
					sourceTypeDetail, getSourceText(sourceTypeDetail, para), getSourceUrl(sourceTypeDetail, para), postTime, 
					postIp, replyId, replyUserId);
		}
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,commentId);
	}

	/**   
	 *******************************************************************************
	 * @function : 发表评论（无需验证）
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
	private Long postCommentWithOutCheck(Long userId,
			Long statusId, String content, String sourceType,
			String sourceTypeDetail, String sourceText, String sourceUrl,
			Date postTime, String postIp, Long replyId, Long replyUserId) {
		
		//处理超链接
		content = JijizuUtil.convertURL(content);
		
		//如果回复人为空，则默认的回复人为微博的原作者
		StatusInfo status = statusDAO.getStatusById(statusId);
		if(replyUserId == null){
			replyUserId = status.getUserId();
		}
		
		//如果回复人不为自己即不是自己回复自己，则发送新消息提示
		if(replyUserId != null && replyUserId.longValue() != userId.longValue()){
			messageService.setNewMessage(CacheConstant.MESSAGE_COMMENT_NEW_COUNT, replyUserId);
		}
		
		Long commentId = statusDAO.insertComment(userId, statusId, content, sourceType, 
				sourceTypeDetail, sourceText, sourceUrl, postTime, 
				postIp, replyId, replyUserId);
		
		//设置最后一条发送微博的内容
		JijizuUtil.setLastContent(content);
		
		//插入at record
		this.handleAtRecord(userId,content, commentId, SysDataDicConstant.AT_RECORD_TYPE_COMMENT);
		
		//更新status表评论数
		this.updateStatusCommentNumByStatusId(statusId);
		
		//TODO 将新评论数量加入缓存
		
		return commentId;
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
	private void updateStatusCommentNumByStatusId(Long statusId){
		statusDAO.updateStatusCommentNumByStatusId(statusId);
	}
	
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
	public List<StatusFace> findStatusFace(String faceType){
		List<StatusFace> list = null;
		try {
			list = (List<StatusFace>)cacheService.getSnaInfo(CacheConstant.STATUS_FACE_TYPE+faceType);
			if(CollectionUtil.isEmptyOrNull(list)){
				list = statusDAO.findFaceByType(faceType);
				if(!CollectionUtil.isEmptyOrNull(list)){
					cacheService.setSnaInfo(CacheConstant.STATUS_FACE_TYPE+faceType, (Serializable)list, CacheConstant.CACHE_EXPRIATION_ONE_DAY);
				}
			}
			
		} catch (Exception e) {
			list = statusDAO.findFaceByType(faceType);
		}
		
		return list;
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
	public PaginationDTO<StatusInfoWithFwdSrc> findFriendsStatusPage(long pageNum, long pageSize,Long userId){
		return statusDAO.findFriendsStatusPage(pageNum, pageSize, userId);
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
	public PaginationDTO<CommentInfo> findCommentsByStatusIdPage(long pageNum, long pageSize,Long statusId){
		return statusDAO.findCommentsByStatusIdPage(pageNum, pageSize, statusId);
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
		return statusDAO.getStatusWithFwdSrc(statusId);
	}
	
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
	public JsonResult deleteStatus(UserInfo sessionUserInfo,Long statusId){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.STATUSID, statusId);
		JsonResult result = deleteStatusCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		statusDAO.deleteStatusById(statusId, sessionUserInfo.getUserId());
		statusDAO.updateStatusCommentNumByStatusId(statusId);
		userInfoDAO.updateUserStatusNumByUserId(sessionUserInfo.getUserId());
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,statusId);
	}
	
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
	public JsonResult deleteComment(UserInfo sessionUserInfo,Long commentId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.COMMENTID, commentId);
		JsonResult result = deleteCommentCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		CommentInfo comment = (CommentInfo)para.get(CheckParam.TEMP_COMMENTINFO);
		statusDAO.deleteCommentById(commentId);
		statusDAO.updateStatusCommentNumByStatusId(comment.getStatusId());
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
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
	public PaginationDTO<StatusInfoWithFwdSrc> findUserStatusPage(long pageNum, long pageSize,Long userId){
		return statusDAO.findUserStatusPage(pageNum, pageSize, userId);
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
	public PaginationDTO<CommentInfoWithStatus> findCommentInPage(long pageNum, long pageSize,Long userId){
		return statusDAO.findCommentInPage(pageNum, pageSize, userId);
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
	public PaginationDTO<CommentInfoWithStatus> findCommentOutPage(long pageNum, long pageSize,Long userId){
		return statusDAO.findCommentOutPage(pageNum, pageSize, userId);
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
	public PaginationDTO<StatusInfoWithFwdSrc> findAtMeStatusPage(long pageNum, long pageSize,Long userId){
		return statusDAO.findAtMeStatusPage(pageNum, pageSize, userId);
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
	public PaginationDTO<CommentInfoWithStatus> findAtMeCommentPage(long pageNum, long pageSize,Long userId){
		return statusDAO.findAtMeCommentPage(pageNum, pageSize, userId);
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
		return statusDAO.findBeforeStatusList(userId, statusId, rownum);
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
		return statusDAO.findAfterStatusList(userId, statusId, rownum);
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
		return statusDAO.countAfterStatus(userId, statusId);
	}

	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

	public void setPostStatusCheckContext(CheckContext postStatusCheckContext) {
		this.postStatusCheckContext = postStatusCheckContext;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public void setForwardStatusCheckContext(CheckContext forwardStatusCheckContext) {
		this.forwardStatusCheckContext = forwardStatusCheckContext;
	}

	public void setPostCommentCheckContext(CheckContext postCommentCheckContext) {
		this.postCommentCheckContext = postCommentCheckContext;
	}

	public void setDeleteStatusCheckContext(CheckContext deleteStatusCheckContext) {
		this.deleteStatusCheckContext = deleteStatusCheckContext;
	}

	public void setDeleteCommentCheckContext(CheckContext deleteCommentCheckContext) {
		this.deleteCommentCheckContext = deleteCommentCheckContext;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public AlbumService getAlbumService() {
		return albumService;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

}
