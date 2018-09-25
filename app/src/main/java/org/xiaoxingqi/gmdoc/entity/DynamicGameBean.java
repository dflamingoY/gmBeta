package org.xiaoxingqi.gmdoc.entity;

import java.io.Serializable;

/**
 * Created by yzm on 2017/11/13.
 * <p>
 * 动态的游戏实体
 */

public class DynamicGameBean implements Serializable {
    private int game_id;
    private String game_type;
    private String game_name;
    private String game_cover;
    private int score;
    private String game_pla;
    private String game_ver;
    private String good;
    private String bad;

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_cover() {
        return game_cover;
    }

    public void setGame_cover(String game_cover) {
        this.game_cover = game_cover;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGame_pla() {
        return game_pla;
    }

    public void setGame_pla(String game_pla) {
        this.game_pla = game_pla;
    }

    public String getGame_ver() {
        return game_ver;
    }

    public void setGame_ver(String game_ver) {
        this.game_ver = game_ver;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

}
