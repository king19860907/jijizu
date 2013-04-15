package com.jijizu.base.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * 缓存操作接口。<br/>
 * 所有过期时间都以秒为单位。<br/>
 * @author majun
 * @since 2011-06-14
 */
public interface Cache
{

    void add(Collection<Object> keyCondition, Serializable value, int expiration)
            throws Exception;

    boolean safeAdd(Collection<Object> keyCondition, Serializable value,
            int expiration) throws Exception;

    void set(Collection<Object> keyCondition, Serializable value, int expiration)
            throws Exception;

    boolean safeSet(Collection<Object> keyCondition, Serializable value,
            int expiration) throws Exception;

    void replace(Collection<Object> keyCondition, Serializable value,
            int expiration) throws Exception;

    boolean safeReplace(Collection<Object> keyCondition, Serializable value,
            int expiration) throws Exception;

    Serializable get(Collection<Object> keyCondition) throws Exception;

    Map<String, Object> get(String... keys) throws Exception;

    long incr(Collection<Object> keyCondition, int by) throws Exception;

    long addOrIncr(Collection<Object> keyCondition, int by, int expiration)
            throws Exception;

    long decr(Collection<Object> keyCondition, int by) throws Exception;

    void flushAll() throws Exception;

    void delete(Collection<Object> keyCondition) throws Exception;

    Serializable get(String key) throws Exception;

    boolean safeDelete(Collection<Object> keyCondition) throws Exception;

    void stop() throws Exception;

    void add(String key, Serializable value, int expiration)
            throws TimeoutException, InterruptedException, MemcachedException;

    void set(String key, Serializable value, int expiration)
            throws TimeoutException, InterruptedException, MemcachedException;

    void replace(String key, Serializable value, int expiration)
            throws TimeoutException, InterruptedException, MemcachedException;

    long incr(String key, int by) throws TimeoutException,
            InterruptedException, MemcachedException;

    public long incr(String key, long by,long defaultValue, long timeout, int expiration) throws TimeoutException,
    InterruptedException, MemcachedException;
    
    long addOrIncr(String key, int by, int expiration) throws TimeoutException,
            InterruptedException, MemcachedException;

    long decr(String key, int by) throws TimeoutException,
            InterruptedException, MemcachedException;

    void delete(String key) throws TimeoutException, InterruptedException,
            MemcachedException;
    /**
     * 返回指定前缀的key，为 null 返回所有key
     * @param preKey
     * @return
     */
    List<String> getKeys(String preKey)throws MemcachedException, InterruptedException, TimeoutException;
}