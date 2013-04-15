package com.jijizu.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHolder implements ApplicationContextAware{	
	private static ApplicationContext applicationContext;	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		if(applicationContext != null) {
			//throw new BeanCreationException("ApplicationContextHolder already holded 'applicationContext'.");
		    System.out.println("Spring_重新加载");
		}
		applicationContext = context;
	}
	public static ApplicationContext getApplicationContext() {
		if(applicationContext == null)
			throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
		return applicationContext;
	}
	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}
}
