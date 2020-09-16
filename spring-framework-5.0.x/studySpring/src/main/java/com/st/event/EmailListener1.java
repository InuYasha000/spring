package com.st.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener1 implements ApplicationListener<EmailEvent> {
	@Override
	public void onApplicationEvent(EmailEvent event) {
		System.out.println("EmailListener1监听到了触发的EmailEvent");
	}
}
