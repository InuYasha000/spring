package com.st.testSetAutowiredMode;

public class CityService {

	//	@Autowired
	CityDao cityDao;

	public void query() {
		cityDao.query();
	}
	// 注入方式：构造方法，spring一定会调用一个构造方法，写了无参的默认就会调用无参的构造方法，
	// 只有mode 为3的时候才会选择 依赖形参最多的构造方法
	public CityService(CityDao cityDao) {
		this.cityDao = cityDao;
		System.out.println("constructor注入： " + cityDao);
	}

	public CityService() {
		System.out.println("无依赖的constructor" );
	}
	// set方式注入：
	// 注意这种注入方式是依赖方法名 set，只要以set这个单词开头，如果采用的是 方式
	// spring将会直接调用这些匹配到的方法，并传入依赖的实参，如果方法没有依赖将不会调用
	public void setX(CityDao dao) {
		System.out.println("调用set方法byType注入：" + dao);
		this.cityDao = dao;
	}

	// byname的时候， 依赖set方法的名字， 是根据set方法后面的名字当name去spring容器中匹配beanName
	public void setCityDaoImpl2(CityDao dao) {
		System.out.println("调用setCityDaoImpl2方法 byName 注入：" + dao);
		this.cityDao = dao;
	}

// 下面的两个方法怎么也不可能被调用
//	public void cityDao(CityDao cityDao) {
//		System.out.println("调用cityDao方法注入：" + cityDao);
//		this.cityDao = cityDao;
//	}
//
//	public void setXxx() {
//		System.out.println("没有依赖，这样的方法将永远不会被spring调用");
//	}
}
