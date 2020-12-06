package com.framework.context;

import com.framework.async.AsyncDBService;
import com.framework.cache.CacheEntityService;
import com.logic.player.PlayerService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Context implements ApplicationContextAware {

    private  static ApplicationContext applicationContext;

    private static PlayerService playerService;

    private static AsyncDBService asyncDBService;

    private static CacheEntityService cacheEntityService;

    public static <T> T getBeans(Class<T> tclass ){
        return applicationContext.getBean(tclass);
   }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static PlayerService getPlayerService() {
        return playerService;
    }
    @Autowired
    public  void setPlayerService(PlayerService playerService) {
        Context.playerService = playerService;
    }

    public static AsyncDBService getAsyncDBService() {
        return asyncDBService;
    }
    @Autowired
    public void setAsyncDBService(AsyncDBService asyncDBService) {
        Context.asyncDBService = asyncDBService;
    }

    public static CacheEntityService getCacheEntityService() {
        return cacheEntityService;
    }
    @Autowired
    public  void setCacheEntityService(CacheEntityService cacheEntityService) {
        Context.cacheEntityService = cacheEntityService;
    }
}
