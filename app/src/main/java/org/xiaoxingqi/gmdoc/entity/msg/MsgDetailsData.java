package org.xiaoxingqi.gmdoc.entity.msg;


import org.xiaoxingqi.gmdoc.entity.BaseRespData;

import java.util.List;

/**
 * Created by yzm on 2017/12/5.
 */

public class MsgDetailsData extends BaseRespData {


    /**
     * state : 200
     * data : {"current_page":1,"data":[{"date":"2017-12-05 10:08:15","from_uid":56,"to_uid":1,"content":"哈哈","is_pic":0,"from_name":"小爽么么哒","from_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50","to_name":"游戏档案GM","to_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg"},{"date":"2017-11-04 15:59:15","from_uid":1,"to_uid":56,"content":"反腐败","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-11-04 15:59:04","from_uid":1,"to_uid":56,"content":"方法","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-10-20 18:05:18","from_uid":56,"to_uid":1,"content":"&amp;","is_pic":0,"from_name":"小爽么么哒","from_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50","to_name":"游戏档案GM","to_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg"},{"date":"2017-10-20 18:04:52","from_uid":56,"to_uid":1,"content":"&amp;","is_pic":0,"from_name":"小爽么么哒","from_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50","to_name":"游戏档案GM","to_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg"},{"date":"2017-10-20 18:04:25","from_uid":1,"to_uid":56,"content":"&amp;","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-10-20 18:03:54","from_uid":1,"to_uid":56,"content":"!@#$%^&amp;*()","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-10-13 16:28:16","from_uid":1,"to_uid":56,"content":"士大夫撒地方","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-10-13 16:28:11","from_uid":1,"to_uid":56,"content":"士大夫撒发","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-10-13 16:28:01","from_uid":1,"to_uid":56,"content":"树大根深对方感受到","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"}],"first_page_url":"http://api.gmdoc.com/get_msg?page=1","from":1,"last_page":3,"last_page_url":"http://api.gmdoc.com/get_msg?page=3","next_page_url":"http://api.gmdoc.com/get_msg?page=2","path":"http://api.gmdoc.com/get_msg","per_page":10,"prev_page_url":null,"to":10,"total":21}
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
         * data : [{"date":"2017-12-05 10:08:15","from_uid":56,"to_uid":1,"content":"哈哈","is_pic":0,"from_name":"小爽么么哒","from_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50","to_name":"游戏档案GM","to_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg"},{"date":"2017-11-04 15:59:15","from_uid":1,"to_uid":56,"content":"反腐败","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-11-04 15:59:04","from_uid":1,"to_uid":56,"content":"方法","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-10-20 18:05:18","from_uid":56,"to_uid":1,"content":"&amp;","is_pic":0,"from_name":"小爽么么哒","from_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50","to_name":"游戏档案GM","to_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg"},{"date":"2017-10-20 18:04:52","from_uid":56,"to_uid":1,"content":"&amp;","is_pic":0,"from_name":"小爽么么哒","from_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50","to_name":"游戏档案GM","to_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg"},{"date":"2017-10-20 18:04:25","from_uid":1,"to_uid":56,"content":"&amp;","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-10-20 18:03:54","from_uid":1,"to_uid":56,"content":"!@#$%^&amp;*()","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-10-13 16:28:16","from_uid":1,"to_uid":56,"content":"士大夫撒地方","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-10-13 16:28:11","from_uid":1,"to_uid":56,"content":"士大夫撒发","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"},{"date":"2017-10-13 16:28:01","from_uid":1,"to_uid":56,"content":"树大根深对方感受到","is_pic":0,"from_name":"游戏档案GM","from_avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","to_name":"小爽么么哒","to_avatar":"http://image.gmdoc.com/AVATAR_151065490184682.jpg?imageView2/1/w/140/q/50"}]
         * first_page_url : http://api.gmdoc.com/get_msg?page=1
         * from : 1
         * last_page : 3
         * last_page_url : http://api.gmdoc.com/get_msg?page=3
         * next_page_url : http://api.gmdoc.com/get_msg?page=2
         * path : http://api.gmdoc.com/get_msg
         * per_page : 10
         * prev_page_url : null
         * to : 10
         * total : 21
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private String next_page_url;
        private String path;
        private int per_page;
        private Object prev_page_url;
        private int to;
        private int total;
        private List<BaseMsgDetailsBean> data;

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

        public Object getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(Object prev_page_url) {
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

        public List<BaseMsgDetailsBean> getData() {
            return data;
        }

        public void setData(List<BaseMsgDetailsBean> data) {
            this.data = data;
        }
    }
}
