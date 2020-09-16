package com.st.testPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor就是spring提供的一个扩展接口（spring的后置处理器），让我们可以在bean的生命周期中对bean实例进行操作
 * 必须返回bean 不然spring接收不到这个bean了
 *
 * 在spring还没有把bean放入容器之前做操作，此时bean已经被实例化了但是还没有放入spring bean容器，
 * 返回的bean将被放入spring的bean容器中（bean容器是singtonObject，bd容器就是beanFactory Map）
 *应用场景： @PostConstructor注解的实现和AOP的实现 都是通过实现 BeanPostProcessor接口实现的，
 */

/**
 * spring提供的扩展接口有5个：
 * BeanPostProcessor， BeanFactoryPostProcessor
 *
 */
@Component
public class TestPostProcessor implements BeanPostProcessor, PriorityOrdered {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		//
		if (beanName.equals("dao")) {
			System.out.println("dao Bean before Init");
		}
		/**
		 * bean就是spring生成的bean实例
		 * 这个时候我们可以操作bean
		 * springAOP就是在这个时候通过BeanPostProcessor （spring提供的后置处理器） 和bean建立了联系，
		 * 实现对bean的代理， 返回代理对象bean， 使用@import(ImportSelector.class)做开关
		 */
		// bean这个时候还没有put到spring容器中，执行完我们这个处理器之后，将put到容器里面
		// 这里的所谓的容器就是name为beanName, value为bean实例的concurrentHashMao，在spring中叫SingletonObjects。和之前的beanFactory中的map不同
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("dao")) {
			System.out.println("dao Bean bafter Init");
		}
		return bean;
	}

	@Override
	public int getOrder() { // 实现PriorityOrdered， order越小 越先执行
		return 101;
	}
}
