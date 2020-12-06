package com.framework.async;

import com.framework.async.entity.AsyncDBEntity;
import com.framework.async.util.ExceptionCallBack;
import com.framework.async.util.ISyncStrategy;
import com.framework.async.util.SyncException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadFactory;

public class SyncQueue implements Runnable{

    private static AsyncDBEntity SHUTDOWN_ENTITY = new AsyncDBEntity();
    /**
     * 队列ID
     */
    private final int queueId ;
    /**
     * 是否停止
     */
    private volatile boolean stop = false;
    /**
     * 已经同步到数据库的次数
     */
    private volatile long syncCount = 0;
    /**
     * 待同步队列
     */
    private final BlockingQueue<AsyncDBEntity> syncQueue= new LinkedTransferQueue<>();  //为啥要选这个队列呢？
    /**
     * 异常回调
     */
    private ExceptionCallBack exceptionCallBack;

    private ISyncStrategy syncStrategy;

    public SyncQueue(int queueId,ISyncStrategy syncStrategy, ExceptionCallBack exceptionCallBack) {
        this.exceptionCallBack = exceptionCallBack;
        this.syncStrategy = syncStrategy;
        this.queueId = queueId;
    }

    public boolean submit(AsyncDBEntity entity){
        return   syncQueue.add(entity);
    }
    @Override
    public void run() {
            while (true){
                final int numEachLoop = syncStrategy.getNumEachLoop();
                final int tryTime = syncStrategy.getTryTime();
                for(int i=0;i<numEachLoop;i++){
                    AsyncDBEntity takeEntity = null;
                    try{
                        takeEntity = syncQueue.take();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    if(takeEntity == null || takeEntity == SHUTDOWN_ENTITY){
                        break;
                    }
                    try{
                        takeEntity.trySync(tryTime);
                        syncCount++;
                    } catch (Exception e){
                        exceptionCallBack.onException(e);
                    }
                    if(syncQueue.isEmpty()){
                        if(stop){
                            continue;
                        } else {
                           try{
                               int size = syncQueue.size();
                               long sleepTime = syncStrategy.getSleepTime(size);
                               Thread.sleep(sleepTime);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                        }
                    }
                }

            }



    }

    public boolean shutDown(long mills) {
        //
        stop =true;
        syncQueue.add(SHUTDOWN_ENTITY);
        return true;
    }
}
