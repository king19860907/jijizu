package com.jijizu.core.auth.dao.impl;

import java.util.Map;

import com.jijizu.core.auth.dao.AuthDAO;
import com.jijizu.core.dao.IbatisBaseDAO;

public class AuthDAOImpl extends IbatisBaseDAO
	implements AuthDAO {
	
	/**   
	 *******************************************************************************
	 * @function : 保存个人认证申请
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-1-29   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveAuthPersonal(Map<String,Object> para){
		return (Long)this.insert("saveAuthPersonal", para);
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否已经提交过个人认证申请
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean isHavePostPersonalAuth(Long userId){
		Long count = (Long)this.selectOneObject("countAuthPersonalNotRefuse", userId);
		return count > 0;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 是否已经提交过企业认证申请
	 * @param userId
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public boolean isHavePostEnterpriseAuth(Long userId){
		Long count = (Long)this.selectOneObject("countEnterpriseNotRefuse", userId);
		return count > 0;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 保存企业认证申请
	 * @param para
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-2-1   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public Long saveAuthEnterprise(Map<String,Object> para){
		return (Long)this.insert("saveAuthEnterprise", para);
	}

}
