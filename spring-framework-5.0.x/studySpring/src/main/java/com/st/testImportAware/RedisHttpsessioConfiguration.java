package com.st.testImportAware;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * ImportAware 接口可以获取注解信息，可以和@Profile类似的功能（根据注解value 执行相应的逻辑）
 * 功能是 当前类可以拿到 @Import这个类 的类的任意注解的值
 */

@Configuration
public class RedisHttpsessioConfiguration implements ImportAware {
	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {
		Set<String> annotationTypes = importMetadata.getAnnotationTypes(); // 拿到@Import这个类的 类上的所有注解
		System.out.println(annotationTypes);
		Map<String, Object> map = importMetadata.getAnnotationAttributes(EnableRedisHttpsession.class.getName());
		AnnotationAttributes attrs = AnnotationAttributes.fromMap(map);
		String key = attrs.getString("key");
		Number aliveTime = attrs.getNumber("aliveTime");
		System.out.println(key);
		System.out.println(aliveTime);
	}
}
