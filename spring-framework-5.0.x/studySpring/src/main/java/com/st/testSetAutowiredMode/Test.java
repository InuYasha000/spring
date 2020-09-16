package com.st.testSetAutowiredMode;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();

		CityService cityService = ac.getBean(CityService.class);
		cityService.query();
	}
}
