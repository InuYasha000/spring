package com.st.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// 邮件事件的监听者 （也就是观察者）
// 因为被观察者是SpringContext， 而被观察者必须持有观察者的引用，
// 所以EmailListener必须实现 ApplicationListener接口， spring内部将会做类似 list.add( EmailListener)的操作
// 等到ac.publishEvent的时候 ， 将遍历list中所有的类 ， 依次执行事件

@Component
public class EmailListener implements ApplicationListener<EmailEvent> {
	@Override
	public void onApplicationEvent(EmailEvent event) {
		System.out.println("EmailListener监听到了触发的EmailEvent");
	}
}
