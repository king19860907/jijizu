package com.jijizu.core.user.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.jijizu.base.util.CookieUtil;
import com.jijizu.base.util.DateUtil;
import com.jijizu.base.util.Encrypter;
import com.jijizu.base.util.ImageUtils;
import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.album.service.AlbumService;
import com.jijizu.core.cache.service.CacheService;
import com.jijizu.core.check.CheckContext;
import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.CheckParam;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.InitData;
import com.jijizu.core.constant.OperateConstanct;
import com.jijizu.core.constant.SysDataDicConstant;
import com.jijizu.core.dto.AreaPostJson;
import com.jijizu.core.dto.JsonResult;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.email.dto.EmailTemplate;
import com.jijizu.core.email.service.EmailService;
import com.jijizu.core.follow.dao.FollowDAO;
import com.jijizu.core.notice.service.NoticeService;
import com.jijizu.core.search.dto.UserSearch;
import com.jijizu.core.service.MessageService;
import com.jijizu.core.user.dao.PostAreaDAO;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.ChildInfo;
import com.jijizu.core.user.dto.CompanyInfo;
import com.jijizu.core.user.dto.LivingCommunity;
import com.jijizu.core.user.dto.PostArea;
import com.jijizu.core.user.dto.RecommendUser;
import com.jijizu.core.user.dto.SchoolInfo;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.dto.UserJob;
import com.jijizu.core.user.dto.UserMessage;
import com.jijizu.core.user.dto.UserSchool;
import com.jijizu.core.user.service.UserInfoService;


public class UserInfoServiceImpl implements UserInfoService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private UserInfoDAO userInfoDAO;
	
	private PostAreaDAO postAreaDAO;
	
	private AlbumService albumService;
	
	private FollowDAO followDAO;
	
	private MessageService messageService;
	
	private NoticeService noticeService;
	
	private EmailService emailService;
	
	private CacheService cacheService;
	
	/**
	 * 用户注册验证
	 */
	private CheckContext userRegisterCheckContext;
	
	/**
	 * 用户登录验证
	 */
	private	CheckContext userLoginCheckContext;
	
	/**
	 * 添加工作信息验证
	 */
	private CheckContext addUserJobCheckContext;
	
	/**
	 * 删除工作信息验证
	 */
	private CheckContext deleteUserJobCheckContext;
	
	/**
	 * 更新用户工作信息验证
	 */
	private CheckContext updateUserJobCheckContext;
	
	/**
	 * 添加用户学校信息验证
	 */
	private CheckContext addUserSchoolCheckContext;
	
	/**
	 * 删除用户学校信息验证
	 */
	private CheckContext deleteUserSchoolCheckContext;
	
	/**
	 * 更新用户学校信息验证
	 */
	private CheckContext updateUserSchoolCheckContext;
	
	/**
	 * 更新用户信息验证
	 */
	private CheckContext updateUserInfoCheckContext;
	
	/**
	 * 添加孩子信息验证
	 */
	private CheckContext addChildCheckContext;
	
	/**
	 * 删除孩子信息验证
	 */
	private CheckContext deleteChildCheckContext;
	
	/**
	 * 更新孩子信息验证
	 */
	private CheckContext updateChildCheckContext;
	
	/**
	 * 获取用户消息提示验证
	 */
	private CheckContext getUserMessageCheckContext;
	
	/**
	 *	找回密码验证
	 */
	private CheckContext findPwdCheckContext;
	
	/**
	 * 重置密码验证
	 */
	private CheckContext resetPwdCheckContext;
	
	/**
	 * 找回密码Email模板
	 */
	private EmailTemplate findPwdEmailTemplate;
	
	/**   
	 *******************************************************************************
	 * @function : 重置密码
	 * @param logName
	 * @param userId
	 * @param random
	 * @param password
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult resetPwd(String logName,Long userId,String random,String password){
		Map<String,Object> para = new HashMap<String,Object>();
		logName = Encrypter.decrypt(logName);
		para.put(CheckParam.LOGNAME, logName);
		para.put(CheckParam.USERID, userId);
		para.put(CheckParam.RANDOM, random);
		para.put(CheckParam.PASSWORD, password);
		JsonResult result = resetPwdCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		userInfoDAO.updatePwd(userId, logName, password);
		try {
			cacheService.deleteSnaInfo(CacheConstant.FIND_PWD_USER_KEY_NAME+logName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 找回密码
	 * @param logName
	 * @param iCode
	 * @return
	 * @throws Exception 
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-13   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult findPwd(String logName,String iCode) throws Exception{
		Map<String,Object> para = new HashMap<String,Object>();
		para.put(CheckParam.LOGNAME, logName);
		para.put(CheckParam.ICODE, iCode);
		
		JsonResult result = findPwdCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		sendFindPwdEmail(logName);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}

	/**   
	 *******************************************************************************
	 * @function : 发送找回密码邮件
	 * @param logName
	 * @throws Exception
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-14   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void sendFindPwdEmail(String logName) throws Exception {
		UserInfo userInfo = userInfoDAO.getUserInfoByLogName(logName);
		String random =  StringUtil.UUIDString();
		Map<String,String> emailMap = new HashMap<String,String>();
		emailMap.put("logName", Encrypter.encrypt(logName));
		emailMap.put("userId", userInfo.getUserId()+"");
		emailMap.put("random", random);
		cacheService.setSnaInfo(CacheConstant.FIND_PWD_USER_KEY_NAME+logName, random, CacheConstant.CACHE_EXPRIATION_HALF_AN_HOUR);
		
		String content = emailService.getContent(findPwdEmailTemplate.getLocation(), emailMap);
		emailService.sendEmail(logName, findPwdEmailTemplate.getSubject(), content, true);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取推荐用户
	 * @param userId
	 * @param size
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
	public List<RecommendUser> findRecommendUser(Long userId,Long size){
		List<RecommendUser> userList = new ArrayList<RecommendUser>();
		List<Long> userIds = new ArrayList<Long>();
		UserInfo userInfo = userInfoDAO.getUserInfoById(userId);
		if(userInfo == null){
			return userList;
		}
		
		//查询邻居
		userList = userInfoDAO.findNeighbourRecommendUser(userId, size);
		if(userList != null){
			size = size - userList.size();
			for(RecommendUser u:userList){
				userIds.add(u.getUserId());
			}
		}
		
		//查询同学
		if(size != 0){
			List<RecommendUser> newList = userInfoDAO.findSchoolRecommendUser(userId, size, userIds);
			if(newList != null){
				size = size - newList.size();
				for(RecommendUser u:newList){
					userIds.add(u.getUserId());
				}
			}
			userList.addAll(newList);
		}
		
		//查询同事
		if(size != 0){
			List<RecommendUser> newList = userInfoDAO.findJobRecommendUser(userId, size, userIds);
			if(newList != null){
				size = size - newList.size();
				for(RecommendUser u:newList){
					userIds.add(u.getUserId());
				}
			}
			userList.addAll(newList);
		}
		
		//随机推荐(中文名用户)
		if(size !=0){
			List<RecommendUser> newList = userInfoDAO.findRecommendRandomByGBKName(userId, size, userIds);
			if(newList != null){
				size = size - newList.size();
				for(RecommendUser u:newList){
					userIds.add(u.getUserId());
				}
			}
			userList.addAll(newList);
		}
		
		//随机推荐(剩下的用户)
		if(size != 0){
			userList.addAll(userInfoDAO.findRecommendRandom(userId, size,userIds));
		}
		
		return userList;
	}

	/**   
	 *******************************************************************************
	 * @function :  根据用户ID获取用户信息
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
	@Override
	public UserInfo getUserInfoById(Long userId) {
		return userInfoDAO.getUserInfoById(userId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取用户新消息提示
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
	public JsonResult getUserMessage(UserInfo sessionUserInfo){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		JsonResult result = getUserMessageCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		Long userId = sessionUserInfo.getUserId();
		UserMessage userMessage = new UserMessage();
		userMessage.setCommentNum(messageService.getNewMessage(CacheConstant.MESSAGE_COMMENT_NEW_COUNT, userId));
		userMessage.setAtMeNum(messageService.getNewMessage(CacheConstant.MESSAGE_AT_NEW_COUNT, userId));
		userMessage.setMailNum(messageService.getNewMessage(CacheConstant.MESSAGE_MAIL_NEW_COUNT, userId));
		userMessage.setNoticeNum(messageService.getNewMessage(CacheConstant.MESSAGE_NOTICE_NEW_COUNT, userId));
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,userMessage);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据用户ID获取用户信息
	 * @param userId
	 * @param isLoadSchoolAndJob 是否加载学校和工作信息
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public UserInfo getUserInfoById(Long userId,boolean isLoadSchoolAndJob){
		
		UserInfo userInfo = userInfoDAO.getUserInfoById(userId);
		if(isLoadSchoolAndJob&&userInfo != null){
			List<UserSchool> schoolList = userInfoDAO.findUserSchool(userId, 1L);
			if(schoolList.size() > 0){
				userInfo.setLastestUserSchool(schoolList.get(0));
			}
			List<UserJob> jobList = userInfoDAO.findUserJob(userId, 1L);
			if(jobList.size() > 0){
				userInfo.setLastestUserJob(jobList.get(0));
			}
		}
		
		return userInfo;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 初始化方法
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-19   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void init(){
		InitData.areaJson = initArea();
	}
	
	/**   
	 *******************************************************************************
	 * @function : 初始化地区
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-19   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private String initArea(){
		List<PostArea> areaList = postAreaDAO.findAllArea();
        
        Iterator<PostArea> it = areaList.iterator();
        while (it.hasNext())
        {
        	PostArea area = it.next();
            if (area.getAreaId() < 100)
            {
               log.debug("wineConfig 446:::" + area.getAreaId());
            }
            if (area.getClassType().equals(SysDataDicConstant.POST_AREA_CLASS_TYPE_PROVINCE))
            {
            	InitData.areaProvince.put(area.getAreaId(),
                        area);
            }
            if (area.getClassType().equals(SysDataDicConstant.POST_AREA_CLASS_TYPE_CITY))
            {
            	InitData.areaCity.put(area.getAreaId(), area);
            }
            if (area.getClassType().equals(SysDataDicConstant.POST_AREA_CLASS_TYPE_DISTRICT))
            {
            	InitData.areaDistrict.put(area.getAreaId(),
                        area);
            }
        }
        AreaPostJson dto = new AreaPostJson();
        return JSONObject.fromObject(dto).toString();
	}
	
	/**   
	 *******************************************************************************
	 * @function : 用户注册
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
	public JsonResult registerUser(Map<String,Object> para){
		
		para.put(CheckParam.TARGET+CheckParam.IS_NEED_NULL_CHECK, true);
		JsonResult result = userRegisterCheckContext.check(para);
		
		if(result != null){
			return result;
		}
		
		Long userId = userInfoDAO.saveUser(para);

		//为用户创建“默认专辑”和微博配图
		if(userId != null){
			albumService.createDefaultAlbum(userId,userId);
		}
		
		String target = (String)para.get(CheckParam.TARGET);
		this.userLogin((String)para.get(CheckParam.LOGNAME),(String)para.get(CheckParam.PASSWORD),target,false);
		
		//添加官方账号为好友
		followDAO.followOne(userId, CommonConstant.OFFICAL_ACCOUNT_USER_ID);
		followDAO.followOne(CommonConstant.OFFICAL_ACCOUNT_USER_ID, userId);
		userInfoDAO.updateUserFriendNumByUserId(userId);
		userInfoDAO.updateUserFriendNumByUserId(CommonConstant.OFFICAL_ACCOUNT_USER_ID);
		
		//发送系统通知
		String content = "欢迎加入集集组，因为是小朋友主题社区，安全第一，所以再次提醒您以真实姓名注册，同时为便于加到更多好友，小集带您去完善您和您孩子的信息！<a href='/user/base.jspa'>http://www.jijizu.com/user/base.jspa</a>";
		noticeService.sendNotice(userId, content);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,target);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户信息
	 * @param para
	 * @param sessionUserInfo
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
	public JsonResult updateUserInfo(Map<String,Object> para,UserInfo sessionUserInfo){
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		//不需要为空检查的参数
		para.put(CheckParam.BLOOD+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.HOMETOWNPROVINCE+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.HOMETOWNCITY+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.HOMETOWNDISTRICT+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.BIRTHDAY+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.USERDESC+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.HOMETOWNLIVINGCOMMUNITY+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.LIVINGCOMMUNITY+CheckParam.IS_NEED_NULL_CHECK, false);
		
		JsonResult result = updateUserInfoCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		String birthday = (String)para.get(CheckParam.BIRTHDAY);
		para.put(CheckParam.BIRTHDAY, DateUtil.parseDate(birthday, "yyyy-MM-dd"));
		userInfoDAO.updateUserInfo(para);
		
		UserInfo userInfo = userInfoDAO.getUserInfoById(sessionUserInfo.getUserId());
		JijizuUtil.setSessionUserInfo(userInfo);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 用户登录
	 * @param logName
	 * @param password
	 * @param target
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
	public JsonResult userLogin(String logName,String password,String target,boolean autoLogin){
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put(CheckParam.LOGNAME, logName);
		para.put(CheckParam.PASSWORD, password);
		
		JsonResult result = userLoginCheckContext.check(para);
		
		if(result != null){
			return result;
		}
		
		UserInfo userInfo = (UserInfo)para.get(CheckParam.TMEP_USERINFO);
		
		System.out.println("----------------------------查询用户成功-------------------------------");
		
		CookieUtil.setUserIdToCookie(ServletActionContext.getRequest(),ServletActionContext.getResponse(), userInfo.getUserId(), autoLogin);
		
		CookieUtil.setCookie(ServletActionContext.getRequest(),ServletActionContext.getResponse(), "log_name", logName, CookieUtil.MAX_AGE_WEEK);
		
		JijizuUtil.setSessionUserInfo(userInfo);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG, target);
		
	}
	
	/**
	 * 
	 *******************************************************************************
	 * @function : 登出
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult logOut(Long userId){
		JijizuUtil.deleteSessionUserInfo(userId);
		
		CookieUtil.deleteUserIdCookie(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
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
		return userInfoDAO.getUserInfoByName(name);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 根据昵称获取用户
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
		return userInfoDAO.getUserInfoByNickName(nickName);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户头像
	 * @param sessionUserInfo
	 * @param headImgUrl
	 * @param x
	 * @param y
	 * @param h
	 * @param w
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public void updateHeadImage(UserInfo sessionUserInfo,String headImgUrl,int x,int y,int h,int w){
		
		long userId = sessionUserInfo.getUserId();
		
		// 进行头像的裁切，180*180 ，90*90， 50*50尺寸
		try {
			if (headImgUrl.indexOf('.') > 0) {//非sina头像才可以裁切。
				String originalFileFullName = headImgUrl.substring(headImgUrl.lastIndexOf('/')+1);
				String originalFileName = originalFileFullName.substring(0,originalFileFullName.lastIndexOf('.'));
				String fileExt = ImageUtils.getExtention(originalFileFullName);
				
				String fn = ImageUtils.getImgFilePath(null);
				
				File imageFile = new File(ServletActionContext
						.getServletContext().getRealPath(fn)
						+ "/"
						+ originalFileFullName);
				
				//根据选定的范围，重写图片
				ImageUtils.cutImageByDrag(imageFile, x, y, w,h);
				
				//1.压缩成缩略图
				ImageUtils.createSmallImage(imageFile, originalFileName, fn,fileExt 
						,Integer.parseInt(CommonConstant.HEAD_IMG_SIZE_50)
						,Integer.parseInt(CommonConstant.HEAD_IMG_SIZE_80)
						,Integer.parseInt(CommonConstant.HEAD_IMG_SIZE_150));
				
				//保存头像路径到数据库。
				String savedHeadImgUrl = headImgUrl.substring(0,
						headImgUrl.lastIndexOf('/') + 1)
						+ originalFileName+"_"+CommonConstant.HEAD_IMG_SIZE_150+ImageUtils.getExtention(fileExt);
				
				userInfoDAO.updateUserHeadImgUrl(savedHeadImgUrl, userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		return userInfoDAO.findUserInfoByAtRecord(refId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 添加工作信息 
	 * @param sessionUserInfo
	 * @param companyName
	 * @param province
	 * @param city
	 * @param startYear
	 * @param endYear
	 * @param department
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
	public JsonResult addUserJob(UserInfo sessionUserInfo, String companyName,
			String province, String city, String startYear, String endYear,String department){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.COMPANYNAME, companyName);
		JsonResult result = addUserJobCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		Long companyId = saveCompanyInfo(companyName);
		
		// 保存用户工作信息
		Long jobId = userInfoDAO.saveUserJob(sessionUserInfo.getUserId(), companyId, startYear, endYear, department,province,city);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,jobId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新用户工作信息
	 * @param sessionUserInfo
	 * @param jobId
	 * @param companyName
	 * @param province
	 * @param city
	 * @param startYear
	 * @param endYear
	 * @param department
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
	public JsonResult updateUserJob(UserInfo sessionUserInfo,Long jobId,String companyName,
			String province,String city,String startYear,String endYear,String department){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.COMPANYNAME, companyName);
		para.put(CheckParam.JOBID, jobId);
		JsonResult result = updateUserJobCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		Long companyId = saveCompanyInfo(companyName);
				
		// 更新用户工作信息
		userInfoDAO.updateUserJob(jobId, companyId, sessionUserInfo.getUserId(), startYear, endYear, department, province, city);
				
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}

	/**   
	 *******************************************************************************
	 * @function : 保存公司信息
	 * @param companyName
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
	private Long saveCompanyInfo(String companyName) {
		// 通过公司名称查询公司信息
		CompanyInfo company = userInfoDAO.getCompanyByName(companyName);
		Long companyId = null;
				
		// 如果不存在公司信息，保存新输入的公司信息
		if(company == null){
			companyId = userInfoDAO.saveCompany(companyName);
		}else{
			companyId = company.getCompanyId();
		}
		return companyId;
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
		List<UserJob> jobs = userInfoDAO.findUserJob(userId, rownum);
		for(UserJob job : jobs){
			List<UserInfo> users = userInfoDAO.findUserByCompany(job.getCompanyId(),userId, rownum);
			job.setColleagues(users);
		}
		return jobs;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询用户工作信息
	 * @param userId
	 * @param rownum
	 * @param isLoadUsers
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
	public List<UserJob> findUserJob(Long userId,Long rownum,boolean isLoadUsers){
		if(isLoadUsers){
			return findUserJob(userId, rownum);
		}
		return userInfoDAO.findUserJob(userId, rownum);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除用户工作信息
	 * @param jobId
	 * @param sessioUserInfo
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-18   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult deleteUserJob(Long jobId,UserInfo sessioUserInfo){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessioUserInfo);
		para.put(CheckParam.JOBID, jobId);
		
		JsonResult result = deleteUserJobCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		userInfoDAO.deleteUserJob(jobId, sessioUserInfo.getUserId());
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
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
		return userInfoDAO.findCompanyByName(companyName, rownum);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 添加用户学校信息
	 * @param sessionUserInfo
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
	public JsonResult addUserSchool(UserInfo sessionUserInfo,Long schoolId,String department,String type,String startYear,String schoolName){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		JsonResult result = addUserSchoolCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		
		para.put(CheckParam.SCHOOLID, schoolId);
		para.put(CheckParam.SCHOOLNAME, schoolName);
		para.put(CheckParam.SCHOOLTYPE, type);
		processSchoolInfo(para);
		schoolId = (Long)para.get(CheckParam.SCHOOLID);
		Long id = userInfoDAO.saveUserSchool(sessionUserInfo.getUserId(), schoolId, department, type, startYear);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,id);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询学校信息
	 * @param type
	 * @param areaName
	 * @param schoolName
	 * @param firstLetter	首字母
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
		return userInfoDAO.findSchoolInfo(type, areaName, schoolName,firstLetter);
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
		List<UserSchool> list = userInfoDAO.findUserSchool(userId, rownum);
		for(UserSchool userSchool : list){
			userSchool.setClassmates(userInfoDAO.findUserBySchool(userId, userSchool.getSchoolId(), 8L));
		}
		return list;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 查询用户学校信息
	 * @param userId
	 * @param rownum
	 * @param isLoadUser
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
	public List<UserSchool> findUserSchool(Long userId,Long rownum,boolean isLoadUser){
		if(isLoadUser){
			return findUserSchool(userId, rownum);
		}
		return userInfoDAO.findUserSchool(userId, rownum);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除用户学校信息
	 * @param userId
	 * @param schoolId
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-21   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public JsonResult deleteUserSchool(UserInfo sessionUserInfo,Long id){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.USERSCHOOLID, id);
		JsonResult result = deleteUserSchoolCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		userInfoDAO.deleteUserSchool(sessionUserInfo.getUserId(), id);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
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
	public JsonResult updateUserSchool(UserInfo sessionUserInfo,Long userSchoolId,Long schoolId,String department,String type,String startYear,String schoolName){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		JsonResult result = updateUserSchoolCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		para.put(CheckParam.SCHOOLID, schoolId);
		para.put(CheckParam.SCHOOLNAME, schoolName);
		para.put(CheckParam.SCHOOLTYPE, type);
		processSchoolInfo(para);
		schoolId = (Long)para.get(CheckParam.SCHOOLID);
		userInfoDAO.updateUserSchool(userSchoolId, sessionUserInfo.getUserId(), schoolId, department, type, startYear);
	
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
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
		return userInfoDAO.saveSchoolInfo(schoolName, type, areaLevel, areaName);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 添加孩子信息
	 * @param para
	 * @param sessionUserInfo
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
	public JsonResult addChild(Map<String,Object> para,UserInfo sessionUserInfo){
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.DEPARTMENT+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.SCHOOLNAME+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.CHILDID+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.SCHOOLID+CheckParam.IS_NEED_NULL_CHECK, false);
		JsonResult result = addChildCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		processSchoolInfo(para);
		
		para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		String birthday = (String)para.get(CheckParam.BIRTHDAY);
		para.put(CheckParam.BIRTHDAY, DateUtil.parseDate(birthday, "yyyy-MM-dd"));
		Long childId = userInfoDAO.saveChildInfo(para);
	
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG,childId);
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
		return userInfoDAO.findChildInfoByUserId(userId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 删除孩子信息
	 * @param sessionUserInfo
	 * @param childId
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
	public JsonResult deleteChild(UserInfo sessionUserInfo,Long childId){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.CHILDID, childId);
		JsonResult result = deleteChildCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		userInfoDAO.deleteChildInfo(childId, sessionUserInfo.getUserId());
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更新孩子信息
	 * @param sessionUserInfo
	 * @param para
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
	public JsonResult updateChild(UserInfo sessionUserInfo,Map<String,Object> para){
		para.put(CheckParam.SESSIONUSERINFO, sessionUserInfo);
		para.put(CheckParam.DEPARTMENT+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.SCHOOLNAME+CheckParam.IS_NEED_NULL_CHECK, false);
		para.put(CheckParam.SCHOOLID+CheckParam.IS_NEED_NULL_CHECK, false);
		JsonResult result = updateChildCheckContext.check(para);
		if(result != null){
			return result;
		}
		
		para.put(CheckParam.USERID, sessionUserInfo.getUserId());
		String birthday = (String)para.get(CheckParam.BIRTHDAY);
		para.put(CheckParam.BIRTHDAY, DateUtil.parseDate(birthday, "yyyy-MM-dd"));
		
		processSchoolInfo(para);
		
		userInfoDAO.updateChildInfo(para);
		
		return new JsonResult(OperateConstanct.OPERATE_SUCCESS, OperateConstanct.OPERATE_SUCCESS_DEFAULT_MSG);
	}

	/**   
	 *******************************************************************************
	 * @function : 操作学校信息
	 * @param para
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-3-8   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void processSchoolInfo(Map<String, Object> para) {
		String schoolName = (String)para.get(CheckParam.SCHOOLNAME);
		String type = (String)para.get("type");
		if(StringUtil.isNotNullOrEmpty(schoolName)){
			SchoolInfo schoolInfo = userInfoDAO.getSchoolInfoBySchoolNameAndType(schoolName, type);
			Long schoolIdLong = null;
			if(schoolInfo == null){
				schoolIdLong = userInfoDAO.saveSchoolInfo(schoolName, type, null, null);
			}else{
				schoolIdLong = schoolInfo.getSchoolId();
			}
			para.put(CheckParam.SCHOOLID, schoolIdLong);
		}
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
		return userInfoDAO.findLivingCommunity(name,areaName, areaNameFather,rownum);
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
		userInfoDAO.updateUserReadCommentTime(userId);
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
		userInfoDAO.updateUserReadAtTime(userId);
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
	public PaginationDTO<UserInfo> findUserListPage(UserSearch userSearch,long pageNum, long pageSize){
		return userInfoDAO.findUserListPage(userSearch, pageNum, pageSize);
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
	public PaginationDTO<UserInfo> findUserOrderByStatusNumPage(Long userId,long pageNum, long pageSize){
		return userInfoDAO.findUserOrderByStatusNumPage(userId, pageNum, pageSize);
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
	public PaginationDTO<UserInfo> findUserByCompanyPage(Long userId,Long companyId,long pageNum, long pageSize){
		return userInfoDAO.findUserByCompanyPage(userId,companyId, pageNum, pageSize);
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
		return userInfoDAO.getLivingCommunityById(id);
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
	public PaginationDTO<UserInfo> findUserByLivingCommunityPage(Long userId,Long livingCommunityId,Long district,long pageNum, long pageSize){
		return userInfoDAO.findUserByLivingCommunityPage(userId, livingCommunityId,district, pageNum, pageSize);
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
		return userInfoDAO.findUserBySchoolPage(userId, schoolId, pageNum, pageSize);
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
		return userInfoDAO.findUserByChildSchoolPage(userId, schoolId, pageNum, pageSize);
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
		return userInfoDAO.getChildInfoById(childId);
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	public void setPostAreaDAO(PostAreaDAO postAreaDAO) {
		this.postAreaDAO = postAreaDAO;
	}

	public void setUserRegisterCheckContext(CheckContext userRegisterCheckContext) {
		this.userRegisterCheckContext = userRegisterCheckContext;
	}

	public void setUserLoginCheckContext(CheckContext userLoginCheckContext) {
		this.userLoginCheckContext = userLoginCheckContext;
	}

	public void setAddUserJobCheckContext(CheckContext addUserJobCheckContext) {
		this.addUserJobCheckContext = addUserJobCheckContext;
	}

	public void setDeleteUserJobCheckContext(CheckContext deleteUserJobCheckContext) {
		this.deleteUserJobCheckContext = deleteUserJobCheckContext;
	}

	public void setUpdateUserJobCheckContext(CheckContext updateUserJobCheckContext) {
		this.updateUserJobCheckContext = updateUserJobCheckContext;
	}

	public void setAddUserSchoolCheckContext(CheckContext addUserSchoolCheckContext) {
		this.addUserSchoolCheckContext = addUserSchoolCheckContext;
	}

	public void setDeleteUserSchoolCheckContext(
			CheckContext deleteUserSchoolCheckContext) {
		this.deleteUserSchoolCheckContext = deleteUserSchoolCheckContext;
	}

	public void setUpdateUserSchoolCheckContext(
			CheckContext updateUserSchoolCheckContext) {
		this.updateUserSchoolCheckContext = updateUserSchoolCheckContext;
	}

	public void setUpdateUserInfoCheckContext(
			CheckContext updateUserInfoCheckContext) {
		this.updateUserInfoCheckContext = updateUserInfoCheckContext;
	}

	public void setAddChildCheckContext(CheckContext addChildCheckContext) {
		this.addChildCheckContext = addChildCheckContext;
	}

	public void setDeleteChildCheckContext(CheckContext deleteChildCheckContext) {
		this.deleteChildCheckContext = deleteChildCheckContext;
	}

	public void setUpdateChildCheckContext(CheckContext updateChildCheckContext) {
		this.updateChildCheckContext = updateChildCheckContext;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	public void setGetUserMessageCheckContext(
			CheckContext getUserMessageCheckContext) {
		this.getUserMessageCheckContext = getUserMessageCheckContext;
	}

	public void setFollowDAO(FollowDAO followDAO) {
		this.followDAO = followDAO;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setFindPwdCheckContext(CheckContext findPwdCheckContext) {
		this.findPwdCheckContext = findPwdCheckContext;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setFindPwdEmailTemplate(EmailTemplate findPwdEmailTemplate) {
		this.findPwdEmailTemplate = findPwdEmailTemplate;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public void setResetPwdCheckContext(CheckContext resetPwdCheckContext) {
		this.resetPwdCheckContext = resetPwdCheckContext;
	}

}
