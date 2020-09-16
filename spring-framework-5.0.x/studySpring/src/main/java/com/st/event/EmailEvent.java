package com.st.event;

import org.springframework.context.ApplicationEvent;

// 自定义邮件事件, 必须继承spring 的ApplicationEvent，
// 构造方法要接收到ApplicationContext
public class EmailEvent extends ApplicationEvent {

//	private String content;
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public String getContent() {
//		return content;
//	}

	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public EmailEvent(Object source) {
		super(source);
	}
}
