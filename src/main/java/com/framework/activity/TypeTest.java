package com.framework.activity;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TypeTest {
    public static void main(String[] args) {
        ActionType t1= ActionType.EAT;
        ActionType t2 =ActionType.MOVE;
        ActionType t3 = ActionType.RUN;
        ActionType t4 = ActionType.WALK;

        Map<ActionType,Integer> values = new HashMap<>();
        values.put(t1,t1.code);
        values.put(t2,t2.code);
        values.put(t3,t3.code);
        values.put(t4,t4.code);


        Map<ActionType,Integer> enumsMap = new EnumMap<>(ActionType.class);  //这个enumMap有啥用，为啥是这种写法,哦哦是为了更快的获取
        Set<ActionType> actionTypes = enumsMap.keySet();
        for(ActionType type : actionTypes){
            System.out.println(type.code);
        }

    }
}
