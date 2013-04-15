package com.jijizu.core.mail.service;

import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.mail.dto.MailDetail;
import com.jijizu.core.mail.dto.MailShow;
import com.jijizu.core.user.dto.UserInfo;

public interface MailService {
	
	/**   
	 *******************************************************************************
	 * @function : 更新私信内容
	 * @param sessionUserInfo
	 * @param mailShowId
	 * @param mailDetailId
	 * @param content
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	JsonResult updateMailContent(UserInfo sessionUserInfo,Long mailShowId,Long mailDetailId,String content);
	
	/**   
	 *******************************************************************************
	 * @function : 发送私信
	 * @param sessionUserInfo
	 * @param toNickName
	 * @param content
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
	JsonResult sendMail(UserInfo sessionUserInfo,String toNickName,String content);
	
	/**   
	 *******************************************************************************
	 * @function : 发送私信
	 * @param fromUserid
	 * @param toUserId
	 * @param content
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	Long sendMail(Long fromUserId,Long toUserId,String content,boolean replaceHtmlTag);
	
	/**   
	 *******************************************************************************
	 * @function : 查看私信
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
	PaginationDTO<MailShow> findMailShowPage(long pageNum, long pageSize,Long userId);
	
	/**   
	 *******************************************************************************
	 * @function : 删除私信显示记录
	 * @param sessionUserInfo
	 * @param mailShowId
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
	JsonResult deleteShowMail(UserInfo sessionUserInfo,Long mailShowId);
	
	/**   
	 *******************************************************************************
	 * @function : 删除私信详细记录
	 * @param sessionUserInfo
	 * @param mailDetailId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	JsonResult deleteDetailMail(UserInfo sessionUserInfo,Long mailDetailId);
	
	/**   
	 *******************************************************************************
	 * @function : 查看我与某人的私信对话
	 * @param mailShowId
	 * @param pageNum
	 * @param pageSize
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
	PaginationDTO<MailDetail> findMailDetailPage(long mailShowId,long pageNum,Long pageSize);

	/**   
	 *******************************************************************************
	 * @function : 获取私信显示记录
	 * @param mailShowId
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
	MailShow getMailShowById(Long mailShowId);
}
