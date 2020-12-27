package precidate;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 默认函数式接口
 * Predicate<T>  实例： filter   返回boolean
 * Comsumer<T>    void forEach
 * Function<T, R>  Stream. map（）
 * Supplier<T>
 */
public class FunctionIntefaceTest {

    /**
     * 简单理解成消费者模型：常见的有ForEach
     */
    @Test
    public void test() {
        Consumer<String> consumer = a -> System.out.println(a);
        consumer.accept("hello World");
    }

    /**
     * Function 参数T  返回Ｒ  在我们的Lambda中需要返回R
     */
    @Test
    public void funtionTest() {
        Function<String, Boolean> function = s -> {
            return false;
        };
        Boolean jackie = function.apply("jackie");
        System.out.println(jackie);

    }

    /**
     * 用于获取信息
     */
    @Test
    public void supplier() {
        Supplier<String> supplier = () -> "supplier";
        String s = supplier.get();
        System.out.println(s);
    }

    /**
     * 再次消费 andThen ，注意是对第二个消费者进行accept
     */
    @Test
    public void consumerTest() {
        Consumer<String> consumer = str -> System.out.println(str);
        Consumer<String> consumer1 = consumer.andThen(str -> System.out.println(str.toUpperCase()));//再次消费！！！
        consumer1.accept("good game");

    }

/**
 * #Function<T,R>
 * R apply(T t);
 * compose(before)  先执行before
 * andThen(after)　　后执行after    before和after都是特指方法体中的方法：Function<? super V, ? extends T> before
 * identity  接受 apply方法，返回T
 */

}
