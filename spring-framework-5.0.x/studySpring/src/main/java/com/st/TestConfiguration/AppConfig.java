package com.st.TestConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.st.TestConfiguration")
@Configuration // 加了这个注解spring将通过cglib生成AppConfig的代理类
public class AppConfig {

//	@Bean
//	public static IndexDao1 indexDao() { // 如果是static修饰的方法， 就算加了Configuration依然会执行两遍构造，因为对于静态方法spring内部处理是不一样的
//		return new IndexDao1();
//	}

	@Bean
	public IndexDao1 indexDao() {
		return new IndexDao1();
	}

	@Bean
	public  IndexDao2 indexDao2() {
		indexDao(); // 没有Configuration注解， 将会执行IndexDao1的两次构造方法，不符合默认单例规则
		return new IndexDao2();
	}

	// 如果加了@Configuration注解， spring内部将打上full的标记， 注入beanFactory之后，
	// 将在调用postProcessBeanFactory（BeanFactoryPostProcessor接口的方法）的时候再次从beanFactory中拿出来并判断有没有full标记
	// 有的话将使用cglib（内部使用asm） 生成这个bd对象的原来的类的代理类（AppConfig的代理类），并注入beanFactory，对方法进行拦截添加逻辑（有三个拦截的类，执行AppConfig的任何方法将执行 添加的三个类的逻辑）
	// 使用@Bnea注入对象的时候，如果是单例模式，将不再new新的对象，将会直接从beanFactory中拿
}
