package com.jijizu.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jijizu.core.dao.IbatisBaseDAO;
import com.jijizu.core.dao.SysDataDicDAO;
import com.jijizu.core.dto.SysDataDic;

public class SysDataDicDAOImpl extends IbatisBaseDAO implements SysDataDicDAO {

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
	public List<SysDataDic> findSysDataDicByParentId(String parentId,
			Long cancelFlag) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("parentId", parentId);
		para.put("cancelFlag", cancelFlag);
		return (List<SysDataDic>) getSqlMapClientTemplate().queryForList(
				"findSysDataDicByParentId", para);
	}
	
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
	public List<Map<String,String>> findDictCodeAndNameRelationship(){
		return this.select("findDictCodeAndNameRelationship", null);
	}

}
