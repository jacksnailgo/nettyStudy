package com.test;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.*;
import javassist.NotFoundException;

/**
 * Javassist
 * 相当于对应用程序的二进制文件进行修改
 * JVM是不允许在运行时动态重载一个类的.
 */
public class ServiceUtils {
    /**
     *  ClassPool其实是一张保存了CtClass信息的哈希表，key=类的全限定类名，value=类名对应的CtClass对象。
     *            当需要对某个类修改的时候，通过方法getCtClass(className)从classpool获取到相应的CtClass
     *  CtClass：编译时的类信息，一个class文件在代码中的抽象表现形式，全限定类名可以获取CtClass对象，用于表示这个类文件
     *  CtMethod: 类中的方法 定义或修改
     *  CtField:  类中的属性 定义或修改
     * @throws NotFoundException
     * @throws CannotCompileException
     */
    public static void done() throws NotFoundException, CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();//获取默认的类池
        //通过全限定类名从类池获取对应需要增强的类
        CtClass base = classPool.getOrNull("com.test.BaseService");
        if (base  == null){
            System.err.println("can not found");
            return;
        }
        //通过方法getDeclaredMethod和类中需要增强的方法名字得到CtMethod类型的方法抽象
        CtMethod basemethod = base.getDeclaredMethod("printA");
        if(basemethod == null ){
            System.err.println("找不到该方法");
            return;
        }
        //组合增强字符串，使用$1获取方法的参数。
        StringBuffer sbf = new StringBuffer();
        sbf.append("{");
        sbf.append("System.out.println(\"start\"); ");
        sbf.append("}");

        //进行增强,调用之前增强
        basemethod.insertBefore(sbf.toString());

        StringBuffer sbf2 = new StringBuffer();
        sbf2.append("{");
        sbf2.append("System.out.println(\"end1111\"); ");
        sbf2.append("}");

        //进行增强,调用需要增强方法之后增强
        basemethod.insertAfter(sbf2.toString());
        //替换增强后的字节码
        base.toClass();
    }


    public static void test(){
        try {
            ServiceUtils.done();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
        BaseService baseService = new BaseService();
        baseService.printA();
    }

    public static void main(String[] args) {
        test();
    }
}
