package com.fizz.utils.spring;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Fizz
 * @since 2019/9/30 23:35
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return SpringUtils.applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) {
        return SpringUtils.applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
        return SpringUtils.applicationContext.getEnvironment().getProperty(key);
    }
}
