/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.config;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.lang.Nullable;

/**
 * {@link BeanDefinitionParser} for the {@code aspectj-autoproxy} tag,
 * enabling the automatic application of @AspectJ-style aspects found in
 * the {@link org.springframework.beans.factory.BeanFactory}.
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @since 2.0
 */
class AspectJAutoProxyBeanDefinitionParser implements BeanDefinitionParser {

	/**
	 * SpringAOP部分使用JDK动态代理或者CGLIB来为目标对象创建 代理（建议尽量使用JDK的动态代理）
	 * 如果被代理的目标对象实现了至少一个接口，则会使用JDK动态代理。所有该目标类型实现的接口都将被代理。若该目标对象没有实现任何接口，则创建一个CGLIB代理
	 * 强制使用CGLIB需要设置<aop:config proxy-target-class="true">...</aop:config>
	 *
	 * JDK动态代理∶其代理对象必须是某个接口的实现，它是通过在运行期间创建一个接 口的实现类来完成对目标对象的代理。 
	 * CGLIB代理∶实现原理类似于JDK动态代理，只是它在运行期间生成的代理对象是 针对目标类扩展的子类。CGLIB是高效的代码生成包，底层是依靠ASM（开源的Java 字节码编辑类库）操作字节码实现的，性能比 JDK 强。
	 *  expose-proxy∶有时候目标对象内部的自我调用将无法实施切面中的增强，如下示例∶
	 * publlc interface AService {
	 * 		public void a();
	 * 		public void b();
	 * }
	 *  @service()
	 *  public class AServiceImpl1 implements AService{
	 * 		 @Transactional(propagation = Propagation,REQUIRED)
	 * 		publlc vold a(){
	 * 			this.b(); 
	 * 			//((AService)AopContext.currentProxy()).b()
	 * 		}
	 * 		@Transactional(propagation=Propagation.REQUIRES_NEW)
	 * 		public void b(){}
	 * }
	 * 此处的this指向目标对象，因此调用this.b0将不会执行b事务切面，即不会执行事务增强，因此b方法的事务定义"@Transactional（propagation=Propagation.REQUIRESNEW）"将不会实施，
	 * 为了解决这个问题，我们可以这样做∶
	 * <aop:aspectj-autoproxy exposa-proxy="true"/>
	 * 然后将以上代码中的"this.b0;"修改为"((AService)AopContext.currentProxy()).b();
	 * 通过以上的修改便可以完成对a和b方法的同时增强。
	 */
	@Override
	@Nullable
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		//注册 AnnotatlonAwareAspectJAutoProxyCreator
		AopNamespaceUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(parserContext, element);
		//对于注解中子类的处理
		extendBeanDefinition(element, parserContext);
		return null;
	}

	private void extendBeanDefinition(Element element, ParserContext parserContext) {
		BeanDefinition beanDef =
				parserContext.getRegistry().getBeanDefinition(AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME);
		if (element.hasChildNodes()) {
			addIncludePatterns(element, parserContext, beanDef);
		}
	}

	private void addIncludePatterns(Element element, ParserContext parserContext, BeanDefinition beanDef) {
		ManagedList<TypedStringValue> includePatterns = new ManagedList<>();
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			if (node instanceof Element) {
				Element includeElement = (Element) node;
				TypedStringValue valueHolder = new TypedStringValue(includeElement.getAttribute("name"));
				valueHolder.setSource(parserContext.extractSource(includeElement));
				includePatterns.add(valueHolder);
			}
		}
		if (!includePatterns.isEmpty()) {
			includePatterns.setSource(parserContext.extractSource(element));
			beanDef.getPropertyValues().add("includePatterns", includePatterns);
		}
	}

}
