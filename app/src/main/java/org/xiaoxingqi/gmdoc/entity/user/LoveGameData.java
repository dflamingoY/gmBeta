package org.xiaoxingqi.gmdoc.entity.user;


import org.xiaoxingqi.gmdoc.entity.BaseRespData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yzm on 2017/11/17.
 */

public class LoveGameData extends BaseRespData {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {

        private String id;
        private String uid;
        private String game_name;
        private String desc;
        private String img;
        private String rank;

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

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }
}
