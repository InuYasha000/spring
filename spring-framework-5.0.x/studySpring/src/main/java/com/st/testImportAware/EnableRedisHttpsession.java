package com.st.testImportAware;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Import(RedisHttpsessioConfiguration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableRedisHttpsession {
	String key() default "";
	int aliveTime() default 0;
}
