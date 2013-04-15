package com.jijizu.web.diary.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.diary.dto.DiaryInfo;
import com.jijizu.core.diary.dto.SickInfo;
import com.jijizu.core.diary.service.DiaryService;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dto.ChildInfo;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

public class DiaryActionAjax extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8321500203626048847L;
	
	private DiaryService diaryService;
	
	private UserInfoService userInfoService;
	
	private Long childId;
	
	private String diaryType;
	
	private Long diaryId;
	
	private String q;
	
	public String findMilkNames(){
		
		List<String> milkNames = diaryService.findMilkNames(q, 8L);
		
		this.outListToJson(milkNames);
		
		return NONE;
		
	}
	
	public String findSymptomNames() {
		List<String> names = new ArrayList<String>();
		if(StringUtil.isNotNullOrEmpty(q.trim())) {
			List<SickInfo> list = diaryService.findSickInfoByType(q.trim(),SysDataDicConstant.SICK_TYPE_SYMPTOM, 8L);
			if(list != null && list.size()>0) {
				for(SickInfo sick : list) {
					names.add(sick.getSickName());
				}
			}
		}
		this.outListToJson(names);
		return NONE;
	}
	
	public String sickNames() {
		List<String> names = new ArrayList<String>();
		if(StringUtil.isNotNullOrEmpty(q.trim())) {
			List<SickInfo> list = diaryService.findSickInfoByType(q.trim(),SysDataDicConstant.SICK_TYPE_SICK, 8L);
			if(list != null && list.size()>0) {
				for(SickInfo sick : list) {
					names.add(sick.getSickName());
				}
			}
		}
		this.outListToJson(names);
		return NONE;
	}
	
	public String deleteDiary() {
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = diaryService.deleteDiary(sessionUserInfo, diaryId);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	
	public String addDiary() {
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		Map<String,Object> params = getParams();
		
		String ip = getIp(request);
		
		params.put(CheckParam.IP, ip);
		
		JsonResult result = diaryService.addDiary(sessionUserInfo, params);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String updateDiary() {
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		Map<String,Object> params = getParams();
		
		JsonResult result = diaryService.updateDiary(sessionUserInfo, params);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String findDiary() {
		
		if(!StringUtil.isNotNullOrEmpty(diaryType)) {
			diaryType = SysDataDicConstant.DIARY_TYPE_HEIGHT;
		}
		
		List<DiaryInfo> diaryList = diaryService.findChildDiaryData(childId, diaryType,12L);
		
		ChildInfo childInfo = userInfoService.getChildInfoById(childId);
		
		List<Map<String,String>> avgDiaryData = diaryService.findAvgDiaryData(diaryType,diaryList,12L);
		
		List<Object> list = new ArrayList<Object>();
		list.add(diaryList);
		list.add(avgDiaryData);
		list.add(childInfo);
		this.outListToJson(list);
		
		return NONE;
	}

	public void setDiaryService(DiaryService diaryService) {
		this.diaryService = diaryService;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public void setDiaryType(String diaryType) {
		this.diaryType = diaryType;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}


	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	public void setQ(String q) {
		this.q = q;
	}

}
