package com.st.testPostProcessor.testImportBeanDefinitionRegistrar;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


//@Component 我们手动注入了这个类，不能交给spring管理， 不然报错
// 在mybaris中 这个类相当于是MapperFactoryBean， 不能依赖spring，只能实现spring的扩展接口
public class MyFactoryBean implements FactoryBean, InvocationHandler {

	Class clazz;

	public MyFactoryBean(Class clazz) {
		this.clazz = clazz;
	}
	@Override
	public Object getObject() throws Exception {
		// 生成动态代理对象（可以不需要实际实现对象，jdk动态代理本身也只是根据接口生成对象而已）返回
		Class[] clazzs = new Class[] {clazz};
		MybatisDao dao = (MybatisDao) Proxy.newProxyInstance(this.getClass().getClassLoader(), clazzs, this);
		return dao;
	}

	@Override
	public Class<?> getObjectType() {
		return MybatisDao.class;
	}

	@Override
	public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
		System.out.println("调用方法");
		// 注意 因为我们是对MybatisDao接口直接进行代理， 所以根本就没之前的被代理对象Target
		// 获取调用的 MybatisDao接口的方法对象， 拿到注解内容
		Method method1 = o.getClass().getInterfaces()[0].getMethod(method.getName(), int.class);
		Select selectdAnno = method1.getDeclaredAnnotation(Select.class);
		System.out.println(selectdAnno.value());
		return null;
	}
}
