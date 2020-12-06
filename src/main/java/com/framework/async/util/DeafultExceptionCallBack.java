package com.framework.async.util;

import org.springframework.stereotype.Component;

@Component
public class DeafultExceptionCallBack implements ExceptionCallBack {
    @Override
    public void onException(Exception e) {

    }
}
