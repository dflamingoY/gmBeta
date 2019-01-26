package org.xiaoxingqi.gmdoc.entity.game;


import org.xiaoxingqi.gmdoc.entity.BaseSimpleData;

import java.util.List;

/**
 * Created by yzm on 2017/11/8.
 */

public class GameVideoImgData {

    private GameBean game;
    private int my_con_num;
    private int total;
    private int state;
    private List<BaseSimpleData> pic;
    private List<BaseSimpleData> video;
    private List<Object> my_con_pic;

    public GameBean getGame() {
        return game;
    }

    public void setGame(GameBean game) {
        this.game = game;
    }

    public int getMy_con_num() {
        return my_con_num;
    }

    public void setMy_con_num(int my_con_num) {
        this.my_con_num = my_con_num;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<BaseSimpleData> getPic() {
        return pic;
    }

    public void setPic(List<BaseSimpleData> pic) {
        this.pic = pic;
    }

    public List<BaseSimpleData> getVideo() {
        return video;
    }

    public void setVideo(List<BaseSimpleData> video) {
        this.video = video;
    }

    public List<Object> getMy_con_pic() {
        return my_con_pic;
    }

    public void setMy_con_pic(List<Object> my_con_pic) {
        this.my_con_pic = my_con_pic;
    }

    public static class GameBean {

        private String platform;
        private int id;
        private int platform_id;
        private String game_name;
        private String other_name;
        private String game_name_en;
        private String introduce;
        private Object introduce_en;
        private double score;
        private int version;
        private String developer;
        private String publisher;
        private String link;
        private String type;
        private Object age;
        private String sale_time;
        private String cover;
        private Object h_cover;
        private String updated_at;
        private String created_at;
        private String source;
        private Object is_hot;
        private String extra;
        private Object remarks1;
        private Object remarks2;
        private Object remarks3;
        private int state;
        private Object release;
        private String ver_name;

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPlatform_id() {
            return platform_id;
        }

        public void setPlatform_id(int platform_id) {
            this.platform_id = platform_id;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getOther_name() {
            return other_name;
        }

        public void setOther_name(String other_name) {
            this.other_name = other_name;
        }

        public String getGame_name_en() {
            return game_name_en;
        }

        public void setGame_name_en(String game_name_en) {
            this.game_name_en = game_name_en;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public Object getIntroduce_en() {
            return introduce_en;
        }

        public void setIntroduce_en(Object introduce_en) {
            this.introduce_en = introduce_en;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getDeveloper() {
            return developer;
        }

        public void setDeveloper(String developer) {
            this.developer = developer;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getSale_time() {
            return sale_time;
        }

        public void setSale_time(String sale_time) {
            this.sale_time = sale_time;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public Object getH_cover() {
            return h_cover;
        }

        public void setH_cover(Object h_cover) {
            this.h_cover = h_cover;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Object getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(Object is_hot) {
            this.is_hot = is_hot;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public Object getRemarks1() {
            return remarks1;
        }

        public void setRemarks1(Object remarks1) {
            this.remarks1 = remarks1;
        }

        public Object getRemarks2() {
            return remarks2;
        }

        public void setRemarks2(Object remarks2) {
            this.remarks2 = remarks2;
        }

        public Object getRemarks3() {
            return remarks3;
        }

        public void setRemarks3(Object remarks3) {
            this.remarks3 = remarks3;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getRelease() {
            return release;
        }

        public void setRelease(Object release) {
            this.release = release;
        }

        public String getVer_name() {
            return ver_name;
        }

        public void setVer_name(String ver_name) {
            this.ver_name = ver_name;
        }
    }
}
