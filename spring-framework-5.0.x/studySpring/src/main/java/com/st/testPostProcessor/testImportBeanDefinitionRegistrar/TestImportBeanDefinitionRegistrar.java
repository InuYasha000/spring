package com.st.testPostProcessor.testImportBeanDefinitionRegistrar;


import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * mybatis的@MapperScan注解将接口扫描出来并变成了对象放入spring， 怎么做到？ 实现一个@MapperScan
 *  有问题的思路：
 *  首先： 需要根据接口生成对象，使用动态代理， 实现当前接口生成 代理类
 *  然后将通过代理对象获取的代理类 手动生成db对象put到spring的map中
 *  但是我们没没法在当前环境获取到代理类，所以不能行
 *
 *  问题解决： 使用FactoryBean 修改返回的对象
 *
 *  mybatis的源码类是MapperScannerRegistrar
 *
 */

public class TestImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar { // 这个接口让我们可以生成bd对象并注册

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// 传入接口的类 为了生成MybatisDao的bd对象，后面我们就可以生成代理对象
		BeanDefinitionBuilder build = BeanDefinitionBuilder.genericBeanDefinition(MybatisDao.class);
		// 获取MybatisDao类的 bd对象
		GenericBeanDefinition bd = (GenericBeanDefinition) build.getBeanDefinition();
		// 修改bd对象类型，这个MyFactoryBean被我们修改成了返回我们需要的对象
		// bd对象是通过Class类来的，spring生成bean都是通过bd对象生成的， Class能做(反射)的bd基本也可以做
		// 传入类的全限定名当MyFactoryBean构造函数的参ConfigurationClassPostProcessor数， mybatis 的@MapperScan源码实现就是这么做的
		// bd.getConstructorArgumentValues().addGenericArgumentValue("com.st.testPostProcessor.testImportBeanDefinitionRegistrar.MybatisDao");

		// mybatis执行这个代码的类是ClassPathMapperScanner的processBeanDefinitions方法,这个类继承了spring扫描包的类：ClassPathBeanDefinitionScanner
		// mybatis的这个方法是扫描出所有的interface放入list中，然后遍历这个list，拿出每一个接口全限定名传入addGenericArgumentValue方法，完成bd的构造传参
		bd.getConstructorArgumentValues().addGenericArgumentValue(bd.getBeanClassName());
		bd.setBeanClass(MyFactoryBean.class); // setBeanClass必须放在设置构造方法的参数的后面

		// 设置自动装配类型（有三种类型， No，byType，byName，byConstuctor 一旦设置这个我们在类当中甚至可以不需要写@Autowired就可以达到自动装配的效果），为no的话，我们自己写@Autowired也能自动装配
		// 非常强大， mybatis内部产生MapperFactoryBean对象（所有的mapper都是他）在@MapperScan的时候 也是使用这个方法，根据MapperFactoryBean 的set确定哪些属性需要自动装配（有set将自动装配）
		// 如果在这里设置bd的自动装配采用的是set方法装配的（也就是需要set方法）
//		bd.setAutowireMode(2);
		// put到spring的map中
		registry.registerBeanDefinition("mybatisDao", bd);
	}
}

