package com.jijizu.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jijizu.core.user.dto.UserInfo;


public class SpringTest {
	
	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
	
		UserInfo userInfo = (UserInfo)ac.getBean("userInfo");
		
		System.out.println(userInfo.getUserId());
	}
	
}
