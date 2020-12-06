package com.framework.dao;

/**
 *
 * @param <T>
 */
public interface Updater<T> {
    boolean update(T data);

    boolean insert(T data);

    boolean delete(T data);
}
