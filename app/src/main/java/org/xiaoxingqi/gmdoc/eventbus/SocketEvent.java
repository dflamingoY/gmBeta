package org.xiaoxingqi.gmdoc.eventbus;

public class SocketEvent {
    private String msg;

    public SocketEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
