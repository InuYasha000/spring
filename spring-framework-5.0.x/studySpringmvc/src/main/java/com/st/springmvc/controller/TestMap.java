package com.st.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestMap {

	@ResponseBody
	@RequestMapping("/map")
	public Map testMap() {
		Map<String, String> map = new HashMap();
		map.put("1", "小明");
		return map;
	}
}
