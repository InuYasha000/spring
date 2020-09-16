package com.st.testEnableProxy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试ImportSelector 的使用
 */
public class Test {

	public static void main(String[] args) {
		// 实现 使用@EnableProxy注解开启动态代理 （便于理解spring动态代理的原理）
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(TestApp.class);
		ac.refresh();

		Dao bean = ac.getBean(Dao.class);
		bean.query();
	}


}
