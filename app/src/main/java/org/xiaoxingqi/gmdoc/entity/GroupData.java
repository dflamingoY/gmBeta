package org.xiaoxingqi.gmdoc.entity;

import java.util.List;

/**
 * Created by yzm on 2017/11/21.
 * 分组列表
 */

public class GroupData extends BaseRespData {


    /**
     * tem_list : [{"name":"犄角之势","count":3,"id":36},{"count":3,"name":"未分组","id":0}]
     * state : 200
     */

    private List<TemListBean> tem_list;

    public List<TemListBean> getTem_list() {
        return tem_list;
    }

    public void setTem_list(List<TemListBean> tem_list) {
        this.tem_list = tem_list;
    }

    public static class TemListBean {
        /**
         * name : 犄角之势
         * count : 3
         * id : 36
         */

        private String name;
        private int count;
        private int id;
        private boolean isSelected;

        public TemListBean() {
        }

        public TemListBean(String name, int id, boolean isSelected) {
            this.name = name;
            this.id = id;
            this.isSelected = isSelected;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
