package com.jackie.transfer;

public abstract class Transfer {
    protected short commandId;

    protected long createTime = System.currentTimeMillis();

    public short getCommandId() {
        return commandId;
    }

    public void setCommandId(short commandId) {
        this.commandId = commandId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
