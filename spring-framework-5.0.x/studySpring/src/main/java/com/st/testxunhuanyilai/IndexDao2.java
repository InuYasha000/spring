package com.st.testxunhuanyilai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndexDao2 {
	@Autowired
	IndexDao1 dao1;
}
