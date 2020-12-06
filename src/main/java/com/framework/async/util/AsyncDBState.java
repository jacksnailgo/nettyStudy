package com.framework.async.util;

import com.framework.async.entity.AsyncDBEntity;
import com.framework.dao.Updater;

public enum AsyncDBState {
    NORMAL(){
        @Override
        public boolean doOperation(Updater<AsyncDBEntity> updater, AsyncDBEntity entity) {
            return true;
        }
    },
    DELETED(){
        @Override
        public boolean doOperation(Updater<AsyncDBEntity> updater, AsyncDBEntity entity) {
            return true;
        }
    },
    DELETE(){
        @Override
        public boolean doOperation(Updater<AsyncDBEntity> updater, AsyncDBEntity entity) {
            try{
                return updater.delete(entity);
            }catch (Exception e){
                throw new SyncException(String.format("%",entity),e);
            }
        }
    },
    UPDATE(){
        @Override
        public boolean doOperation(Updater<AsyncDBEntity> updater, AsyncDBEntity entity) {
            try{
                return updater.update(entity);
            }catch (Exception e){
                throw new SyncException(String.format("%",entity),e);
            }
        }
    },
    INSERT(){
        @Override
        public boolean doOperation(Updater<AsyncDBEntity> updater, AsyncDBEntity entity) {
            try{
                return updater.insert(entity);
            }catch (Exception e){
                throw new SyncException(String.format("%",entity),e);
            }
        }
    },
    ;

    public abstract boolean doOperation(Updater<AsyncDBEntity> updater, AsyncDBEntity entity);
}
