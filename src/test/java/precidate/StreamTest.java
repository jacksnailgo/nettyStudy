package precidate;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.logic.player.entity.PlayerActor;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class StreamTest {

    public static void main(String[] args) {
        StreamTest test = new StreamTest();
        test.streamList();
    }

    /**
     * 对内部元素切片处理，相当于双重for循环
     */
    @Test
    public void flatMap(){
        List<String> list = Arrays.asList("jackie","tom","daddy","jonny","peter","yaqi");
        //Map<Integer,String> map = Maps.newHashMap();
        List<String> collect = list.stream().flatMap(s -> {
            List<String> list1 = Lists.newArrayList();
            for (char c : s.toCharArray()) {
                list1.add("" +c);
            }
            return list1.stream();
        }).collect(Collectors.toList());
        System.out.println(collect);
    }
    @Test
    public void streamList(){
        List<String> list = Arrays.asList("jackie","tom","daddy","jonny","peter","yaqi");
        Map<Integer,String> map = Maps.newHashMap();
        List<String> collect = list.stream().map(str ->
                {
                    int count = 0;
                    map.put(count++,"com." + str);
                    return "com." + str;
                }
        ).collect(Collectors.toList());
        System.out.println(map);
    }

    /**
     * 流式处理HashMap，修改Key和Value
     * 生成新的Map
     * 最后的Collects.ToMap()
     * 也是使用Stream流  key ->
     */
    @Test
    public void streamMap(){
        Map<Integer, PlayerActor> map = Maps.newConcurrentMap();
        PlayerActor playerActor = new PlayerActor();
        playerActor.setAccount("jackie1");
        map.put(1,playerActor);
        playerActor.setAccount("jackie2");
        map.put(2,playerActor);
        playerActor.setAccount("jackie3");
        map.put(3,playerActor);
        playerActor.setAccount("jackie4");
        map.put(4,playerActor);

        Map<String, Long> collect = map.entrySet().stream().map(integerPlayerActorEntry -> {
            PlayerActor value = integerPlayerActorEntry.getValue();
            System.out.println("key is "+integerPlayerActorEntry.getKey());
            value.setAccount(value.getAccount().substring(0,value.getAccount().length()-1)+integerPlayerActorEntry.getKey());
            return integerPlayerActorEntry.setValue(value);
        }).collect(Collectors.toMap(item -> item.getAccount(), item -> item.getCopper()));

        collect.forEach((key,value) ->{
            System.out.println("element : " + key + ",value is " + value);
        });


    }

    /**
     * Lambda表达式获得Key和Value的值
     */
    @Test
    public void forEachMap(){
        Map<Integer, PlayerActor> map = Maps.newConcurrentMap();
        PlayerActor playerActor = new PlayerActor();
        playerActor.setAccount("jackie1");
        map.put(1,playerActor);
        playerActor.setAccount("jackie2");
        map.put(2,playerActor);
        playerActor.setAccount("jackie3");
        map.put(3,playerActor);
        playerActor.setAccount("jackie4");
        map.put(4,playerActor);
        map.forEach((key,value) ->{
            System.out.println("输入账号" +key + value.getAccount());
        });
    }

    @Test
    public void sort(){
        List<Employee> employees = DataUtils.getEmployees();
        List<Employee> collect = employees.stream().sorted((e1, e2) -> e1.getAge() - e2.getAge()).collect(Collectors.toList());
        System.out.println(collect);
        List<Employee> collect1 = employees.stream().sorted(Comparator.comparing(Employee::getAge).
                thenComparing(Comparator.comparing(Employee::getSalary).reversed())).collect(Collectors.toList());
        System.out.println(collect1);
    }

    @Test
    public void partionBy(){
        List<Employee> employees = DataUtils.getEmployees();
        Map<Boolean, List<Employee>> collect = employees.stream().collect(Collectors.partitioningBy(employee -> employee.getAge() > 30));
       // System.out.println(collect);
        // 指定对象的某个字段进行分组
        Map<Double, List<Employee>> collect1 = employees.stream().collect(Collectors.groupingBy(employee -> employee.getSalary()));
        //System.out.println(collect1);

        Map<String, Set<Employee>> collect2 = employees.stream().collect(Collectors.groupingBy(employee -> {
            return employee.getSalary() > 10000 ? "高薪" : "底薪";
        }, Collectors.toSet()));
        System.out.println(collect2.get("高薪"));
        Map<Double, Set<Employee>> collect3 = employees.stream().collect(Collectors.groupingBy(employee -> employee.getSalary(), Collectors.toSet()));
       // System.out.println(collect3);

    }

    /**
     * 数值流
     */
    @Test
    public void test5(){
        List<Employee> employees = DataUtils.getEmployees();

        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 7);
        IntStream range = IntStream.range(1, 10);  //生成 1-10的int值
       // range.forEach(System.out::println);

        employees.stream().mapToInt(e->e.getAge()).forEach(System.out::println);


    }
}
