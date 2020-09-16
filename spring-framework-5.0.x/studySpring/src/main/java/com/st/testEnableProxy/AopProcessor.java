package com.st.testEnableProxy;

import com.st.AppConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Proxy;
public class AopProcessor implements BeanPostProcessor { // 处理开启 AOP的类
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("daoImpl")) {
			// bean 将是DaoImpl的实例，这里开启代理， 返回代理对象
			bean = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {Dao.class}, new DaoImplProxy(bean));
		}
		return bean;
	}
}
