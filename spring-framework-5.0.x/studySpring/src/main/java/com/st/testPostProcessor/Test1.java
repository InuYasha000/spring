package com.st.testPostProcessor;

import com.st.AppConfig;
import com.st.dao.Dao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 源码环境
public class Test1 {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		// 不用@Component的方式，将BeanFactoryPostProcessor交给spring
		ac.addBeanFactoryPostProcessor(new TestBeanFactoryPostProcessor());
		ac.refresh();
		Dao dao = (Dao)ac.getBean("dao");
		Dao dao1 = (Dao)ac.getBean("dao");
		System.out.println(dao == dao1);
	}
}
