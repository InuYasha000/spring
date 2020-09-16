package com.st.beanLifecycle.beanPostProcessor;

import com.st.beanLifecycle.beanPostProcessor.controller.CityController;
import com.st.beanLifecycle.beanPostProcessor.service.City;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();
		CityController cityController = (CityController) ac.getBean("cityController");
		cityController.query();
		System.out.println("=================");
		City city = (City) ac.getBean("cityController1");
		city.query();
	}
}
