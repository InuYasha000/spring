package com.st.testEnableProxy;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ImportSelector和ImportBeanDefinitionRegistrar区别：
 * ImportSelector接口 在selectImports方法中返回这些类名的字符串数组
 * spring内部将通过这些全限定名得到Class对象，然后new BeanDefinition（Class） 生成bd放入beanFactory中，
 * 注意 从Class -> bd的过程 我们无法插手，因为没有注册器
 * 而 ImportBeanDefinitionRegistrar 我们则可以从Class -> bd的过程中 进行插手，
 * 从例子中也可以看到是我们自己生成bd对象然后手动注册到beanFactory中，我们可以动态改变 bd 注册到beanFactory，因为有注册器
 *
 */
// DefrredImportSelector继承了ImportSelector 是一个延迟加载的ImportSelector，ImportSelector是会立刻被spring解析的。
public class ImportSelectorImpl implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// 传入开启代理的类， 相当于在AopProcessor 加@Component 注册到spring中
		// 可以引入多个（返回的是字符串数组）
		return new String[]{AopProcessor.class.getName()};
	}
}
