package com.st.testPostProcessor.testImportBeanDefinitionRegistrar;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// 实现类似mybatis @Select
public @interface Select {
	String value();
}
