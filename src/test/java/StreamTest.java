import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.logic.player.entity.PlayerActor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
}
