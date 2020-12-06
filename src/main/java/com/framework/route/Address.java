package com.framework.route;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Address {

    String address() default "";

    boolean access() default  true;

}
