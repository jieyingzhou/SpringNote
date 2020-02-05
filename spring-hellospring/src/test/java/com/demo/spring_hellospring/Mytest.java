package com.demo.spring_hellospring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.pojo.Hello;

public class Mytest {
	public static void main(String[] args) {
		// get Spring context 
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		// our objects are managed by spring now, get it directly
		Hello hello = (Hello) context.getBean("hello");
		System.out.println(hello.toString());
	}
	


}
