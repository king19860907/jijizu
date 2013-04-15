package com.jijizu.core.follow.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.jijizu.core.dao.IbatisBaseDAO;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.follow.dao.FollowDAO;
import com.jijizu.core.user.dto.UserInfo;

public class FollowDAOImpl extends IbatisBaseDAO
	implements FollowDAO{

	/**   
	 *******************************************************************************
	 * @function : 关注某人
	 * @param userId		关注发起人
	 * @param followUserId	被关注人
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-31   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public long followOne(Long userId,Long followUserId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("followUserId", followUserId);
		return (Long)this.insert("followOne", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 判断两个用户是否互相关注
	 * @param userId1
	 * @param userId2
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-31   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean isFollowEachOther(Long userId1,Long userId2){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId1", userId1);
		para.put("userId2", userId2);
		return ((Long)selectOneObject("isFollowEachOther", para) > 0);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取好友
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @param nickName
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-4   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findFriendsPage(long pageNum, long pageSize,Long userId,String nickName) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("nickName", nickName);
		PaginationDTO<UserInfo> pagination = new PaginationDTO<UserInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findFriendsPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取共同好友
	 * @param pageNum
	 * @param pageSize
	 * @param userId1
	 * @param userId2
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findCommonFriendsPage(long pageNum,long pageSize,Long userId1,Long userId2){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId1", userId1);
		para.put("userId2", userId2);
		PaginationDTO<UserInfo> pagination = new PaginationDTO<UserInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findCommonFriendsPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 取消关注
	 * @param userId
	 * @param followUserId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void followCancel(Long userId,Long followUserId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("followUserId", followUserId);
		this.delete("followCancel", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取发送申请好友
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-8   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findSendApplyFriendsPage(long pageNum,long pageSize,Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<UserInfo> pagination = new PaginationDTO<UserInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findSendApplyFriendsPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否已关注某人
	 * @param userId
	 * @param followUserId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-19   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean hasFollowUser(Long userId,Long followUserId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("followUserId", followUserId);
		Long count = (Long)this.selectOneObject("hasFollowUser", para);
		return count.longValue()>0;
	}
	
}
