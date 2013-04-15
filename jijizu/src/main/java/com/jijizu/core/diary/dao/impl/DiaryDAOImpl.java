package com.jijizu.core.diary.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.dao.IbatisBaseDAO;
import com.jijizu.core.diary.dao.DiaryDAO;
import com.jijizu.core.diary.dto.DiaryInfo;
import com.jijizu.core.diary.dto.SickInfo;
import com.jijizu.core.user.dto.UserInfo;

public class DiaryDAOImpl extends IbatisBaseDAO
	implements DiaryDAO{
	
	/**   
	 *******************************************************************************
	 * @function : 保存小本本
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
	public Long saveDiary(Map<String,Object> para) {
		return (Long)this.insert("saveDiary", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新小本本
	 * @param para
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-7   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateDiary(Map<String,Object> para) {
		this.update("updateDiary",para);
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
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("childId", childId);
		para.put("diaryType", diaryType);
		return (List<DiaryInfo>)this.select("findChildDiary", para);
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
	public List<Map<String,String>> findAvgDiaryData(String diaryType,List<Long> childMonths,Long rownum){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("diaryType", diaryType);
		para.put("childMonths", childMonths);
		para.put("maxMonth", childMonths.size()>0?childMonths.get(0):0);
		para.put("rownum", rownum+childMonths.size());
		return (List<Map<String,String>>)this.select("findAvgDiaryData", para);
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
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("childId", childId);
		para.put("diaryType", diaryType);
		para.put("rownum", rownum);
		return (List<DiaryInfo>)this.select("findChildDiaryData", para);
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
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("diaryId", diaryId);
		return (DiaryInfo)this.selectOneObject("getDiaryById", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据id查询孩子的小本本 -验证框架使用
	 * @param diaryId
	 * @param para
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
	public DiaryInfo getDiaryById(Long diaryId,Map<String,Object> para) {
		DiaryInfo diaryInfo = null;
		if(para != null && para.get(CheckParam.TEMP_DIARYINFO) != null) {
			diaryInfo = (DiaryInfo)para.get(CheckParam.TEMP_DIARYINFO);
		}
		if(diaryInfo == null) {
			diaryInfo = this.getDiaryById(diaryId);
			if(diaryInfo != null) {
				para.put(CheckParam.TEMP_DIARYINFO, diaryInfo);
			}
		}
		return diaryInfo;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除小本本信息
	 * @param diaryId
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-2   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteDiaryInfo(Long diaryId,Long userId) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("diaryId", diaryId);
		para.put("userId", userId);
		this.delete("deleteDiaryInfo", para);
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
	public List<DiaryInfo> findDiaryByMonth(Long userId,Date startDate,Date endDate){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("userId", userId);
		para.put("startDate", startDate);
		para.put("endDate", endDate);
		return (List<DiaryInfo>)this.select("findDiaryByMonth", para);
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
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("milkName", milkName);
		para.put("rownum", rownum);
		return (List<String>)this.select("findMilkNames", para);
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
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("firstTimeType", firstTimeType);
		return (Long)this.selectOneObject("getFirstDayAvg", para);
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
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sickName", sickName);
		para.put("sickType", sickType);
		para.put("rownum", rownum);
		return (List<SickInfo>)this.select("findSickInfoByType", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存疾病信息
	 * @param sickName
	 * @param sickType
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveSick(String sickName,String sickType) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sickName", sickName);
		para.put("sickType", sickType);
		return (Long)this.insert("saveSick", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存小本本与症状关系
	 * @param diaryId
	 * @param sickId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveDiarySymptom(Long diaryId,String sickType,String [] symptomNames) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("diaryId", diaryId);
		para.put("symptomNames", symptomNames);
		para.put("sickType",sickType);
		return (Long)this.insert("saveDiarySymptom", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 通过疾病名称修改小本本的确诊疾病
	 * @param sickName
	 * @param sickType
	 * @param diaryId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateDiarySickIdBySickName(String sickName,String sickType,Long diaryId) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sickName", sickName);
		para.put("sickType", sickType);
		para.put("diaryId",diaryId);
		this.update("updateDiarySickIdBySickName",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据diaryId删除症状与小本本关系
	 * @param diaryId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteDiarySymptom(Long diaryId) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("diaryId", diaryId);
		this.delete("deleteDiarySymptomByDiaryId", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据小本本ID获取症状
	 * @param diaryId
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
	public List<SickInfo> findSymptomByDiaryId(Long diaryId){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("diaryId", diaryId);
		return (List<SickInfo>)this.select("findSymptomByDiaryId", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据ID获取疾病信息
	 * @param sickId
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
	public SickInfo getSickInfoById(Long sickId){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sickId", sickId);
		return (SickInfo)this.selectOneObject("getSickInfoById", para);
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
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("diaryId", diaryId);
		para.put("userId", userId);
		para.put("rownum", rownum);
		return (List<UserInfo>)this.select("findSameSickUser", para);
	}
	
}
