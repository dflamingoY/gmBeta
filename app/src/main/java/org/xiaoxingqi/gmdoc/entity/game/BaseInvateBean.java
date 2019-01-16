package org.xiaoxingqi.gmdoc.entity.game;

import java.io.Serializable;

/**
 * Created by yzm on 2017/12/8.
 */

public class BaseInvateBean implements Serializable {

    /**
     * game_name : 来自地狱的奔跑者
     * platform_id : XBOX ONE
     * version : 美版
     * game_id : 109081
     * name : 大王
     * created_at : 2017-12-08 12:30:22
     * uid : 109
     * to_uid : 108
     * state : 1
     */

    private String game_name;
    private String platform_id;
    private String version;
    private int game_id;
    private String name;
    private String created_at;
    private int uid;
    private int to_uid;
    private int state;//收到 2 以评分  发出  3 拒绝 1 邀请已发出
    private int dt_id;
    private int id;
    private String desc;
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getDt_id() {
        return dt_id;
    }

    public void setDt_id(int dt_id) {
        this.dt_id = dt_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(String platform_id) {
        this.platform_id = platform_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(int to_uid) {
        this.to_uid = to_uid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
