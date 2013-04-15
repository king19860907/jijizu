package com.jijizu.base.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.jijizu.core.cache.service.CacheService;
import com.jijizu.core.constant.CacheConstant;
import com.jijizu.core.constant.CommonConstant;
import com.jijizu.core.constant.InitData;
import com.jijizu.core.follow.service.FollowService;
import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.user.dao.UserInfoDAO;
import com.jijizu.core.user.dto.UserInfo;
import com.jijizu.core.user.service.UserInfoService;

/**
 ******************************************************************************* 
 * @project : 集集组
 * @type : JijizuUtil
 * @function : 集集组相关工具类
 ******************************************************************************* 
 * @version ：1.1.0
 * @creator ：majun
 * @date ：2012-12-24
 ******************************************************************************* 
 * @revision ：
 * @revisor ：
 * @date ：
 * @memo ：
 ******************************************************************************* 
 */

public class JijizuUtil {

	private static CacheService cacheService = null;
	
	protected static final Log log = LogFactory.getLog(JijizuUtil.class);
	
	private static UserInfoService userInfoService = null;
	
	private static FollowService followService = null;
	
	private static UserInfoService getUserInfoService(){
		if(userInfoService == null){
			userInfoService = (UserInfoService) ApplicationContextHolder.getBean("userInfoService");
		}
		return userInfoService;
	}
	
	private static FollowService getFollowService(){
		if(followService == null){
			followService = (FollowService) ApplicationContextHolder.getBean("followService");
		}
		return followService;
	}

	/**
	 * 返回memcached操作对应的service
	 * 
	 * @return
	 */
	private static CacheService getCacheService() {
		if (cacheService == null) {
			cacheService = (CacheService) ApplicationContextHolder
					.getBean("cacheService");
		}
		return cacheService;
	}

	/**
	 ******************************************************************************* 
	 * @function : 获取用户SESSION
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
	public static UserInfo getSessionUserInfo() {
		Long userId = CookieUtil.getUserIdFromCookie(ServletActionContext
				.getRequest());
		UserInfo userInfo = null;
		if (userId > 0) {
			try {
				userInfo = (UserInfo) getCacheService().getSnaInfo(
						CacheConstant.USER_INFO_SESSION_NAME + userId);
			} catch (Exception e) {
				e.equals(e.getMessage());
			}

			// 自动登录
			if (userInfo == null) {
				UserInfoDAO userInfoDAO =  (UserInfoDAO) ApplicationContextHolder
				.getBean("userInfoDAO");
				userInfo = userInfoDAO.getUserInfoById(userId);
				setSessionUserInfo(userInfo);
			}
		}

		return userInfo;
	}

	/**
	 ******************************************************************************* 
	 * @function : 设置用户SESSION
	 * @param userInfo
	 ******************************************************************************* 
	 * @creator ：majun
	 * @date ：2012-12-21
	 ******************************************************************************* 
	 * @revisor ：
	 * @date ：
	 * @memo ：
	 ******************************************************************************* 
	 */
	public static void setSessionUserInfo(UserInfo userInfo) {
		try {
			if (userInfo != null) {
				getCacheService().setSnaInfo(
						CacheConstant.USER_INFO_SESSION_NAME
								+ userInfo.getUserId(), userInfo,
						CacheConstant.CACHE_EXPRIATION_ONE_HOUR);
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 ******************************************************************************* 
	 * @function : 删除用户SESSION
	 * @param userId
	 ******************************************************************************* 
	 * @creator ：majun
	 * @date ：2012-12-21
	 ******************************************************************************* 
	 * @revisor ：
	 * @date ：
	 * @memo ：
	 ******************************************************************************* 
	 */
	public static void deleteSessionUserInfo(Long userId) {
		try {
			getCacheService().deleteSnaInfo(
					CacheConstant.USER_INFO_SESSION_NAME + userId);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取用户最后一条发送的微博内容
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-24   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static String getLastContent() {
		UserInfo userInfo = getSessionUserInfo();
		if (userInfo != null) {
				try {
					String lastContent = (String)getCacheService().getSnaInfo(
							CacheConstant.STATUS_LAST_CONTENT_BY_USER + userInfo.getUserId());
					return lastContent;
				} catch (Exception e) {
					log.error(e.getMessage());
				}
		}
		return null;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 记录用户最后一条微博的内容
	 * @param content
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-24   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static void setLastContent(String content){
		UserInfo userInfo = getSessionUserInfo();
		// 已登录情况。
		if (userInfo != null) {
				try {
					getCacheService().setSnaInfo(
							CacheConstant.STATUS_LAST_CONTENT_BY_USER + userInfo.getUserId(),
							content, CacheConstant.CACHE_EXPRIATION_ONE_HOUR);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取工程绝对路径
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-27   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static String getAbsolutePath(){
		String absolutePath = ServletActionContext.getRequest().getRequestURL().toString();
		return absolutePath.replaceAll(ServletActionContext.getRequest()
				.getServletPath(), "");
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取头像
	 * @param headImgUrl
	 * @param imgSize
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static String getHeadImgUrl(String headImgUrl,String imgSize){
		if(!StringUtil.isNotNullOrEmpty(headImgUrl)){
			headImgUrl = getAbsolutePath()+CommonConstant.DEFAULT_HEAD_IMG_URL+imgSize+".jpg";
			return headImgUrl;
		}else{
			String headImg = headImgUrl.replaceAll("_"+CommonConstant.HEAD_IMG_SIZE_150, "_"+imgSize);
			return headImg;
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 格式化输出内容
	 * @param s
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-28   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static String formatContent(String s){
	    if ((s==null)||(s.length()<1)){
	        return "";
	    }
	    
	    String result = s;	
	    
	    result = formatAtString(result);	    
	    String faceResult=result;
	    String regex = "\\[[a-zA-Z0-9\u4e00-\u9fa5]+\\]";//@[a-zA-Z0-9\u4e00-\u9fa5_]+
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(result);
        Map<String, StatusFace> faceMap = InitData.statusFaceMap;
        while (mat.find()){
        	String faceName = mat.group();
        	StatusFace face = faceMap.get(faceName);
        	if(face !=null){
        		faceResult=faceResult.replaceAll("\\["+face.getName()+"\\]", "<img src='"+face.getImgUrl()+"' alt='"+face.getName()+"'/>");
        	}
        }
	    return faceResult;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 处理at信息
	 * @param s
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
	public static String formatAtString(String s){
		
		 final String USER_VIEW_URL = "<a class=\"USER_CARD green\" nick-name=\"%s\" uid=\"%s\" title=\"...\" href=\"%s\">%s</a>";
		    
		    if ((s==null) || (s.length()<1)){
		        return "";
		    }
		    
		    StringBuffer sb = new StringBuffer();
		    
		    String regex = "@[^@\\s\\.:：*' '<#]+";//@[a-zA-Z0-9\u4e00-\u9fa5_]+
	        Pattern pat = Pattern.compile(regex);
	        Matcher mat = pat.matcher(s);
	        int idx=0;
	        
	        userInfoService = getUserInfoService();
	        
	        while (mat.find()){
	            int startIdx = mat.start();
	            int endIdx = mat.end();
	            
	            String atStr = mat.group();            
	            String nickName = atStr.substring(1);
	            
	            if (startIdx>idx){
	                sb.append(s.substring(idx, startIdx));
	            }
	            
	            String url = "#";
	            UserInfo userDto = null;
	            if (nickName!=null&&nickName.length()>0&&nickName.indexOf("(")>0&&nickName.indexOf(")")>0){
	            	userDto = userInfoService.getUserInfoByNickName(nickName.substring(nickName.indexOf("(")+1,nickName.indexOf(")")));
	            	if (userDto!=null){
	            		url = "/u/"+userDto.getUserId();
	            	}else{
	            		return s;
	            	}
	            }else{
	            	return s;
	            }
	            
	            String tempAtStr = "";
	            if(nickName.indexOf("(")>0&&nickName.indexOf(")")>0){
	            	tempAtStr = atStr.substring(0, nickName.indexOf("(")+1);
	            }else{
	            	tempAtStr = atStr;
	            }
	            
	            sb.append(String.format(USER_VIEW_URL, nickName,userDto.getUserId(), url, tempAtStr));
	            
	            idx = endIdx;
	        }
	        
	        if (idx<s.length()){
	            sb.append(s.substring(idx));
	        }
	        
	        return sb.toString();
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取nickname列表 并排重
	 * @param s
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
	public static Set<String> formatAtNickName(String s){
	    if ((s==null) || (s.length()<1)){
	        return null;
	    }
	    Set<String> list = new HashSet<String>();
	    
	    String regex = "@[^@\\s\\.:：*' '<#]+";//@[a-zA-Z0-9\u4e00-\u9fa5_]+
	    Pattern pat = Pattern.compile("\u56de\u590d"+regex);
	    Matcher mat = pat.matcher(s);
	    String content = mat.replaceAll("");
         pat = Pattern.compile(regex);
         mat = pat.matcher(content);
        try{
	        while (mat.find()){
	            String atStr = mat.group();        
	            String nickName = atStr.substring(atStr.indexOf("(")+1,atStr.indexOf(")"));
	            list.add(nickName);
	        }
        }catch(Exception e){
        	log.error(e);
        }
        return list;
	}
	
	public static String convertURL(String text,boolean replaceHtmlTag){
		if(replaceHtmlTag){
			text = text.replace("<", "&lt;").replace(">", "&gt;");
		}
		String regex = "((http://)?([a-z]+[.])|(www.))\\w+[.]([a-z]{2,4})?[[.]([a-z]{2,4})]+((/[\\S&&[^,;\"”，。；、/()\\[\\]（）【】\u4E00-\u9FA5]]+)+)?([.][a-z]{2,4}+|)";
		String A1 = " <a target=\"_bank\" href={0} title={1}>";
		String A2 = "</a>";
		StringBuffer sb = new StringBuffer(text);
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(text);
		int index = 0;
		int index1 = 0;
		List<String> urls = new ArrayList();
		while (mat.find()) {
			String url = mat.group();
			urls.add(url);
			if (url.indexOf("http://") != 0)
				url = "http://" + url;
			Object obj[] = { "'" + url + "'","'" + url + "'" };
			
			String a = MessageFormat.format(A1, obj);
			int l = a.length();
			index += index1;
			sb.insert(mat.start() + index, a);
			index += l;
			sb.insert((mat.end()) + index, A2);
			index1 = A2.length();
		}
		
		String result = sb.toString();
		for (String url : urls) {
			result = result.replace(">" + url + "</a>", ">http://jijizu.com/</a>");
		}
		return result;

	}
	
	/**
	 * 转换url成加密格式。
	 * 
	 * @param text
	 * @return
	 */
	public static String convertURL(String text) {
		return convertURL(text, true);
	}
	
	/**
	 * 去掉html标签 , (如"<a href...>abc</a>" 结果为  "abc"）
	 * @param html
	 * @return
	 */
	public static String removeHtmlTag(String html){
		
		if ((html==null) || (html.length()==0)){
		    return "";
		}		
		String nonHtml = html.replaceAll("<[^>]*>", "").replaceAll("&nbsp;", " ").replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("\n", "");
		
		//增加截取4个//@作为
		nonHtml = truncate3AtContent(nonHtml);
		if (nonHtml.length() > 140){
			//nonHtml = nonHtml.substring(0,135)+"...";
		}
		return nonHtml;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 去掉html标签
	 * @param html
	 * @param isGetHref	是否获取htef的值，为true时("<a href='123'...>abc</a>" 结果为  "123"），为false时("<a href='123'...>abc</a>" 结果为  "abc"）
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
	public static String removeHtmlTag(String html,boolean isGetHref) {
		StringBuffer nonHtml = new StringBuffer();
		if(!isGetHref) {
			return removeHtmlTag(html);
		}else {
			String regex = "<a[a-z0-9 ='\"\\\\_:/.>]+</a>";
			Pattern pat = Pattern.compile(regex);
			Matcher mat = pat.matcher(html);
			while(mat.find()){
				mat.appendReplacement(nonHtml, getHtmlHref(mat.group()));
			}
			mat.appendTail(nonHtml);
		}
		return nonHtml.toString();
	}
	
	/**
	 * 
	 *******************************************************************************
	 * @function : 获取html连接 (如"<a href='123'...>abc</a>" 结果为  "123"）
	 * @param html
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
	public static String getHtmlHref(String html) {
		String regex = "href=['\"]([a-z0-9:./]+)['\"]";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(html);
		while(mat.find()){
			return mat.group(1);
		}
		return html;
	}
	
	public static void main(String[] args) {
		String str = "today is {0} a nice day {1} !!!";
		StringBuffer sb = new StringBuffer();
		String regex = "[{0-9}]+";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(str);
		int i=1;
		while(matcher.find()) {
			matcher.appendReplacement(sb,"replace"+i);
			i++;
		}
		matcher.appendTail(sb);
		System.out.println(sb.toString());
	}
	
	/**
	 * 截取3个//@作为转发的附加语句。
	 * @param s
	 * @return
	 */
	public static String truncate3AtContent(String s) {
		String[] arr = s.split("//@");
		String temp = "";
		for (int i = 0; i < arr.length; i++) {
			temp += "//@" + arr[i];
			if (i == 3) {
				break;
			}
		}
		if (arr.length > 0) {
			return temp.substring(3);
		}
		return s;
	}	
	
	public static String ellipsis(String s, int length){
		return ellipsis(s, length, "");
	}
	
	public static String ellipsis(String s, int length, String emptyString){	   
	   
	    if ((length<=0) || (s==null) || (s.length()==0)){
	       return emptyString;
	    }   
	    
	   s = removeHtmlTag(s);
	   
	    if (s.length()<=length){
	        return s;
	    }
	   
	    return s.substring(0, length) + "...";
	}
	
	
	/**   
	 *******************************************************************************
	 * @function : 是否互相关注
	 * @param userId
	 * @param followUserId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-10   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static boolean isFollowEachOther(Long userId,Long followUserId){
		return getFollowService().isFollowEachOther(userId, followUserId);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获取星座民称
	 * @param date
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
	public static String getConstellationNameByDateString(String date){
		SimpleDateFormat sdf =  new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US);  
		String cname = "";
		try {
			cname = getConstellationNameByDate(sdf.parse(date));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cname;
	}

	public static String getConstellationNameByDate(Date date){
		if(date==null){
			return null;
		}
		String cname="";
		SimpleDateFormat dformat=new SimpleDateFormat("Mdd");
		int md = Integer.valueOf(dformat.format(date));
		if(md>=120 && md<=218){
			cname="水瓶座";
		}else if(md>=219 && md<=320){
			cname="双鱼座";
		}else if(md>=321 && md<=420){
			cname="白羊座";
		}else if(md>=421 && md<=520){
			cname="金牛座";
		}else if(md>=521 && md<=621){
			cname="双子座";
		}else if(md>=622 && md<=722){
			cname="巨蟹座";
		}else if(md>=723 && md<=822){
			cname="狮子座";
		}else if(md>=823 && md<=922){
			cname="处女座";
		}else if(md>=923 && md<=1023){
			cname="天秤座";
		}else if(md>=1024 && md<=1122){
			cname="天蝎座";
		}else if(md>=1123 && md<=1221){
			cname="射手座";
		}else if(md>=101 && md<=119){
			cname="摩羯座";
		}else if(md>=1222 && md<=1231){
			cname="摩羯座";
		}
		return cname;
	}
	
	public static Map<Integer,String> weekNumStringMap = new HashMap<Integer,String>();
	
	static{
		weekNumStringMap.put(1, "周一");
		weekNumStringMap.put(2, "周二");
		weekNumStringMap.put(3, "周三");
		weekNumStringMap.put(4, "周四");
		weekNumStringMap.put(5, "周五");
		weekNumStringMap.put(6, "周六");
		weekNumStringMap.put(0, "周日");
	}


}
