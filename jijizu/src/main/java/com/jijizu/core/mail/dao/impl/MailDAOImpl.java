package com.jijizu.core.mail.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.jijizu.core.dao.IbatisBaseDAO;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.mail.dao.MailDAO;
import com.jijizu.core.mail.dto.MailDetail;
import com.jijizu.core.mail.dto.MailShow;

public class MailDAOImpl extends IbatisBaseDAO
	implements MailDAO{

	/**   
	 *******************************************************************************
	 * @function : 保存私信
	 * @param fromUserId
	 * @param toUserId
	 * @param mailContent
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
	public Long saveMail(Long fromUserId,Long toUserId,String mailContent){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("fromUserId", fromUserId);
		para.put("toUserId", toUserId);
		para.put("mailContent", mailContent);
		return (Long)this.insert("saveMail", para);
	}
	
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
	public PaginationDTO<MailShow> findMailShowPage(long pageNum, long pageSize,Long userId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<MailShow> pagination = new PaginationDTO<MailShow>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findMailShowPage", pagination);
	}
	
	/**   
     *******************************************************************************
     * @function : 进入私信界面更新最新私信记录数
     * @param userId
     * @param endRowNum
     * @param startRowNum
     *******************************************************************************
     * @creator ：majun   
     * @date ：2011-5-24   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    public void updateNewMailNumByShowMail(Long userId,Long endRowNum,Long startRowNum){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("userId", userId);
    	para.put("endRowNum", endRowNum);
    	para.put("startRowNum", startRowNum);
    	
    	getSqlMapClientTemplate().update("updateNewMailNumByShowMail",para);
    }
    
    /**   
     *******************************************************************************
     * @function : 更新用户的最新私信记录数
     * @param userId
     *******************************************************************************
     * @creator ：majun   
     * @date ：2011-5-24   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    public void updateSnsUserNewMailByUserId(Long userId){
    	getSqlMapClientTemplate().update("updateSnsUserNewMailByUserId",userId);
    }
	
	/**   
	 *******************************************************************************
	 * @function : 根据mail_show_id删除私信记录
	 * @param mailShowId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteMailDetailByShowId(Long mailShowId){
		this.update("deleteMailDetailByShwoId",mailShowId);
	}
	
	/**   
     *******************************************************************************
     * @function : 根据mail_detail_id删除私信记录
     * @param mailDetailId
     *******************************************************************************
     * @creator ：majun   
     * @date ：2011-5-25   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    public void deleteMailDetailByDetailId(Long mailDetailId){
    	getSqlMapClientTemplate().update("deleteMailDetailByDetailId",mailDetailId);
    }
	
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
	public MailShow getMailShowById(Long mailShowId){
	    return (MailShow)this.selectOneObject("getMailShowById",mailShowId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取私信详细记录
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
	public MailDetail getMailDetailById(Long mailDetailId){
	    return (MailDetail)this.selectOneObject("getMailDetailById",mailDetailId);
	}
	
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
	public PaginationDTO<MailDetail> findMailDetailPage(long mailShowId,long pageNum,Long pageSize){
    	
    	Map<String, Object> para = new HashMap<String, Object>();
        para.put("mailShowId", mailShowId);
        PaginationDTO<MailDetail> pagination = new PaginationDTO<MailDetail>();
        pagination.setPara(para);
        pagination.setPerPageRecordNum(pageSize);
        pagination.setCurPageNum(pageNum);
        
    	return this.selectPage("findMailDetailPage", pagination);
    }
	
	/**
     * 
     *******************************************************************************
     * @function : 进入私信详细页面界面更新最新私信记录数
     * @param mailShowId
     *******************************************************************************
     * @creator ：majun   
     * @date ：2011-5-25   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    public void updateNewMailNumByMailShowId(Long mailShowId){
    	getSqlMapClientTemplate().update("updateNewMailNumByMailShowId",mailShowId);
    }
    
    /**   
     *******************************************************************************
     * @function : 更新mailshow表中的内容
     * @param content
     * @param mailShowId
     *******************************************************************************
     * @creator ：majun   
     * @date ：2013-3-5   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    public void updateMailShowContent(String content,Long mailShowId){
    	Map<String, Object> para = new HashMap<String, Object>();
    	para.put("content", content);
		para.put("mailShowId", mailShowId);
		this.update("updateMailShowContent",para);
    }
    
    /**   
     *******************************************************************************
     * @function : 更具mailShowID更新相关的最后一条mailDetail的内容
     * @param content
     * @param mailShowId
     *******************************************************************************
     * @creator ：majun   
     * @date ：2013-3-5   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    public void updateLastMailDetailContentByMailShowId(String content,Long mailShowId){
    	Map<String, Object> para = new HashMap<String, Object>();
    	para.put("content", content);
		para.put("mailShowId", mailShowId);
		this.update("updateLastMailDetailContentByMailShowId",para);
    }
    
    /**   
     *******************************************************************************
     * @function : 根据mailDetailId更新mailDetail的内容
     * @param content
     * @param mailDetailId
     *******************************************************************************
     * @creator ：majun   
     * @date ：2013-3-5   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    public void updateMailDetailByMailDetailId(String content,Long mailDetailId){
    	Map<String, Object> para = new HashMap<String, Object>();
    	para.put("content", content);
		para.put("mailDetailId", mailDetailId);
		this.update("updateMailDetailByMailDetailId",para);
    }
    
    /**   
     *******************************************************************************
     * @function : 是否为最后一条mailDetail
     * @param mailDetailId
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
    public boolean isLastMailDetail(Long mailDetailId){
    	Long lastMailDetailId = (Long)this.selectOneObject("getLastMailDetailIdByMailDetailId", mailDetailId);
    	if(lastMailDetailId != null && mailDetailId != null && lastMailDetailId.longValue() == mailDetailId.longValue()){
    		return true;
    	}
    	return false;
    }
    
    /**   
     *******************************************************************************
     * @function : 根据mailDetailId更新mailshow内容
     * @param content
     * @param mailDetailId
     *******************************************************************************
     * @creator ：majun   
     * @date ：2013-3-5   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    public void updateMailShowContentByMailDetailId(String content,Long mailDetailId){
    	Map<String, Object> para = new HashMap<String, Object>();
    	para.put("content", content);
		para.put("mailDetailId", mailDetailId);
		this.update("updateMailShowContentByMailDetailId",para);
    }
	
}
