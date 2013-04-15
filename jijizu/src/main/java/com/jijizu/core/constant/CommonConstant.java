package com.jijizu.core.constant;


/**   
 *******************************************************************************
 * @project : 也买网   
 * @type : CommonConstant
 * @function : 公共的静态常量-一些难以归类的静态常量都在这里
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-19   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class CommonConstant {
	
	/**
	 * 集集组官方账号用户ID
	 */
	public static final Long OFFICAL_ACCOUNT_USER_ID = 2L;

    /**
     * 是否取消：已取消
     */
    public static final Long CANCEL_FLAG_YES = 1L;
    
    /**
     * 是否取消：未取消
     */
    public static final Long CANCEL_FLAG_NO = 0L;
    
    /**
     * 不显示标识：不显示
     */
    public static final Long NOT_SHOW_FLAG_YES = 1L;
    
    /**
     * 不显示标识：显示
     */
    public static final Long NOT_SHOW_FLAG_NO = 0L;
    
    /**
     * 真标识
     */
    public static final Long TRUE = 1l;
    
    /**
     * 假标识
     */
    public static final Long FALSE = 0l;
    
    /**
     * 用户头像尺寸：50*50
     */
    public static final String HEAD_IMG_SIZE_50 = "50";
    
    /**
     * 用户头像尺寸：80*80
     */
    public static final String HEAD_IMG_SIZE_80= "80";
    
    /**
     * 用户头像尺寸：150*150
     */
    public static final String HEAD_IMG_SIZE_150 = "150";
    
    /**
     * 默认头像路径
     */
    public static final String DEFAULT_HEAD_IMG_URL="/images/people/defaultHeader_";
    
    /**
     * 默认用户简介
     */
    public static final String DEFAULT_USER_DESC = "暂无介绍";
    
    /**
     * 好友类型：他的好友
     */
    public static final String FRIENDS_TYPE_HIS_FRIENDS = "his";
    
    /**
     * 好友类型：共同好友
     */
    public static final String FRIENDS_TYPE_COMMON_FRIENDS = "common";
    
    /**
     * 集集组状态：尚未开始
     */
    public static final String GROUP_STATUS_NOT_BEGIN = "0";
    
    /**
     * 集集组状态：进行中
     */
    public static final String GROUP_STATUS_ING = "1";
    
    /**
     * 集集组状态：已结束
     */
    public static final String GROUP_STATUS_END = "2";
    
    /**
     * 集集组状态：正在报名
     */
    public static final String GROUP_STATUS_APPLY_ING = "3";
    
    /**
     * 相册名称：默认专辑
     */
    public static final String ALBUM_NAME_DEFAULT = "默认专辑";
    
    /**
     * 相册名称：微博配图
     */
    public static final String ALBUM_NAME_STATUS = "微博配图";
    
}
