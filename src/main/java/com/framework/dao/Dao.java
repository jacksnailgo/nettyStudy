package com.framework.dao;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Dao {
    public boolean sync() default true;

    Class<? extends Updater> daoClass();
}
