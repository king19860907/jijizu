package com.jijizu.base.util.collection;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * 具有Iterator功能的Enumeration。<br/>
 * @author luke_huang
 * @since 2011-06-27
 *
 * @param <T> 泛型参数
 */
public class IteratorEnumeration<T> implements Enumeration<T> {

	private Iterator<T> it = null;
	
	public IteratorEnumeration(Iterator<T> it) {
		this.it = it;
	}

	@Override
	public boolean hasMoreElements() {
		return it.hasNext();
	}

	@Override
	public T nextElement() {
		return it.next();
	}

}
