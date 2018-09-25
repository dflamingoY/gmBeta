package org.xiaoxingqi.gmdoc.entity;

import java.io.Serializable;

/**
 * Created by yzm on 2017/11/28.
 */

public class BaseTitleData implements Serializable {

    protected boolean isSelected;
    protected String horizontalTitle;

    public BaseTitleData(boolean isSelected, String horizontalTitle) {
        this.isSelected = isSelected;
        this.horizontalTitle = horizontalTitle;
    }

    public BaseTitleData() {

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getHorizontalTitle() {
        return horizontalTitle;
    }

    public void setHorizontalTitle(String horizontalTitle) {
        this.horizontalTitle = horizontalTitle;
    }
}
