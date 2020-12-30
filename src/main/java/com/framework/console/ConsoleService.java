package com.framework.console;

import com.framework.async.util.AsyncDBState;
import com.framework.async.util.DBOperation;
import com.framework.context.Context;
import com.google.common.collect.Maps;
import com.logic.player.dao.PlayerDao;
import com.logic.player.entity.PlayerActor;
import javassist.CtClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConsoleService implements IConsoleService{

    @Console(method = "test",desc = "测试方法1")
    public void test(){

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
