package com.st.beanLifecycle.beanPostProcessor.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CityServiceInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy");
		return null;
	}
}
