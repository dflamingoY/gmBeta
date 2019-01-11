package org.xiaoxingqi.gmdoc.entity;

/**
 * Created by yzm on 2017/11/22.
 */

public class ImgBean {

    private String key;
    private String value;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public ImgBean(String key) {
        this.key = key;
    }

    public ImgBean(String key, boolean isSelected) {
        this.key = key;
        this.isSelected = isSelected;
    }

    public ImgBean() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
