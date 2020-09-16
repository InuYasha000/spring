package com.st.beanLifecycle.beanPostProcessor.controller;

import com.st.beanLifecycle.beanPostProcessor.service.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityController {

	@Autowired
	City cityService;

	public void query() {
		cityService.query();
	}
}
