package com.jijizu.core.service.impl;

import java.util.List;
import java.util.Map;

import com.jijizu.core.constant.InitData;
import com.jijizu.core.dao.SysDataDicDAO;
import com.jijizu.core.dto.SysDataDic;
import com.jijizu.core.service.SysDataDicService;

public class SysDataDicServiceImpl implements SysDataDicService {
	
	private SysDataDicDAO sysDataDicDAO;

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
			Long cancelFlag){
		return sysDataDicDAO.findSysDataDicByParentId(parentId, cancelFlag);
	}
	
	public void init(){
		initDictCodeAndNameRelationship();
	}
	
	/**   
	 *******************************************************************************
	 * @function : 初始化字典表code和name的对应关系
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-25   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private void initDictCodeAndNameRelationship(){
		List<Map<String,String>> list = sysDataDicDAO.findDictCodeAndNameRelationship();
		for(Map<String,String> map : list){
			String code = map.get("DICT_CODE");
			String name = map.get("NAME");
			InitData.sysDictCodeAndNameRelationShip.put(code, name);
		}
	}

	public SysDataDicDAO getSysDataDicDAO() {
		return sysDataDicDAO;
	}

	public void setSysDataDicDAO(SysDataDicDAO sysDataDicDAO) {
		this.sysDataDicDAO = sysDataDicDAO;
	}
	
}
