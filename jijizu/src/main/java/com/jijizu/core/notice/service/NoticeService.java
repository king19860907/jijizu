package com.jijizu.core.notice.service;

import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.notice.dto.NoticeInfo;
import com.jijizu.core.user.dto.UserInfo;

public interface NoticeService {
	
	/**   
	 *******************************************************************************
	 * @function : 删除系统通知
	 * @param sessionUserInfo
	 * @param noticeId
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
	JsonResult deleteNotice(UserInfo sessionUserInfo,Long noticeId);

	/**   
	 *******************************************************************************
	 * @function : 发送通知（用官方账号）
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
	Long sendNotice(Long toUserId,String content);
	
	/**   
	 *******************************************************************************
	 * @function : 
	 * @param toUserId
	 * @param content
	 * @param replaceHtmlTag
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
	Long sendNotice(Long toUserId,String content,boolean replaceHtmlTag);
	
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
	PaginationDTO<NoticeInfo> findNoticePage(long pageNum, long pageSize,Long userId);
	
	/**   
	 *******************************************************************************
	 * @function : 更新系统通知内容
	 * @param sessionUserInfo
	 * @param noticeId
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
	JsonResult updateNoticeContent(UserInfo sessionUserInfo,Long noticeId,String content);
	
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
	void updateNoticeHasReadByUserId(Long userId);
	
}
