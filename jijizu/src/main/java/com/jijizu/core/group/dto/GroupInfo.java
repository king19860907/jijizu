package com.jijizu.core.group.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.DateUtil;
import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.InitData;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.user.dto.PostArea;
import com.jijizu.core.user.dto.UserInfo;

public class GroupInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4467008707752288985L;
	
	private Long groupId;
	
	private String title;
	
	private String groupDesc;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date applyEndDate;
	
	private String posterImgUrl;
	
	private String address;
	
	private Double cost;
	
	private String enterType;
	
	private String enterTypeDetail;
	
	private Date createdate;
	
	private Date updateDate;
	
	private Long userId;
	
	private Long cancelFlag;
	
	private Long enterNum;
	
	private Long province;
	
	private Long city;
	
	private Long district;
	
	private String userName;
	
	private GroupUsers groupUsers;
	
	private Long vFlag;
	
	private Long enterpriseFlag;

	private Long startAge;
	
	private Long endAge;
	
	private Long applyNum;
	
	private String parentsLimit;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGroupDesc() {
		return groupDesc;
	}
	
	public String getGroupDescHtml(){
		return groupDesc.replaceAll("\\n", "<br/>").replaceAll("\\s","&nbsp;&nbsp;");
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public Integer getStartDateHour(){
		if(startDate!=null){
			return DateUtil.getHourByDate(startDate);
		}
		return null;
	}
	
	public Integer getStartDateMinute(){
		if(startDate!=null){
			return DateUtil.getMinuteByDate(startDate);
		}
		return null;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public Integer getEndDateHour(){
		if(endDate!=null){
			return DateUtil.getHourByDate(endDate);
		}
		return null;
	}
	
	public Integer getEndDateMinute(){
		if(endDate!=null){
			return DateUtil.getMinuteByDate(endDate);
		}
		return null;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getApplyEndDateHour() {
		if(applyEndDate != null) {
			return DateUtil.getHourByDate(applyEndDate);
		}
		return 0;
	}
	
	public Integer getApplyEndDateMinute() {
		if(applyEndDate != null) {
			return DateUtil.getMinuteByDate(applyEndDate);
		}
		return 0;
	}

	public String getPosterImgUrl() {
		if(!StringUtil.isNullOrEmpty(posterImgUrl)){
			return posterImgUrl;
		}else{
			return JijizuUtil.getAbsolutePath()+"/images/event_img_bigl.jpg";
		}
	}

	public void setPosterImgUrl(String posterImgUrl) {
		this.posterImgUrl = posterImgUrl;
	}

	public String getAddress() {
		return address;
	}
	
	public String getAddress(int length){
		if(StringUtil.isNotNullOrEmpty(address)){
			return StringUtil.getStrByLen(address, length, "...");
		}
		return null;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getEnterType() {
		return enterType;
	}
	
	public String getEnterTypeStr(){
		String enterTypeStr = InitData.sysDictCodeAndNameRelationShip.get(enterType);
		if(StringUtil.isNotNullOrEmpty(enterType) && SysDataDicConstant.GROUP_ENTER_TYPE_FRIENDS.equals(enterType)) {
			enterTypeStr += "-"+InitData.sysDictCodeAndNameRelationShip.get(enterTypeDetail);
	}
		return enterTypeStr;
	}

	public void setEnterType(String enterType) {
		this.enterType = enterType;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Long cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public Long getEnterNum() {
		return enterNum;
	}

	public void setEnterNum(Long enterNum) {
		this.enterNum = enterNum;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}
	
	public String getProviceStr(){
		if(province != null){
			PostArea postArea = InitData.areaProvince.get(province);
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}
	
	public String getCityStr(){
		if(city != null){
			PostArea postArea = InitData.areaCity.get(city);
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}
	
	public String getDistrictStr(){
		if(district != null){
			PostArea postArea = InitData.areaDistrict.get(district);
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getSmallPosterImgUrl(){
		if(StringUtil.isNotNullOrEmpty(posterImgUrl)){
			String imgNameWithoutExt = posterImgUrl.substring(0,
					posterImgUrl.lastIndexOf('.'));

			return imgNameWithoutExt + "_t"
					+ posterImgUrl.substring(posterImgUrl.lastIndexOf('.'));
		}else{
			return JijizuUtil.getAbsolutePath()+"/images/event_img_small.jpg";
		}
	}
	
	public String getGroupStatus() {
		Date nowDate = new Date();
		if(startDate != null && endDate != null){
			if(applyEndDate != null) {
				if(nowDate.getTime()<applyEndDate.getTime()) {
					return CommonConstant.GROUP_STATUS_APPLY_ING;
				}
			}
			if(nowDate.getTime() < startDate.getTime()){
				return CommonConstant.GROUP_STATUS_NOT_BEGIN+"";
			}else if(nowDate.getTime() >= startDate.getTime() && nowDate.getTime() <= endDate.getTime()){
				return CommonConstant.GROUP_STATUS_ING+"";
			}else if(nowDate.getTime() > endDate.getTime()){
				return CommonConstant.GROUP_STATUS_END+"";
			}
		}
		
		return null;
	}
	
	public String getStartDayOfWeek() {
		if(startDate != null){
			return JijizuUtil.weekNumStringMap.get(DateUtil.getWeekDay(startDate));
		}
		return null;
	}
	
	public String getEndDayOfWeek(){
		if(endDate != null){
			return JijizuUtil.weekNumStringMap.get(DateUtil.getWeekDay(endDate));
		}
		return null;
	}
	
	public String getApplyEndDayOfWeek() {
		if(applyEndDate != null) {
			return JijizuUtil.weekNumStringMap.get(DateUtil.getWeekDay(applyEndDate));
		}
		return null;
	}
	
	public String getDefaultJoinStatus(){
		if(SysDataDicConstant.GROUP_ENTER_TYPE_PUBIC.equals(enterType) || 
				(SysDataDicConstant.GROUP_ENTER_TYPE_FRIENDS.equals(enterType) && SysDataDicConstant.GROUP_ENTER_TYPE_DETAIL_NOT_NEED_AUDIT.equals(enterTypeDetail))){
			return SysDataDicConstant.GROUP_JOIN_STATUS_PASS;
		}else{
			return SysDataDicConstant.GROUP_JOIN_STATUS_AUDIT;
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否可报名
	 * @param sessionUserInfo
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean canJoin(UserInfo sessionUserInfo){
		boolean canJoin = false;
		if(sessionUserInfo != null && groupUsers == null 
				&& CommonConstant.GROUP_STATUS_APPLY_ING.equals(getGroupStatus())
				&& this.userId != null
				&& sessionUserInfo.getUserId().longValue() != this.userId.longValue()){
			canJoin = true;
		}else if(sessionUserInfo == null 
				&& CommonConstant.GROUP_STATUS_APPLY_ING.equals(getGroupStatus())) {
			canJoin = true;
		}
		return canJoin;
	}
	
	/**
	 * 
	 *******************************************************************************
	 * @function : 是否可上传图片
	 * @param sessionUserInfo
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean canUpload(UserInfo sessionUserInfo){
		boolean canUpload = false;
		if(isCreator(sessionUserInfo) || groupUsers != null){
			canUpload=true;
		}
		return canUpload;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否可取消报名
	 * @param sessionUserInfo
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean canCancel(UserInfo sessionUserInfo){
		boolean canCancel = false;
		if(sessionUserInfo != null && groupUsers != null
				&& (CommonConstant.GROUP_STATUS_NOT_BEGIN.equals(getGroupStatus()) || CommonConstant.GROUP_STATUS_APPLY_ING.equals(getGroupStatus()))){
			canCancel = true;
		}
		return canCancel;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否为创建者
	 * @param sessionUserInfo
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean isCreator(UserInfo sessionUserInfo){
		boolean isCreator = false;
		if(sessionUserInfo != null && sessionUserInfo.getUserId().longValue() == userId.longValue()){
			isCreator = true;
		}
		return isCreator;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否可修改集集组
	 * @param sessionUserInfo
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean canUpdate(UserInfo sessionUserInfo){
		boolean canUpdate = false;
		if(isCreator(sessionUserInfo) && (CommonConstant.GROUP_STATUS_NOT_BEGIN.equals(getGroupStatus()) || CommonConstant.GROUP_STATUS_APPLY_ING.equals(getGroupStatus()))){
			canUpdate = true;
		}
		return canUpdate;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否可删除集集组
	 * @param sessionUserInfo
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean canDelete(UserInfo sessionUserInfo){
		boolean canDelete = false;
		if(isCreator(sessionUserInfo) && (CommonConstant.GROUP_STATUS_NOT_BEGIN.equals(getGroupStatus()) || CommonConstant.GROUP_STATUS_APPLY_ING.equals(getGroupStatus()))){
			canDelete = true;
		}
		return canDelete;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否可管理集集组成员
	 * @param sessionUserInfo
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean canManageUsers(UserInfo sessionUserInfo){
		boolean canManageUsers = false;
		if(isCreator(sessionUserInfo)){
			canManageUsers = true;
		}
		return canManageUsers;
	}

	public GroupUsers getGroupUsers() {
		return groupUsers;
	}

	public void setGroupUsers(GroupUsers groupUsers) {
		this.groupUsers = groupUsers;
	}

	public Long getVFlag() {
		return vFlag;
	}

	public void setVFlag(Long vFlag) {
		this.vFlag = vFlag;
	}

	public Long getEnterpriseFlag() {
		return enterpriseFlag;
	}

	public void setEnterpriseFlag(Long enterpriseFlag) {
		this.enterpriseFlag = enterpriseFlag;
	}
	
	public String getEnterTypeDetail() {
		return enterTypeDetail;
}

	public void setEnterTypeDetail(String enterTypeDetail) {
		this.enterTypeDetail = enterTypeDetail;
	}

	public Date getApplyEndDate() {
		return applyEndDate;
	}

	public void setApplyEndDate(Date applyEndDate) {
		this.applyEndDate = applyEndDate;
	}

	public Long getStartAge() {
		return startAge;
	}

	public void setStartAge(Long startAge) {
		this.startAge = startAge;
	}

	public Long getEndAge() {
		return endAge;
	}

	public void setEndAge(Long endAge) {
		this.endAge = endAge;
	}
	
	public String getAgeRange() {
		
		if(startAge != null && endAge != null) {
			if(startAge.longValue() == endAge.longValue()) {
				return startAge+"岁";
			}else {
				return startAge + "-" + endAge;
			}
		}else if(startAge == null && endAge != null) {
			return endAge+"岁以下";
		}else if(startAge != null && endAge == null) {
			return startAge+"岁以上";
		}
		
		return null;
	}

	public Long getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(Long applyNum) {
		this.applyNum = applyNum;
	}

	public String getParentsLimit() {
		return parentsLimit;
	}

	public void setParentsLimit(String parentsLimit) {
		this.parentsLimit = parentsLimit;
	}
	
	public String getApplyNumStr() {
		if(applyNum == null || applyNum.longValue() == -1) {
			return "不限";
		}else{
			return applyNum+"人";
		}
	}
	
	public String getParentsLimitStr() {
		if(StringUtil.isNotNullOrEmpty(parentsLimit)) {
			return InitData.sysDictCodeAndNameRelationShip.get(parentsLimit)+"参加";
		}
		return null;
	}
	
}
