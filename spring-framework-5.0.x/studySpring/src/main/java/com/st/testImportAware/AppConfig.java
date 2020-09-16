package com.st.testImportAware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.st.testImportAware")
@EnableRedisHttpsession(key="a", aliveTime=1000)
@Import(RedisHttpsessioConfiguration.class)
public class AppConfig {
}
