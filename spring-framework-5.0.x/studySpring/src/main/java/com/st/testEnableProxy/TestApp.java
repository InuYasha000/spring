package com.st.testEnableProxy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.st.testEnableProxy")
// 加这个我们自定义的注解将开启对DaoImpl的代理
@EnableProxy
//@Import(ImportSelectorImpl.class)
public class TestApp {
}
