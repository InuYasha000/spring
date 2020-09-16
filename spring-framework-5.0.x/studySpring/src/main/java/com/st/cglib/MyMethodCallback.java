package com.st.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodCallback implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("拦截方法---");
		// 代理对象调用父类的methodProxy方法
		Object ret = methodProxy.invokeSuper(o, objects);
		return ret;
	}
}
