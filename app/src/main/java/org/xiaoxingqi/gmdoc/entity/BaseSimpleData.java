package org.xiaoxingqi.gmdoc.entity;

import java.io.Serializable;

/**
 * Created by yzm on 2017/11/6.
 */

public class BaseSimpleData extends BaseTitleData implements Serializable {
    private String url;
    private String pic;
    private String name;
    private String uid;
    private String title;
    private String id;
    private String label;
    private String avatar;
    private String desc;
    private int type;
    private String like_game;
    private int fans_num;
    private int follow_num;
    private int dt_num;
    private int blog_num;
    private int short_num;
    private int long_num;
    private int is_sub;//0 没有关系 1已经关注 2互相关注
    private String username;
    private String created_at;
    private String game_id;
    private int tem;
    private int user;
    private String date;
    private long time;
    private String platform;
    private String game_name;
    private String version;
    private String motto;
    private int is_black;
    private boolean selected;


    public int getIs_black() {
        return is_black;
    }

    public void setIs_black(int is_black) {
        this.is_black = is_black;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public BaseSimpleData() {

    }

    public BaseSimpleData(boolean isSelected, String horizontalTitle) {
        this.isSelected = isSelected;
        this.horizontalTitle = horizontalTitle;
    }

    public BaseSimpleData(boolean isSelected, String horizontalTitle, String game_id) {
        this.isSelected = isSelected;
        this.horizontalTitle = horizontalTitle;
        this.game_id = game_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getTem() {
        return tem;
    }

    public void setTem(int tem) {
        this.tem = tem;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLike_game() {
        return like_game;
    }

    public void setLike_game(String like_game) {
        this.like_game = like_game;
    }

    public int getFans_num() {
        return fans_num;
    }

    public void setFans_num(int fans_num) {
        this.fans_num = fans_num;
    }

    public int getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(int follow_num) {
        this.follow_num = follow_num;
    }

    public int getDt_num() {
        return dt_num;
    }

    public void setDt_num(int dt_num) {
        this.dt_num = dt_num;
    }

    public int getBlog_num() {
        return blog_num;
    }

    public void setBlog_num(int blog_num) {
        this.blog_num = blog_num;
    }

    public int getShort_num() {
        return short_num;
    }

    public void setShort_num(int short_num) {
        this.short_num = short_num;
    }

    public int getLong_num() {
        return long_num;
    }

    public void setLong_num(int long_num) {
        this.long_num = long_num;
    }

    public int getIs_sub() {
        return is_sub;
    }

    public void setIs_sub(int is_sub) {
        this.is_sub = is_sub;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
