package com.len.trans.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {
	@Autowired
	@Qualifier("springUtil")
	private SpringUtil springUtil;
	
	@Override
	public void setApplicationContext(ApplicationContext context)
		throws BeansException {
		springUtil.setContext(context);
	}	
}
