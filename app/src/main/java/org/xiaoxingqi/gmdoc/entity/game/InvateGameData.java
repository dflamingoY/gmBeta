package org.xiaoxingqi.gmdoc.entity.game;


import org.xiaoxingqi.gmdoc.entity.BaseRespData;

import java.util.List;

/**
 * Created by yzm on 2017/12/13.
 */

public class InvateGameData extends BaseRespData {


    /**
     * current_page : 1
     * data : [{"name":"游戏档案GM","id":1,"avatar":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","desc":"有问题找我，我是您的客服雪泉","state":0},{"name":"纱雾","id":35,"avatar":"http://image.gmdoc.com/FuFiUiUrDbhvRHVqtiRuCaF1WBYr","desc":"哈哈哈哈，喔尼酱阿斯顿啊啊啊啊啊啊啊啊a","state":0},{"name":"拉面新闻","id":59,"avatar":"http://image.gmdoc.com/GMDOC_8339915811433497.jpg","desc":"我哈哈哈哈哈哈收拾收拾就睡觉顶焦度计分放","state":2},{"name":"游戏档案","id":80,"avatar":"/images/avatar_default.png","desc":null,"state":2},{"name":"jjfly","id":102,"avatar":"/images/avatar_default.png","desc":null,"state":2},{"name":"大王","id":109,"avatar":"/images/avatar_default.png","desc":null,"state":0},{"name":"戏子v","id":112,"avatar":"http://image.gmdoc.com/GMDOC_7614839411701351.jpg","desc":null,"state":0},{"name":"小樱","id":115,"avatar":"/images/avatar_default.png","desc":null,"state":0}]
     * first_page_url : http://api.gmdoc.com/invite_users?page=1
     * from : 1
     * last_page : 1
     * last_page_url : http://api.gmdoc.com/invite_users?page=1
     * next_page_url : null
     * path : http://api.gmdoc.com/invite_users
     * per_page : 10
     * prev_page_url : null
     * to : 8
     * total : 8
     * state : 200
     */

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
    private List<BaseInvateBean> data;

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

    public List<BaseInvateBean> getData() {
        return data;
    }

    public void setData(List<BaseInvateBean> data) {
        this.data = data;
    }

}
