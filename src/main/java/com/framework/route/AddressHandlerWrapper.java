package com.framework.route;

import com.google.common.base.Preconditions;

import java.lang.reflect.Method;

public class AddressHandlerWrapper {
    private final Object target;
    private final Method method;

    public AddressHandlerWrapper(Object target, Method method) {
        Preconditions.checkNotNull(target,"cant't be null");
        Preconditions.checkNotNull(method,"cant be null");
        this.target = target;
        this.method = method;
        method.setAccessible(true);
    }

    public void invoke(Object...args) throws Exception{
        method.invoke(target,args);
    }
}
