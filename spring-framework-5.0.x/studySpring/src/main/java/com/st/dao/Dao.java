package com.st.dao;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class Dao {

	public void query() {
		System.out.println("DaoQuery");
	}

	public Dao() {
		System.out.println("Constructor");
	}


	@PostConstruct
	public void init() {
		System.out.println("init");
	}
}
