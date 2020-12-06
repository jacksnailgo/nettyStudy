package com.framework.cache;

public class LoadingCacheTest {
    public static void main(String[] args) {
        /**
         * https://segmentfault.com/a/1190000011105644
         * 当从LoadingCache中读取一个指定key的记录时，
         * 如果该记录不存在，则LoadingCache可以自动执行加载数据到缓存的操作
         * 在调用CacheBuilder的build方法时，必须传递一个CacheLoader类型的参数，CacheLoader的load方法需要我们提供实现
         */
    }
}
