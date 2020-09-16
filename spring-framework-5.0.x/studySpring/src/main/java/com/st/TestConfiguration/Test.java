package com.st.TestConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();
		IndexDao1 indexDao1 = (IndexDao1) ac.getBean("indexDao"); // @Bean的注入 以方法名为beanName
//		indexDao1.query();
	}
}
