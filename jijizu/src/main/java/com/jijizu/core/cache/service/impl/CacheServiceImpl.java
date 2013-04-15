package com.jijizu.core.cache.service.impl;

import java.io.Serializable;

import com.jijizu.base.cache.Cache;
import com.jijizu.core.cache.service.CacheService;

/**
 ******************************************************************************* 
 * @project :集集组
 * @type : CacheServiceImpl
 * @function : 缓存操作业务接口实现类。<br/>
 ******************************************************************************* 
 * @version ：1.1.0
 * @creator ：luke_huangd
 * @date ：2011-6-15
 ******************************************************************************* 
 * @revision ：
 * @revisor ：
 * @date ：
 * @memo ：
 ******************************************************************************* 
 */
public class CacheServiceImpl implements CacheService {

	private Cache dataCache;

	private Cache snaCache;

	public void setDataCache(Cache dataCache) {
		this.dataCache = dataCache;
	}

	public void setSnaCache(Cache snaCache) {
		this.snaCache = snaCache;
	}

	@Override
	public void setData(String key, Serializable data, int expriation)
			throws Exception {
		dataCache.set(key, data, expriation);
	}

	@Override
	public void deleteData(String key) throws Exception {
		dataCache.delete(key);
	}

	@Override
	public Serializable getData(String key) throws Exception {
		return dataCache.get(key);
	}

	@Override
	public long incrData(String key, int incr) throws Exception {
		return dataCache.incr(key, incr);
	}

	@Override
	public long decrData(String key, int decr) throws Exception {
		return dataCache.decr(key, decr);
	}
	
	@Override
	public void setSnaInfo(String key, Serializable snaInfo, int expriation)
			throws Exception {
		snaCache.set(key, snaInfo, expriation);
	}

	@Override
	public void deleteSnaInfo(String key) throws Exception {
		snaCache.delete(key);
	}

	@Override
	public Serializable getSnaInfo(String key) throws Exception {
		return snaCache.get(key);
	}


}
