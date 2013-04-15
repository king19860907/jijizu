package com.jijizu.core.user.dao.impl;

import java.util.List;

import com.jijizu.core.dao.IbatisBaseDAO;
import com.jijizu.core.user.dao.PostAreaDAO;
import com.jijizu.core.user.dto.PostArea;

public class PostAreaDAOImpl extends IbatisBaseDAO
	implements PostAreaDAO{

	/**   
	 *******************************************************************************
	 * @function : 查询所有地区
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
	public List<PostArea> findAllArea(){
		return getSqlMapClientTemplate().queryForList("findAllArea");
	}
	
}
