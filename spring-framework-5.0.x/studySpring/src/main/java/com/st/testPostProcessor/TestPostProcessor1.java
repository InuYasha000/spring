package com.st.testPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor就是spring提供的一个扩展接口，让我们可以在bean的生命周期中对bean进行操作
 * 必须返回bean 不然spring接收不到这个bean了
 */
@Component
public class TestPostProcessor1 implements BeanPostProcessor , PriorityOrdered {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		//
		if (beanName.equals("dao")) {
			System.out.println("dao Bean before Init1");
		}
		/**
		 * bean就是spring生成的bean实例
		 * 这个时候我们可以操作bean
		 * springAOP就是在这个时候通过BeanPostProcessor （spring提供的后置处理器） 和bean建立了联系
		 * 实现对bean的代理， 返回代理对象bean
		 */
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("dao")) {
			System.out.println("dao Bean bafter Init1");
		}
		return bean;
	}

	@Override
	public int getOrder() {
		return 100;
	}
}
