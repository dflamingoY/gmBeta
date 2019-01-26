package org.xiaoxingqi.gmdoc.entity.msg;

import java.io.Serializable;

/**
 * Created by yzm on 2017/12/5.
 */

public class BaseMsgBean implements Serializable {
    private String content;
    private String created_at;
    private int is_pic;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getIs_pic() {
        return is_pic;
    }

    public void setIs_pic(int is_pic) {
        this.is_pic = is_pic;
    }
}
