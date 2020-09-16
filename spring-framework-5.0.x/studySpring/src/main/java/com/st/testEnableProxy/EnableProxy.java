package com.st.testEnableProxy;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
// 导入我们实现的ImportSelectorImpl， 有这个类就相当于导入了AopProcessor处理器，就将开启代理
@Import(ImportSelectorImpl.class)
public @interface EnableProxy {
}
