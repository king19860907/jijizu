package com.jijizu.base.cache;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Cache单实例工厂类。<br/>
 * 所有过期时间格式为：10s, 5mn, 23h, 3d。<br/>
 * 同时保证在jvm里和spring上下文里是单实例。<br/>
 * 在spring中的用法：
 * 
 * <pre>
 * <bean id="demoDefaultCache" class="com.yesmynet.cache.SingleCacheFactoryBean" destroy-method="destroy">
 *       <property name="cache">
 *           <bean class="com.yesmynet.cache.CacheMemcachedDefaultImpl">
 *               <constructor-arg index="0">
 *                   <list>
 *                       <value>192.168.1.234:11211</value>
 *                       <value>192.168.1.234:11221</value>
 *                   </list>
 *               </constructor-arg>
 *               <constructor-arg index="1">
 *                   <list>
 *                       <value>2</value>
 *                       <value>1</value>
 *                   </list>
 *               </constructor-arg>
 *               <constructor-arg index="2"><value>4</value></constructor-arg>
 *               <constructor-arg index="3"><value>true</value></constructor-arg>
 *               <constructor-arg index="4"><value>demoDefaultCacheInstance</value></constructor-arg>
 *           </bean>
 *       </property>
 * </bean>
 * </pre>
 * 
 * @deprecated，请使用<code>com.yesmynet.cache.SingleCacheWrapperFactoryBean<code>类。
 * 
 * @author huangyongqiang
 * @since 2011-06-14
 */
@Deprecated
public class SingleCacheFactoryBean implements InitializingBean,
		DisposableBean, FactoryBean {

	protected static Logger logger = LoggerFactory.getLogger(SingleCacheFactoryBean.class);

	protected static boolean singletonFlag = true;

	/**
	 * The underlying cache implementation
	 */
	private static Cache cache;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (null == cache) {
			throw new IllegalStateException(
					"not found 'cache' for CacheHolder ");
		}
	}

	@Override
	public void destroy() throws Exception {
		if (null != cache) {
			this.cache.stop();
			cache = null;
		}
	}

	public void setCache(Cache c) {
		if (null != cache) {
			logger.warn("CacheHolder already holded 'cache'");
			return;
		}
		cache = c;
	}

	/**
	 * Add an element only if it doesn't exist.
	 * 
	 * @param key
	 *            Element key
	 * @param value
	 *            Element value
	 * @param expiration
	 *            Ex: 10s, 3mn, 8h
	 * @throws Exception
	 */
	public static void add(Collection<Object> keyCondition, Serializable value,
			String expiration) throws Exception {
		checkSerializable(value);
		cache.add(keyCondition, value, parseDuration(expiration));
	}

	public static void add(String key, Serializable value, String expiration)
			throws Exception {
		checkSerializable(value);
		cache.add(key, value, parseDuration(expiration));
	}

	/**
	 * Add an element only if it doesn't exist, and return only when the element
	 * is effectivly cached.
	 * 
	 * @param key
	 *            Element key
	 * @param value
	 *            Element value
	 * @param expiration
	 *            Ex: 10s, 3mn, 8h
	 * @return If the element an eventually been cached
	 * @throws Exception
	 */
	public static boolean safeAdd(Collection<Object> keyCondition,
			Serializable value, String expiration) throws Exception {
		checkSerializable(value);
		return cache.safeAdd(keyCondition, value, parseDuration(expiration));
	}

	/**
	 * Add an element only if it doesn't exist and store it indefinitly.
	 * 
	 * @param key
	 *            Element key
	 * @param value
	 *            Element value
	 * @throws Exception
	 */
	public static void add(Collection<Object> keyCondition, Serializable value)
			throws Exception {
		checkSerializable(value);
		cache.add(keyCondition, value, parseDuration(null));
	}

	public static void add(String key, Serializable value) throws Exception {
		checkSerializable(value);
		cache.add(key, value, parseDuration(null));
	}

	public static long addOrIncr(Collection<Object> keyCondition, int by,
			int expiration) throws Exception {
		checkSerializable(by);
		return cache.addOrIncr(keyCondition, by, expiration);
	}

	public static long addOrIncr(String key, int by, int expiration)
			throws Exception {
		checkSerializable(by);
		return cache.addOrIncr(key, by, expiration);
	}

	/**
	 * Set an element.
	 * 
	 * @param key
	 *            Element key
	 * @param value
	 *            Element value
	 * @param expiration
	 *            Ex: 10s, 3mn, 8h
	 * @throws Exception
	 */
	public static void set(Collection<Object> keyCondition, Serializable value,
			String expiration) throws Exception {
		checkSerializable(value);
		cache.set(keyCondition, value, parseDuration(expiration));
	}

	public static void set(String key, Serializable value, String expiration)
			throws Exception {
		checkSerializable(value);
		cache.set(key, value, parseDuration(expiration));
	}

	/**
	 * Set an element and return only when the element is effectivly cached.
	 * 
	 * @param key
	 *            Element key
	 * @param value
	 *            Element value
	 * @param expiration
	 *            Ex: 10s, 3mn, 8h
	 * @return If the element an eventually been cached
	 * @throws Exception
	 */
	public static boolean safeSet(Collection<Object> keyCondition,
			Serializable value, String expiration) throws Exception {
		checkSerializable(value);
		return cache.safeSet(keyCondition, value, parseDuration(expiration));
	}

	/**
	 * Set an element and store it indefinitly.
	 * 
	 * @param key
	 *            Element key
	 * @param value
	 *            Element value
	 * @throws Exception
	 */
	public static void set(Collection<Object> keyCondition, Serializable value)
			throws Exception {
		checkSerializable(value);
		cache.set(keyCondition, value, parseDuration(null));
	}

	public static void set(String key, Serializable value) throws Exception {
		checkSerializable(value);
		cache.set(key, value, parseDuration(null));
	}

	/**
	 * Replace an element only if it already exists.
	 * 
	 * @param key
	 *            Element key
	 * @param value
	 *            Element value
	 * @param expiration
	 *            Ex: 10s, 3mn, 8h
	 * @throws Exception
	 */
	public static void replace(Collection<Object> keyCondition,
			Serializable value, String expiration) throws Exception {
		checkSerializable(value);
		cache.replace(keyCondition, value, parseDuration(expiration));
	}

	public static void replace(String key, Serializable value, String expiration)
			throws Exception {
		checkSerializable(value);
		cache.replace(key, value, parseDuration(expiration));
	}

	/**
	 * Replace an element only if it already exists and return only when the
	 * element is effectivly cached.
	 * 
	 * @param key
	 *            Element key
	 * @param value
	 *            Element value
	 * @param expiration
	 *            Ex: 10s, 3mn, 8h
	 * @return If the element an eventually been cached
	 * @throws Exception
	 */
	public static boolean safeReplace(Collection<Object> keyCondition,
			Serializable value, String expiration) throws Exception {
		checkSerializable(value);
		return cache
				.safeReplace(keyCondition, value, parseDuration(expiration));
	}

	/**
	 * Replace an element only if it already exists and store it indefinitly.
	 * 
	 * @param key
	 *            Element key
	 * @param value
	 *            Element value
	 * @throws Exception
	 */
	public static void replace(Collection<Object> keyCondition,
			Serializable value) throws Exception {
		checkSerializable(value);
		cache.replace(keyCondition, value, parseDuration(null));
	}

	public static void replace(String key, Serializable value) throws Exception {
		checkSerializable(value);
		cache.replace(key, value, parseDuration(null));
	}

	/**
	 * Increment the element value (must be a Number).
	 * 
	 * @param key
	 *            Element key
	 * @param by
	 *            The incr value
	 * @return The new value
	 * @throws Exception
	 */
	public static long incr(Collection<Object> keyCondition, int by)
			throws Exception {
		return cache.incr(keyCondition, by);
	}

	public static long incr(String key, int by) throws Exception {
		return cache.incr(key, by);
	}

	/**
	 * Increment the element value (must be a Number) by 1.
	 * 
	 * @param key
	 *            Element key
	 * @return The new value
	 * @throws Exception
	 */
	public static long incr(Collection<Object> keyCondition) throws Exception {
		return incr(keyCondition, 1);
	}

	public static long incr(String key) throws Exception {
		return incr(key, 1);
	}

	/**
	 * Decrement the element value (must be a Number).
	 * 
	 * @param key
	 *            Element key
	 * @param by
	 *            The decr value
	 * @return The new value
	 * @throws Exception
	 */
	public static long decr(Collection<Object> keyCondition, int by)
			throws Exception {
		return cache.decr(keyCondition, by);
	}

	public static long decr(String key, int by) throws Exception {
		return cache.decr(key, by);
	}

	/**
	 * Decrement the element value (must be a Number) by 1.
	 * 
	 * @param key
	 *            Element key
	 * @return The new value
	 * @throws Exception
	 */
	public static long decr(Collection<Object> keyCondition) throws Exception {
		return decr(keyCondition, 1);
	}

	public static long decr(String key) throws Exception {
		return decr(key, 1);
	}

	/**
	 * Retrieve an object.
	 * 
	 * @param key
	 *            The element key
	 * @return The element value or null
	 * @throws Exception
	 */
	public static Object get(Collection<Object> keyCondition) throws Exception {
		return cache.get(keyCondition);
	}

	public static Object get(String key) throws Exception {
		return cache.get(key);
	}

	/**
	 * Bulk retrieve.
	 * 
	 * @param key
	 *            List of keys
	 * @return Map of keys & values
	 * @throws Exception
	 */
	public static Map<String, Object> get(String... keys) throws Exception {
		return cache.get(keys);
	}

	/**
	 * Delete an element from the cache.
	 * 
	 * @param key
	 *            The element key *
	 * @throws Exception
	 */
	public static void delete(Collection<Object> keyCondition) throws Exception {
		cache.delete(keyCondition);
	}

	public static void delete(String key) throws Exception {
		cache.delete(key);
	}

	/**
	 * Delete an element from the cache and return only when the element is
	 * effectivly removed.
	 * 
	 * @param key
	 *            The element key
	 * @return If the element an eventually been deleted
	 * @throws Exception
	 */
	public static boolean safeDelete(Collection<Object> keyCondition)
			throws Exception {
		return cache.safeDelete(keyCondition);
	}

	/**
	 * Clear all data from cache.
	 * 
	 * @throws Exception
	 */
	public static void flushAll() throws Exception {
		cache.flushAll();
	}

	/**
	 * Convenient clazz to get a value a class type;
	 * 
	 * @param <T>
	 *            The needed type
	 * @param key
	 *            The element key
	 * @param clazz
	 *            The type class
	 * @return The element value or null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Collection<Object> keyCondition, Class<T> clazz)
			throws Exception {
		return (T) cache.get(keyCondition);
	}

	/**
	 * Stop the cache system.
	 * 
	 * @throws Exception
	 */
	public static void stop() throws Exception {
		cache.stop();
	}

	public static void cleanHolder() {
		cache = null;
	}

	/**
	 * Utility that check that an object is serializable.
	 */
	static void checkSerializable(Object value) {
		if (!(value instanceof Serializable)) {
			throw new IllegalStateException(
					"Cannot cache a non-serializable value of type "
							+ value.getClass().getName(),
					new NotSerializableException(value.getClass().getName()));
		}
	}

	static Pattern days = Pattern.compile("^([0-9]+)d$");
	static Pattern hours = Pattern.compile("^([0-9]+)h$");
	static Pattern minutes = Pattern.compile("^([0-9]+)mn$");
	static Pattern seconds = Pattern.compile("^([0-9]+)s$");

	/**
	 * Parse a duration
	 * 
	 * @param duration
	 *            3h, 2mn, 7s
	 * @return The number of seconds
	 */
	public static int parseDuration(String duration) {
		if (duration == null) {
			return 60 * 60 * 24 * 30;
		}
		int toAdd = -1;
		if (days.matcher(duration).matches()) {
			Matcher matcher = days.matcher(duration);
			matcher.matches();
			toAdd = Integer.parseInt(matcher.group(1)) * (60 * 60) * 24;
		} else if (hours.matcher(duration).matches()) {
			Matcher matcher = hours.matcher(duration);
			matcher.matches();
			toAdd = Integer.parseInt(matcher.group(1)) * (60 * 60);
		} else if (minutes.matcher(duration).matches()) {
			Matcher matcher = minutes.matcher(duration);
			matcher.matches();
			toAdd = Integer.parseInt(matcher.group(1)) * (60);
		} else if (seconds.matcher(duration).matches()) {
			Matcher matcher = seconds.matcher(duration);
			matcher.matches();
			toAdd = Integer.parseInt(matcher.group(1));
		}
		if (toAdd == -1) {
			throw new IllegalArgumentException("Invalid duration pattern : "
					+ duration);
		}
		return toAdd;
	}

	@Override
	public Object getObject() throws Exception {
		return SingleCacheFactoryBean.cache;
	}

	@Override
	public Class<?> getObjectType() {
		return null == SingleCacheFactoryBean.cache ? Cache.class
				: SingleCacheFactoryBean.cache.getClass();
	}

	@Override
	public boolean isSingleton() {
		return singletonFlag;
	}

}
