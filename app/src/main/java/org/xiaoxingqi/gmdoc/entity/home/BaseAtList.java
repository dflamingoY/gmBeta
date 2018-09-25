package org.xiaoxingqi.gmdoc.entity.home;

import java.io.Serializable;

/**
 * Created by yzm on 2017/12/7.
 */

public class BaseAtList implements Serializable {

    private String uid;
    private String username;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
