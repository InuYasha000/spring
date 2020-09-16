package com.st.testxunhuanyilai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndexDao1 {
	@Autowired
	IndexDao2 dao2;
}
