package com.framework.cache;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CacheSearchManager implements ApplicationContextAware {

   public Map<Class<? extends CacheSearch>,Object> cacheMap = new HashMap<>();
    /**
     * 预先将那些Entity的类型按类别存储起来,只能根据entity所在的Dao去进行识别，
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, CacheSearch> beansOfType = applicationContext.getBeansOfType(CacheSearch.class);
        for(CacheSearch cache : beansOfType.values()){
            Class<? extends CacheSearch> cacheClass = cache.getClass();
        }
    }

    @PostConstruct
    public void init(){

    }
}
