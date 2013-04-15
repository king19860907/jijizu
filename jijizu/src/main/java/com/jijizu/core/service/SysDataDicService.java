package com.jijizu.core.service;

import java.util.List;

import com.jijizu.core.dto.SysDataDic;

public interface SysDataDicService {

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
	List<SysDataDic> findSysDataDicByParentId(String parentId, Long cancelFlag);

}
