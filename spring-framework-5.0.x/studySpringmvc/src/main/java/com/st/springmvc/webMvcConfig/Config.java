package com.st.springmvc.webMvcConfig;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class Config implements WebMvcConfigurer {

	// 配置一个解析controller接口返回的 map 的解析器（消息转换器）， converters是springMvc内部自带的解析器list
	// 这个方法是专门用来配置 springmvc解析controller返回值的
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		System.out.println("==========添加 FastJsonHttpMessageConverter=============");
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		converters.add(converter);
	}


}
