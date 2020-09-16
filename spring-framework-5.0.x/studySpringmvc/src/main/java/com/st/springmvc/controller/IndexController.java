package com.st.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controller的第一种写法， 我们经常用的写法
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public void index() {
		System.out.println("正常写法");
	}
}
