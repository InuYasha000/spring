package com.st.testPostProcessor.testImportBeanDefinitionRegistrar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybatisService {
	@Autowired
	private MybatisDao mybatisDao;

	public void list() {
		mybatisDao.query(123);
	}
}
