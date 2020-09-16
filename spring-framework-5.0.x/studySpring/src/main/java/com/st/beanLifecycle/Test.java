package com.st.beanLifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();
		// 销毁bean实例

		System.out.println("==================== 开始销毁 ====================");
//		ac.removeBeanDefinition("personBean");
//		ac.destroy();
		ac.close();
		System.out.println(ac.getBean(PersonBean.class));
	}
}
