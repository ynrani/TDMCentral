/*
 * Object Name : ApplicationContextProvider.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		3:41:06 PM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.tdm.resourcebundle;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

/**
 * @author vkrish14
 *
 */
public class ApplicationContextProvider implements ApplicationContextAware{
	
	private static ApplicationContext context;

    public static ApplicationContext getContext() {

        if (context != null) {
            return context;
        }
        else {
            throw new IllegalStateException("The Spring application context is not yet available.");
        }

    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		if (context == null) {
            ApplicationContextProvider.context = applicationContext;
        }
        else {
            throw new IllegalStateException("The application context provider was already initialized.");
        }	
	}
	
}
