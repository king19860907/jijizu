package com.jijizu.base.cache;

/**
 * 缓存运行时异常类。<br/>
 * 
 * @author majun
 * @since 2.1.4
 * @date 2012-01-13
 * 
 */
public class CacheRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 6881987863621811254L;

	public CacheRuntimeException() {
		super();
	}

	public CacheRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CacheRuntimeException(String message) {
		super(message);
	}

	public CacheRuntimeException(Throwable cause) {
		super(cause);
	}

}

