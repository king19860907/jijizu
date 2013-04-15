package com.jijizu.base.cache;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 单实例Cache包装工厂类。<br/>
 * 
 * 在spring中的用法：
 * 
 * <pre>
 * <bean id="demoDefaultCache" class="com.yesmynet.cache.SingleCacheWrapperFactoryBean">
 * 		<property name="serverHosts">
 * 			<list>
 *          	<value>192.168.1.234:11211</value>
 *              <value>192.168.1.234:11221</value>
 *          </list>
 * 		</property>
 * 		<property name="weights">
 * 			<list>
 *          	<value>2</value>
 *              <value>1</value>
 *          </list>
 * 		</property>
 * 		<property name="connectionPoolSize" value="4" />
 * 		<property name="useBinary" value="true" />
 * 		<property name="instanceName" value="demoDefaultCacheInstance" />
 * </bean>
 * </pre>
 * 
 * 
 * @author majun
 * @since 2.1.4
 * @date 2012-01-13
 * 
 */
public class SingleCacheWrapperFactoryBean implements InitializingBean,
		DisposableBean, FactoryBean {

	protected static boolean singletonFlag = false;

	private String[] serverHosts;

	private int[] weights;

	private int connectionPoolSize;

	private boolean useBinary;

	private String instanceName;

	private Cache cache;

	public void setServerHosts(String[] serverHosts) {
		this.serverHosts = serverHosts;
	}

	public void setWeights(int[] weights) {
		this.weights = weights;
	}

	public void setConnectionPoolSize(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}

	public void setUseBinary(boolean useBinary) {
		this.useBinary = useBinary;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	@Override
	public Object getObject() throws Exception {
		return this.cache;
	}

	@Override
	public Class<Cache> getObjectType() {
		return Cache.class;
	}

	@Override
	public boolean isSingleton() {
		return singletonFlag;
	}

	@Override
	public void destroy() throws Exception {
		if (null != this.cache) {
			this.cache.stop();
			this.cache = null;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (null == this.cache) {
			this.cache = new CacheMemcachedDefaultImpl(this.serverHosts,
					this.weights, this.connectionPoolSize, this.useBinary,
					this.instanceName);
		}
	}
}