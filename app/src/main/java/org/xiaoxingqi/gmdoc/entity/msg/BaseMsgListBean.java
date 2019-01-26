package org.xiaoxingqi.gmdoc.entity.msg;

import java.io.Serializable;

/**
 * Created by yzm on 2017/12/5.
 */

public class BaseMsgListBean implements Serializable {

    private int id;
    private String name;
    private String img;
    private String motto;
    private int unread;//未读提示
    private String created_at;
    private BaseMsgBean msg;
    private int type = 1;//正常消息  2 系统消息

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public BaseMsgBean getMsg() {
        return msg;
    }

    public void setMsg(BaseMsgBean msg) {
        this.msg = msg;
    }
}
