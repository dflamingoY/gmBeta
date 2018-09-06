package org.xiaoxingqi.gmdoc.entity.home;

import org.xiaoxingqi.gmdoc.entity.ActiveBean;
import org.xiaoxingqi.gmdoc.entity.BaseRespData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yzm on 2018/1/3.
 */

public class HomeActiveData extends BaseRespData {


    /**
     * state : 200
     * data : {"current_page":1,"data":[{"id":1,"activity_name":"异度之刃2","activity_pic":"http://cdn.gmdoc.com//Dv3katEB_400x400.jpg","activity_desc":"包含#异度之刃2#和#异度神剑2#的动态和博文会在这里显示，欢迎与大家分享你的乐园见闻，关键剧情记得打剧透哦~"},{"id":2,"activity_name":"资讯分享","activity_pic":"http://cdn.gmdoc.com//DQHoCnDWkAI6ZGT.jpg","activity_desc":"包含#资讯#的动态和博文会在这里显示，欢迎与大家分享你看到的游戏资讯~"},{"id":5,"activity_name":"音乐","activity_pic":"http://cdn.gmdoc.com//img_bg_activity_question@2x.png","activity_desc":"包含#音乐#的动态和博文会在这里显示，欢迎与大家分享你正在听的游戏音乐~"},{"id":6,"activity_name":"趣图","activity_pic":"http://cdn.gmdoc.com//Frxe8StUip3pcUnJPgO9ypMz-VFo.jpg","activity_desc":"包含#趣图#的动态和博文会在这里显示，欢迎与大家分享你看到的欢乐图片和动图，不限于游戏领域~"},{"id":3,"activity_name":"提问","activity_pic":"http://cdn.gmdoc.com//Bloodborne-7.jpg","activity_desc":"包含#提问#的动态和博文会在这里显示，可以在这里向游戏档案的玩家们提问~"},{"id":7,"activity_name":"新人报道","activity_pic":"http://cdn.gmdoc.com//025_ymjd.jpg","activity_desc":"包含#新人报道#的动态和博文会在这里显示，欢迎刚刚注册游戏档案的玩家向大家介绍自己的游戏偏好和兴趣~"}],"first_page_url":"http://dev-api.gmdoc.com/activity_list?page=1","from":1,"last_page":1,"last_page_url":"http://dev-api.gmdoc.com/activity_list?page=1","next_page_url":null,"path":"http://dev-api.gmdoc.com/activity_list","per_page":15,"prev_page_url":null,"to":6,"total":6}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * current_page : 1
         * data : [{"id":1,"activity_name":"异度之刃2","activity_pic":"http://cdn.gmdoc.com//Dv3katEB_400x400.jpg","activity_desc":"包含#异度之刃2#和#异度神剑2#的动态和博文会在这里显示，欢迎与大家分享你的乐园见闻，关键剧情记得打剧透哦~"},{"id":2,"activity_name":"资讯分享","activity_pic":"http://cdn.gmdoc.com//DQHoCnDWkAI6ZGT.jpg","activity_desc":"包含#资讯#的动态和博文会在这里显示，欢迎与大家分享你看到的游戏资讯~"},{"id":5,"activity_name":"音乐","activity_pic":"http://cdn.gmdoc.com//img_bg_activity_question@2x.png","activity_desc":"包含#音乐#的动态和博文会在这里显示，欢迎与大家分享你正在听的游戏音乐~"},{"id":6,"activity_name":"趣图","activity_pic":"http://cdn.gmdoc.com//Frxe8StUip3pcUnJPgO9ypMz-VFo.jpg","activity_desc":"包含#趣图#的动态和博文会在这里显示，欢迎与大家分享你看到的欢乐图片和动图，不限于游戏领域~"},{"id":3,"activity_name":"提问","activity_pic":"http://cdn.gmdoc.com//Bloodborne-7.jpg","activity_desc":"包含#提问#的动态和博文会在这里显示，可以在这里向游戏档案的玩家们提问~"},{"id":7,"activity_name":"新人报道","activity_pic":"http://cdn.gmdoc.com//025_ymjd.jpg","activity_desc":"包含#新人报道#的动态和博文会在这里显示，欢迎刚刚注册游戏档案的玩家向大家介绍自己的游戏偏好和兴趣~"}]
         * first_page_url : http://dev-api.gmdoc.com/activity_list?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://dev-api.gmdoc.com/activity_list?page=1
         * next_page_url : null
         * path : http://dev-api.gmdoc.com/activity_list
         * per_page : 15
         * prev_page_url : null
         * to : 6
         * total : 6
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private String next_page_url;
        private String path;
        private int per_page;
        private String prev_page_url;
        private int to;
        private int total;
        private List<ActiveBean> data;

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

        public String getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(String next_page_url) {
            this.next_page_url = next_page_url;
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

        public String getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(String prev_page_url) {
            this.prev_page_url = prev_page_url;
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

        public List<ActiveBean> getData() {
            return data;
        }

        public void setData(List<ActiveBean> data) {
            this.data = data;
        }
    }


}
