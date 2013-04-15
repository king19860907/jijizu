package com.jijizu.core.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jijizu.base.util.CommonValidate;
import com.jijizu.core.dao.IbatisBaseDAO;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.search.dto.UserSearch;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.ChildInfo;
import com.jijizu.core.user.dto.CompanyInfo;
import com.jijizu.core.user.dto.LivingCommunity;
import com.jijizu.core.user.dto.RecommendUser;
import com.jijizu.core.user.dto.SchoolInfo;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.dto.UserJob;
import com.jijizu.core.user.dto.UserSchool;

public class UserInfoDAOImpl extends IbatisBaseDAO
	implements UserInfoDAO{
	
	/**   
	 *******************************************************************************
	 * @function : 根据用户ID获取用户信息
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public UserInfo getUserInfoById(Long userId){
		return (UserInfo)getSqlMapClientTemplate().queryForObject("getUserInfoById",userId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据用户登录名获取用户信息
	 * @param logName
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public UserInfo getUserInfoByLogName(String logName){
		return (UserInfo)getSqlMapClientTemplate().queryForObject("getUserInfoByLogName",logName);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存用户
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveUser(Map<String,Object> para){
		String logName = (String)para.get("logName");
		if(CommonValidate.isEmail(logName)){
			para.put("email", logName);
		}else if(CommonValidate.isMobile(logName)){
			para.put("mobile", logName);
		}
		return (Long)getSqlMapClientTemplate().insert("saveUser",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户信息
	 * @param para
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-24   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateUserInfo(Map<String,Object> para){
		this.update("updateUserInfo",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据用户名及密码获取用户信息
	 * @param logName
	 * @param password
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-21   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public UserInfo getUserInfoByLogNameAndPassword(String logName,String password){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("logName", logName);
		para.put("password", password);
		return (UserInfo)getSqlMapClientTemplate().queryForObject("getUserInfoByLogNameAndPassword",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户发送的微博数
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-24   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateUserStatusNumByUserId(Long userId){
		Map<String,Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		this.update("updateUserStatusNumByUserId", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户集集组数
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-27   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateUserGroupNumByUserId(Long userId){
		Map<String,Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		this.update("updateUserGroupNumByUserId", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户的好友数量
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-31   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateUserFriendNumByUserId(Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		this.update("updateUserFriendNumByUserId",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据名字获取用户
	 * @param name
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public UserInfo getUserInfoByName(String name){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("name", name);
		return (UserInfo)this.selectOneObject("getUserInfoByName", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取at记录用户
	 * @param refId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-5   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<UserInfo> findUserInfoByAtRecord(Long refId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("refId", refId);
		return (List<UserInfo>)this.select("findUserInfoByAtRecord", para);
	}
	
	/**
	 * 
	 *******************************************************************************
	 * @function : 根据昵称获取用户信息
	 * @param nickName
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-7   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public UserInfo getUserInfoByNickName(String nickName){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("nickName", nickName);
		return (UserInfo)this.selectOneObject("getUserInfoByNickName", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户头像
	 * @param headImgUrl
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-16   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateUserHeadImgUrl(String headImgUrl,Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("headImgUrl", headImgUrl);
		para.put("userId", userId);
		this.update("updateUserHeadImgUrl",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存公司信息
	 * @param companyName
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveCompany(String companyName){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("companyName", companyName);
		return (Long)this.insert("saveCompany", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存用户工作信息
	 * @param userId
	 * @param companyId
	 * @param startYear
	 * @param endYear
	 * @param department
	 * @param province
	 * @param city
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveUserJob(Long userId,Long companyId,String startYear,String endYear,String department,String province,String city){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("companyId", companyId);
		para.put("startYear", startYear);
		para.put("endYear", endYear);
		para.put("department", department);
		para.put("province", province);
		para.put("city", city);
		return (Long)this.insert("saveUserJob", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 通过公司名获取公司信息
	 * @param companyName
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public CompanyInfo getCompanyByName(String companyName){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("companyName", companyName);
		return (CompanyInfo)this.selectOneObject("getCompanyByName", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询用户工作信息
	 * @param userId
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<UserJob> findUserJob(Long userId,Long rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("rownum", rownum);
		return (List<UserJob>)this.select("findUserJob", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据公司名称查找用户
	 * @param companyId
	 * @param userId
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<UserInfo> findUserByCompany(Long companyId,Long userId,Long rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("companyId", companyId);
		para.put("userId", userId);
		para.put("rownum", rownum);
		return (List<UserInfo>)this.select("findUserByCompany", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除用户工作信息
	 * @param jobId
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteUserJob(Long jobId,Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("jobId", jobId);
		para.put("userId", userId);
		this.delete("deleteUserJob", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 通过名称查找公司信息
	 * @param companyName
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<CompanyInfo> findCompanyByName(String companyName,Long rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("companyName", companyName);
		para.put("rownum", rownum);
		return (List<CompanyInfo>)this.select("findCompanyByName", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户工作信息
	 * @param jobId
	 * @param companyId
	 * @param userId
	 * @param startYear
	 * @param endYear
	 * @param department
	 * @param province
	 * @param city
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateUserJob(Long jobId,Long companyId,Long userId,String startYear,String endYear,String department,String province,String city){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("jobId", jobId);
		para.put("companyId", companyId);
		para.put("userId", userId);
		para.put("startYear", startYear);
		para.put("endYear", endYear);
		para.put("department", department);
		para.put("province", province);
		para.put("city", city);
		this.update("updateUserJob", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存用户学校信息
	 * @param userId
	 * @param schoolId
	 * @param department
	 * @param type
	 * @param startYear
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-21   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveUserSchool(Long userId,Long schoolId,String department,String type,String startYear){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("schoolId", schoolId);
		para.put("department", department);
		para.put("type", type);
		para.put("startYear", startYear);
		return (Long)this.insert("saveUserSchool", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询学校信息
	 * @param type
	 * @param areaName
	 * @param schoolName
	 * @param firstLetter 首字母
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-21   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<SchoolInfo> findSchoolInfo(String type,String areaName,String schoolName,String firstLetter){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("type", type);
		para.put("areaName", areaName);
		para.put("schoolName", schoolName);
		para.put("firstLetter", firstLetter);
		return (List<SchoolInfo>)this.select("findSchoolInfo", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询用户学校信息
	 * @param userId
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-21   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<UserSchool> findUserSchool(Long userId,Long rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("rownum", rownum);
		return (List<UserSchool>)this.select("findUserSchool", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据学校查询用户
	 * @param userId
	 * @param schoolId
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-21   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<UserInfo> findUserBySchool(Long userId,Long schoolId,Long rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("schoolId", schoolId);
		para.put("rownum", rownum);
		return (List<UserInfo>)this.select("findUserBySchool", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除用户学校信息
	 * @param userId
	 * @param id
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-21   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteUserSchool(Long userId,Long id){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("id", id);
		this.delete("deleteUserSchool", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户学校信息
	 * @param userSchoolId
	 * @param userId
	 * @param schoolId
	 * @param department
	 * @param type
	 * @param startYear
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-23   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateUserSchool(Long userSchoolId,Long userId,Long schoolId,String department,String type,String startYear){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userSchoolId", userSchoolId);
		para.put("userId", userId);
		para.put("schoolId", schoolId);
		para.put("department", department);
		para.put("type", type);
		para.put("startYear", startYear);
		this.update("updateUserSchool",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存学校信息
	 * @param schoolName
	 * @param type
	 * @param areaLevel
	 * @param areaName
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-24   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveSchoolInfo(String schoolName,String type,String areaLevel,String areaName){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("schoolName", schoolName);
		para.put("type", type);
		para.put("areaLevel", areaLevel);
		para.put("areaName", areaName);
		return (Long) this.insert("saveSchoolInfo", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存孩子信息
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-24   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveChildInfo(Map<String,Object> para){
		return (Long)this.insert("saveChildInfo", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据昵称获取孩子信息
	 * @param nickName
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-24   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public ChildInfo getChildInfoByNickName(String nickName){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("nickName", nickName);
		return (ChildInfo)this.selectOneObject("getChildInfoByNickName", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据ID获取孩子信息
	 * @param childId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-25   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public ChildInfo getChildInfoById(Long childId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("childId", childId);
		return (ChildInfo)this.selectOneObject("getChildInfoById", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据用户查询孩子信息
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-25   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<ChildInfo> findChildInfoByUserId(Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		return (List<ChildInfo>)this.select("findChildInfoByUserId", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除孩子信息
	 * @param childId
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-25   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void deleteChildInfo(Long childId,Long userId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("childId", childId);
		para.put("userId", userId);
		this.update("deleteChildInfo",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新孩子信息
	 * @param para
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-25   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateChildInfo(Map<String,Object> para){
		this.update("updateChildInfo",para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询生活小区
	 * @param name
	 * @param areaNameFather
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-27   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<LivingCommunity> findLivingCommunity(String name,String areaName,String areaNameFather,int rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("name", name);
		para.put("areaName", areaName);
		para.put("areaNameFather", areaNameFather);
		para.put("rownum", rownum);
		return (List<LivingCommunity>)this.select("findLivingCommunity", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 通过ID获得生活小区
	 * @param id
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-27   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public LivingCommunity getLivingCommunityById(Long id){
		return (LivingCommunity)this.selectOneObject("getLivingCommunityById", id);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户读取评论时间
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateUserReadCommentTime(Long userId){
		this.update("updateUserReadCommentTime",userId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户读取at时间
	 * @param userId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateUserReadAtTime(Long userId){
		this.update("updateUserReadAtTime",userId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 随机查询推荐用户
	 * @param userId
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<RecommendUser> findRecommendRandom(Long userId,Long rownum,List<Long> userIds){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("rownum", rownum);
		para.put("userIds", userIds);
		return (List<RecommendUser>)this.select("findRecommendRandom", para);
	}
	
	
	/**   
	 *******************************************************************************
	 * @function : 随即查询中文用户名用户
	 * @param userId
	 * @param rownum
	 * @param userIds
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-17   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<RecommendUser> findRecommendRandomByGBKName(Long userId,Long rownum,List<Long> userIds){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("rownum", rownum);
		para.put("userIds", userIds);
		return (List<RecommendUser>)this.select("findRecommendRandomByGBKName", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 随机查询邻居推荐用户
	 * @param userId
	 * @param rownum
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<RecommendUser> findNeighbourRecommendUser(Long userId,Long rownum){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("rownum", rownum);
		return (List<RecommendUser>)this.select("findNeighbourRecommendUser", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 随机查询同校推荐用户
	 * @param userId
	 * @param rownum
	 * @param userIds
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<RecommendUser> findSchoolRecommendUser(Long userId,Long rownum,List<Long> userIds){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("rownum", rownum);
		para.put("userIds", userIds);
		return (List<RecommendUser>)this.select("findSchoolRecommendUser", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 随机查询同事推荐用户
	 * @param userId
	 * @param rownum
	 * @param userIds
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-26   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public List<RecommendUser> findJobRecommendUser(Long userId,Long rownum,List<Long> userIds){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("rownum", rownum);
		para.put("userIds", userIds);
		return (List<RecommendUser>)this.select("findJobRecommendUser", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询用户
	 * @param userSearch
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-27   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findUserListPage(UserSearch userSearch,long pageNum, long pageSize) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userSearch", userSearch);
		PaginationDTO<UserInfo> pagination = new PaginationDTO<UserInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findUserListPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查找用户更具微博数排序
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-7   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findUserOrderByStatusNumPage(Long userId,long pageNum, long pageSize) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		PaginationDTO<UserInfo> pagination = new PaginationDTO<UserInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findUserOrderByStatusNumPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更具学校类型及名称获取学校信息
	 * @param schoolName
	 * @param type
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-8   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public SchoolInfo getSchoolInfoBySchoolNameAndType(String schoolName,String type){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("schoolName", schoolName);
		para.put("type", type);
		return (SchoolInfo)this.selectOneObject("getSchoolInfoBySchoolNameAndType", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据公司名称查找用户（分页）
	 * @param userId
	 * @param companyId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-12   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findUserByCompanyPage(Long userId,Long companyId,long pageNum, long pageSize) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("companyId", companyId);
		PaginationDTO<UserInfo> pagination = new PaginationDTO<UserInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findUserByCompanyPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据生活小区查询用户
	 * @param userId
	 * @param livingCommunityId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-12   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findUserByLivingCommunityPage(Long userId,Long livingCommunityId,Long district,long pageNum, long pageSize) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("livingCommunityId", livingCommunityId);
		para.put("district", district);
		PaginationDTO<UserInfo> pagination = new PaginationDTO<UserInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findUserByLivingCommunityPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据学校查找用户(分页)
	 * @param userId
	 * @param schoolId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-12   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findUserBySchoolPage(Long userId,Long schoolId,long pageNum, long pageSize){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("schoolId", schoolId);
		PaginationDTO<UserInfo> pagination = new PaginationDTO<UserInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findUserBySchoolPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据孩子的学校查找用户
	 * @param userId
	 * @param schoolId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-13   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public PaginationDTO<UserInfo> findUserByChildSchoolPage(Long userId,Long schoolId,long pageNum, long pageSize){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("schoolId", schoolId);
		PaginationDTO<UserInfo> pagination = new PaginationDTO<UserInfo>();
		pagination.setPara(para);
		pagination.setPerPageRecordNum(pageSize);
		pagination.setCurPageNum(pageNum);
		return this.selectPage("findUserByChildSchoolPage", pagination);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新密码
	 * @param userId
	 * @param logName
	 * @param password
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updatePwd(Long userId,String logName,String password){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", userId);
		para.put("logName", logName);
		para.put("password", password);
		this.update("updatePwd",para);
		
	}
	
}
