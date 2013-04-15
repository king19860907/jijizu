package com.jijizu.core.group.service;

import java.util.List;
import java.util.Map;

import com.jijizu.core.album.dto.PhotoInfo;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.group.dto.GroupInfo;
import com.jijizu.core.group.dto.GroupUsers;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.user.dto.UserInfo;

public interface GroupService {
	
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
	JsonResult uploadPhotoBatch(UserInfo sessionUserInfo,List<PhotoInfo> photoList,Long groupId);
	
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
	JsonResult copyPhotoToGroup(UserInfo sessionUserInfo,String photoIds,Long groupId);

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
	JsonResult addGroup(UserInfo sessionUserInfo,Map<String, Object> para);
	
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
	GroupInfo getGroupInfoById(Long groupId);
	
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
	GroupInfo getGroupInfoById(Long groupId,Long userId,boolean isLoadGroupUsers);
	
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
	PaginationDTO<GroupInfo> findGroupInfoPage(long pageNum, long pageSize);
	
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
	PaginationDTO<GroupInfo> findMyCreateGroupPage(long pageNum, long pageSize,Long userId);
	
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
	JsonResult enterGroup(UserInfo sessionUserInfo,Map<String,Object> para);
	
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
	JsonResult cancelEnterGroup(UserInfo sessionUserInfo,Long groupId);
	
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
	PaginationDTO<GroupInfo> findMyEnterGroupPage(long pageNum, long pageSize,Long userId);
	
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
	PaginationDTO<GroupUsers> findGroupUsersPage(Long groupId, long pageNum, long pageSize);
	
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
	List<GroupInfo> findRecommendGroup(UserInfo sessionUserInfo,long rownum);
	
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
	List<GroupInfo> findHotGroup(UserInfo sessionUserInfo,long rownum);
	
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
	PaginationDTO<StatusInfoWithFwdSrc> findGroupStatusPage(Long groupId,
			long pageNum, long pageSize);
	
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
	JsonResult deleteGroup(UserInfo sessionUserInfo,Long groupId);
	
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
	JsonResult passGroupUser(UserInfo sessionUserInfo,Long userId,Long groupId);
	
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
	JsonResult refuseGroupUser(UserInfo sessionUserInfo,Long userId,Long groupId);
	
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
	JsonResult updateGroup(UserInfo sessionUserInfo,Map<String,Object> para);
	
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
	Long postGroupPhoto(Long userId,String mediaUrl,Long statusId,Long groupId,String content);
	
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
	List<GroupInfo> findConveneGroup(Long rownum);
	
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
	List<GroupInfo> findConveneGroupFromCache(Long rownum);
	
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
	JsonResult enterWaitingList(UserInfo sessionUserInfo,Map<String,Object> para);
	
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
	PaginationDTO<GroupUsers> findGroupUsersAllPage(Long groupId, long pageNum, long pageSize);
}
