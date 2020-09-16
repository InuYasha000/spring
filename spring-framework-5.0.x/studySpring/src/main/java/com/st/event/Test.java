package com.st.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// springboot中源码大量运用了spring的事件
public class Test {
	public static void main(String[] args) {
		// spring的事件， 观察者模式
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		// 查看ApplicationEvent ctrl+alt+b 可以看到spring内置的事件有： start refresh close stop 四个

		// new EmailEvent(ac) 意思是将邮件事件 EmailEvent 交给 ac(也就是spring)
		// 执行这个之后 代表这spring拥有了 邮件的事件
		EmailEvent springEmailEvent = new EmailEvent(ac);
		// 这里有 ac，emailListener, emailEvent。
		// emailListener, emailEvent。各自实现相应的spring提供的类或接口
		// ac.publishEvent的时候传入需要触发的事件对象（这里是 EmailEvent 邮件事件）
		// 最后触发观察者的监听的事件对应的方法

		// spring发邮件的时候执行publishEvent 同时派发发邮件的通知， 将会触发观察者监听的事件
		ac.publishEvent(springEmailEvent);

	}

}
