package org.xiaoxingqi.gmdoc.entity.msg;

/**
 * Created by yzm on 2017/12/5.
 */

public class BaseMsgDetailsBean {


    /**
     * date : 2017-12-05 10:08:15
     * from_uid : 56
     * to_uid : 1
     * content : 哈哈
     * is_pic : 0
     * from_name : 小爽么么哒
     * from_avatar : http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50
     * to_name : 游戏档案GM
     * to_avatar : http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg
     */

    private String date;
    private String from_uid;
    private String to_uid;
    private String content;
    private int is_pic;
    private String from_name;
    private String from_avatar;
    private String to_name;
    private String to_avatar;
    private String dt_id;

    public String getDt_id() {
        return dt_id;
    }

    public void setDt_id(String dt_id) {
        this.dt_id = dt_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(String from_uid) {
        this.from_uid = from_uid;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIs_pic() {
        return is_pic;
    }

    public void setIs_pic(int is_pic) {
        this.is_pic = is_pic;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getFrom_avatar() {
        return from_avatar;
    }

    public void setFrom_avatar(String from_avatar) {
        this.from_avatar = from_avatar;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getTo_avatar() {
        return to_avatar;
    }

    public void setTo_avatar(String to_avatar) {
        this.to_avatar = to_avatar;
    }
}
