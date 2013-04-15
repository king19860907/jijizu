package com.jijizu.web.user.action;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jijizu.base.util.CookieUtil;
import com.jijizu.base.util.IdentifyingCode;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.cache.service.CacheService;
import com.jijizu.core.check.service.CheckService;
import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.CookieConstant;
import com.jijizu.core.constant.InitData;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.user.dto.CompanyInfo;
import com.jijizu.core.user.dto.LivingCommunity;
import com.jijizu.core.user.dto.PostArea;
import com.jijizu.core.user.dto.SchoolInfo;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;
import com.jijizu.web.action.BaseAction;

@Controller
@Scope("prototype")
public class UserActionAjax extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 419431573125890636L;
	
	private UserInfoService userInfoService;
	
	private CheckService logNameNotExistCheck;
	
	private CheckService nickNameNotExistCheck;
	
	private String logName;
	
	private String password;
	
	private String companyName;
	
	private String province;
	
	private String city;
	
	private String district;
	
	private String startYear;
	
	private String endYear;
	
	private String department;
	
	private Long jobId;
	
	private String q;
	
	private Long schoolId;
	
	private String type;
	
	private Long userSchoolId;
	
	private Long childId;
	
	private String areaNameFather;
	
	private boolean isAutoLogin;
	
	private String schoolName;
	
	private CacheService cacheService;
	
	private String iCode;
	
	private Long userId;
	
	private String random;
	
	public String resetPwd(){
		
		JsonResult result = userInfoService.resetPwd(logName, userId, random, password);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String findPwd() throws Exception{
		
		JsonResult result = userInfoService.findPwd(logName, iCode);
				
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String identifyingCode(){
		
		 //设置不缓存图片   
        response.setHeader("Pragma", "No-cache");   
        response.setHeader("Cache-Control", "No-cache");   
        response.setDateHeader("Expires", 0) ;   
        //指定生成的相应图片   
        response.setContentType("image/jpeg") ;   
        IdentifyingCode idCode = new IdentifyingCode();   
        BufferedImage image =new BufferedImage(idCode.getWidth() , idCode.getHeight() , BufferedImage.TYPE_INT_BGR) ;   
        Graphics2D g = image.createGraphics() ;   
        //定义字体样式   
        Font myFont = new Font("黑体" , Font.BOLD , 22) ;   
        //设置字体   
        g.setFont(myFont) ;   
           
        g.setColor(idCode.getRandomColor(200 , 250)) ;   
        //绘制背景   
        g.fillRect(0, 0, idCode.getWidth() , idCode.getHeight()) ;   
           
        g.setColor(idCode.getRandomColor(180, 200)) ;   
        idCode.drawRandomLines(g, 160) ;   
        
        //验证码
        String code = idCode.drawRandomString(4, g) ;   
        //生成随机数cookie
        String uid = StringUtil.UUIDString();
        CookieUtil.setCookie(request,response,CookieConstant.IDENTIFYING_CODE, uid, 60*10);
        //将随机数作为key，验证吗作为value放入memcache
        try {
			cacheService.setSnaInfo(uid, code, CacheConstant.CACHE_EXPRIATION_TEN_MINUTES);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
        g.dispose() ;   
        try {
			ImageIO.write(image, "JPEG", response.getOutputStream()) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        return NONE;
		
	}
	
	public String getNewMessage(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = userInfoService.getUserMessage(sessionUserInfo);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String getAreaData(){
		 super.outString(InitData.areaJson);
		 return NONE;
	}
	
	public String userRegister(){
		
		Map<String, Object> para = this.getParams();
		
		JsonResult result = userInfoService.registerUser(para);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String updateUserInfo(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		Map<String,Object> para = this.getParams();
		
		JsonResult result = userInfoService.updateUserInfo(para,sessionUserInfo);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String checkLogName(){
		
		Map<String, Object> para = this.getParams();
		
		JsonResult result = logNameNotExistCheck.check(para);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String checkNickName(){
		
		Map<String,Object> para = this.getParams();
		
		JsonResult result = nickNameNotExistCheck.check(para);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String userLogin(){
		
		JsonResult result = userInfoService.userLogin(logName, password,target,isAutoLogin);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String logOut(){
			
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		if(sessionUserInfo != null){
			JsonResult result = userInfoService.logOut(sessionUserInfo.getUserId());
			this.outObjectToJson(result);
		}
		return NONE;
	}
	
	public String addUserJob(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = userInfoService.addUserJob(sessionUserInfo, companyName, province, city, startYear, endYear, department);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String updateUserJob(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = userInfoService.updateUserJob(sessionUserInfo, jobId, companyName, province, city, startYear, endYear, department);
				
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String deleteUserJob(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = userInfoService.deleteUserJob(jobId, sessionUserInfo);
	
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String findCompanyNames(){
		
		UserInfo sessionUserinfo = getSessionUserInfo();
		
		if(sessionUserinfo != null){
			List<CompanyInfo> companyList = userInfoService.findCompanyByName(q, 10L);
			List<String> nameList = new ArrayList<String>();
			for(CompanyInfo c : companyList){
				nameList.add(c.getCompanyName()+"|"+c.getCompanyId());
			}
			this.outListToJson(nameList);
		}
		
		return NONE;
	}
	
	public String addUserSchool(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = userInfoService.addUserSchool(sessionUserInfo, schoolId, department, type, startYear,schoolName);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String deleteUserSchool(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = userInfoService.deleteUserSchool(sessionUserInfo, userSchoolId);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String updateUserSchool(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = userInfoService.updateUserSchool(sessionUserInfo, userSchoolId, schoolId, department, type, startYear,schoolName);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String findSchoolNames(){
		
		UserInfo sessionUserinfo = getSessionUserInfo();
		
		if(sessionUserinfo != null){
			List<SchoolInfo> schoolList = userInfoService.findSchoolInfo(type,null, q, null);
			List<String> nameList = new ArrayList<String>();
			for(SchoolInfo c : schoolList){
				nameList.add(c.getSchoolName()+"|"+c.getSchoolId());
			}
			this.outListToJson(nameList);
		}
		
		return NONE;
	}
	
	public String findLivingCommunityNames(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		try{
			areaNameFather = areaNameFather.substring(0,areaNameFather.length()-2);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(sessionUserInfo != null){
			
			String areaName = null;
			if(StringUtil.isNotNullOrEmpty(district)){
				PostArea  area= InitData.areaDistrict.get(Long.parseLong(district));
				if(area != null){
					areaName = area.getAreaName().substring(0,area.getAreaName().length()-1);
				}
			}
			
			List<LivingCommunity> list = userInfoService.findLivingCommunity(q,areaName, areaNameFather,20);
			List<String> nameList = new ArrayList<String>();
			for(LivingCommunity l : list){
				nameList.add(l.getName()+"|"+l.getId());
			}
			this.outListToJson(nameList);
		}
		return NONE;
	}
	
	public String saveSchoolInfo(){
		
		UserInfo sessionUserinfo = getSessionUserInfo();
		if(sessionUserinfo != null && StringUtil.isNotNullOrEmpty(q)){
			String s1[] = q.split(":");
			String areaName = s1[0];
			String univString = s1[1];
			String univs[] = univString.split("-");
			for(String nuivName : univs){
				userInfoService.saveSchoolInfo(nuivName,type, SysDataDicConstant.POST_AREA_CLASS_TYPE_PROVINCE, areaName);
			}
		}
		
		return NONE;
	}
	
	public String addChild(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		Map<String,Object> para = getParams();
		
		JsonResult result = userInfoService.addChild(para, sessionUserInfo);
		
		this.outObjectToJson(result);
		
		return NONE;
	}
	
	public String deleteChild(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		JsonResult result = userInfoService.deleteChild(sessionUserInfo, childId);
		
		this.outObjectToJson(result);
		
		return NONE;
		
	}
	
	public String updateChild(){
		
		UserInfo sessionUserInfo = getSessionUserInfo();
		
		Map<String,Object> para = getParams();
		
		JsonResult result = userInfoService.updateChild(sessionUserInfo, para);
		
		this.outObjectToJson(result);
		
		return NONE;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public void setLogNameNotExistCheck(CheckService logNameNotExistCheck) {
		this.logNameNotExistCheck = logNameNotExistCheck;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public void setNickNameNotExistCheck(CheckService nickNameNotExistCheck) {
		this.nickNameNotExistCheck = nickNameNotExistCheck;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUserSchoolId(Long userSchoolId) {
		this.userSchoolId = userSchoolId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public void setAreaNameFather(String areaNameFather) {
		this.areaNameFather = areaNameFather;
	}

	public boolean isAutoLogin() {
		return isAutoLogin;
	}

	public void setAutoLogin(boolean isAutoLogin) {
		this.isAutoLogin = isAutoLogin;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public void setICode(String iCode) {
		this.iCode = iCode;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setRandom(String random) {
		this.random = random;
	}

}
