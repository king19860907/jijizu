package com.jijizu.core.check.service.impl.status;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.status.dao.StatusDAO;
import com.jijizu.core.status.dto.CommentInfo;

public class CommentExistCheck extends CheckCommonField
	implements CheckService{
	
	private StatusDAO statusDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Long commentId = (Long)para.get(CheckParam.COMMENTID);
		if(commentId != null){
			CommentInfo comment = statusDAO.getCommentByCommentId(commentId);
			if(comment == null){
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}else{
				para.put(CheckParam.TEMP_COMMENTINFO, comment);
			}
		}
		return null;
	}

	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}
	
}
