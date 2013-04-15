package com.jijizu.core.group.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jijizu.base.util.DateUtil;
import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.album.dao.AlbumDAO;
import com.jijizu.core.album.dto.AlbumInfo;
import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.cache.service.CacheService;
import com.jijizu.core.check.CheckContext;
import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.email.dto.EmailTemplate;
import com.jijizu.core.email.service.EmailService;
import com.jijizu.core.follow.dao.FollowDAO;
import com.jijizu.core.group.dao.GroupDAO;
import com.jijizu.core.group.dto.GroupInfo;
import com.jijizu.core.group.dto.GroupUsers;
import com.jijizu.core.group.service.GroupService;
import com.jijizu.core.mail.service.MailService;
import com.jijizu.core.notice.service.NoticeService;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.ChildInfo;
import com.jijizu.core.user.dto.UserInfo;

public class GroupServiceImpl implements GroupService {

	private GroupDAO groupDAO;
	
	private UserInfoDAO userInfoDAO;
	
	private AlbumDAO albumDAO;
	
	private MailService mailService;
	
	private CacheService cacheService;
	
	private EmailService emailService;
	
	private FollowDAO followDAO;
	
	private NoticeService noticeService;
	
	/**
	 * 创建集集组email模板
	 */
	private EmailTemplate addGroupEmailTemplate;
	
	/**
	 * 发起集集组验证
	 */
	private CheckContext addGroupCheckContext;
	
	/**
	 * 参加集集组验证
	 */
	private CheckContext enterGroupCheckContext;
	
	/**
	 * 加入等待列表验证
	 */
	private CheckContext enterWaitingListCheckContext;
	
	/**
	 * 取消参加集集组验证
	 */
	private CheckContext cancelEnterGroupCheckContext;
	
	/**
	 * 删除集集组验证
	 */
	private CheckContext deleteGroupCheckContext;
	
	/**
	 * 通过用户参加集集组验证
	 */
	private CheckContext passGroupUserCheckContext;
	
	/**
	 * 拒绝用户参加集集组验证
	 */
	private CheckContext refuseGroupUserCheckContext;
	
	/**
	 * 更新集集组验证
	 */
	private CheckContext updateGroupCheckContext;
	
	/**
	 * 复制照片到集集组验证
	 */
	private CheckContext copyPhotoToGroupCheckContext;
	
	/**
	 * 批量上传图片验证
	 */
	private CheckContext uploadPhotoBatchCheckContext;
	
	/**   
	 *******************************************************************************
	 * @function : 批量上传图片
	 * @param sessionUserInfo
	 * @param photoList
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-17   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult uploadPhotoBatch(UserInfo sessionUserInfo,List<PhotoInfo> photoList,Long groupId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.GROUPID, groupId);
		JsonResult result = uploadPhotoBatchCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		AlbumInfo album = getGroupDefaultAlbum(groupId);
		if(album != null && photoList != null && photoList.size() > 0){
			for(PhotoInfo photo : photoList){
				if(photo != null){
					Long photoId = albumDAO.savePhoto(photo.getName(), photo.getContent(), album.getAlbumId(), photo.getImgUrl(), null, groupId, sessionUserInfo.getUserId());
					if(album.getDefaultPhotoId() == null){
						albumDAO.updateAlbumDefaultPhoto(photoId, album.getAlbumId());
					}
				}
			}
			albumDAO.updateAlbumPhotoNum(album.getAlbumId());
		}
	
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取集集组默认相册
	 * @param groupId
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-17   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private AlbumInfo getGroupDefaultAlbum(Long groupId){
		AlbumInfo album = albumDAO.getGroupDefaultAlbum(groupId);
		GroupInfo group = groupDAO.getGroupInfoById(groupId);
		if(album == null && group != null){
			Long albumId = albumDAO.saveAlbum(CommonConstant.ALBUM_NAME_DEFAULT, null, groupId, null, group.getUserId(), group.getUserId());
			album = albumDAO.getAlbumById(albumId);
		}
		return album;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 集集组中发布微薄照片
	 * @param userId
	 * @param mediaUrl
	 * @param statusId
	 * @param groupId
	 * @param content
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
	public Long postGroupPhoto(Long userId,String mediaUrl,Long statusId,Long groupId,String content){
		AlbumInfo album = this.getGroupDefaultAlbum(groupId);
		Long photoId = albumDAO.savePhoto(null, content, album.getAlbumId(), mediaUrl, statusId, groupId, userId);
		if(album.getDefaultPhotoId() == null){
			albumDAO.updateAlbumDefaultPhoto(photoId, album.getAlbumId());
		}
		albumDAO.updateAlbumPhotoNum(album.getAlbumId());
		return photoId;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 复制照片到集集组
	 * @param sessionUserInfo
	 * @param photoIds
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-17   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult copyPhotoToGroup(UserInfo sessionUserInfo,String photoIds,Long groupId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.GROUPID, groupId);
		JsonResult result = copyPhotoToGroupCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		if(StringUtil.isNotNullOrEmpty(photoIds)){
			AlbumInfo album = this.getGroupDefaultAlbum(groupId);
			if(album != null){
				String [] ids = photoIds.split("-");
				for(String id : ids){
					Long photoId = Long.parseLong(id);
					PhotoInfo photo = albumDAO.getPhotoInfoById(photoId);
					if(photo != null){
						photoId = albumDAO.savePhoto(photo.getName(), photo.getContent(), album.getAlbumId(),photo.getImgUrl(), photo.getStatusId(), groupId, sessionUserInfo.getUserId());
						if(album.getDefaultPhotoId() == null){
							albumDAO.updateAlbumDefaultPhoto(photoId, album.getAlbumId());
						}
					}
				}
				albumDAO.updateAlbumPhotoNum(album.getAlbumId());
			}
		}
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 发起集集组
	 * @param sessionUserInfo
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-29   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult addGroup(UserInfo sessionUserInfo,Map<String, Object> para){
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.MYFILE+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.POSTERIMGURL+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.ENTERTYPEDETAIL+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.STARTAGE+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.ENDAGE+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.ONLYONE+CheckParam.IS_NEED_NULL_CHECK, false);
		JsonResult result = addGroupCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		Date startDate = DateUtil.parseDate((String)para.get(CheckParam.STARTDAY), (String)para.get(CheckParam.STARTHOUR), (String)para.get(CheckParam.STARTMINUTE));
		Date endDate = DateUtil.parseDate((String)para.get(CheckParam.ENDDAY), (String)para.get(CheckParam.ENDHOUR), (String)para.get(CheckParam.ENDMINUTE));
		Date applyEndDate = DateUtil.parseDate((String)para.get(CheckParam.APPLYENDDAY), (String)para.get(CheckParam.APPLYENDHOUR), (String)para.get(CheckParam.APPLYENDMINUTE));
		para.put(CheckParam.STARTDATE, startDate);
		para.put(CheckParam.ENDDATE, endDate);
		para.put(CheckParam.APPLYENDDATE, applyEndDate);
		para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		if(para.get(CheckParam.ONLYONE) == null) {
			para.remove(CheckParam.PARENTSLIMIT);
		}
		
		Long groupId = groupDAO.saveGroup(para);
		userInfoDAO.updateUserGroupNumByUserId(sessionUserInfo.getUserId());
		if(groupId != null){
			//生成默认相册
			albumDAO.saveAlbum(CommonConstant.ALBUM_NAME_DEFAULT, null, groupId, null, sessionUserInfo.getUserId(), sessionUserInfo.getUserId());
			
			GroupInfo groupInfo = groupDAO.getGroupInfoById(groupId, para);
			
			//发送邮件
			this.sendAddGroupMail(sessionUserInfo,groupInfo);
			
		}
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,groupId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 创建集集组邮件
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void sendAddGroupMail(UserInfo fromUser,GroupInfo groupInfo){
		List<UserInfo> friends = followDAO.findFriendsPage(0, 1000, fromUser.getUserId(), null).getResult();
		if(friends != null && friends.size() > 0){
			Map<String,String> model = new HashMap<String,String>();
			model.put("fromUser", fromUser.getName());
			model.put("groupName", groupInfo.getTitle());
			model.put("url", JijizuUtil.getAbsolutePath()+"/group/"+groupInfo.getGroupId());
			String content = emailService.getContent(addGroupEmailTemplate.getLocation(), model);
			for(UserInfo u : friends){
				emailService.sendEmail(u.getEmail(), StringUtil.formatString(addGroupEmailTemplate.getSubject(), fromUser.getName()), content, true);
			}
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新集集组
	 * @param sessionUserInfo
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-6   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult updateGroup(UserInfo sessionUserInfo,Map<String,Object> para){
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.MYFILE+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.ENTERTYPEDETAIL+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.POSTERIMGURL+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.STARTAGE+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.ENDAGE+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.ONLYONE+CheckParam.IS_NEED_NULL_CHECK, false);
		JsonResult result = updateGroupCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		Date startDate = DateUtil.parseDate((String)para.get(CheckParam.STARTDAY), (String)para.get(CheckParam.STARTHOUR), (String)para.get(CheckParam.STARTMINUTE));
		Date endDate = DateUtil.parseDate((String)para.get(CheckParam.ENDDAY), (String)para.get(CheckParam.ENDHOUR), (String)para.get(CheckParam.ENDMINUTE));
		Date applyEndDate = DateUtil.parseDate((String)para.get(CheckParam.APPLYENDDAY), (String)para.get(CheckParam.APPLYENDHOUR), (String)para.get(CheckParam.APPLYENDMINUTE));
		para.put(CheckParam.STARTDATE, startDate);
		para.put(CheckParam.ENDDATE, endDate);
		para.put(CheckParam.APPLYENDDATE, applyEndDate);
		para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		if(para.get(CheckParam.ONLYONE) == null) {
			para.remove(CheckParam.PARENTSLIMIT);
		}
		
		groupDAO.updateGroup(para);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,para.get(CheckParam.GROUPID));
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据ID获取集集组信息
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-31   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public GroupInfo getGroupInfoById(Long groupId){
		return groupDAO.getGroupInfoById(groupId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 
	 * @param groupId
	 * @param userId
	 * @param isLoadGroupUsers		是否加载groupUsers
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public GroupInfo getGroupInfoById(Long groupId,Long userId,boolean isLoadGroupUsers){
		return groupDAO.getGroupInfoById(groupId, userId, isLoadGroupUsers);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询集集组
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-31   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<GroupInfo> findGroupInfoPage(long pageNum, long pageSize){
		return groupDAO.findGroupInfoPage(pageNum, pageSize);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询我发起的集集组
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-31   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<GroupInfo> findMyCreateGroupPage(long pageNum, long pageSize,Long userId){
		return groupDAO.findMyCreateGroupPage(pageNum, pageSize, userId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 加入集集组
	 * @param sessionUserInfo
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-31   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult enterGroup(UserInfo sessionUserInfo,Map<String,Object> para){
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		if(sessionUserInfo != null){
			para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		}
		if(!para.containsKey(CheckParam.CHILDIDS)) {
			para.put(CheckParam.CHILDIDS, null);
		}
		para.put(CheckParam.CHILDIDS+CheckParam.IS_NEED_NULL_CHECK, false);
		JsonResult result = enterGroupCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		//保存参加集集组孩子信息
		Long groupId = Long.parseLong(para.get(CheckParam.GROUPID).toString());
		String childNames = this.saveGroupChilds(sessionUserInfo.getUserId(), groupId,(String)para.get(CheckParam.CHILDIDS));
		para.put(CheckParam.CHILDNAME, childNames);
		
		Long groupUserId = groupDAO.saveGroupUsers(para);
		
		groupDAO.updateGroupEnterNum(groupId);
		
		GroupInfo group = groupDAO.getGroupInfoById(groupId, para);
		this.sendAuditMail(sessionUserInfo.getUserId(), group);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,groupUserId);
		}
		
	/**   
	 *******************************************************************************
	 * @function : 发送审核私信
	 * @param userInfo
	 * @param group
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void sendAuditMail(Long userId,GroupInfo group) {
		if(this.isNeedAudit(group.getEnterType(), group.getEnterTypeDetail())) {
			UserInfo userInfo = userInfoDAO.getUserInfoById(userId);
			String content = "@"+userInfo.getAtName()+" 申请参加你所创建的集集组\""+group.getTitle()+"\""+JijizuUtil.getAbsolutePath()+"/group/manageUser.jspa?groupId="+group.getGroupId();
			mailService.sendMail(userInfo.getUserId(),group.getUserId(),content,false);
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 加入等待列表
	 * @param sessionUserInfo
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult enterWaitingList(UserInfo sessionUserInfo,Map<String,Object> para) {
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		if(sessionUserInfo != null){
			para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		}
		if(!para.containsKey(CheckParam.CHILDIDS)) {
			para.put(CheckParam.CHILDIDS, null);
		}
		para.put(CheckParam.CHILDIDS+CheckParam.IS_NEED_NULL_CHECK, false);
		JsonResult result = enterWaitingListCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		//保存参加集集组孩子信息
		Long groupId = Long.parseLong(para.get(CheckParam.GROUPID).toString());
		String childNames = this.saveGroupChilds(sessionUserInfo.getUserId(), groupId,(String)para.get(CheckParam.CHILDIDS));
		para.put(CheckParam.CHILDNAME, childNames);
		
		para.put(CheckParam.JOINSTATUS, SysDataDicConstant.GROUP_JOIN_STATUS_WAIT);
		Long groupUserId = groupDAO.saveGroupUsers(para);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,groupUserId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存孩子参加集集组信息
	 * @param userId
	 * @param groupId
	 * @param childIds
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-25   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private String saveGroupChilds(Long userId,Long groupId,String childIds) {
		String childName="";
		if(StringUtil.isNotNullOrEmpty(childIds)) {
			String [] childIdArray = childIds.split(",");
			for(String childId:childIdArray) {
				ChildInfo childInfo = userInfoDAO.getChildInfoById(Long.parseLong(childId));
				if(childInfo != null) {
					childName+=childInfo.getName()+",";
					groupDAO.saveGroupChilds(userId, Long.parseLong(childId), groupId);
				}
			}
		}
		if(StringUtil.isNotNullOrEmpty(childName)) {
			childName = childName.substring(0,childName.length()-1);
		}
		return childName;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 取消参加集集组
	 * @param sessionUserInfo
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult cancelEnterGroup(UserInfo sessionUserInfo,Long groupId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.GROUPID, groupId);
		if(sessionUserInfo != null){
			para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		}
		
		JsonResult result = cancelEnterGroupCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		groupDAO.deleteGroupUsersByGroupIdAndUserId(sessionUserInfo.getUserId(), groupId);
		groupDAO.deleteGroupChilds(sessionUserInfo.getUserId(), groupId);
		groupDAO.updateGroupEnterNum(groupId);
		userInfoDAO.updateUserGroupNumByUserId(sessionUserInfo.getUserId());
		
		//完成集集组参加人数等修改后，将等待区域中的用户加入集集组
		this.waitingUserToEnterGroup(groupId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 将等待区域中的用户加入集集组
	 * @param groupId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void waitingUserToEnterGroup(Long groupId) {
		//这里去最新的集集组信息
		GroupInfo group = groupDAO.getGroupInfoById(groupId);
		//如果集集组有报名人数限制
		if(group.getApplyNum() != null && group.getApplyNum().longValue() > 0) {
			List<GroupUsers> groupUsers = groupDAO.findGroupUsersByJoinStatus(groupId, SysDataDicConstant.GROUP_JOIN_STATUS_WAIT);
			//有用户在等待区域
			if(groupUsers != null && groupUsers.size() > 0) {
				Long hasEnterNum = group.getEnterNum();
				//可参加的人数
				Long canEnterNum = group.getApplyNum()-hasEnterNum;
				for(GroupUsers user:groupUsers){
					//本次要参加的人数
					Long enterNumThisTime = user.getAdultNum();
					if(StringUtil.isNotNullOrEmpty(user.getChildName())) {
						enterNumThisTime += user.getChildName().split(",").length;
					}
					if(enterNumThisTime.longValue() <= canEnterNum) {
						if(!this.isNeedAudit(group.getEnterType(), group.getEnterTypeDetail())){
							groupDAO.updateGroupUserJoinStatus(groupId, user.getUserId(), SysDataDicConstant.GROUP_JOIN_STATUS_PASS);
						}else {
							groupDAO.updateGroupUserJoinStatus(groupId, user.getUserId(), SysDataDicConstant.GROUP_JOIN_STATUS_AUDIT);
						}
						groupDAO.updateGroupEnterNum(groupId);
						canEnterNum -= enterNumThisTime;
						
						String content = "您所等待集集组\""+group.getTitle()+"\"由于有用户取消参加该集集组，因此您已经顺利参加该集集组，详情见"+JijizuUtil.getAbsolutePath()+"/group/"+groupId;
						noticeService.sendNotice(user.getUserId(), content,false);
						this.sendAuditMail(user.getUserId(), group);
					}
				}
			}
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否需要审核
	 * @param enterType
	 * @param enterTypeDetail
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private boolean isNeedAudit(String enterType,String enterTypeDetail) {
		//如果集集组为公开招募或仅好友参加中的无需审核
		if(SysDataDicConstant.GROUP_ENTER_TYPE_PUBIC.equals(enterType) || 
				(SysDataDicConstant.GROUP_ENTER_TYPE_FRIENDS.equals(enterType) && SysDataDicConstant.GROUP_ENTER_TYPE_DETAIL_NOT_NEED_AUDIT.equals(enterTypeDetail))){
			return false;
		}else {
			return true;
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询我参加的集集组
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-2   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<GroupInfo> findMyEnterGroupPage(long pageNum, long pageSize,Long userId){
		return groupDAO.findMyEnterGroupPage(pageNum, pageSize, userId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询集集组参加的用户
	 * @param groupId
	 * @param pageNum
	 * @param pageSize
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
	public PaginationDTO<GroupUsers> findGroupUsersPage(Long groupId, long pageNum, long pageSize){
		return groupDAO.findGroupUsersPage(groupId, pageNum, pageSize);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询集集组参加的用户-包含等待区域的用户
	 * @param groupId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<GroupUsers> findGroupUsersAllPage(Long groupId, long pageNum, long pageSize){
		return groupDAO.findGroupUsersAllPage(groupId, pageNum, pageSize);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询推荐的集集组
	 * @param userId
	 * @param city
	 * @param rownum
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
	public List<GroupInfo> findRecommendGroup(UserInfo sessionUserInfo,long rownum){
		String city = null;
		Long userId = null;
		if(sessionUserInfo != null){
			UserInfo userInfo = userInfoDAO.getUserInfoById(sessionUserInfo.getUserId());
			if(userInfo != null){
				userId = userInfo.getUserId();
				city = userInfo.getCity();
			}
		}
		
		return groupDAO.findRecommendGroupByCity(userId, city, rownum);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询热门集集组
	 * @param sessionUserInfo
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-13   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<GroupInfo> findHotGroup(UserInfo sessionUserInfo,long rownum){
		String city = null;
		if(sessionUserInfo != null){
			city = sessionUserInfo.getCity();
		}
		
		List<GroupInfo> groupList = null;
		try {
			groupList = (List<GroupInfo>)cacheService.getSnaInfo(CacheConstant.HOT_GROUPS_KEY_NAME+city);
		} catch (Exception e) {
			groupList = this.findHotGroupFromDB(city, rownum);
		}
		if(groupList == null){
			groupList = this.findHotGroupFromDB(city, rownum);
		}
		
		return groupList;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 从数据库中获取热门集集组
	 * @param city
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-13   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private List<GroupInfo> findHotGroupFromDB(String city,long rownum){
		List<GroupInfo> groupList = groupDAO.findHotGroupByCity(city, rownum);
		try {
			cacheService.setSnaInfo(CacheConstant.HOT_GROUPS_KEY_NAME+city, (Serializable)groupList, CacheConstant.CACHE_EXPRIATION_ONE_HOUR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupList;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询集集组微博
	 * @param groupId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<StatusInfoWithFwdSrc> findGroupStatusPage(Long groupId,
			long pageNum, long pageSize){
		return groupDAO.findGroupStatusPage(groupId, pageNum, pageSize);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除集集组
	 * @param sessionUserInfo
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult deleteGroup(UserInfo sessionUserInfo,Long groupId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.GROUPID, groupId);
		JsonResult result = deleteGroupCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		groupDAO.deleteGroup(groupId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_ERROR_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 集集组通过审核
	 * @param sessionUserInfo
	 * @param userId
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult passGroupUser(UserInfo sessionUserInfo,Long userId,Long groupId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.USERID, userId);
		para.put(CheckParam.GROUPID, groupId);
		JsonResult result = passGroupUserCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		groupDAO.passGroupUser(groupId, userId);
		
		GroupInfo group = groupDAO.getGroupInfoById(groupId, para);
		UserInfo userInfo = userInfoDAO.getUserInfoById(sessionUserInfo.getUserId());
		String content = "@"+userInfo.getAtName()+" 通过了您所申请参加的集集组 \""+group.getTitle()+"\""
			+ JijizuUtil.getAbsolutePath()+"/group/"+groupId;
		mailService.sendMail(sessionUserInfo.getUserId(), userId, content,false);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_ERROR_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 拒绝用户参加集集组
	 * @param sessionUserInfo
	 * @param userId
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult refuseGroupUser(UserInfo sessionUserInfo,Long userId,Long groupId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.USERID, userId);
		para.put(CheckParam.GROUPID, groupId);
		JsonResult result = refuseGroupUserCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		groupDAO.deleteGroupUser(groupId, userId);
		
		GroupInfo group = groupDAO.getGroupInfoById(groupId, para);
		UserInfo userInfo = userInfoDAO.getUserInfoById(sessionUserInfo.getUserId());
		String content = "@"+userInfo.getAtName()+" 拒绝了您所申请参加的集集组 \""+group.getTitle()+"\""
			+ JijizuUtil.getAbsolutePath()+"/group/"+groupId;
		mailService.sendMail(sessionUserInfo.getUserId(), userId, content,false);
	
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_ERROR_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询正在召集的集集组
	 * @param rownum
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
	public List<GroupInfo> findConveneGroup(Long rownum){
		return groupDAO.findConveneGroup(rownum);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 从缓存中获取正在招募的集集组
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-8   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<GroupInfo> findConveneGroupFromCache(Long rownum){
		List<GroupInfo> groupList = null;
		try {
			groupList = (List<GroupInfo>)cacheService.getSnaInfo(CacheConstant.CONVENE_GROUP_KEY_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			groupList = this.findConveneGroup(rownum);
		}
		if(groupList == null) {
			groupList = this.findConveneGroup(rownum);
			try {
				cacheService.setSnaInfo(CacheConstant.CONVENE_GROUP_KEY_NAME, (Serializable)groupList, CacheConstant.CACHE_EXPRIATION_ONE_HOUR);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return groupList;
	}
	
	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public void setAddGroupCheckContext(CheckContext addGroupCheckContext) {
		this.addGroupCheckContext = addGroupCheckContext;
	}

	public void setEnterGroupCheckContext(CheckContext enterGroupCheckContext) {
		this.enterGroupCheckContext = enterGroupCheckContext;
	}

	public void setCancelEnterGroupCheckContext(
			CheckContext cancelEnterGroupCheckContext) {
		this.cancelEnterGroupCheckContext = cancelEnterGroupCheckContext;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	public void setDeleteGroupCheckContext(CheckContext deleteGroupCheckContext) {
		this.deleteGroupCheckContext = deleteGroupCheckContext;
	}

	public void setPassGroupUserCheckContext(CheckContext passGroupUserCheckContext) {
		this.passGroupUserCheckContext = passGroupUserCheckContext;
	}

	public void setRefuseGroupUserCheckContext(
			CheckContext refuseGroupUserCheckContext) {
		this.refuseGroupUserCheckContext = refuseGroupUserCheckContext;
	}

	public void setUpdateGroupCheckContext(CheckContext updateGroupCheckContext) {
		this.updateGroupCheckContext = updateGroupCheckContext;
	}

	public void setAlbumDAO(AlbumDAO albumDAO) {
		this.albumDAO = albumDAO;
	}

	public void setCopyPhotoToGroupCheckContext(
			CheckContext copyPhotoToGroupCheckContext) {
		this.copyPhotoToGroupCheckContext = copyPhotoToGroupCheckContext;
	}

	public void setUploadPhotoBatchCheckContext(
			CheckContext uploadPhotoBatchCheckContext) {
		this.uploadPhotoBatchCheckContext = uploadPhotoBatchCheckContext;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setAddGroupEmailTemplate(EmailTemplate addGroupEmailTemplate) {
		this.addGroupEmailTemplate = addGroupEmailTemplate;
	}

	public void setFollowDAO(FollowDAO followDAO) {
		this.followDAO = followDAO;
	}
	
	public void setEnterWaitingListCheckContext(
			CheckContext enterWaitingListCheckContext) {
		this.enterWaitingListCheckContext = enterWaitingListCheckContext;
}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

}
