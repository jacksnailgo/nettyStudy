import java.teste.FatherTest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterizedTypeTest {

    public static void main(String[] args) {
        FatherTest fatherTest = new FatherTest();

        Type genericSuperclass = fatherTest.getClass().getGenericSuperclass();
        ParameterizedType type1 = (ParameterizedType) genericSuperclass;
        int length = type1.getActualTypeArguments().length;
        System.out.println("父类的名字：" +genericSuperclass.getTypeName());
        System.out.println("泛型的接口");
        System.out.println("length :" + length);
        System.out.println(type1.getActualTypeArguments()[0]);
        System.out.println(type1.getActualTypeArguments()[1]);
        if(ParameterizedType.class.isAssignableFrom(genericSuperclass.getClass())){
            System.out.println(fatherTest.getClass().getName() + "is 参数化类型");
        }
    }
}
