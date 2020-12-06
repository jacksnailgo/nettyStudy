package com.framework.async;

import com.framework.async.SyncQueue;
import com.framework.async.entity.AsyncDBEntity;
import com.framework.async.util.ExceptionCallBack;
import com.framework.async.util.ISyncStrategy;

import java.util.concurrent.*;

public class SyncQueuePool {

    private SyncQueue[] pool;

    private int poolSize;
    /**
     * 监视线程
     */
    private ScheduledExecutorService monitor;

    private final ExecutorService workExecutors;

    public volatile boolean open;


    public SyncQueuePool(int poolSize,ThreadFactory threadFactory, ISyncStrategy syncStrategy, ExceptionCallBack exceptionCallBack) {
        this.open =true;
        this.poolSize = poolSize;
        pool = new SyncQueue[poolSize];
        workExecutors = Executors.newFixedThreadPool(poolSize,threadFactory);  //按照我们的线程工厂来创建数据库工作线程
        for(int i=0;i<poolSize;i++){
            pool[i] = new SyncQueue(i,syncStrategy,exceptionCallBack);
            //todo 之前一直在考虑SyncQueue如何跑Run方法，原来是通过线程池
            workExecutors.execute(pool[i]);  //通过线程池启动syncQueue执行器，自己会去执行他的Run方法
        }
    }

    public boolean submit(AsyncDBEntity entity){
        SyncQueue queue = entity.getSyncQueue();
        if(queue == null){
            int id = entity.hashCode() % poolSize;
            queue = pool[id];
            entity.setSyncQueue(queue);
        }
      return  queue.submit(entity);
    }
    public boolean shutDown(long mills) throws InterruptedException{
        open = false;
        for(int i=0;i<poolSize;i++){
            pool[i].shutDown(mills);
        }
        workExecutors.shutdown();
        workExecutors.awaitTermination(mills, TimeUnit.MILLISECONDS);
        return true;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
