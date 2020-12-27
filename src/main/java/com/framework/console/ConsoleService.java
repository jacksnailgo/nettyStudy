package com.framework.console;

import com.framework.async.util.AsyncDBState;
import com.framework.async.util.DBOperation;
import com.framework.context.Context;
import com.google.common.collect.Maps;
import com.logic.player.dao.PlayerDao;
import com.logic.player.entity.PlayerActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConsoleService implements IConsoleService{

    @Console(method = "test",desc = "测试方法1")
    public void test(){
        System.out.println("hello World");
        //测试一下入库的操作
        PlayerActor playerActor = new PlayerActor();
        playerActor.setPlayerId(1005);
        playerActor.setAccount("jackie");
        playerActor.setCopper(101);
        playerActor.insert();
        /*if(DBOperation.INSERT.canOperation(AsyncDBState.INSERT)){
            System.out.println(true);
        }*/
    }
    @Console(method = "test2",desc = "测试方法2")
    public void test2(){
        PlayerDao beans = Context.getBeans(PlayerDao.class);
        PlayerActor playerActor = beans.getBy(1L);
        System.out.println(playerActor);

    }

    @Console(method = "test3",desc = "测试方法3")
    public void test3(){

    }

}
