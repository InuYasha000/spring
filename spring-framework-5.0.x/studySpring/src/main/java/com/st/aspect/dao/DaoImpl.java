package com.st.aspect.dao;

import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements Dao {
	public DaoImpl(String s) {

	}

	@Override
	public void query() {
		System.out.println("query");
	}
}
