package org.xiaoxingqi.gmdoc.entity;

import java.util.List;

/**
 * Created by yzm on 2017/11/18.
 * <p>
 * 粉丝
 */

public class RespFansData extends BaseRespData {


    /**
     * state : 200
     * data : {"current_page":1,"data":[{"uid":35,"type":1,"username":"纱雾","avatar":"http://image.gmdoc.com/FuFiUiUrDbhvRHVqtiRuCaF1WBYr","like_game":"高达","desc":"哈哈哈哈，喔尼酱阿斯顿啊啊啊啊啊啊啊啊a","fans_num":11,"follow_num":3,"dt_num":31,"blog_num":12,"short_num":14,"long_num":8,"is_sub":0},{"uid":40,"type":3,"username":"戏子","avatar":"http://image.gmdoc.com/GMDOC_2298822333577064.jpg","like_game":"实况足球","desc":"哈哈","fans_num":8,"follow_num":7,"dt_num":74,"blog_num":11,"short_num":22,"long_num":6,"is_sub":0}],"first_page_url":"http://api.gmdoc.com/fans/108?page=1","from":1,"last_page":1,"last_page_url":"http://api.gmdoc.com/fans/108?page=1","next_page_url":null,"path":"http://api.gmdoc.com/fans/108","per_page":15,"prev_page_url":null,"to":2,"total":2}
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
         * data : [{"uid":35,"type":1,"username":"纱雾","avatar":"http://image.gmdoc.com/FuFiUiUrDbhvRHVqtiRuCaF1WBYr","like_game":"高达","desc":"哈哈哈哈，喔尼酱阿斯顿啊啊啊啊啊啊啊啊a","fans_num":11,"follow_num":3,"dt_num":31,"blog_num":12,"short_num":14,"long_num":8,"is_sub":0},{"uid":40,"type":3,"username":"戏子","avatar":"http://image.gmdoc.com/GMDOC_2298822333577064.jpg","like_game":"实况足球","desc":"哈哈","fans_num":8,"follow_num":7,"dt_num":74,"blog_num":11,"short_num":22,"long_num":6,"is_sub":0}]
         * first_page_url : http://api.gmdoc.com/fans/108?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://api.gmdoc.com/fans/108?page=1
         * next_page_url : null
         * path : http://api.gmdoc.com/fans/108
         * per_page : 15
         * prev_page_url : null
         * to : 2
         * total : 2
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private Object next_page_url;
        private String path;
        private int per_page;
        private Object prev_page_url;
        private int to;
        private int total;
        private List<BaseSimpleData> data;

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

        public Object getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(Object next_page_url) {
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

        public List<BaseSimpleData> getData() {
            return data;
        }

        public void setData(List<BaseSimpleData> data) {
            this.data = data;
        }
    }
}
