package com.st.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * controller的第二种写法， 以beanName为"/index1" 的bean对象， 匹配/index1的请求
 */
@Controller("/index1")
public class IndexController1 implements org.springframework.web.servlet.mvc.Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("第二种写法 ， 规定了将会被调用的方法： handleRequest");
		return null;
	}
}
