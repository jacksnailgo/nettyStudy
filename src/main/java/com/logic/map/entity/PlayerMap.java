package com.logic.map.entity;

import com.framework.async.entity.BaseEntity;
import com.framework.cache.CacheSearch;
import com.framework.dao.Dao;
import com.logic.map.entity.dao.PlayerMapDao;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Table
@Entity(name = "PlayerMap")
@Dao(sync = true,daoClass = PlayerMapDao.class)
@CacheSearch
public class PlayerMap extends BaseEntity {
    @Id
    private long id;

   private int x;

   private int y;

   private int height;

   private byte constructionType;

   private String constructionData;

   @Transient
   private Map<Integer,Long> constructionMap = new HashMap<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public byte getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(byte constructionType) {
        this.constructionType = constructionType;
    }

    public String getConstructionData() {
        return constructionData;
    }

    public void setConstructionData(String constructionData) {
        this.constructionData = constructionData;
    }

    public Map<Integer, Long> getConstructionMap() {
        return constructionMap;
    }

    public void setConstructionMap(Map<Integer, Long> constructionMap) {
        this.constructionMap = constructionMap;
    }
}
