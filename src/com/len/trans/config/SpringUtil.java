package com.len.trans.config;

import org.springframework.context.ApplicationContext;

public class SpringUtil {
	private ApplicationContext context;
	public SpringUtil(){		
	}
	public Object getBean(String bean){	//��ʼ��ʧ�ܵĻ���contextΪ��	
		return context.getBean(bean);		
	}
	public ApplicationContext getContext() {
		return context;
	}
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	
}
