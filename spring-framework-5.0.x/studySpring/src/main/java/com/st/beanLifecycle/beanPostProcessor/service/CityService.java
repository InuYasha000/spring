package com.st.beanLifecycle.beanPostProcessor.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 这个包 将详解 bean实例化过程中 所执行的所有的 BeanPostProcessor 后置处理器
 *  注意 后置处理器是对所有的bean实例化的时候进行拦截(除了他自身， 原因就是实例化他自身这个bean的时候， 还没有将他作为后置处理器注册到spring中)
 *  InstantiationAwareBeanPostProcessor 等等都是继承了BeanPostProcessor
 */

// 除了InstantiationAwareBeanPostProcessor，
// 在bean实例化的过程中 还有SmartInstantiationAwareBeanPostProcessor、 MergedBeanDefinitionPostProcessor、 BeanPostProcessor
@Component
public class CityService implements InstantiationAwareBeanPostProcessor,
		City, InvocationHandler{
	/**
	 *  这个方法如果返回了对象（任意对象 包括字符串） 而不返回null，
	 *  spring实例化当前参数中beanName对应的bean实例的过程中， 会直接将这个bean 实例化为 我们所返回的对象，
	 *  而且， 注意： 将只会执行这个BeanPostProcessor的 postProcessAfterInitialization方法逻辑（源码就是这样子的）
	 *  其他后续逻辑（真正根据类实例化bean对象，并属性依赖等等逻辑）将不再执行了， 完成bean的实例化
	 *
	 *  功能： 我们可以根据传过来的beanName对象, 返回我们想要的的beanName 的对象，
	 *  应用场景： spring的开启切面的注解@EnableAspectJAutoProxy会给spring注入一个叫AbstractAutoProxyCreator 的类
	 *  这个类就实现了InstantiationAwareBeanPostProcessor接口，实现了 postProcessBeforeInstantiation
	 *  在这个方法中做了一个判断，如果实例化的bean包含@Before，@Pointcut等切面类特有的注解，就将这个类放入一个map中
	 *  后续在生成代理对象增强类的功能的时候将 排除这些类，就算@Pointcut(execute)匹配到也会排除
	 */
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//		return 123;
//		return null;
		// 这里也可以在实例化某个bean的时候，返回我们任意的对象
		if (beanName.equals("cityController1")) { // 在实例化cityController1的时候， 直接返回我们自定义的代理对象
			return Proxy.newProxyInstance(this.getClass().getClassLoader(),
					new Class[]{City.class}, this);
		}
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (beanName.equals("cityController")) {
			// 如果返回false，代表是 不让CityController自动注入任何依赖的bean，
			// 也就是控制不让CityController类的依赖属性进行填充
//			return false;
			return true;
		}
		// 其他类则正常注入
		return true;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		// 其他类如果要自动装配这个类， 这里如果返回null， 也将不能依赖成功
		// 如果返回pvs 就可以装配成功，pvs就是属性填充的具体逻辑
		return pvs;
	}
	@Override
	public void query() {
		System.out.println("query");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy");
		return method.invoke(this, args);
	}
}
