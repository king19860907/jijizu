package com.jijizu.core.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jijizu.core.status.dto.StatusFace;
import com.jijizu.core.user.dto.PostArea;

/**   
 *******************************************************************************
 * @project : 也买网   
 * @type : InitData
 * @function : 初始化数据类
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-20   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class InitData {

	/**
	 * 地区
	 */
    public static Map<Long, PostArea> areaProvince = new HashMap<Long, PostArea>();
    
    public static Map<Long, PostArea> areaCity = new HashMap<Long, PostArea>();
    
    public static Map<Long, PostArea> areaDistrict = new HashMap<Long, PostArea>();
	
    /**
     * 地区json字符串
     */
    public static String areaJson = null;
    
    /**
	 * 敏感词：character敏感词首字，list敏感词列表
	 */
	public static Map<Character, List<String>> illegalwordMap = new HashMap<Character, List<String>>();
	
	/**
	 * 字段表code和name的对应关心
	 */
    public static Map<String, String> sysDictCodeAndNameRelationShip = new HashMap<String, String>();
	
    /**
     * 微博表情
     */
    public static Map<String,StatusFace> statusFaceMap = new HashMap<String,StatusFace>();
}
