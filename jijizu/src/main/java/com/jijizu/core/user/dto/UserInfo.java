package com.jijizu.core.user.dto;

import java.io.Serializable;
import java.util.Date;

import com.jijizu.base.util.DateUtil;
import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.InitData;
import com.jijizu.core.constant.SysDataDicConstant;

public class UserInfo implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5598254446012074536L;

	private Long userId;
	
	private String name;
	
	private String headImgUrl;
	
	private String gender;
	
	private String logName;
	
	private String password;
	
	private String mobile;
	
	private String email;
	
	private String province;
	
	private String city;
	
	private String district;
	
	private String familyStatus;
	
	private Date regDate;
	
	private Date updateDate;
	
	private Long cancelFlag;
	
	private String regSource;
	
	private String regSourceDetail;
	
	private Long statusNum;
	
	private Long friendNum;
	
	private Long groupNum;
	
	private String nickName;
	
	private String userDesc;
	
	private Long newMailNum;
	
	private Date birthday;
	
	private String blood;
	
	private String hometownProvince;
	
	private String hometownCity;
	
	private String hometownDistrict;
	
	private LivingCommunity livingCommunity;
	
	private LivingCommunity hometownLivingCommunity;
	
	private String statusPermission;
	
	private String albumPermission;
	
	private UserSchool lastestUserSchool;
	
	private UserJob lastestUserJob;
	
	private Long enterpriseFlag;
	
	private Long vFlag;
	
	private Date readCommentTime;
	
	private Date readAtTime;
	
	private Boolean isFollowEachOther;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}
	
	public String getName(int len,String subffix){
		return StringUtil.getStrByLen(name, len, subffix);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProvince() {
		return province;
	}
	
	public String getProviceStr(){
		if(StringUtil.isNotNullOrEmpty(province)){
			PostArea postArea = InitData.areaProvince.get(Long.parseLong(province));
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}
	
	public String getCityStr(){
		if(StringUtil.isNotNullOrEmpty(city)){
			PostArea postArea = InitData.areaCity.get(Long.parseLong(city));
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}
	
	public String getDistrictStr(){
		if(StringUtil.isNotNullOrEmpty(district)){
			PostArea postArea = InitData.areaDistrict.get(Long.parseLong(district));
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getFamilyStatus() {
		return familyStatus;
	}

	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Long cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getRegSource() {
		return regSource;
	}

	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}

	public String getRegSourceDetail() {
		return regSourceDetail;
	}

	public void setRegSourceDetail(String regSourceDetail) {
		this.regSourceDetail = regSourceDetail;
	}

	public Long getStatusNum() {
		return statusNum;
	}

	public void setStatusNum(Long statusNum) {
		this.statusNum = statusNum;
	}

	public Long getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(Long friendNum) {
		this.friendNum = friendNum;
	}

	public String getHeadImgDefault(){
		return headImgUrl;
	}
	
	public String getHeadImgUrl() {
		return getHeadImgUrl(CommonConstant.HEAD_IMG_SIZE_50);
	}
	
	public String getHeadImgUrl(String imgSize){
		return JijizuUtil.getHeadImgUrl(headImgUrl, imgSize);
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Long getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(Long groupNum) {
		this.groupNum = groupNum;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserDesc() {
		if(!StringUtil.isNotNullOrEmpty(userDesc)){
			this.userDesc = CommonConstant.DEFAULT_USER_DESC;
		}
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public Long getNewMailNum() {
		return newMailNum;
	}

	public void setNewMailNum(Long newMailNum) {
		this.newMailNum = newMailNum;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Integer getYear(){
		if(birthday != null){
			return DateUtil.getYearByDate(birthday);
		}
		return null;
	}
	
	public Integer getMonth(){
		if(birthday != null){
			return DateUtil.getMonth(birthday);
		}
		return null;
	}
	
	public Integer getDay(){
		if(birthday != null){
			return DateUtil.getDayByDate(birthday);
		}
		return null;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getHometownProvince() {
		return hometownProvince;
	}
	
	public String getHometownProvinceStr(){
		if(StringUtil.isNotNullOrEmpty(hometownProvince)){
			PostArea postArea = InitData.areaProvince.get(Long.parseLong(hometownProvince));
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}

	public void setHometownProvince(String hometownProvince) {
		this.hometownProvince = hometownProvince;
	}

	public String getHometownCity() {
		return hometownCity;
	}
	
	public String getHometownCityStr(){
		if(StringUtil.isNotNullOrEmpty(hometownCity)){
			PostArea postArea = InitData.areaCity.get(Long.parseLong(hometownCity));
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}

	public void setHometownCity(String hometownCity) {
		this.hometownCity = hometownCity;
	}

	public String getHometownDistrict() {
		return hometownDistrict;
	}
	
	public String getHometownDistrictStr(){
		if(StringUtil.isNotNullOrEmpty(hometownDistrict)){
			PostArea postArea = InitData.areaDistrict.get(Long.parseLong(hometownDistrict));
			if(postArea != null){
				return postArea.getAreaName();
			}
		}
		return null;
	}

	public void setHometownDistrict(String hometownDistrict) {
		this.hometownDistrict = hometownDistrict;
	}

	public LivingCommunity getLivingCommunity() {
		return livingCommunity;
	}

	public void setLivingCommunity(LivingCommunity livingCommunity) {
		this.livingCommunity = livingCommunity;
	}

	public LivingCommunity getHometownLivingCommunity() {
		return hometownLivingCommunity;
	}

	public void setHometownLivingCommunity(LivingCommunity hometownLivingCommunity) {
		this.hometownLivingCommunity = hometownLivingCommunity;
	}
	
	public String getLivingCommunityName(){
		if(livingCommunity != null){
			return livingCommunity.getName();
		}
		return "请输入小区名或路名...";
	}
	
	public String getHometownLivingCommunityName(){
		if(hometownLivingCommunity != null){
			return hometownLivingCommunity.getName();
		}
		return "请输入小区名或路名...";
	}

	public String getStatusPermission() {
		return statusPermission;
	}

	public void setStatusPermission(String statusPermission) {
		this.statusPermission = statusPermission;
	}

	public String getAlbumPermission() {
		return albumPermission;
	}

	public void setAlbumPermission(String albumPermission) {
		this.albumPermission = albumPermission;
	}
	
	public String getConstellation(){
		if(birthday != null){
			return JijizuUtil.getConstellationNameByDate(birthday);
		}
		return null;
	}

	public UserSchool getLastestUserSchool() {
		return lastestUserSchool;
	}

	public void setLastestUserSchool(UserSchool lastestUserSchool) {
		this.lastestUserSchool = lastestUserSchool;
	}

	public UserJob getLastestUserJob() {
		return lastestUserJob;
	}

	public void setLastestUserJob(UserJob lastestUserJob) {
		this.lastestUserJob = lastestUserJob;
	}

	public Long getEnterpriseFlag() {
		return enterpriseFlag;
	}

	public void setEnterpriseFlag(Long enterpriseFlag) {
		this.enterpriseFlag = enterpriseFlag;
	}

	public Long getVFlag() {
		return vFlag;
	}

	public void setVFlag(Long vFlag) {
		this.vFlag = vFlag;
	}

	public Date getReadCommentTime() {
		return readCommentTime;
	}

	public void setReadCommentTime(Date readCommentTime) {
		this.readCommentTime = readCommentTime;
	}

	public Date getReadAtTime() {
		return readAtTime;
	}

	public void setReadAtTime(Date readAtTime) {
		this.readAtTime = readAtTime;
	}
	
	/**
	 * 
	 *******************************************************************************
	 * @function : 获取at时的名称：name(nickName) 形式
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public String getAtName(){
		if(StringUtil.isNotNullOrEmpty(name) && StringUtil.isNotNullOrEmpty(nickName)){
			return name+"("+nickName+")";
		}
		return null;
	}

	/**   
	 *******************************************************************************
	 * @function : 是否互相关注
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean isFollowEachOther(UserInfo sessionUserInfo){
		if(isFollowEachOther != null){
			return isFollowEachOther;
		}else{
			if(sessionUserInfo != null && userId != null){
				this.isFollowEachOther = JijizuUtil.isFollowEachOther(sessionUserInfo.getUserId(), userId);
			}else{
				this.isFollowEachOther = false;
			}
			return isFollowEachOther;
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否可查看用户微博
	 * @param sessionUserInfo
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean canViewStatus(UserInfo sessionUserInfo){
		if(SysDataDicConstant.PERMISSION_TYPE_ALL.equals(statusPermission)){
			return true;
		}
		if(sessionUserInfo != null && userId != null 
				&& sessionUserInfo.getUserId().longValue() == userId.longValue()){
			return true;
		}
		if(SysDataDicConstant.PERMISSION_TYPE_FRIENDS.equals(statusPermission)
				&& isFollowEachOther(sessionUserInfo)){
			return true;
		}
		return false;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否可查看用户相册
	 * @param sessionUserInfo
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean canViewAlbum(UserInfo sessionUserInfo){
		if(SysDataDicConstant.PERMISSION_TYPE_ALL.equals(albumPermission)){
			return true;
		}
		if(sessionUserInfo != null && userId != null 
				&& sessionUserInfo.getUserId().longValue() == userId.longValue()){
			return true;
		}
		if(SysDataDicConstant.PERMISSION_TYPE_FRIENDS.equals(albumPermission)
				&& isFollowEachOther(sessionUserInfo)){
			return true;
		}
		return false;
	}
	
}
