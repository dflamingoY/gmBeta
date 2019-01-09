package org.xiaoxingqi.gmdoc.entity;

import org.xiaoxingqi.gmdoc.entity.home.BaseAtList;

import java.util.List;

/**
 * Created by yzm on 2017/11/16.
 */

public class CommentData extends BaseRespData {


    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private String path;
        private int per_page;
        private int to;
        private int total;
        private List<CommentDataBean> data;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public String getFirst_page_url() {
            return first_page_url;
        }

        public void setFirst_page_url(String first_page_url) {
            this.first_page_url = first_page_url;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public String getLast_page_url() {
            return last_page_url;
        }

        public void setLast_page_url(String last_page_url) {
            this.last_page_url = last_page_url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<CommentDataBean> getData() {
            return data;
        }

        public void setData(List<CommentDataBean> data) {
            this.data = data;
        }

    }

    public static class CommentDataBean {

        private String avatar;
        private int cid;
        private String content;
        private String creat_time;
        private int env_id;
        private int id;
        private int is_like;
        private int is_my;
        private int like_num;
        private int like_status;
        private int type;
        private int uid;
        private int user_id;
        private String username;
        private String like_game;
        private String from_id;
        private String from_img;
        private String from_name;
        private int from_user;
        private List<BaseAtList> ate_list;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreat_time() {
            return creat_time;
        }

        public void setCreat_time(String creat_time) {
            this.creat_time = creat_time;
        }

        public int getEnv_id() {
            return env_id;
        }

        public void setEnv_id(int env_id) {
            this.env_id = env_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }

        public int getIs_my() {
            return is_my;
        }

        public void setIs_my(int is_my) {
            this.is_my = is_my;
        }

        public int getLike_num() {
            return like_num;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public int getLike_status() {
            return like_status;
        }

        public void setLike_status(int like_status) {
            this.like_status = like_status;
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

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLike_game() {
            return like_game;
        }

        public void setLike_game(String like_game) {
            this.like_game = like_game;
        }

        public String getFrom_id() {
            return from_id;
        }

        public void setFrom_id(String from_id) {
            this.from_id = from_id;
        }

        public String getFrom_img() {
            return from_img;
        }

        public void setFrom_img(String from_img) {
            this.from_img = from_img;
        }

        public String getFrom_name() {
            return from_name;
        }

        public void setFrom_name(String from_name) {
            this.from_name = from_name;
        }

        public int getFrom_user() {
            return from_user;
        }

        public void setFrom_user(int from_user) {
            this.from_user = from_user;
        }

        public List<BaseAtList> getAte_list() {
            return ate_list;
        }

        public void setAte_list(List<BaseAtList> ate_list) {
            this.ate_list = ate_list;
        }
    }

}
