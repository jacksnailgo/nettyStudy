package com.framework.activity;

public enum ActionType {
    MOVE(1),
    STAY(2),
    TALK(3),
    EAT(4),
    WALK(5),
    RUN(6),
    PAUSE(7),

    ;

    public int code;

    ActionType(int code) {
        this.code = code;
    }
}
