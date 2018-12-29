package org.xiaoxingqi.gmdoc.entity.login;

import org.xiaoxingqi.gmdoc.entity.BaseRespData;

/**
 * Created by yzm on 2017/12/27.
 * 登录检测信息临时使用
 */

public class LoginUserData extends BaseRespData {


    /**
     * state : 200
     * user : {"id":108,"name":"大王游泳","email":"ym199210@163.com","phone":13662693562,"sex":2,"img":"http://cdn.gmdoc.com/1514196704145.png","updated_at":"2017-12-27 18:27:55","created_at":"2017-11-06 18:08:20","like_game":null,"desc":null,"motto":null,"user_num":0,"domain":null,"constellation":null,"qq_token":"","qq_openid":"","refresh_token":"","extime_in":0,"wx_token":"5_-5CWaSZlRNujxIufvCUuhjC_KXBdDmcenwmJQsM5lfb_AE1x0o3MD85o3BBj42S4Z04kA63CfTcPukN8IcT3_g","wx_openid":"o6F--1euUkwpyR0JV_A_xp-Njr-8","wx_ref_token":"5_AVZBfeSzsIOJJk2VVLZ9aoRZ6SE2asyYNsagcHr90hQfqCoXj4k7wCNXoPN_Hh-PwCi0KxS6P67AXdRZjrqzGw","wx_extime_in":2147483647,"wx_unid":"oGEegwGlBrHV7bShRmylXVaDiAVQ","wx_pc_openid":"","wx_public_openid":"","role":2,"top_image":"http://cdn.gmdoc.com/1513237632306.png","is_recommend":0,"money":"0.00","idcard":"511***7","real_name":"杨志明","wx_name":"流水叮哝","wx_img":"http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqibeJVJgMqzPRzDzSgEHlvW2xLQGgvjibibTRbIn3I2I2yj00l3RvE3pbl9Vibwr9EEBNDsaZWnX2gMg/0","ios_registration_id":null,"api_token":"C1u2VcO6BIb5JpZikTH69dHSIkNQjnF2XBk4WQnPDjLRaGm5QeUd3G4cFLfI"}
     */

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 108
         * name : 大王游泳
         * email : ym199210@163.com
         * phone : 13662693562
         * sex : 2
         * img : http://cdn.gmdoc.com/1514196704145.png
         * updated_at : 2017-12-27 18:27:55
         * created_at : 2017-11-06 18:08:20
         * like_game : null
         * desc : null
         * motto : null
         * user_num : 0
         * domain : null
         * constellation : null
         * qq_token :
         * qq_openid :
         * refresh_token :
         * extime_in : 0
         * wx_token : 5_-5CWaSZlRNujxIufvCUuhjC_KXBdDmcenwmJQsM5lfb_AE1x0o3MD85o3BBj42S4Z04kA63CfTcPukN8IcT3_g
         * wx_openid : o6F--1euUkwpyR0JV_A_xp-Njr-8
         * wx_ref_token : 5_AVZBfeSzsIOJJk2VVLZ9aoRZ6SE2asyYNsagcHr90hQfqCoXj4k7wCNXoPN_Hh-PwCi0KxS6P67AXdRZjrqzGw
         * wx_extime_in : 2147483647
         * wx_unid : oGEegwGlBrHV7bShRmylXVaDiAVQ
         * wx_pc_openid :
         * wx_public_openid :
         * role : 2
         * top_image : http://cdn.gmdoc.com/1513237632306.png
         * is_recommend : 0
         * money : 0.00
         * idcard : 511***7
         * real_name : 杨志明
         * wx_name : 流水叮哝
         * wx_img : http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqibeJVJgMqzPRzDzSgEHlvW2xLQGgvjibibTRbIn3I2I2yj00l3RvE3pbl9Vibwr9EEBNDsaZWnX2gMg/0
         * ios_registration_id : null
         * api_token : C1u2VcO6BIb5JpZikTH69dHSIkNQjnF2XBk4WQnPDjLRaGm5QeUd3G4cFLfI
         */

        private int id;
        private String name;
        private String email;
        private long phone;
        private int sex;
        private String img;
        private String updated_at;
        private String created_at;
        private String like_game;
        private String desc;
        private String motto;
        private int user_num;
        private String domain;
        private String constellation;
        private String qq_token;
        private String qq_openid;
        private String refresh_token;
        private int extime_in;
        private String wx_token;
        private String wx_openid;
        private String wx_ref_token;
        private int wx_extime_in;
        private String wx_unid;
        private String wx_pc_openid;
        private String wx_public_openid;
        private int role;
        private String top_image;
        private int is_recommend;
        private String money;
        private String idcard;
        private String real_name;
        private String wx_name;
        private String wx_img;
        private Object ios_registration_id;
        private String api_token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public long getPhone() {
            return phone;
        }

        public void setPhone(long phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public String getLike_game() {
            return like_game;
        }

        public void setLike_game(String like_game) {
            this.like_game = like_game;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getMotto() {
            return motto;
        }

        public void setMotto(String motto) {
            this.motto = motto;
        }

        public int getUser_num() {
            return user_num;
        }

        public void setUser_num(int user_num) {
            this.user_num = user_num;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getQq_token() {
            return qq_token;
        }

        public void setQq_token(String qq_token) {
            this.qq_token = qq_token;
        }

        public String getQq_openid() {
            return qq_openid;
        }

        public void setQq_openid(String qq_openid) {
            this.qq_openid = qq_openid;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public int getExtime_in() {
            return extime_in;
        }

        public void setExtime_in(int extime_in) {
            this.extime_in = extime_in;
        }

        public String getWx_token() {
            return wx_token;
        }

        public void setWx_token(String wx_token) {
            this.wx_token = wx_token;
        }

        public String getWx_openid() {
            return wx_openid;
        }

        public void setWx_openid(String wx_openid) {
            this.wx_openid = wx_openid;
        }

        public String getWx_ref_token() {
            return wx_ref_token;
        }

        public void setWx_ref_token(String wx_ref_token) {
            this.wx_ref_token = wx_ref_token;
        }

        public int getWx_extime_in() {
            return wx_extime_in;
        }

        public void setWx_extime_in(int wx_extime_in) {
            this.wx_extime_in = wx_extime_in;
        }

        public String getWx_unid() {
            return wx_unid;
        }

        public void setWx_unid(String wx_unid) {
            this.wx_unid = wx_unid;
        }

        public String getWx_pc_openid() {
            return wx_pc_openid;
        }

        public void setWx_pc_openid(String wx_pc_openid) {
            this.wx_pc_openid = wx_pc_openid;
        }

        public String getWx_public_openid() {
            return wx_public_openid;
        }

        public void setWx_public_openid(String wx_public_openid) {
            this.wx_public_openid = wx_public_openid;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public String getTop_image() {
            return top_image;
        }

        public void setTop_image(String top_image) {
            this.top_image = top_image;
        }

        public int getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(int is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getWx_name() {
            return wx_name;
        }

        public void setWx_name(String wx_name) {
            this.wx_name = wx_name;
        }

        public String getWx_img() {
            return wx_img;
        }

        public void setWx_img(String wx_img) {
            this.wx_img = wx_img;
        }

        public Object getIos_registration_id() {
            return ios_registration_id;
        }

        public void setIos_registration_id(Object ios_registration_id) {
            this.ios_registration_id = ios_registration_id;
        }

        public String getApi_token() {
            return api_token;
        }

        public void setApi_token(String api_token) {
            this.api_token = api_token;
        }
    }
}
