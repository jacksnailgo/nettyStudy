package com.framework.cache;

import com.framework.async.entity.AsyncDBEntity;

import com.framework.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheEntityService {

    @Autowired
    private CacheSearchManager cacheManager;

    public void updateCache(AsyncDBEntity entity) {
        Context.getAsyncDBService().update(entity);
    }

    public void addToCache(AsyncDBEntity entity) {
        Context.getAsyncDBService().insert(entity);
    }

    public void deleteFromCache(AsyncDBEntity entity) {
        Context.getAsyncDBService().delete(entity);
    }
}
