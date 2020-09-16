package com.st.testSetAutowiredMode;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * 随便继承什么类 只要可以注册bd就行
 *
 * 测试setAutowiredMode自动装配模型的含义（在我们手动将bean添加到spring的时候有用，比如mybatis）
 *
 */
// 自动装配和注入方式（也叫自动装配模式）的区别， autowired 和 bytype， 自动装配是不用手动在xml中维护依赖的属性对象，而是使用default-autowired='bytype'
// 而注入方式 bytype byname  是bean注入的一种匹配容器中的bean方式
// 可以在bd中设置自动装配模型指定 使用set方法还是构造方法来注入依赖的bean， 或者指定set方法并且使用byName还是byType的注入方式，也就是下面的setAutowireMode，
@Component
public class getBeanDefinition implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		BeanDefinitionBuilder build = BeanDefinitionBuilder.genericBeanDefinition(CityService.class);
		GenericBeanDefinition bd = (GenericBeanDefinition) build.getBeanDefinition();
//		 无论mode怎么样 都会调用一个构造方法, 0 1 2 3 4对应的含义在AbstractBeanDefinition类中, AUTOWIRE_BY_NAME等等

//		 什么都不采用， 如果写了@Autowired等等， 就按照Autowired规则去匹配
//		 bd.setAutowireMode(0);

//		 byName,spring将调用set方法注入依赖（set方法的名字必须出去set后匹配到spring容器中的beanName，匹配不到将无法注入）,
//		 也就是依赖的类型在容器中存在多个对象的时候， 需要用这个方式指定采用byName注入。
		 bd.setAutowireMode(1);

//		 byTyp, spring将调用set方法注入依赖， 并按照byType去匹配并注入
//		 bd.setAutowireMode(2);

//		byConstructor, spring将调用依赖形参（形参类型在spring容器中）最多的构造方法，使用btType注入依赖，
//		但是如果在spring容器找到多个相同类型的对象，将采用无参的构造方法
//		bd.setAutowireMode(3);


//		 4这种方式已经过时 spring3.0之后不再支持, 和2表现一致
//		 bd.setAutowireMode(4);
		registry.registerBeanDefinition("cityService", bd);
	}
}
