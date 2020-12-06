package com.framework.console;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Console {
    String method () default "";
    String desc() default "描述";
}
