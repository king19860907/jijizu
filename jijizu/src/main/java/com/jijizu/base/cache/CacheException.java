package com.jijizu.base.cache;

/**
 * 缓存异常类。<br/>
 * 
 * @author majun
 * @since 2011-06-14
 * 
 */
public class CacheException extends Exception {

	private static final long serialVersionUID = 12518420368381469L;

	public CacheException() {
		super();
	}

	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}

	public CacheException(String message) {
		super(message);
	}

	public CacheException(Throwable cause) {
	}

}

