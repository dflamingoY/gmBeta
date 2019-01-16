package org.xiaoxingqi.gmdoc.entity.game;


import org.xiaoxingqi.gmdoc.entity.BaseRespData;
import org.xiaoxingqi.gmdoc.entity.BaseSimpleData;

import java.util.List;

/**
 * Created by yzm on 2017/11/10.
 */

public class GameTabData extends BaseRespData {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BaseSimpleData> labels;
        private List<BaseSimpleData> users;

        public List<BaseSimpleData> getLabels() {
            return labels;
        }

        public void setLabels(List<BaseSimpleData> labels) {
            this.labels = labels;
        }

        public List<BaseSimpleData> getUsers() {
            return users;
        }

        public void setUsers(List<BaseSimpleData> users) {
            this.users = users;
        }
    }
}
