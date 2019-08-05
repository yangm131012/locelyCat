package com.yangm.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
/**
 * spring获取bean工具类
 */
@Component
public class SpringUtil implements ApplicationContextAware, ServletContextListener {

private static ApplicationContext applicationContext = null;
	
	private static ServletContext servletContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}
	
	public static ServletContext getServletContext() {
		return servletContext;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		SpringUtil.servletContext = sce.getServletContext();
	}
 
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
 
	}

	public static <T> T getBean(Class<T> cla) {
		return applicationContext.getBean(cla);
	}

	public static <T> T getBean(String name, Class<T> cal) {
		return applicationContext.getBean(name, cal);
	}

	public static String getProperty(String key) {
		return applicationContext.getBean(Environment.class).getProperty(key);
	}
	
}
