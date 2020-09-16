package com.st;

import com.st.dao.Dao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 源码环境， 分析spring源码注入bean的过程
public class Test {
	public static void main(String[] args) {

		// 创建工厂，将类转换成bd对象， bd对象不是bean对象，是BeanDifinition的实例对象 put到ac.beanFactory的beanDefinitionMap
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		// 注册的类的方式：
		// 1、register 是手动注册， 将类转换成bd对象put到工厂的map中
		// 2、ac.scan() 是手动扫描包， 将包下的所有的类转换成bd对象put到工厂的map中
		// 3、还有一种方式是使用spring的扩展接口ImportBeanDefinitionRegistrar
		ac.register(AppConfig.class);
//		ac.register(Dao.class);// 也可以注册bean，这种注册都可以不需要@Component
		// bean工厂已经有了 开始生成扫描的包的bd对象实例 并put到ac.beanFactory的beanDefinitionMap
		// 然后初始化bean的实例对象
		ac.refresh(); // 完成包的扫描
		Dao dao = (Dao)ac.getBean("dao");
		dao.query();
	}
}
