package com.jijizu.core.dao;

import java.util.List;
import java.util.Map;

import com.jijizu.core.dto.SysDataDic;

public interface SysDataDicDAO {

	/**   
	 *******************************************************************************
	 * @function : 根据复类型获取系统字典
	 * @param parentId
	 * @param cancelFlag
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-19   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	List<SysDataDic> findSysDataDicByParentId(String parentId,
			Long cancelFlag);
	
	/**   
	 *******************************************************************************
	 * @function : 查询出字典表中code和name的对应关系
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-25   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	List<Map<String,String>> findDictCodeAndNameRelationship();
	
}
