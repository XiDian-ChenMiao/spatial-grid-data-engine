package com.xidian.spatial.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 文件描述：应用上下文
 * 创建作者：陈苗
 * 创建时间：2017/7/25 19:54
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.applicationContext != null) {
            throw new IllegalStateException("ApplicationContextHolder already holded 'applicationContext'.");
        }
        this.applicationContext = applicationContext;
        logger.info("holded applicationContext, displayName:" + applicationContext.getDisplayName());
    }

    public static ApplicationContext getApplicationContext() {
        if(applicationContext == null)
            throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
        return applicationContext;
    }

    public static <T> T getBean(String beanName, Class<T> cls) {
        return getApplicationContext().getBean(beanName, cls);
    }

    public static void cleanHolder() {
        applicationContext = null;
    }
}
