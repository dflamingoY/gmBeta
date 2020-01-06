package org.xiaoxingqi.gmdoc.entity;


import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData;

import java.util.List;

/**
 * Created by yzm on 2017/12/12.
 */

public class BowenDetailsData extends BaseRespData {

    private List<HomeUserShareData.ContributeBean> data;
    private List<String> labels;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<HomeUserShareData.ContributeBean> getData() {
        return data;
    }

    public void setData(List<HomeUserShareData.ContributeBean> data) {
        this.data = data;
    }
}
