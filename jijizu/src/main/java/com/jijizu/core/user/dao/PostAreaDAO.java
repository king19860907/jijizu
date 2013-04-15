package com.jijizu.core.user.dao;

import java.util.List;

import com.jijizu.core.user.dto.PostArea;

public interface PostAreaDAO {
	
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
	List<PostArea> findAllArea();

}
