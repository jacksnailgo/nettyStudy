package com.framework.dao;

import java.util.List;

/**
 * 继承Updater，表示可以数据库操作
 * @param <T>
 */
public interface BaseDao<T> extends Updater {

    List<T> getALl();

    T getBy(long id);



}
