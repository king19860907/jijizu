package com.jijizu.core.diary.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jijizu.base.util.DateUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.check.CheckContext;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.diary.dao.DiaryDAO;
import com.jijizu.core.diary.dto.DiaryInfo;
import com.jijizu.core.diary.dto.SickInfo;
import com.jijizu.core.diary.service.DiaryService;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.status.service.StatusService;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.ChildInfo;
import com.jijizu.core.user.dto.UserInfo;

public class DiaryServiceImpl implements DiaryService {
	
	private DiaryDAO diaryDAO;
	
	private UserInfoDAO userInfoDAO;
	
	private StatusService statusService;
	
	/**
	 * 新建小本本验证
	 */
	private CheckContext addDiaryCheckContext;
	
	/**
	 * 删除小本本验证
	 */
	private CheckContext deleteDiaryCheckContext;
	
	/**
	 * 更新小本本验证
	 */
	private CheckContext updateDiaryCheckContext;
	
	/**   
	 *******************************************************************************
	 * @function : 删除小本本
	 * @param sessionUserInfo
	 * @param diaryId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-2   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult deleteDiary(UserInfo sessionUserInfo,Long diaryId) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.DIARYID, diaryId);
		JsonResult result = deleteDiaryCheckContext.check(para);
		if(result != null) {
			return result;
		}
		
		DiaryInfo diaryInfo = diaryDAO.getDiaryById(diaryId, para);
		if(diaryInfo != null && diaryInfo.getStatusId() != null) {
			statusService.deleteStatus(sessionUserInfo, diaryInfo.getStatusId());
		}
		
		diaryDAO.deleteDiaryInfo(diaryId, sessionUserInfo.getUserId());
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	
	/**   
	 *******************************************************************************
	 * @function : 新建小本本
	 * @param sessionUserInfo
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-29   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult addDiary(UserInfo sessionUserInfo,Map<String,Object> para) {
		//需要进行验证的参数
		Map<String,Object> checkPara = new HashMap<String,Object>();
		checkPara.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		checkPara.put(CheckParam.CONTENT, para.get(CheckParam.CONTENT));
		checkPara.put(CheckParam.DIARYDATE, para.get(CheckParam.DIARYDATE));
		checkPara.put(CheckParam.DIARYTYPE, para.get(CheckParam.DIARYTYPE));
		checkPara.put(CheckParam.CHILDID, para.get(CheckParam.CHILDID));
		checkPara.put(CheckParam.TITLE, para.get(CheckParam.TITLE));
		
		JsonResult result = addDiaryCheckContext.check(checkPara);
		if(result != null) {
			return result;
		}
		
		//保存微博
		Object notShowFlagObject = para.get(CheckParam.NOTSHOWFLAG);
		Long notShowFlag = 1L;
		if(notShowFlagObject != null && CommonConstant.NOT_SHOW_FLAG_NO.longValue()==Long.parseLong(notShowFlagObject.toString())) {
			notShowFlag = 0L;
		}
		result = statusService.postStatus(sessionUserInfo, (String)para.get(CheckParam.CONTENT),(String)para.get(CheckParam.MEDIATYPE),
				(String)para.get(CheckParam.MEDIAURL), (String)para.get(CheckParam.SOURCETYPE), (String)para.get(CheckParam.SOURCETYPEDETAIL),
				null, (String)para.get(CheckParam.IP), false, null, null,notShowFlag);
		if(OperateConstanct.OPERATE_SUCCESS.equals(result.getFlag())){
			para.put(CheckParam.STATUSID, result.getData());
		}
				
		//保存小本本		
	    String diaryDateStr = (String)para.get(CheckParam.DIARYDATE);
		Date diaryDate = DateUtil.parseDate(diaryDateStr, "yyyy-MM-dd");
		para.put(CheckParam.DIARYDATE, diaryDate);
		para.put(CheckParam.CHILDMONTH, getDiaryMonthAndChildMonthDif(Long.parseLong(para.get(CheckParam.CHILDID).toString()),diaryDate));
		para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		Long diaryId = diaryDAO.saveDiary(para);
		
		//保存疾病信息
		saveSickInfo(sessionUserInfo, para,diaryId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,diaryId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存疾病信息
	 * @param sessionUserInfo
	 * @param para
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void saveSickInfo(UserInfo sessionUserInfo,Map<String,Object> para,Long diaryId) {
		if(diaryId != null && SysDataDicConstant.DIARY_TYPE_SICK.equals(para.get(CheckParam.DIARYTYPE))) {
			//删除原来保存的症状
			diaryDAO.deleteDiarySymptom(diaryId);
			
			//保存症状
			String symptomStr = (String)para.get(CheckParam.SYMPTOMS);
			saveSymptom(diaryId, symptomStr);
			
			//保存确诊疾病
			String sickName = (String)para.get(CheckParam.SICKNAME);
			saveDefiniteSick(diaryId, sickName);
		}
	}


	/**   
	 *******************************************************************************
	 * @function : 保存确诊疾病
	 * @param diaryId
	 * @param sickName
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void saveDefiniteSick(Long diaryId, String sickName) {
		
		//保存确诊疾病
		if(StringUtil.isNotNullOrEmpty(sickName)) {
			diaryDAO.saveSick(sickName.trim(), SysDataDicConstant.SICK_TYPE_SICK);
		}
		//更新小本本确诊疾病信息
		diaryDAO.updateDiarySickIdBySickName(sickName, SysDataDicConstant.SICK_TYPE_SICK, diaryId);
	}


	/**   
	 *******************************************************************************
	 * @function : 保存症状
	 * @param diaryId
	 * @param symptomStr
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void saveSymptom(Long diaryId, String symptomStr) {
		//保存症状
		String [] symptoms = symptomStr.split(",");
		List<Long> sickIdList = null;
		if(symptoms != null && symptoms.length > 0) {
			sickIdList = new ArrayList<Long>();
			for(String symptom : symptoms) {
				if(StringUtil.isNotNullOrEmpty(symptom)) {
					Long sickId = diaryDAO.saveSick(symptom, SysDataDicConstant.SICK_TYPE_SYMPTOM);
					sickIdList.add(sickId);
				}
			}
			
		 //保存症状与小本本关系
		 diaryDAO.saveDiarySymptom(diaryId, SysDataDicConstant.SICK_TYPE_SYMPTOM, symptoms);
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新小本本
	 * @param sessionUserInfo
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-7   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult updateDiary(UserInfo sessionUserInfo,Map<String,Object> para) {
		//需要进行验证的参数
		Map<String,Object> checkPara = new HashMap<String,Object>();
		checkPara.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		checkPara.put(CheckParam.CONTENT, para.get(CheckParam.CONTENT));
		checkPara.put(CheckParam.DIARYDATE, para.get(CheckParam.DIARYDATE));
		checkPara.put(CheckParam.DIARYTYPE, para.get(CheckParam.DIARYTYPE));
		checkPara.put(CheckParam.CHILDID, para.get(CheckParam.CHILDID));
		checkPara.put(CheckParam.TITLE, para.get(CheckParam.TITLE));
		checkPara.put(CheckParam.DIARYID, para.get(CheckParam.DIARYID));
		
		JsonResult result = updateDiaryCheckContext.check(checkPara);
		if(result != null) {
			return result;
		}
		
		String diaryDateStr = (String)para.get(CheckParam.DIARYDATE);
		Date diaryDate = DateUtil.parseDate(diaryDateStr, "yyyy-MM-dd");
		para.put(CheckParam.DIARYDATE, diaryDate);
		para.put(CheckParam.CHILDMONTH, getDiaryMonthAndChildMonthDif(Long.parseLong(para.get(CheckParam.CHILDID).toString()),diaryDate));
		para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		diaryDAO.updateDiary(para);
		
		//更新微博内容
		if(para.get(CheckParam.STATUSID) != null) {
			statusService.updateStatusContentAndMediaUrlById(Long.parseLong(para.get(CheckParam.STATUSID).toString()), 
					sessionUserInfo.getUserId(), (String)para.get(CheckParam.CONTENT),
					(String)para.get(CheckParam.MEDIATYPE), (String)para.get(CheckParam.MEDIAURL));
		}
		
		Long diaryId = Long.parseLong(para.get(CheckParam.DIARYID).toString());
		this.saveSickInfo(sessionUserInfo, para,diaryId);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,para.get(CheckParam.DIARYID));
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取小本本记录时间和孩子的出生时间的时间差
	 * @param childId
	 * @param diaryDate
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-29   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private int getDiaryMonthAndChildMonthDif(Long childId,Date diaryDate) {
		ChildInfo childInfo = userInfoDAO.getChildInfoById(childId);
		if(childInfo != null && childInfo.getBirthday() != null) {
			return DateUtil.getMonthDif(childInfo.getBirthday(), diaryDate);
		}
		return 0;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询孩子的小本本
	 * @param childId
	 * @param diaryType
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-29   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<DiaryInfo> findChildDiary(Long childId,String diaryType){
		return diaryDAO.findChildDiary(childId, diaryType);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询平均小本本数据
	 * @param diaryType
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<Map<String,String>> findAvgDiaryData(String diaryType,List<DiaryInfo> childDiaryData,Long rownum){
		List<Long> childMonths = new ArrayList<Long>();
		for(DiaryInfo diary : childDiaryData) {
			childMonths.add(diary.getChildMonth());
		}
		return diaryDAO.findAvgDiaryData(diaryType,childMonths,rownum);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询孩子小本本数据
	 * @param childId
	 * @param diaryType
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<DiaryInfo> findChildDiaryData(Long childId,String diaryType,Long rownum){
		return diaryDAO.findChildDiaryData(childId, diaryType,rownum);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据id查询孩子的小本本
	 * @param diaryId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-2   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public DiaryInfo getDiaryById(Long diaryId){
		return diaryDAO.getDiaryById(diaryId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据id查询孩子的小本本
	 * @param diaryId
	 * @param isLoadStatus	是否加载微博数据
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-2   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public DiaryInfo getDiaryById(Long diaryId,boolean isLoadStatus){
		DiaryInfo diaryInfo = this.getDiaryById(diaryId);
		if(isLoadStatus && diaryInfo != null && diaryInfo.getStatusId() != null){
			StatusInfoWithFwdSrc status = statusService.getStatusWithFwdSrc(diaryInfo.getStatusId());
			diaryInfo.setStatus(status);
		}
		return diaryInfo;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据id查询孩子的小本本
	 * @param diaryId
	 * @param isLoadStatus	是否加载微博数据
	 * @param isLoadChild	是否加载孩子数据
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-2   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public DiaryInfo getDiaryById(Long diaryId,boolean isLoadStatus,boolean isLoadChild) {
		DiaryInfo diaryInfo = this.getDiaryById(diaryId, isLoadStatus);
		if(isLoadChild && diaryInfo != null && diaryInfo.getChildId() != null) {
			ChildInfo child = userInfoDAO.getChildInfoById(diaryInfo.getChildId());
			diaryInfo.setChild(child);
		}
		return diaryInfo;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据id查询孩子的小本本
	 * @param diaryId
	 * @param isLoadStatus		是否加载微博数据
	 * @param isLoadChild		是否加载孩子数据
	 * @param isLoadSymptom		是否加载症状
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-11   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public DiaryInfo getDiaryById(Long diaryId,boolean isLoadStatus,boolean isLoadChild,boolean isLoadSymptom){
		DiaryInfo diaryInfo = this.getDiaryById(diaryId, isLoadStatus, isLoadChild);
		if(isLoadSymptom && diaryInfo != null && SysDataDicConstant.DIARY_TYPE_SICK.equals(diaryInfo.getDiaryType())){
			List<SickInfo> symptomList = diaryDAO.findSymptomByDiaryId(diaryInfo.getDiaryId());
			diaryInfo.setSymptomList(symptomList);
		}
		return diaryInfo;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据id查询孩子的小本本
	 * @param diaryId
	 * @param isLoadStatus		是否加载微博数据
	 * @param isLoadChild		是否加载孩子数据
	 * @param isLoadSymptom		是否加载症状
	 * @param isLoadSick		是否加载确诊疾病
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-11   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public DiaryInfo getDiaryById(Long diaryId,boolean isLoadStatus,boolean isLoadChild,boolean isLoadSymptom,boolean isLoadSick){
		DiaryInfo diaryInfo = this.getDiaryById(diaryId, isLoadStatus, isLoadChild,isLoadSymptom);
		if(isLoadSick && diaryInfo != null && SysDataDicConstant.DIARY_TYPE_SICK.equals(diaryInfo.getDiaryType()) && diaryInfo.getSickId() != null){
			diaryInfo.setSick(diaryDAO.getSickInfoById(diaryInfo.getSickId()));
		}
		return diaryInfo;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据月份查询小本本数据
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-2   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Map<String,List<DiaryInfo>> findDiaryByMonth(Long userId,Date startDate,Date endDate){
		Map<String,List<DiaryInfo>> data = new HashMap<String,List<DiaryInfo>>();
		List<DiaryInfo> diaryList = diaryDAO.findDiaryByMonth(userId, startDate,endDate);
		for(DiaryInfo diary : diaryList) {
			String day = DateUtil.get4yMd(diary.getDiaryDate());
			if(data.get(day) == null) {
				List<DiaryInfo> list = new ArrayList<DiaryInfo>();
				list.add(diary);
				data.put(day, list);
			}else {
				data.get(day).add(diary);
			}
		}
		return data;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询奶粉品牌
	 * @param milkName
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<String> findMilkNames(String milkName,Long rownum){
		return diaryDAO.findMilkNames(milkName, rownum);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取第一次的平均值
	 * @param firstTimeType
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-7   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long getFirstDayAvg(String firstTimeType){
		return diaryDAO.getFirstDayAvg(firstTimeType);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据类型获取疾病列表
	 * @param sickType
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-9   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<SickInfo> findSickInfoByType(String sickName,String sickType,Long rownum){
		return diaryDAO.findSickInfoByType(sickName,sickType, rownum);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询相同症状的用户
	 * @param diaryId
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-11   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<UserInfo> findSameSickUser(Long diaryId,Long userId,Long rownum){
		return diaryDAO.findSameSickUser(diaryId, userId,rownum);
	}
	
	public void setDiaryDAO(DiaryDAO diaryDAO) {
		this.diaryDAO = diaryDAO;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	public void setAddDiaryCheckContext(CheckContext addDiaryCheckContext) {
		this.addDiaryCheckContext = addDiaryCheckContext;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}


	public void setDeleteDiaryCheckContext(CheckContext deleteDiaryCheckContext) {
		this.deleteDiaryCheckContext = deleteDiaryCheckContext;
	}


	public void setUpdateDiaryCheckContext(CheckContext updateDiaryCheckContext) {
		this.updateDiaryCheckContext = updateDiaryCheckContext;
	}

}
