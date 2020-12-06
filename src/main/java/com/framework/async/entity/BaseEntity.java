package com.framework.async.entity;

import com.framework.context.Context;

import java.io.Serializable;


public class BaseEntity extends AsyncDBEntity implements  Serializable {

    public void update(){
        Context.getAsyncDBService().update(this);
    }
    public void insert(){
        Context.getAsyncDBService().insert(this);
    }

    public void delete(){
        Context.getAsyncDBService().delete(this);
    }

    public void serialize(){

    }

    public void deserialize(){

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
