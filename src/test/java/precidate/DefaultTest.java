package precidate;

import org.junit.Test;

/**
 * default关键字用于在接口中定义默认方法，该方法有方法体
 */
public class DefaultTest {
    public static void main(String[] args) {
        Defalut1 defalut1 = new Defalut1();
        defalut1.show();
    }
}

interface A {
    default void show(){
        System.out.println("Print A");
    }
}

interface B extends A {
    @Override
    default void show() {
        System.out.println("Print B");
    }
}

class Defalut1 implements B{

    public void test(){
        show();
    }
}
