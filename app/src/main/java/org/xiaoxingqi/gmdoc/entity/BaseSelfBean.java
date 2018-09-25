package org.xiaoxingqi.gmdoc.entity;

import java.io.Serializable;

/**
 * Created by yzm on 2017/11/13.
 * 基类
 */

public class BaseSelfBean implements Serializable {
    private String title;
    private int uid;
    private String avatar;
    private int is_sub;
    private String username;
    private String content;
    private String like_game;
    private String id;
    private String created_at;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIs_sub() {
        return is_sub;
    }

    public void setIs_sub(int is_sub) {
        this.is_sub = is_sub;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLike_game() {
        return like_game;
    }

    public void setLike_game(String like_game) {
        this.like_game = like_game;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
