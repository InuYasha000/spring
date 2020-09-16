package com.st.aspect;

import com.st.aspect.dao.Dao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	public static void main(String[] args) {
		// 调试Aop源码，必须配置切面 @Aspect， 不然不会生成DaoImpl的代理对象
		// testEnableProxy包是模拟开启AOP代理， 和Aspect是一样的原理。只是Aspect是在postProcessAfterInitialization方法中进行代理

		// Aspect的AOP 开启@EnableAspectJAutoProxy
		// 将执行AbstractAutoProxyCreator类的postProcessAfterInitialization方法生成代理对象
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		Dao dao = ac.getBean(Dao.class);
		dao.query();
	}
}
