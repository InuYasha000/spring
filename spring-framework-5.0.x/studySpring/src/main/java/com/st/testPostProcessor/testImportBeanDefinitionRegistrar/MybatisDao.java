package com.st.testPostProcessor.testImportBeanDefinitionRegistrar;

public interface MybatisDao {

	@Select("select id form table where 1=1")
	public void query(int num);
}
