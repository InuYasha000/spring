package com.st.beanLifecycle;

import com.sun.org.apache.xml.internal.security.Init;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * bean的生命周期，Aware类型除了下面列举的三个，还有:
 * ResourceLoaderAware 获得ResourceLoader对象， ServletContextAware获取MVC中的ServletContext对象，ServletConfigAware获取ServletConfig对象
 * Aware是针对某个类的Bean的定制化， PostProcessor是针对一系列Bean做操作
 *
 */
@Service
public class PersonBean implements InitializingBean,DisposableBean,
		ApplicationContextAware, BeanFactoryAware, BeanNameAware,
		BeanPostProcessor
{

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("执行InitializingBean接口：afterPropertiesSet方法");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("执行DisposableBean接口：destroy方法");
	}

	@PostConstruct
	public void init() {
		System.out.println("执行PostConstruct注解 标注的方法： init方法");
	}

	@PreDestroy
	public void destroy1() {
		System.out.println("执行PreDestroy注解 标注的方法： destroy1 方法");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("执行ApplicationContextAware接口：setApplicationContext方法, 拿到ApplicationContext->" + applicationContext);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("执行BeanFactoryAware接口：setBeanFactory方法, 拿到beanFactory对象->" + beanFactory);
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("执行BeanNameAware接口：setBeanName方法, 拿到BeanName->" + name);
	}


	/**
	 * 通过测试发现这里并不能输出 beanName为personBean 的 bean对象，
	 * 具体原因看源码分析， 简洁的说：因为personBean实现了PostProcessors后置处理器之后，他的bean实例化是在 他作为后置处理器注册之前
	 * 也就是说他实例化的时候， 拿后置处理器的时候，spring维护的后置处理器数组中还没有自己这个类。
	 *
	 * spring维护的后置处理器是 AbstractBeanFactory.beanPostProcessors。
	 *
	 * 他的实例化是在 refresh方法的 invokeBeanFactoryPostProcessors方法中，实例化的过程中会去spring的后置处理器数组中遍历所有的处理器，调用
	 * postProcessBeforeInitialization和postProcessAfterInitialization方法
	 * 而作为后置处理器注册到spring的后置处理器数组中，是在接下来的registerBeanPostProcessors方法中
	 *
	 * 也就是说这里输出的beanName只能是没有实现这些后置处理器的类。
	 *
	 * BeanPostProcessors可以这么理解为： 每个正常的bean实例化过程中，会遍历所有的实现了BeanPostProcessors对象
	 * 调用这些对象的 postProcessBeforeInitialization和after方法，传入自己的beanName和bean对象
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("执行BeanPostProcessor接口：postProcessBeforeInitialization方法, 拿到beanName ->" + beanName + "，和bean对象 ->" + bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("执行BeanPostProcessor接口：postProcessAfterInitialization方法, 拿到beanName ->" + beanName + "，和bean对象 ->" + bean);
		return bean;
	}
}
