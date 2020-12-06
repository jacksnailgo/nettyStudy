package com.framework.async.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThreadFactory implements ThreadFactory {

    private ThreadGroup group;

    private String groupName;

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public SimpleThreadFactory() {
        init();
        groupName = group.getName();
    }

    public SimpleThreadFactory(String groupName) {
        init();
        this.groupName = groupName;

    }

    private void init() {
        Executors.defaultThreadFactory();
        SecurityManager securityManager = System.getSecurityManager();
        group = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = groupName +"-SyncQueueThread-" +atomicInteger.getAndIncrement();
        Thread thread = new Thread(group,r,threadName);
        if(thread.isDaemon()){
            thread.setDaemon(false);
        }
        if(thread.getPriority() != Thread.NORM_PRIORITY){
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
