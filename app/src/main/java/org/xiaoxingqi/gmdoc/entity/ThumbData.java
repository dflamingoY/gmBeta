package org.xiaoxingqi.gmdoc.entity;

/**
 * Created by yzm on 2017/11/15.
 * <p>
 * 点赞
 */

public class ThumbData extends BaseRespData {


    /**
     * data : {"id":1,"like_count":0,"like_status":0}
     * state : 200
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
         * id : 1
         * like_count : 0
         * like_status : 0
         */

        private int id;
        private int like_count;
        private int like_status;
        private int type;//1 在玩  2  没有玩
        private int fans_switch;//0关闭 1 开启

        public int getFans_switch() {
            return fans_switch;
        }

        public void setFans_switch(int fans_switch) {
            this.fans_switch = fans_switch;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public int getLike_status() {
            return like_status;
        }

        public void setLike_status(int like_status) {
            this.like_status = like_status;
        }
    }
}
