package com.logic.player.dao;

import com.framework.orm.MyDaoSupport;
import org.springframework.stereotype.Component;

import com.logic.player.entity.PlayerActor;

@Component
public class PlayerDaoImpl extends MyDaoSupport<PlayerActor> implements PlayerDao {
}
