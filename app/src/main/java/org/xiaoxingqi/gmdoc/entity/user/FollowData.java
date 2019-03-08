package org.xiaoxingqi.gmdoc.entity.user;



import org.xiaoxingqi.gmdoc.entity.BaseRespData;
import org.xiaoxingqi.gmdoc.entity.BaseSimpleData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzm on 2017/11/18.
 */

public class FollowData extends BaseRespData {


    private DataBeanX data;


    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {

        private String s_name;
        private int s_count;
        private SubBean sub;
        private ArrayList<TeamBean> team;
        private ArrayList<TeamBean> all_team;

        public String getS_name() {
            return s_name;
        }

        public void setS_name(String s_name) {
            this.s_name = s_name;
        }

        public int getS_count() {
            return s_count;
        }

        public void setS_count(int s_count) {
            this.s_count = s_count;
        }

        public SubBean getSub() {
            return sub;
        }

        public void setSub(SubBean sub) {
            this.sub = sub;
        }

        public ArrayList<TeamBean> getTeam() {
            return team;
        }

        public void setTeam(ArrayList<TeamBean> team) {
            this.team = team;
        }

        public ArrayList<TeamBean> getAll_team() {
            return all_team;
        }

        public void setAll_team(ArrayList<TeamBean> all_team) {
            this.all_team = all_team;
        }
    }

    public static class SubBean {

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

    public static class TeamBean implements Serializable {

        private String name;
        private int count;
        private int id;

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
