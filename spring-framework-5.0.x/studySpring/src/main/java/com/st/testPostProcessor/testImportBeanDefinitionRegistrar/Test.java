package com.st.testPostProcessor.testImportBeanDefinitionRegistrar;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	public static void main(String[] args) {
		// mybatis实现原理
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(TestApp.class);
		ac.refresh();
//		MybatisDao bean = (MybatisDao) ac.getBean(MybatisDao.class);// 现在已经不能用type获取了，因为我们把type改成了MyFactoryBean了
//		MybatisDao bean = (MybatisDao) ac.getBean("mybatisDao");
//		bean.query(123);
		MybatisService service = (MybatisService) ac.getBean(MybatisService.class);
		// 自动注入成功 将调用上面的bean.query方法, 获取注解内容
		service.list();
	}
}
