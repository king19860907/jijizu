package com.jijizu.core.check.service.impl.status;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.status.dao.StatusDAO;
import com.jijizu.core.status.dto.CommentInfo;
import com.jijizu.core.status.dto.StatusInfo;
import com.jijizu.core.user.dto.UserInfo;

/**   
 *******************************************************************************
 * @project : 集集组  
 * @type : CommentCanDeleteCheck
 * @function : 可以删除评论检查-不能删除评论则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-1-5   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class CommentCanDeleteCheck extends CheckCommonField
	implements CheckService{
	
	private StatusDAO statusDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Long commentId = (Long)para.get(CheckParam.COMMENTID);
		UserInfo sessionUserInfo = (UserInfo)para.get(CheckParam.SESSIONUSERINFO);
		if(commentId != null && sessionUserInfo != null){
			CommentInfo comment = this.getComment(para, commentId);
			StatusInfo status = statusDAO.getStatusById(comment.getStatusId());
			if(comment.getUserId().longValue() != sessionUserInfo.getUserId().longValue()
					&& status.getUserId().longValue() != sessionUserInfo.getUserId().longValue()){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	private CommentInfo getComment(Map<String,Object> para,Long commentId){
		CommentInfo commentInfo = (CommentInfo)para.get(CheckParam.TEMP_COMMENTINFO);
		if(commentInfo == null){
			commentInfo = statusDAO.getCommentByCommentId(commentId);
		}
		return commentInfo;
	}

	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}
	
}
