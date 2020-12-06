package com.framework.async.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
//todo  暂时还搞不懂3种事务的修改级别  之后再整理
/**
 * 数据库的操作  更新是先删除再插入
 */
public enum DBOperation {
    /**
     * 插入     1、插入可以在正常操作下进行  2.插入操作可以覆盖Normal
     */
    INSERT(AsyncDBState.INSERT,new AsyncDBState[]{AsyncDBState.NORMAL},null),
    /**
     * 更新   1.更新可以在正常操作下进行  2.更新可以覆盖更新
     */
    UPDATE(AsyncDBState.UPDATE,new AsyncDBState[]{AsyncDBState.NORMAL,AsyncDBState.UPDATE},new AsyncDBState[]{}),
    /**
     * 删除   1. 删除操作可以在正常，更新，插入下进行，    2.删除可以覆盖删除
     */
    DELETE(AsyncDBState.DELETE,new AsyncDBState[]{AsyncDBState.NORMAL,AsyncDBState.DELETE},new AsyncDBState[]{AsyncDBState.NORMAL}),
    ;

    public final AsyncDBState state;

    private final Set<AsyncDBState> needChange;

    private final Set<AsyncDBState> canReplace;

    /**
     *
     * @param state
     * @param needChange
     * @param canReplace
     */
    DBOperation(AsyncDBState state, AsyncDBState[] needChange, AsyncDBState[] canReplace) {
        this.state = state;
        Set<AsyncDBState> needChangeSet = new HashSet<>();
        Set<AsyncDBState> canOperationSet = new HashSet<>();
        if(needChange != null){
           for(AsyncDBState dbState : needChange){
               needChangeSet.add(dbState);
           }
        }
        if(canReplace !=null){
           for(AsyncDBState dbState : canReplace){
               canOperationSet.add(dbState);
           }
        }
        this.needChange = Collections.unmodifiableSet(needChangeSet);
        this.canReplace = Collections.unmodifiableSet(canOperationSet);
    }

    /**
     * state可以被修改  Operation > state
     * 更新   普通和更新 可以被更新修改
     * 插入   普通和更新 可以被插入修改
     * 删除   普通和删除 可以被删除修改
     * @param state
     * @return
     */
    public boolean canChangeAt(AsyncDBState state){
        return true;
        //return needChange.contains(state);
    }

    /**
     * 传入的状态是否可以替换当前操作  Operation > state
     * 更新   普通和更新 可以被更新覆盖
     * 插入   普通和更新 可以被插入覆盖
     * 删除   普通和删除 可以被删除覆盖
     * @param state
     * @return
     */
    public boolean canReplaceAt(AsyncDBState state){
        return true;
       // return canReplace.contains(state);
    }
}
