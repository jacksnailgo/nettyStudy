package com.logic.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerService implements IPlayerService{

    public void login(){
        int id = 1;
        System.out.println("login");
    }
}
