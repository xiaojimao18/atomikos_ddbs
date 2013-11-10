package com.len.trans.config;

import org.springframework.context.ApplicationContext;

public class SpringUtil {
	private ApplicationContext context;
	public SpringUtil(){		
	}
	public Object getBean(String bean){	//初始化失败的话，context为空	
		return context.getBean(bean);		
	}
	public ApplicationContext getContext() {
		return context;
	}
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	
}
