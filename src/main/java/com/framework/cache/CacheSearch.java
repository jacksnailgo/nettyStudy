package com.framework.cache;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CacheSearch {
}
