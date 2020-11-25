package com.jackie.logic.game;

import com.jackie.route.Address;
import com.jackie.route.AddressHandler;
import org.springframework.stereotype.Component;

@AddressHandler
public class GameService {

    @Address(address = "/hello")
    public void sendMessage(){
        System.out.println("hello to Server");
        //给客户端发送消息
    }
}
