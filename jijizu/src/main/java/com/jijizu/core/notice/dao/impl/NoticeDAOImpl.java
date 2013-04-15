package com.jijizu.core.notice.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.jijizu.core.album.dto.AlbumInfo;
import com.jijizu.core.dao.IbatisBaseDAO;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.notice.dao.NoticeDAO;
import com.jijizu.core.notice.dto.NoticeInfo;

public class NoticeDAOImpl extends IbatisBaseDAO implements NoticeDAO {
	
	/**   
	 *******************************************************************************
	 * @function : 保存系统通知
	 * @param fromUserId
	 * @param toUserId
	 * @param content
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveNotice(Long fromUserId,Long toUserId,String content){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("fromUserId", fromUserId);
		para.put("toUserId", toUserId);
		para.put("content", content);
		return (Long)this.insert("saveNotice", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询系统通知
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<NoticeInfo> findNoticePage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<NoticeInfo> pagination = new PaginationDTO<NoticeInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findNoticePage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除系统通知
	 * @param noticeId
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteNotice(Long noticeId,Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("noticeId", noticeId);
		para.put("userId", userId);
		this.update("deleteNotice",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新系统通知内容
	 * @param noticeId
	 * @param userId
	 * @param content
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateNoticeContent(Long noticeId,Long userId,String content){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("noticeId", noticeId);
		para.put("userId", userId);
		para.put("content", content);
		this.update("updateNoticeContent",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 统计新私信数量
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long countNewNoticeNumByUserId(Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		return (Long)this.selectOneObject("countNewNoticeNumByUserId", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据用户更新通知为已读
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateNoticeHasReadByUserId(Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		this.update("updateNoticeHasReadByUserId",para);
	}

}
