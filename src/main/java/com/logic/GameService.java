package com.logic;


import com.framework.route.Address;
import com.framework.route.AddressHandler;

@AddressHandler
public class GameService {

    @Address(address = "/hello")
    public void sendMessage(){
        System.out.println("hello to Server");
        //给客户端发送消息
    }
}
