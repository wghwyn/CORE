package com.wgh.core.Global;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class BeanFactory {
    private final String SPRING_DB_FILE = "classpath:applicationContext-db.xml";
    private final String SPRING_BEAN_FILE = "classpath:applicationContext-bean.xml";
    private final String SPRING_CACHE_FILE = "classpath:applicationContext-cache.xml";
    private final String SPRING_IO_FILE = "classpath:applicationContext-io.xml";
    private final String SPRING_THREAD_FILE = "classpath:applicationContext-thread.xml";
    private static BeanFactory _instance = null;
    public static BeanFactory getInstance(){
        if(_instance == null){
            _instance = new BeanFactory();
        }
        return _instance;
    }

    public Object getBean(String beanName){
        if(beanName == null || beanName.isEmpty()){
            return new Object();
        }
        return new ClassPathXmlApplicationContext(new String[]{SPRING_DB_FILE, SPRING_BEAN_FILE, SPRING_CACHE_FILE}).getBean(beanName);
    }

    public Object getIo(String beanName){
        if(beanName == null || beanName.isEmpty()){
            return new Object();
        }
        return new ClassPathXmlApplicationContext(new String[]{SPRING_IO_FILE}).getBean(beanName);
    }

    public Object getThread(String beanName){
        if(beanName == null || beanName.isEmpty()){
            return new Object();
        }
        return new ClassPathXmlApplicationContext(new String[]{SPRING_THREAD_FILE}).getBean(beanName);
    }
}
