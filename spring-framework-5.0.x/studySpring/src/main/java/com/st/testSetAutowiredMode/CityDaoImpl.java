package com.st.testSetAutowiredMode;

import org.springframework.stereotype.Component;

@Component
public class CityDaoImpl implements CityDao{
	public void query() {
		System.out.println("dao0 query");
	}
}
