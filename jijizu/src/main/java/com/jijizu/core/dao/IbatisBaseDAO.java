package com.jijizu.core.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jijizu.core.dto.PaginationDTO;
import com.jijizu.core.exception.DAOException;

public class IbatisBaseDAO extends SqlMapClientDaoSupport {
    protected final Log log = LogFactory.getLog(getClass());
    /**
     * 考虑到xml并没有配置所有的子类的sqlMapClient， 这里再注一下
     * 默认是sqlMapClient
     * @author jay_liu
     * @param sqlMapClient
     */
    @Resource(name="sqlMapClient")
    public void setSqlMapClientWorkAround(SqlMapClient sqlMapClient) {
    	if (getSqlMapClient()==null) {
    		this.setSqlMapClient(sqlMapClient);
    	}
    }

    /**   
     *******************************************************************************
     * @function : 分页查询
     * @param <T>
     * @param sqlId
     * @param pagination
     * @return
     *******************************************************************************
     * @creator ：hong   
     * @date ：2011-8-12   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    @SuppressWarnings("unchecked")
	public <T> PaginationDTO<T> selectPage(String sqlId, PaginationDTO<T> pagination)
    {
        Map<String, Object> para = pagination.getPara();
        // 如果翻页则只在第一次计算总页数，总记录数需要页面回传。
        if (pagination.getRecordCnt() == 0)
        {
            Object cnt = this.getSqlMapClientTemplate().queryForObject(
                    sqlId + "_cnt", para);
            if (cnt != null)
            {
                log.info("统计信息返回:"+cnt);
                Map map = (Map) cnt;
                pagination.setRecordCnt(map.get("CNT"));
            }
        }
        // 有数据则执行数据查询
        if (pagination.getRecordCnt() > 0)
        {
            para.put("begNum", pagination.getBegRecordNum());
            para.put("endNum", pagination.getEndRecordNum());
            pagination.setResult(this.getSqlMapClientTemplate().queryForList(
                    sqlId, para));
          
        } else
        {
            List<T> result = new ArrayList<T>();
            pagination.setResult(result);
        }
        return pagination;
    }

    public int update(String sqlId, Object para) {
        try {
            return this.getSqlMapClientTemplate().update(sqlId, para);
        } catch (Exception e) {
            throw new DAOException(sqlId, e);
        }
    }
    
    public int update(String sqlId) {
        try {
            return this.getSqlMapClientTemplate().update(sqlId);
        } catch (Exception e) {
            throw new DAOException(sqlId, e);
        }
    }

    public Object insert(String sqlId, Object para) {
        try {
            if(log.isDebugEnabled()){
                log.debug("增加数据:"+sqlId+" params:"+para);
            }
            return this.getSqlMapClientTemplate().insert(sqlId, para);
        } catch (Exception e) {
            throw new DAOException(sqlId, e);
        }
    }

    public int delete(String sqlId, Object para) {
        try {
            return this.getSqlMapClientTemplate().delete(sqlId, para);
        } catch (Exception e) {
            throw new DAOException(sqlId, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List select(String sqlId, Object para) {
        try {
            return this.getSqlMapClientTemplate().queryForList(sqlId, para);
        } catch (Exception e) {
            throw new DAOException(sqlId, e);
        }
    }

    /**
     * 
     ******************************************************************************* 
     * @function : 查询返回
     * @param sqlId
     * @param para
     * @return
     ******************************************************************************* 
     * @creator ：weishen
     * @date ：2011-3-24
     ******************************************************************************* 
     * @revisor ：
     * @date ：
     * @memo ：
     ******************************************************************************* 
     */
    public Object selectOneObject(String sqlId, Object para) {
        try {
            return this.getSqlMapClientTemplate().queryForObject(sqlId, para);
        } catch (Exception e) {
            throw new DAOException(sqlId, e);
        }
    }

    /**
     * 
     * @函数说明：合并操作，使用 oracle merge . sql 注意写法
     * @创建人：weishen
     * @创建时间：2010-12-7 下午02:05:54
     * @修改人：
     * @修改时间：
     * @修改备注：
     * 
     * @param sqlId
     * @param para
     */
    @SuppressWarnings("unchecked")
    public void merge(String sqlId, Map para) {
        try {
            this.update(sqlId, para);
        } catch (Exception e) {
            throw new DAOException(sqlId, e);
        }
    }

    /**
     * 
     ******************************************************************************* 
     * @function : 批量修改
     * @param sqlId
     * @param list
     ******************************************************************************* 
     * @creator ：weishen
     * @date ：2011-3-29
     ******************************************************************************* 
     * @revisor ：
     * @date ：
     * @memo ：
     ******************************************************************************* 
     */
    @SuppressWarnings("unchecked")
    public int batchUpdate(final String sqlId, final List list) {
        try {
            if (list != null) {
                this.getSqlMapClientTemplate().execute(
                        new SqlMapClientCallback() {
                            public Object doInSqlMapClient(
                                    SqlMapExecutor executor)
                                    throws SQLException {
                                executor.startBatch();
                                for (int i = 0, n = list.size(); i < n; i++) {
                                    executor.update(sqlId, list.get(i));
                                }
                                return executor.executeBatch();
                            }
                        });
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
                log.debug("batchUpdate error: id [" + sqlId
                        + "], parameterObject [" + list + "].  Cause: "
                        + e.getMessage());
            }
            log.error(e);
            throw new DAOException(sqlId, e);
        }
        return -1;
    }

    /**
     * 
     ******************************************************************************* 
     * @function : 批量插入
     * @param sqlId
     * @param list
     ******************************************************************************* 
     * @creator ：weishen
     * @date ：2011-3-29
     ******************************************************************************* 
     * @revisor ：
     * @date ：
     * @memo ：
     ******************************************************************************* 
     */
    @SuppressWarnings("unchecked")
    public int batchInsert(final String sqlId, final List list) {
        try {
            if (list != null) {
                this.getSqlMapClientTemplate().execute(
                        new SqlMapClientCallback() {
                            public Object doInSqlMapClient(
                                    SqlMapExecutor executor)
                                    throws SQLException {
                                executor.startBatch();
                                for (int i = 0, n = list.size(); i < n; i++) {
                                    executor.insert(sqlId, list.get(i));
                                }
                                return executor.executeBatch();
                            }
                        });
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("batchInsert error: id [" + sqlId
                        + "], parameterObject [" + list + "].  Cause: "
                        + e.getMessage());
            }
            log.error("",e);
            throw new DAOException(sqlId, e);
        }
        return -1;
    }

    /**
     * 
     ******************************************************************************* 
     * @function : 批量删除
     * @param sqlId
     * @param list
     * @return
     ******************************************************************************* 
     * @creator ：weishen
     * @date ：2011-3-29
     ******************************************************************************* 
     * @revisor ：
     * @date ：
     * @memo ：
     ******************************************************************************* 
     */
    @SuppressWarnings("unchecked")
    public int batchDelete(final String sqlId, final List list) {
        try {
            if (list != null) {
                this.getSqlMapClientTemplate().execute(
                        new SqlMapClientCallback() {
                            public Object doInSqlMapClient(
                                    SqlMapExecutor executor)
                                    throws SQLException {
                                executor.startBatch();
                                for (int i = 0, n = list.size(); i < n; i++) {
                                    executor.delete(sqlId, list.get(i));
                                }
                                return executor.executeBatch();
                            }
                        });
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
                log.debug("batchDelete error: id [" + sqlId
                        + "], parameterObject [" + list + "].  Cause: "
                        + e.getMessage());
            }
            log.error(e);
            throw new DAOException(sqlId, e);
        }
        return -1;
    }
}
