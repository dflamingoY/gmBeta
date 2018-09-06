package org.xiaoxingqi.gmdoc.entity;

/**
 * Created by yzm on 2017/11/2.
 */

public class BaseHomeBean {


    /**
     * cover : http://cdn.gmdoc.com/image/PS4riban00283.jpg
     * extra : 不兼容VR
     * game_name : 巨影都市
     * id : 103401
     * introduce : 《巨影都市》是一款特殊的生存动作游戏，本作会有破坏城市和守护城市的两种奥特曼登场。此外，游戏中可以选择主角性别，男主角的姓名也已确定为三崎健（三崎ケン），在巨影出现后他会赶往公园拯救女友。
     * sale_time : 2017年10月19日
     * score : 10
     * version : 日版
     * version_id : 6
     */

    private String cover;
    private String extra;
    private String game_name;
    private String id;
    private String introduce;
    private String sale_time;
    private float score;
    private String version;
    private int version_id;
    private String title;
    private int type;
    private int uid;
    private String avatar;
    private String name;
    private int layoutType;//布局文件的类型

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getSale_time() {
        return sale_time;
    }

    public void setSale_time(String sale_time) {
        this.sale_time = sale_time;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVersion_id() {
        return version_id;
    }

    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
