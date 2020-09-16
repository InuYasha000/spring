package com.st;

import com.st.dao.Dao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value = "com.st.dao")
//ImportSelect 实现这个接口返回类的全限定名的字符串数组，使用Import引入 spring将会把这些类生成bd对象放入工厂的map中去
// 对于功能的动态开启和关闭，就可以用@Import(ImportSelectImpl.class)来实现，比如定义 一个@EnableXxx注解，内部写@Import(..)开启某个功能
//@Import() // 可以导入包， 或者普通类（Dao.class）,或者ImportSelect.class， 还能ImportBeanDefinitionRegistrar, ImportBeanDefinitionRegistrar具体看测试
public class AppConfig {

}
