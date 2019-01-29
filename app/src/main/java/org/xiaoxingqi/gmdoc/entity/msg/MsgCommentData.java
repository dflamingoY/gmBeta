package org.xiaoxingqi.gmdoc.entity.msg;


import org.xiaoxingqi.gmdoc.entity.BaseRespData;

/**
 * Created by yzm on 2017/12/6.
 */

public class MsgCommentData extends BaseRespData {

    /**
     * data : {"content":"健康斤斤计较","from_uid":108,"is_pic":0,"time":"2017-12-06 15:06:35","to_uid":"1","type":"receive","user":{"id":108,"img":"http://image.gmdoc.com/Fm4dWYM3TnCHWhSg9yqkVAi9oynR","name":"大王游泳"}}
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
         * content : 健康斤斤计较
         * from_uid : 108
         * is_pic : 0
         * time : 2017-12-06 15:06:35
         * to_uid : 1
         * type : receive
         * user : {"id":108,"img":"http://image.gmdoc.com/Fm4dWYM3TnCHWhSg9yqkVAi9oynR","name":"大王游泳"}
         */

        private String content;
        private String from_uid;
        private int is_pic;
        private String time;
        private String to_uid;
        private String type;
        private UserBean user;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFrom_uid() {
            return from_uid;
        }

        public void setFrom_uid(String from_uid) {
            this.from_uid = from_uid;
        }

        public int getIs_pic() {
            return is_pic;
        }

        public void setIs_pic(int is_pic) {
            this.is_pic = is_pic;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTo_uid() {
            return to_uid;
        }

        public void setTo_uid(String to_uid) {
            this.to_uid = to_uid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }
    }

    public static class UserBean {
        /**
         * id : 108
         * img : http://image.gmdoc.com/Fm4dWYM3TnCHWhSg9yqkVAi9oynR
         * name : 大王游泳
         */

        private int id;
        private String img;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
