package com.framework.async.entity;

import com.framework.cache.CacheUpdater;
import com.framework.context.Context;

import java.io.Serializable;


public class BaseEntity extends AsyncDBEntity implements  Serializable, CacheUpdater<BaseEntity> {

    public void update(){
        updateCache();
    }
    public void insert(){
        addToCache();
    }

    public void delete(){
        deleteFromCache();
    }

    public void serialize(){

    }

    public void deserialize(){

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    @Override
    public void updateCache() {
        Context.getCacheEntityService().updateCache(this);
    }

    @Override
    public void addToCache() {
        Context.getCacheEntityService().addToCache(this);
    }

    @Override
    public void deleteFromCache() {
        Context.getCacheEntityService().deleteFromCache(this);
    }
}
