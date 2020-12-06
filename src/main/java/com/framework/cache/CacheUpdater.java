package com.framework.cache;

public interface CacheUpdater<T> {
    void updateCache();

    void addToCache();

    void deleteFromCache();


}
