package org.xiaoxingqi.gmdoc.entity;

import java.util.List;

/**
 * Created by yzm on 2018/1/4.
 */

public class RecommendData extends BaseRespData {

    private RecomendX data;

    public RecomendX getData() {
        return data;
    }

    public void setData(RecomendX data) {
        this.data = data;
    }

    public static class RecomendX {
        private List<RecommendBean> data;
        private String current_page;
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

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
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

        public List<RecommendBean> getData() {
            return data;
        }

        public void setData(List<RecommendBean> data) {
            this.data = data;
        }

    }


    /**
     * state : 200
     * data : [{"id":1775,"title":"那个令我难忘的游戏-异度之刃随笔","cover":"http://cdn.gmdoc.com/GMDOC_150089238574524.jpg","score":-1,"type":3,"game_id":null,"game_name":null,"version":null},{"id":1968,"title":"男人只有在一切都结束的时候哭泣\u2026\u2026","cover":"http://cdn.gmdoc.com/GMDOC_150121419816925.jpg?imageMogr2/thumbnail/880x/crop/!880x360a0a0","score":-1,"type":3,"game_id":0,"game_name":null,"version":null},{"id":1785,"title":"#仁王#那些关原之战未露脸的名将","cover":"http://cdn.gmdoc.com/GMDOC_150089351442089.jpg","score":-1,"type":3,"game_id":null,"game_name":null,"version":null}]
     */


    public static class RecommendBean {
        /**
         * id : 1775
         * title : 那个令我难忘的游戏-异度之刃随笔
         * cover : http://cdn.gmdoc.com/GMDOC_150089238574524.jpg
         * score : -1
         * type : 3
         * game_id : null
         * game_name : null
         * version : null
         */

        private int id;
        private String title;
        private String cover;
        private int score;
        private int type;
        private String game_id;
        private String game_name;
        private String version;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGame_id() {
            return game_id;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
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
    }
}
