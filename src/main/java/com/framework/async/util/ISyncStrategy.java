package com.framework.async.util;

public interface ISyncStrategy {
    /**
     * 每轮循环睡眠时间
     * @param waitingSize
     * @return
     */
    long getSleepTime(int waitingSize);

    /**
     * 每轮循环同步多少个
     * @return
     */
    int getNumEachLoop();

    /**
     * 同步失败的重试次数
     * @return
     */
    int getTryTime();
}
