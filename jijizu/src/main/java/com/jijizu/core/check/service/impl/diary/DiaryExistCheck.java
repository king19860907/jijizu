package com.jijizu.core.check.service.impl.diary;

import java.util.Map;

import com.jijizu.core.check.CheckCommonField;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.diary.dao.DiaryDAO;
import com.jijizu.core.diary.dto.DiaryInfo;
import com.jijizu.core.dto.JsonResult;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : DiaryExistCheck
 * @function : 小本本存在检查-不存在则返回错误
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2013-4-2   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 
public class DiaryExistCheck extends CheckCommonField
	implements CheckService{
	
	private DiaryDAO diaryDAO;

	@Override
	public JsonResult check(Map<String, Object> para) {
		Object diaryId = para.get(CheckParam.DIARYID);
		if(diaryId != null) {
			DiaryInfo diary = diaryDAO.getDiaryById(Long.parseLong(diaryId.toString()), para);
			if(diary == null) {
				return new JsonResult(OperateConstanct.OPERATE_ERROR, errorMsg);
			}
		}
		return null;
	}

	public void setDiaryDAO(DiaryDAO diaryDAO) {
		this.diaryDAO = diaryDAO;
	}
	
}
