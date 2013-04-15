package com.jijizu.base.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SuppressWarnings("unchecked")
public class DebugTools {
	private static final Logger logger = LoggerFactory.getLogger(DebugTools.class);
	
	public static void info(String msg){
		logger.info(msg);
	}

	public static void debug(Class clazz, Map map) {
		System.err.println("===");
		String tmp = getStringFromMap(map);
		if (!"".equals(tmp)) 
		logger.info(clazz.getName() + tmp);
	}
	
	public static void debug(Map map) {
		System.err.print("===");
		String tmp = getStringFromMap(map);
		if (!"".equals(tmp)) 
		logger.info(tmp);
	}
	
	public static void debug(Class clazz, String info) {
		logger.info(clazz.getName(), info);
	}
	
	public static void error(Class clazz, Throwable e) {
		logger.error(clazz.getName(), e);
	}
	
	public static String getStringFromMap(Map map) {
		if (map == null || map.isEmpty()) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer();
			Object key = null;
			Set set = map.keySet();
			Iterator ite = set.iterator();
			while (ite.hasNext()) {
				key = ite.next();
				sb.append(key);
				sb.append(":");
				sb.append(map.get(key));
				sb.append(";");
			}
			return sb.toString();
		}
	}

}

