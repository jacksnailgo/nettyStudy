package ConcurrentThread;

import io.netty.util.collection.ByteObjectHashMap;
import io.netty.util.collection.IntObjectHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IntObjMapMain {

    private static final  int nums = Integer.MAX_VALUE / 1024;

    public static void main(String[] args) {
        System.out.println("put操作比较,前者IntObjectHashMap,后者HashMap");
        List<Person> list = new ArrayList<Person>();
        for(int i =0;i<nums;i++){
            list.add(new Person(i));
        }
        IntObjectHashMap<Person> intObjectMap = new IntObjectHashMap<Person>();
        int i = nums;
        System.out.println("当我们的数量是：" + nums);
        long time1 = System.currentTimeMillis();
        while(i > 0){
            int id =--i;
            intObjectMap.put(id,list.get(i));
        }
        System.out.println(System.currentTimeMillis() - time1);
        HashMap<Integer, Person> integerPersonHashMap = new HashMap<Integer, Person>();
        int num = nums;
        long time3 = System.currentTimeMillis();
        while(num > 0){
            int id = --num;
            integerPersonHashMap.put(id,list.get(i));
        }
        System.out.println(System.currentTimeMillis() - time3);
        System.out.println("get操作 intObjectMap 和 HashMap");
        int index = nums;
        long time4 = System.currentTimeMillis();
        while(index > 0){
            int j = --index;
            Person person = intObjectMap.get(j);

        }
        System.out.println(System.currentTimeMillis()- time4);
        int ss = nums;
        long time5 = System.currentTimeMillis();
        while(ss > 0){
            int t = --ss;
            Person person = integerPersonHashMap.get(t);

        }
        System.out.println(System.currentTimeMillis()-time5);
    }

    //探究为什么ObjectMap比普通的HashMap更快呢？

}
