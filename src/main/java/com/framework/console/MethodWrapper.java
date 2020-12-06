package com.framework.console;

import java.lang.reflect.Method;

public class MethodWrapper {
    private Object owner;  //方法持有者 xxService
    private Method method;
    private String methodName;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
