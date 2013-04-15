package com.jijizu.core.constant;

/**   
 *******************************************************************************
 * @project : 集集组   
 * @type : CacheConstant
 * @function : Cache相关静态常量
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-21   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class CacheConstant {
	
	/**
     * 用户SESSION key name
     */
    public static String USER_INFO_SESSION_NAME = "user_info_";
    
    /**
     * 用户最后一条发送的微博内容 key_name
     */
    public static String STATUS_LAST_CONTENT_BY_USER = "STATUS_LAST_CONTENT_BY_USER_";
    
    /**
     * 表情类型 key_name
     */
    public static String STATUS_FACE_TYPE = "STATUS_FACE_TYPE_";
	
    /**
     * 过期时间:1小时
     */
	public static final int CACHE_EXPRIATION_ONE_HOUR = 60 * 60;
	
	 /**
     * 过期时间:半小时
     */
	public static final int CACHE_EXPRIATION_HALF_AN_HOUR = 60 * 30;
	
	 /**
     * 过期时间:1天
     */
	public static final int CACHE_EXPRIATION_ONE_DAY = 60 * 60 * 24;
	
	 /**
     * 过期时间:10分钟
     */
	public static final int CACHE_EXPRIATION_TEN_MINUTES = 60 * 10;
	
	/**
	 * 热门集集组缓存key_name
	 */
	public static final String HOT_GROUPS_KEY_NAME = "HOT_GROUPS_KEY_NAME_";
	
	/**
	 * 正在招募集集组缓存key_name
	 */
	public static final String CONVENE_GROUP_KEY_NAME = "CONVENE_GROUP_KEY_NAME_";
	
	/**
	 * 找回密码缓存key_name
	 */
	public static final String FIND_PWD_USER_KEY_NAME = "FIND_PWD_USER_KEY_NAME_";
	
	 /**
     * 新评论提示
     */
    public static final String MESSAGE_COMMENT_NEW_COUNT = "MESSAGE_COMMENT_NEW_COUNT_";
    
    /**
     * 新at提示
     */
    public static final String MESSAGE_AT_NEW_COUNT = "MESSAGE_AT_NEW_COUNT_";
    
    /**
     * 新系统通知提示
     */
    public static final String MESSAGE_NOTICE_NEW_COUNT = "MESSAGE_NOTICE_NEW_COUNT_";
    
    /**
     * 新私信提示
     */
    public static final String MESSAGE_MAIL_NEW_COUNT = "MESSAGE_MAIL_NEW_COUNT_";
	
}
