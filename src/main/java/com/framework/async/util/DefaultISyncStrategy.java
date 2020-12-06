package com.framework.async.util;

import org.springframework.stereotype.Component;

@Component
public class DefaultISyncStrategy implements ISyncStrategy{

    private int numEachLoop = 10;

    private long sleepTime = 10;

    private int tryTime = 10;


    @Override
    public long getSleepTime(int waitingSize) {
        if(waitingSize <50){
            return sleepTime;
        }
        return sleepTime/2;
    }

    @Override
    public int getNumEachLoop() {
        return numEachLoop;
    }

    @Override
    public int getTryTime() {
        return tryTime;
    }
}
