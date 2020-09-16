package com.st.testPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * BeadPostProcessor是对bean对象进行操作， 而BeanFactoryPostProcessor是拿到beanFactory 然后对bd对象 进行操作
 *
 *  除了实现BeanFactoryPostProcessor可以做到， 实现BeanDefinitionRegistryPostProcessor也是一样的，
 *  因为BeanDefinitionRegistryPostProcessor继承了BeanFactoryPostProcessor
 *  spring refresh中是先处理BeanDefinitionRegistryPostProcessor的实现类（因为spring内部自己有一个非常之重要的实现类ConfigurationClassPostProcessor也实现了他，需要先执行他内部的方法完成扫描包并注册的逻辑）
 *
 * 应用场景 spring内部ConfigurationClassPostProcessor就是实现了BeanFactoryPostProcessor的PostProcessBeanFactory
 *  完成对@Configuration注解的类的代理（通过cglib实现代理）
 */

/**
 *
 *  BeanDefinitionRegistryPostProcessor接口在BeanFactoryPostProcessor接口之前执行（底层源码就是先获取的BeanDefinitionRegistryPostProcessor类执行）
 *  自定义的(手动ac.addBeanFactoryProcessor 具体看源码注释)也是在BeanFactoryPostProcessor接口之前执行
 *	应用场景 ConfigurationClassPostProcessor中完成包的扫描@Import（三种类型） @Configuration @Bean等等逻辑，spring的核心步骤就是实现了BeanDefinitionRegistryPostProcessor接口
 */
//@Component
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// 可以从beanFactory中获取bd对象（BeanDefinition类（生成bean的类 的描述类 ）的实例）
		BeanDefinition bdDao = beanFactory.getBeanDefinition("dao");
		// 可以对描述类的对象进行修改，从应用到生成的bean上， 比如修改Dao类的Scoped为prototype， 相当于在Dao类上加@Scoped("prototype")
		bdDao.setScope("prototype");
	}
}
