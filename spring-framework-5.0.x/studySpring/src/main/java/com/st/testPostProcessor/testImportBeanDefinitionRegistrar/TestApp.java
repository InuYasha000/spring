package com.st.testPostProcessor.testImportBeanDefinitionRegistrar;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.st.testPostProcessor.testImportBeanDefinitionRegistrar")
@MapperScan
public class TestApp {
}
