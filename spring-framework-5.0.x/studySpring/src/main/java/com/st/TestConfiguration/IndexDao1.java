package com.st.TestConfiguration;

import org.springframework.stereotype.Component;

//@Component
public class IndexDao1 {
	public IndexDao1() {
		System.out.println("indexDao1构造");
	}
	public void query() {
		System.out.println("indexdao1 query");
	}
}
