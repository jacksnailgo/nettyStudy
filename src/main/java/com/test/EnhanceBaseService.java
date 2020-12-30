package com.test;

/**
 * 检测字节码增强的增强类
 */
public class EnhanceBaseService {

    public static void before(){
        System.out.println("before");
    }

    public static void after(){
        System.out.println("after");
    }
}
