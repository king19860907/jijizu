package com.jijizu.web.diary.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jijizu.base.util.DateUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.diary.dto.DiaryDayCalendar;
import com.jijizu.core.diary.dto.DiaryInfo;
import com.jijizu.core.diary.dto.DiaryMonthCalendar;
import com.jijizu.core.diary.dto.DiaryWeekCalendar;
import com.jijizu.core.diary.service.DiaryService;
import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.status.service.StatusService;
import com.jijizu.core.user.dto.ChildInfo;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

public class DiaryAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5456597172087091179L;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private UserInfoService userInfoService;
	
	private StatusService statusService;
	
	private DiaryService diaryService;
	
	private Date diaryDate;
	
	private List<ChildInfo> childList;
	
	private List<StatusFace> faces;
	
	private Long childId;
	
	private Long diaryId;
	
	private DiaryInfo diary;
	
	private DiaryMonthCalendar monthCalendar;
	
	private DiaryWeekCalendar weekCalenday;
	
	private DiaryDayCalendar dayCalendar;
	
	private String month;
	
	private String week;
	
	private String day;
	
	private Map<String,List<DiaryInfo>> diaryDataMap;
	
	private Long firstDayAvg;
	
	private String diaryType;
	
	private List<UserInfo> userList;
	
	public String edit() {
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null) {
			return NOT_LOGIN;
		}
		
		if(diaryId == null){
			return INDEX;
		}
		
		diary = diaryService.getDiaryById(diaryId, true,true,true,true);
		
		if(diary == null){
			return INDEX;
		}
		if(diary.getUserId().longValue() != sessionUserInfo.getUserId().longValue()){
			return INDEX;
		}
		
		childList = userInfoService.findChildInfoByUserId(sessionUserInfo.getUserId());
		
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS); 
		
		target = getReturnUrl(DateUtil.get4yMM(diary.getDiaryDate()));
		
		return SUCCESS;
	}
	
	public String day() {
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if (StringUtil.isNotNullOrEmpty(day)) {
			try {
				dayCalendar = new DiaryDayCalendar(DateUtil.toDate(day, DateUtil.DATE_FORMAT_YYYYMMDD));
			} catch (ParseException e) {
				log.error(e);
				return INDEX;
			}
		}else{
			dayCalendar = new DiaryDayCalendar(new Date());
		}
		
		if(sessionUserInfo !=null){
			diaryDataMap = diaryService.findDiaryByMonth(sessionUserInfo.getUserId(), dayCalendar.getCurrentDay().toDate(),dayCalendar.getNextTwoDay().toDate());
		}
		
		return SUCCESS;
	}
	
	public String week(){
		UserInfo sessionUserInfo = getSessionUserInfo();
		if (StringUtil.isNotNullOrEmpty(week)) {
			try {
				weekCalenday = new DiaryWeekCalendar(DateUtil.toDate(week, DateUtil.DATE_FORMAT_YYYYMMDD));
			} catch (ParseException e) {
				log.error(e);
				return INDEX;
			}
		}else{
			weekCalenday = new DiaryWeekCalendar(new Date());
		}
		
		if(sessionUserInfo != null){
			diaryDataMap = diaryService.findDiaryByMonth(sessionUserInfo.getUserId(), weekCalenday.getFirstDayOfWeek().toDate(),weekCalenday.getLastDayOfWeek().toDate());
		}
		
		return SUCCESS;
	}
	
	public String month() {
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if (StringUtil.isNotNullOrEmpty(month)) {
			try {
				monthCalendar = new DiaryMonthCalendar(DateUtil.toDate(month, DateUtil.DATE_FORMAT_YYYYMM));
			} catch (ParseException e) {
				log.error(e);
				return INDEX;
			}
		}else {
			monthCalendar = new DiaryMonthCalendar(new Date());
		}
		
		if(sessionUserInfo != null){
			diaryDataMap = diaryService.findDiaryByMonth(sessionUserInfo.getUserId(), monthCalendar.getStartDate(),monthCalendar.getEndDate());
		}
		
		return SUCCESS;
	}
	
	public String view(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null) {
			return NOT_LOGIN;
		}
		
		if(diaryId == null){
			return INDEX;
		}
		
		diary = diaryService.getDiaryById(diaryId, true,true,true,true);
		
		if(diary == null){
			return INDEX;
		}
		if(diary.getUserId().longValue() != sessionUserInfo.getUserId().longValue()){
			return INDEX;
		}
		
		if(diary.isFisrtTimeDiaryType()){
			firstDayAvg = diaryService.getFirstDayAvg(diary.getFirstTimeType());
		}
		
		target = getReturnUrl(DateUtil.get4yMM(diary.getDiaryDate()));  
		
		return SUCCESS;
	}
	
	private String getReturnUrl(String diaryDate) {
		String returnUrl =  request.getHeader("referer");
		if(!StringUtil.isNotNullOrEmpty(returnUrl)) {
			returnUrl="/diary/";
		}else if(returnUrl.contains("/diary/create.jspa") || 
				 returnUrl.contains("/diary/edit.jspa")){
			if(StringUtil.isNotNullOrEmpty(diaryDate)){
				returnUrl = "/diary/month/"+diaryDate;
			}else{
				returnUrl="/diary/";
			}
		}
		return returnUrl;
	}
	
	public String create() {
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		
		if(diaryDate == null) {
			diaryDate = new Date();
		}
		
		if(sessionUserInfo != null){
			childList = userInfoService.findChildInfoByUserId(sessionUserInfo.getUserId());
		}
		
		if(childList != null && childList.size() > 0) {
			childId = childList.get(0).getChildId();
		}
		
		faces = statusService.findStatusFace(SysDataDicConstant.FACE_TYPE_STATUS); 
		
		target = getReturnUrl(null);  
		
		return SUCCESS;
	}
	
	public String getSameSickData() {
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		if(sessionUserInfo == null) {
			return SUCCESS;
		}
		
		if(diaryId == null){
			return SUCCESS;
		}
		
		diary = diaryService.getDiaryById(diaryId, false,false,true,true);
		
		userList = diaryService.findSameSickUser(diaryId, sessionUserInfo.getUserId(), 9l);
		
		return SUCCESS;
	}
	
	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public Date getDiaryDate() {
		return diaryDate;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public List<ChildInfo> getChildList() {
		return childList;
	}

	public Long getChildId() {
		return childId;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public List<StatusFace> getFaces() {
		return faces;
	}

	public Long getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	public DiaryInfo getDiary() {
		return diary;
	}

	public void setDiaryService(DiaryService diaryService) {
		this.diaryService = diaryService;
	}

	public DiaryMonthCalendar getMonthCalendar() {
		return monthCalendar;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Map<String, List<DiaryInfo>> getDiaryDataMap() {
		return diaryDataMap;
	}

	public DiaryWeekCalendar getWeekCalenday() {
		return weekCalenday;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public DiaryDayCalendar getDayCalendar() {
		return dayCalendar;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Long getFirstDayAvg() {
		return firstDayAvg;
	}

	public String getDiaryType() {
		return diaryType;
	}

	public void setDiaryType(String diaryType) {
		this.diaryType = diaryType;
	}

	public List<UserInfo> getUserList() {
		return userList;
}

}
