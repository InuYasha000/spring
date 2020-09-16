package com.st.testSetAutowiredMode;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.st.testSetAutowiredMode")
@Import(getBeanDefinition.class)
public class AppConfig {

}
