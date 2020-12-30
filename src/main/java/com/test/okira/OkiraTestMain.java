package com.test.okira;


import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OkiraTestMain {

    public static void main(String[] args) {
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        PlayerActor2 player1 = new PlayerActor2();
        player1.setAccount("jackie");
        player1.setPlayerId(1000016);
        player1.setCopper(222222222);
        player1.setGold(999);

        /*mapperFactory.classMap(PlayerActor2.class,Person.class).field("account","myName")
                .field("playerId","id")
                .field("copper","yinzi")
                .field("gold","yuanbao")
                .field("account","myName")
                .byDefault()
                .register();*/
        /**
         * 为什么没有成功！！！
         */
        Person person = mapperFactory.getMapperFacade().map(player1, Person.class);
        System.out.println(person.toString());
    }

    public class Person{
        private long id;

        private long playerId;

        private String account;

        private String ip;

        private long gold;

        private long copper;

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
    }
}
