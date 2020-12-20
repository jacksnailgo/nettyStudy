package com.framework.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

public class CacheTest {
    public static void main(String[] args) throws ExecutionException {
        /**
         * 标准创建方式   设置最大存储
         * 设置过期时间  expireAfterWrite
         * expireAfterAccess 指定对象多久没有被访问后过期
         * 弱引用 、weakKeys  weakValues
         * 显示清除 invalid
         * 移除监听器  removalListener
         *
         *
         * 自动加载  get中的回调函数，我们去获取数据库中的数据
         *          * 两个线程共享同一个Cache对象时候，
         *  Guava可以保证当有多个线程同时访问Cache中的一个key时，
         *  如果key对应的记录不存在，Guava只会启动一个线程执行get方法中Callable参数对应的任务加载数据存到缓存
         *  当加载完数据后，任何线程中的get方法都会获取到key对应的值
         *
         *  统计信息
         *  recordStats()
         */
        Cache<String, String> stringCache = CacheBuilder.newBuilder().build();
        stringCache.put("account","jackie");
        stringCache.put("access","jj12345");
        stringCache.invalidate("account");
        ConcurrentMap<String, String> stringStringConcurrentMap = stringCache.asMap();
        for(String str : stringStringConcurrentMap.values()){
            System.out.println("value:" + str);
        }

        String result = stringCache.get("account2", new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("回调函数获取result");
                return "hello World";
            }
        });
        System.out.println(result);


    }



}
