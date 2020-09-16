package com.st.testEnableProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DaoImplProxy implements InvocationHandler {

	Object target;

	public DaoImplProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("前置增强");
		Object invoke = method.invoke(target, args);
		return invoke;
	}
}
