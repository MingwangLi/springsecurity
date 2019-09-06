package com.szjzht.admin.compoment;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Auther: mayn
 * @Date: 2019/9/5 10:59
 * @Description:
 */
@Component
public class AdminApplicationContext implements ApplicationContextAware {

    public static  ApplicationContext adminApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        adminApplicationContext = applicationContext;
    }


    public static <T> T getBean(Class<T> tClass) {
        return adminApplicationContext.getBean(tClass);
    }

    public static Object getBean(String name){
        return adminApplicationContext.getBean(name);
    }

    public static  <T> T getBean(String name, Class<T> tClass) {
        return adminApplicationContext.getBean(name,tClass);
    }
}
