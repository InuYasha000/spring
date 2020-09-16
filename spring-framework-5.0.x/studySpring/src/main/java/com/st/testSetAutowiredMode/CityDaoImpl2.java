package com.st.testSetAutowiredMode;

import org.springframework.stereotype.Component;

@Component
public class CityDaoImpl2 implements CityDao{
	public void query() {
		System.out.println("dao2 query");
	}
}
