package com.framework.async.entity;

import com.framework.async.SyncQueue;
import com.framework.async.util.AsyncDBState;
import com.framework.async.util.DBOperation;
import com.framework.dao.Updater;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AsyncDBEntity {
    private AtomicReferenceFieldUpdater<AsyncDBEntity, AsyncDBState> fieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(AsyncDBEntity.class, AsyncDBState.class, "currentState");
    /**
     * 对某个类，被volatile修饰的字段进行原子更新
     */
    volatile AsyncDBState currentState = AsyncDBState.NORMAL;

    private Updater updater;   //执行操作的Dao一定不能为空，否则无法执行

    private SyncQueue syncQueue;

    /**
     * 提交操作，更新entity的状态
     * operation 可能是 update,insert,delete
     * 更新操作，如果当前状态Normal，那么可以update可以替换normal
     * @param operation
     * @return
     */
    public boolean submit(DBOperation operation) {
        AsyncDBState state = currentState;
        for (; ; ) {
            if (operation.canChangeAt(state)) {
                if (operation.canReplaceAt(state)) {
                    //更新当前状态
                    if (!fieldUpdater.compareAndSet(this, state, operation.state)) {
                        continue;  // 继续更新状态
                    }
                }
                return state == AsyncDBState.NORMAL;
            }
            throw new RuntimeException("submit Exception" + state);
        }

    }

    /**
     * 尝试重复操作，直到我们的状态能够通过Dao将entity同步到数据库中
     *
     * @param tryTimes
     * @return
     */
    public boolean trySync(long tryTimes) {
        int syncCount = 0;
        for (; ; ) {
            AsyncDBState state = currentState;
            if (fieldUpdater.compareAndSet(this, state, state != AsyncDBState.DELETE ? AsyncDBState.NORMAL : AsyncDBState.DELETED)) {
                while (syncCount++ < tryTimes) {
                    return state.doOperation(updater, this);
                }
            }
            return false;
        }
    }

    public AsyncDBState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(AsyncDBState currentState) {
        this.currentState = currentState;
    }

    public Updater getUpdater() {
        return updater;
    }

    public void setUpdater(Updater updater) {
        this.updater = updater;
    }

    public SyncQueue getSyncQueue() {
        return syncQueue;
    }

    public void setSyncQueue(SyncQueue syncQueue) {
        this.syncQueue = syncQueue;
    }

    public void serialize() {

    }

    public void deserialize() {

    }
}
