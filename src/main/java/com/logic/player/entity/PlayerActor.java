package com.logic.player.entity;

import com.framework.async.entity.BaseEntity;
import com.framework.cache.CacheSearch;
import com.framework.dao.Dao;
import com.logic.player.dao.PlayerDao;

import javax.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity(name = "PlayerActor")
@Table
@Dao(daoClass = PlayerDao.class)
@CacheSearch
public class PlayerActor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private long playerId;

    private String account;

    private String ip;

    private long gold;

    private long copper;

    @Column(nullable = true)
    private String goodsMapStr;

    @Transient
    private Map<Integer,Integer> goodsMap = new HashMap<>();

    public PlayerActor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getCopper() {
        return copper;
    }

    public void setCopper(long copper) {
        this.copper = copper;
    }

    public String getGoodsMapStr() {
        return goodsMapStr;
    }

    public void setGoodsMapStr(String goodsMapStr) {
        this.goodsMapStr = goodsMapStr;
    }

    public Map<Integer, Integer> getGoodsMap() {
        return goodsMap;
    }

    public void setGoodsMap(Map<Integer, Integer> goodsMap) {
        this.goodsMap = goodsMap;
    }

    @Override
    public String toString() {
        return "PlayerActor{" +
                "playerId=" + playerId +
                ", accouont='" + account + '\'' +
                ", ip='" + ip + '\'' +
                ", gold=" + gold +
                ", copper=" + copper +
                ", goodsMapStr='" + goodsMapStr + '\'' +
                '}';
    }

}
