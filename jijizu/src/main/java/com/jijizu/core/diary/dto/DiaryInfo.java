package com.jijizu.core.diary.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jijizu.base.util.DateUtil;
import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.InitData;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.status.dto.StatusInfoWithFwdSrc;
import com.jijizu.core.user.dto.ChildInfo;

public class DiaryInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4108431964347474419L;
	
	private Long diaryId;
	
	private String diaryType;
	
	private Long childId;
	
	private ChildInfo child;
	
	private String title;
	
	private Date diaryDate;
	
	private Date createDate;
	
	private Long cancelFlag;
	
	private Long childMonth;
	
	private Long statusId;
	
	private StatusInfoWithFwdSrc status;
	
	private Double height;
	
	private Long userId;
	
	private Double weight;
	
	private Double milkMl;
	
	private String milkName;
	
	private String firstTimeType;
	
	private Long firstDay;
	
	private Long recoveryFlag;
	
	private List<SickInfo> symptomList;
	
	private Long sickId;
	
	private SickInfo sick;
	
	public Long getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	public String getDiaryType() {
		return diaryType;
	}

	public void setDiaryType(String diaryType) {
		this.diaryType = diaryType;
	}

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Long cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public Long getChildMonth() {
		return childMonth;
	}

	public void setChildMonth(Long childMonth) {
		this.childMonth = childMonth;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public StatusInfoWithFwdSrc getStatus() {
		return status;
	}

	public void setStatus(StatusInfoWithFwdSrc status) {
		this.status = status;
	}
	
	public String getDiaryDateOfWeek() {
		if(diaryDate != null) {
			return JijizuUtil.weekNumStringMap.get(DateUtil.getWeekDay(diaryDate));
		}
		return null;
	}
	
	public boolean isHeightDiaryType(){
		if(StringUtil.isNotNullOrEmpty(diaryType) && SysDataDicConstant.DIARY_TYPE_HEIGHT.equals(diaryType)){
			return true;
		}
		return false;
	}
	
	public boolean isWeightDiaryType() {
		if(StringUtil.isNotNullOrEmpty(diaryType) && SysDataDicConstant.DIARY_TYPE_WEIGHT.equals(diaryType)){
			return true;
		}
		return false;
	}
	
	public boolean isMilkPowderDiaryType(){
		if(StringUtil.isNotNullOrEmpty(diaryType) && SysDataDicConstant.DIARY_TYPE_MILK_POWDER.equals(diaryType)){
			return true;
		}
		return false;
	}
	
	public boolean isFisrtTimeDiaryType(){
		if(StringUtil.isNotNullOrEmpty(diaryType) && SysDataDicConstant.DIARY_TYPE_FIRST_TIME.equals(diaryType)){
			return true;
		}
		return false;
	}
	
	public boolean isOtherDiaryType(){
		if(StringUtil.isNotNullOrEmpty(diaryType) && SysDataDicConstant.DIARY_TYPE_OTHER.equals(diaryType)){
			return true;
		}
		return false;
	}
	
	public boolean isSickDiaryType(){
		if(StringUtil.isNotNullOrEmpty(diaryType) && SysDataDicConstant.DIARY_TYPE_SICK.equals(diaryType)){
			return true;
		}
		return false;
	}

	public ChildInfo getChild() {
		return child;
	}

	public void setChild(ChildInfo child) {
		this.child = child;
	}

	public Double getMilkMl() {
		return milkMl;
	}

	public void setMilkMl(Double milkMl) {
		this.milkMl = milkMl;
	}

	public String getMilkName() {
		return milkName;
	}

	public void setMilkName(String milkName) {
		this.milkName = milkName;
	}

	public String getFirstTimeType() {
		return firstTimeType;
	}

	public void setFirstTimeType(String firstTimeType) {
		this.firstTimeType = firstTimeType;
	}

	public Long getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(Long firstDay) {
		this.firstDay = firstDay;
	}
	
	public String getFirstTimeTypeStr(){
		if(StringUtil.isNotNullOrEmpty(firstTimeType)){
			return InitData.sysDictCodeAndNameRelationShip.get(firstTimeType);
		}
		return null;
	}

	public Long getRecoveryFlag() {
		return recoveryFlag;
	}

	public void setRecoveryFlag(Long recoveryFlag) {
		this.recoveryFlag = recoveryFlag;
	}

	public List<SickInfo> getSymptomList() {
		return symptomList;
	}

	public void setSymptomList(List<SickInfo> symptomList) {
		this.symptomList = symptomList;
	}
	
	public String getSymptomStr(){
		String str = "";
		if(symptomList != null && symptomList.size() > 0){
			for(SickInfo sick : symptomList){
				str +=sick.getSickName()+",";
			}
		}
		return str.substring(0,str.length()-1);
	}
	
	public boolean isRecovery(){
		if(recoveryFlag != null && recoveryFlag.longValue()==CommonConstant.TRUE.longValue()){
			return true;
		}
		return false;
	}

	public SickInfo getSick() {
		return sick;
	}

	public void setSick(SickInfo sick) {
		this.sick = sick;
	}

	public Long getSickId() {
		return sickId;
	}

	public void setSickId(Long sickId) {
		this.sickId = sickId;
	}

}
