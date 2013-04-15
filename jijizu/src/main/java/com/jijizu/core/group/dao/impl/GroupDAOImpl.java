package com.jijizu.core.group.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.dao.IbatisBaseDAO;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.group.dao.GroupDAO;
import com.jijizu.core.group.dto.GroupInfo;
import com.jijizu.core.group.dto.GroupUsers;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.user.dto.UserInfo;

public class GroupDAOImpl extends IbatisBaseDAO
	implements GroupDAO{

	/**   
	 *******************************************************************************
	 * @function : 保存集集组
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
	public Long saveGroup(Map<String, Object> para){
		return (Long)this.insert("saveGroup", para);
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
		return (GroupInfo)this.selectOneObject("getGroupInfoById", groupId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据ID获取集集组信息-验证框架使用
	 * @param groupId
	 * @param para
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
	public GroupInfo getGroupInfoById(Long groupId,Map<String,Object> para){
		GroupInfo groupInfo = null;
		if(para != null && para.get(CheckParam.TEMP_GROUPINFO) != null){
			groupInfo = (GroupInfo)para.get(CheckParam.TEMP_GROUPINFO);
		}
		if(groupInfo == null){
			groupInfo = (GroupInfo)this.selectOneObject("getGroupInfoById", groupId);
			if(groupInfo != null){
				para.put(CheckParam.TEMP_GROUPINFO, groupInfo);
			}
		}
		return groupInfo;
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
		GroupInfo groupInfo = (GroupInfo)this.selectOneObject("getGroupInfoById", groupId);
		if(isLoadGroupUsers){
			GroupUsers groupUsers = this.getGroupUsersByGroupIdAndUserId(userId, groupId);
			groupInfo.setGroupUsers(groupUsers);
		}
		return groupInfo;
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
	public PaginationDTO<GroupInfo> findGroupInfoPage(long pageNum, long pageSize) {
		Map<String, Object> para = new HashMap<String, Object>();
		PaginationDTO<GroupInfo> pagination = new PaginationDTO<GroupInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findGroupInfoPage", pagination);
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
	public PaginationDTO<GroupInfo> findMyCreateGroupPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);	
		PaginationDTO<GroupInfo> pagination = new PaginationDTO<GroupInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findMyCreateGroupPage", pagination);
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
	public PaginationDTO<GroupInfo> findMyEnterGroupPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);	
		PaginationDTO<GroupInfo> pagination = new PaginationDTO<GroupInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findMyEnterGroupPage", pagination);
	}
	
	/**
	 * 
	 *******************************************************************************
	 * @function : 保存参加集集组记录
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
	public Long saveGroupUsers(Map<String,Object> para){
		return (Long)this.insert("saveGroupUsers", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据集集组ID和用户ID获取用户参加集集组信息
	 * @param userId
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
	public GroupUsers getGroupUsersByGroupIdAndUserId(Long userId,Long groupId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("groupId", groupId);
		return (GroupUsers)this.selectOneObject("getGroupUsersByGroupIdAndUserId", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据集集组ID和用户ID删除用户参加集集组信息
	 * @param userId
	 * @param groupId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteGroupUsersByGroupIdAndUserId(Long userId,Long groupId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("groupId", groupId);
		this.delete("deleteGroupUsersByGroupIdAndUserId", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新集集组参加人数
	 * @param groupId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateGroupEnterNum(Long groupId){
		this.update("updateGroupEnterNum",groupId);
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
	public PaginationDTO<GroupUsers> findGroupUsersPage(Long groupId, long pageNum, long pageSize) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("groupId", groupId);

		PaginationDTO<GroupUsers> pagination = new PaginationDTO<GroupUsers>();

		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);

		return this.selectPage("findGroupUsersPage", pagination);
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
	public PaginationDTO<GroupUsers> findGroupUsersAllPage(Long groupId, long pageNum, long pageSize) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("groupId", groupId);

		PaginationDTO<GroupUsers> pagination = new PaginationDTO<GroupUsers>();

		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);

		return this.selectPage("findGroupUsersAllPage", pagination);
	}
	
	
	/**   
	 *******************************************************************************
	 * @function : 根据城市查询推荐的集集组
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
	public List<GroupInfo> findRecommendGroupByCity(Long userId,String city,long rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("city", city);
		para.put("rownum", rownum);
		return (List<GroupInfo>)this.select("findRecommendGroupByCity", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据城市查询热门集集组
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
	public List<GroupInfo> findHotGroupByCity(String city,long rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("city", city);
		para.put("rownum", rownum);
		return (List<GroupInfo>)this.select("findHotGroupByCity", para);
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
			long pageNum, long pageSize) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("groupId", groupId);
		PaginationDTO<StatusInfoWithFwdSrc> pagination = new PaginationDTO<StatusInfoWithFwdSrc>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findGroupStatusPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除集集组
	 * @param groupId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteGroup(Long groupId){
		this.update("deleteGroup",groupId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 集集组通过审核
	 * @param groupId
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void passGroupUser(Long groupId,Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("groupId", groupId);
		para.put("userId", userId);
		this.update("passGroupUser",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除用户参加集集组信息
	 * @param groupId
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteGroupUser(Long groupId,Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("groupId", groupId);
		para.put("userId", userId);
		this.delete("deleteGroupUser", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新集集组
	 * @param para
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-6   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateGroup(Map<String,Object> para){
		this.update("updateGroup",para);
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
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("rownum", rownum);
		return (List<GroupInfo>)this.select("findConveneGroup", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存孩子参加集集组信息
	 * @param userId
	 * @param childId
	 * @param groupId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-25   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveGroupChilds(Long userId,Long childId,Long groupId) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("userId", userId);
		para.put("childId", childId);
		para.put("groupId", groupId);
		return (Long)this.insert("saveGroupChilds", para);
}
	
	/**   
	 *******************************************************************************
	 * @function : 删除孩子参加集集组信息
	 * @param userId
	 * @param groupId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-25   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteGroupChilds(Long userId,Long groupId) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("userId", userId);
		para.put("groupId", groupId);
		this.delete("deleteGroupChilds", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据加入状态查询集集组用户
	 * @param groupId
	 * @param joinStatus
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
	public List<GroupUsers> findGroupUsersByJoinStatus(Long groupId,String joinStatus){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("groupId", groupId);
		para.put("joinStatus", joinStatus);
		return (List<GroupUsers>)this.select("findGroupUsersByJoinStatus", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 修改集集参加用户的加入状态
	 * @param groupId
	 * @param userId
	 * @param joinStatus
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateGroupUserJoinStatus(Long groupId,Long userId,String joinStatus) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("groupId", groupId);
		para.put("userId", userId);
		para.put("joinStatus", joinStatus);
		this.update("updateGroupUserJoinStatus", para);
	}
	
}
