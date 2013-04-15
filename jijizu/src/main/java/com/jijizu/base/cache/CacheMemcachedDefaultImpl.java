package com.jijizu.base.cache;

import static com.jijizu.base.util.MD5.encodeAsHex;
import static com.jijizu.base.util.StringUtil.isNotNullOrEmpty;
import static com.jijizu.base.util.StringUtil.isNullOrEmpty;
import static com.jijizu.base.util.StringUtil.shortUUIDString;
import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.KeyIterator;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.command.TextCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import com.jijizu.base.util.CommonLog;

/**
 * Cache的Memcached实现类。<br/>
 * 
 * @author huangyongqiang
 * @since 2011-06-14
 */
class CacheMemcachedDefaultImpl implements Cache {

	protected static CommonLog LOG = CommonLog
			.getLog(CacheMemcachedDefaultImpl.class);

	private static final int DEFAULT_CONNECTION_POOL_SIZE = 10;

	/**
	 * 缓存服务器主机。格式{"host1:port1","host2:port2"}。<br/>
	 * 例如{"192.168.1.233:12000", "192.168.1.234:12001"}。<br/>
	 */
	private String[] serverHosts;

	/**
	 * 缓存服务器主机权重，必须大于0。
	 */
	private int[] weights;

	private boolean useBinary = true;

	private int connectionPoolSize;

	private MemcachedClient memcachedClient;

	/**
	 * cache实例名称
	 */
	private String instanceName;

	/**
	 * 
	 * @param 缓存服务器主机
	 *            。格式{"host1:port1","host2:port2"}。例如{"192.168.1.233:12000",
	 *            "192.168.1.234:12001"}。<br/>
	 * @param weights
	 *            缓存服务器主机权重，必须大于0。
	 * @param connectionPoolSize
	 *            缓存服务器连接池大小
	 * @param useBinary
	 *            是否使用二进制协议
	 * @param instanceName
	 *            cache客户端实例名称
	 * @throws CacheException
	 */
	public CacheMemcachedDefaultImpl(String[] serverHosts, int[] weights,
			int connectionPoolSize, boolean useBinary, String instanceName)
			throws CacheException {
		if (null == serverHosts || serverHosts.length < 1 || null == weights
				|| weights.length < 1 || serverHosts.length != weights.length) {
			LOG.error("缓存服务器配置格式不对。主机{0}，权重{1}。", serverHosts, weights);
			throw new CacheException(MessageFormat.format(
					"缓存服务器配置格式不对。主机{0}，权重{1}。", serverHosts, weights));
		}
		StringBuffer hosts = new StringBuffer();
		for (int i = 0; i < serverHosts.length; i++) {
			if (isNullOrEmpty(serverHosts[i])) {
				LOG.error("第{0}缓存服务器主机{1}不对。", i, serverHosts[i]);
				throw new CacheException(MessageFormat.format(
						"第{0}缓存服务器主机{1}不对。", i, serverHosts[i]));
			}
			if (weights[i] < 1) {
				LOG.error("第{0}缓存服务器权重{1}不对。", i, weights[i]);
				throw new CacheException(MessageFormat.format(
						"第{0}缓存服务器权重{1}不对。", i, weights[i]));
			}
			hosts.append(serverHosts[i]).append(" ");
		}
		this.serverHosts = serverHosts;
		this.weights = weights;
		this.connectionPoolSize = (connectionPoolSize > 0 ? connectionPoolSize
				: DEFAULT_CONNECTION_POOL_SIZE);
		this.useBinary = useBinary;
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses(hosts.toString()), weights);

		if (this.useBinary) {
			builder.setCommandFactory(new BinaryCommandFactory());// use binary
																	// protocol
		} else {
			builder.setCommandFactory(new TextCommandFactory());
		}
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());
		builder.setTranscoder(new SerializingTranscoder());
		builder.setConnectionPoolSize(this.connectionPoolSize);
		// builder.getConfiguration().setStatisticsServer(false);//禁止统计连接是否空闲
		try {
			this.memcachedClient = builder.build();
			this.instanceName = isNotEmpty(instanceName) ? this
					.makeRandomInstanceName() : instanceName;
			this.memcachedClient.setName(this.instanceName);
			this.memcachedClient.setMergeFactor(50);// 默认是150，缩小到50
			this.memcachedClient.setOptimizeMergeBuffer(false);// 关闭合并buffer的优化
			this.memcachedClient.setEnableHeartBeat(false);// 仅仅关闭心跳检测，仍然会去统计连接是否空闲
		} catch (IOException e) {
			LOG.error("缓存服务器不能连接，请检查配置。", e);
			throw new CacheException("缓存服务器不能连接，请检查配置。", e);
		}
	}

	/**
	 * @return the serverHosts
	 */
	public String[] getServerHosts() {
		return serverHosts;
	}

	/**
	 * @return the weights
	 */
	public int[] getWeights() {
		return weights;
	}

	/**
	 * @return the useBinary
	 */
	public boolean isUseBinary() {
		return useBinary;
	}

	/**
	 * @return the connectionPoolSize
	 */
	public int getConnectionPoolSize() {
		return connectionPoolSize;
	}

	@Override
	public void add(Collection<Object> keyCondition, Serializable value,
			int expiration) throws TimeoutException, InterruptedException,
			MemcachedException {
		String key = this.makeKeyByCondition(keyCondition);
		this.add(key, value, expiration);
	}

	@Override
	public void add(String key, Serializable value, int expiration)
			throws TimeoutException, InterruptedException, MemcachedException {
		if (isNotNullOrEmpty(key)) {
			memcachedClient.add(key.toString(), expiration, value);
		}
	}

	@Override
	public void set(String key, Serializable value, int expiration)
			throws TimeoutException, InterruptedException, MemcachedException {
		if (isNotNullOrEmpty(key)) {
			memcachedClient.set(key, expiration, value);
		}
	}

	@Override
	public void replace(String key, Serializable value, int expiration)
			throws TimeoutException, InterruptedException, MemcachedException {
		if (isNotNullOrEmpty(key)) {
			memcachedClient.replace(key, expiration, value);
		}
	}

	@Override
	public long incr(String key, int by) throws TimeoutException,
			InterruptedException, MemcachedException {
		if (isNotNullOrEmpty(key)) {
			return memcachedClient.incr(key, by);
		} else {
			return 0;
		}
	}

	@Override
	public long addOrIncr(String key, int by, int expiration)
			throws TimeoutException, InterruptedException, MemcachedException {
		if (isNotNullOrEmpty(key)) {
			return 0;
		}
		Object obj = memcachedClient.get(key);
		if (null == obj) {
			memcachedClient.add(key, expiration, by);
			return by;
		} else {
			return memcachedClient.incr(key, by);
		}
	}

	@Override
	public long decr(String key, int by) throws TimeoutException,
			InterruptedException, MemcachedException {
		if (isNotNullOrEmpty(key)) {
			return memcachedClient.decr(key, by);
		} else {
			return 0;
		}
	}

	@Override
	public void delete(String key) throws TimeoutException,
			InterruptedException, MemcachedException {
		if (isNotNullOrEmpty(key)) {
			memcachedClient.delete(key);
		}
	}

	@Override
	public void flushAll() throws TimeoutException, InterruptedException,
			MemcachedException {
		memcachedClient.flushAll();

	}

	@Override
	public long decr(Collection<Object> keyCondition, int by)
			throws TimeoutException, InterruptedException, MemcachedException {
		String key = this.makeKeyByCondition(keyCondition);
		return this.decr(key, by);
	}

	@Override
	public void delete(Collection<Object> keyCondition)
			throws TimeoutException, InterruptedException, MemcachedException {
		String key = this.makeKeyByCondition(keyCondition);
		this.delete(key);
	}

	@Override
	public Serializable get(Collection<Object> keyCondition) throws Exception {
		String key = this.makeKeyByCondition(keyCondition);
		return this.get(key);
	}

	@Override
	public Map<String, Object> get(String... keys) throws TimeoutException,
			InterruptedException, MemcachedException {
		if (null == keys || keys.length < 1) {
			return null;
		}
		Collection<String> keyCollections = asList(keys);
		return memcachedClient.get(keyCollections);
	}

	@Override
	public long incr(Collection<Object> keyCondition, int by)
			throws TimeoutException, InterruptedException, MemcachedException {
		String key = this.makeKeyByCondition(keyCondition);
		return this.incr(key, by);
	}

	@Override
	public long addOrIncr(Collection<Object> keyCondition, int by,
			int expiration) throws TimeoutException, InterruptedException,
			MemcachedException {
		String key = this.makeKeyByCondition(keyCondition);
		return this.addOrIncr(key, by, expiration);
	}

	@Override
	public void replace(Collection<Object> keyCondition, Serializable value,
			int expiration) throws TimeoutException, InterruptedException,
			MemcachedException {
		String key = this.makeKeyByCondition(keyCondition);
		this.replace(key, value, expiration);
	}

	@Override
	public boolean safeAdd(Collection<Object> keyCondition, Serializable value,
			int expiration) {
		return false;
	}

	@Override
	public boolean safeDelete(Collection<Object> keyCondition) {
		return false;
	}

	@Override
	public boolean safeReplace(Collection<Object> keyCondition,
			Serializable value, int expiration) {
		return false;
	}

	@Override
	public boolean safeSet(Collection<Object> keyCondition, Serializable value,
			int expiration) {
		return false;
	}

	@Override
	public void set(Collection<Object> keyCondition, Serializable value,
			int expiration) throws TimeoutException, InterruptedException,
			MemcachedException {
		String key = this.makeKeyByCondition(keyCondition);
		this.set(key, value, expiration);
	}

	@Override
	public Serializable get(String key) throws Exception {
		if (isNullOrEmpty(key)) {
			return null;
		}
		return memcachedClient.get(key);
	}

	@Override
	public void stop() throws IOException {
		memcachedClient.shutdown();
	}

	/**
	 * 根据key的产生条件生成key
	 * 
	 * @param keyCondition
	 * @return
	 */
	public String makeKeyByCondition(Collection<Object> keyCondition) {
		if (null == keyCondition || keyCondition.isEmpty()) {
			return null;
		}
		StringBuilder conditionBuffer = new StringBuilder();
		for (Object obj : keyCondition) {
			if (null != obj) {
				conditionBuffer.append(obj.toString());
			}
		}
		if (1 > conditionBuffer.length()) {
			return null;
		}
		return encodeAsHex(conditionBuffer.toString());
	}

	protected String makeRandomInstanceName() {
		return MessageFormat.format("{0}_{1}", this.getClass().getName(),
				shortUUIDString());
	}

	@Override
	public List<String> getKeys(String preKey) throws MemcachedException,
			InterruptedException, TimeoutException {
		List<String> list = new java.util.ArrayList<String>();
		String tmp = null;
		for (String host : this.serverHosts) {
			KeyIterator it = memcachedClient.getKeyIterator(AddrUtil
					.getOneAddress(host));
			while (it.hasNext()) {
				if (isNotNullOrEmpty(preKey)) {
					tmp = it.next();
					if (tmp.contains(preKey)) {
						list.add(it.next());
					}
				} else {
					list.add(it.next());
				}
			}
		}
		return list;
	}

	@Override
	public long incr(String key, long by,long defaultValue, long timeout, int expiration)
			throws TimeoutException, InterruptedException, MemcachedException {
		return memcachedClient.incr(key, by, defaultValue, timeout, expiration);
	}

}
