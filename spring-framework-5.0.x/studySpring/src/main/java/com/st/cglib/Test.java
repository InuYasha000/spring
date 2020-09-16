package com.st.cglib;

import com.st.dao.Dao;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;

public class Test {
	/**
	 * 简单使用cglib
	 * @param args
	 */
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		// 将目标Dao类 作为父类(代理Dao类)
		enhancer.setSuperclass(Dao.class);

		enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);

		// 设置拦截方法类（）
		enhancer.setCallback(new MyMethodCallback());

		// 获取代理对象
		Dao dao = (Dao) enhancer.create();
		dao.query();
		dao.init();
	}
}
