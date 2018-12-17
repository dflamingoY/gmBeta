package org.xiaoxingqi.gmdoc.entity.user;


import org.xiaoxingqi.gmdoc.entity.BaseRespData;

/**
 * Created by yzm on 2017/11/7.
 */

public class UserInfoData extends BaseRespData {


    /**
     * state : 200
     * data : {"username":"大王","top_image":null,"sex":1,"avatar":"/images/avatar_default.png","desc":null,"id":null,"uid":109,"etm_num":null,"com_num":null,"like_num":null,"msg_num":null,"fans_num":null,"follow_num":null,"dt_num":null,"blog_num":null,"short_num":null,"long_num":null,"photo_num":null,"blacklist_num":null,"wish_num":null,"collection_num":null,"contribution_num":null,"invite_num":null,"updated_at":null,"like_game":"你猜,我擦,他猜","is_sub":0,"is_black":0,"gold_num":0,"silver_num":0,"bronze_num":0,"role":2,"is_application":null,"application_time":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : 大王
         * top_image : null
         * sex : 1
         * avatar : /images/avatar_default.png
         * desc : null
         * id : null
         * uid : 109
         * etm_num : null
         * com_num : null
         * like_num : null
         * msg_num : null
         * fans_num : null
         * follow_num : null
         * dt_num : null
         * blog_num : null
         * short_num : null
         * long_num : null
         * photo_num : null
         * blacklist_num : null
         * wish_num : null
         * collection_num : null
         * contribution_num : null
         * invite_num : null
         * updated_at : null
         * like_game : 你猜,我擦,他猜
         * is_sub : 0
         * is_black : 0
         * gold_num : 0
         * silver_num : 0
         * bronze_num : 0
         * role : 2
         * is_application : null
         * application_time : null
         */
        private String email;
        private String username;
        private String top_image;
        private String sex;//1 女
        private String avatar;
        private String desc;
        private String id;
        private String uid;
        private String etm_num;
        private String com_num;
        private String like_num;
        private String msg_num;
        private String fans_num;
        private String follow_num;
        private String dt_num;
        private String blog_num;
        private String short_num;
        private String long_num;
        private String photo_num;
        private String blacklist_num;
        private String wish_num;
        private String collection_num;
        private String contribution_num;
        private String invite_num;
        private String updated_at;
        private String like_game;
        private int is_sub;//0 没有关系 1已经关注 2互相关注
        private int is_black;// 0  1
        private String gold_num;
        private String silver_num;
        private String bronze_num;
        private String role;//正式用户  1 正式 2 注册
        private String is_application;
        private String application_time;
        private String phone;
        private int has_qq;//1 已绑定 0 未绑定
        private int has_wx;//
        private String playing_num;
        private int jutou;//0 是开 1是关
        private int fans_switch;//全局1开启0不开启 默认0  是否打开隐藏粉丝数
        private String waiting_num;//待评分列表数量

        public String getWaiting_num() {
            return waiting_num;
        }

        public void setWaiting_num(String waiting_num) {
            this.waiting_num = waiting_num;
        }

        public int getFans_switch() {
            return fans_switch;
        }

        public void setFans_switch(int fans_switch) {
            this.fans_switch = fans_switch;
        }

        public int getJutou() {
            return jutou;
        }

        public void setJutou(int jutou) {
            this.jutou = jutou;
        }

        public String getPlaying_num() {
            return playing_num;
        }

        public void setPlaying_num(String playing_num) {
            this.playing_num = playing_num;
        }

        public int getHas_qq() {
            return has_qq;
        }

        public void setHas_qq(int has_qq) {
            this.has_qq = has_qq;
        }

        public int getHas_wx() {
            return has_wx;
        }

        public void setHas_wx(int has_wx) {
            this.has_wx = has_wx;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTop_image() {
            return top_image;
        }

        public void setTop_image(String top_image) {
            this.top_image = top_image;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getEtm_num() {
            return etm_num;
        }

        public void setEtm_num(String etm_num) {
            this.etm_num = etm_num;
        }

        public String getCom_num() {
            return com_num;
        }

        public void setCom_num(String com_num) {
            this.com_num = com_num;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getMsg_num() {
            return msg_num;
        }

        public void setMsg_num(String msg_num) {
            this.msg_num = msg_num;
        }

        public String getFans_num() {
            return fans_num;
        }

        public void setFans_num(String fans_num) {
            this.fans_num = fans_num;
        }

        public String getFollow_num() {
            return follow_num;
        }

        public void setFollow_num(String follow_num) {
            this.follow_num = follow_num;
        }

        public String getDt_num() {
            return dt_num;
        }

        public void setDt_num(String dt_num) {
            this.dt_num = dt_num;
        }

        public String getBlog_num() {
            return blog_num;
        }

        public void setBlog_num(String blog_num) {
            this.blog_num = blog_num;
        }

        public String getShort_num() {
            return short_num;
        }

        public void setShort_num(String short_num) {
            this.short_num = short_num;
        }

        public String getLong_num() {
            return long_num;
        }

        public void setLong_num(String long_num) {
            this.long_num = long_num;
        }

        public String getPhoto_num() {
            return photo_num;
        }

        public void setPhoto_num(String photo_num) {
            this.photo_num = photo_num;
        }

        public String getBlacklist_num() {
            return blacklist_num;
        }

        public void setBlacklist_num(String blacklist_num) {
            this.blacklist_num = blacklist_num;
        }

        public String getWish_num() {
            return wish_num;
        }

        public void setWish_num(String wish_num) {
            this.wish_num = wish_num;
        }

        public String getCollection_num() {
            return collection_num;
        }

        public void setCollection_num(String collection_num) {
            this.collection_num = collection_num;
        }

        public String getContribution_num() {
            return contribution_num;
        }

        public void setContribution_num(String contribution_num) {
            this.contribution_num = contribution_num;
        }

        public String getInvite_num() {
            return invite_num;
        }

        public void setInvite_num(String invite_num) {
            this.invite_num = invite_num;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getLike_game() {
            return like_game;
        }

        public void setLike_game(String like_game) {
            this.like_game = like_game;
        }

        public int getIs_sub() {
            return is_sub;
        }

        public void setIs_sub(int is_sub) {
            this.is_sub = is_sub;
        }

        public int getIs_black() {
            return is_black;
        }

        public void setIs_black(int is_black) {
            this.is_black = is_black;
        }

        public String getGold_num() {
            return gold_num;
        }

        public void setGold_num(String gold_num) {
            this.gold_num = gold_num;
        }

        public String getSilver_num() {
            return silver_num;
        }

        public void setSilver_num(String silver_num) {
            this.silver_num = silver_num;
        }

        public String getBronze_num() {
            return bronze_num;
        }

        public void setBronze_num(String bronze_num) {
            this.bronze_num = bronze_num;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getIs_application() {
            return is_application;
        }

        public void setIs_application(String is_application) {
            this.is_application = is_application;
        }

        public String getApplication_time() {
            return application_time;
        }

        public void setApplication_time(String application_time) {
            this.application_time = application_time;
        }
    }
}
