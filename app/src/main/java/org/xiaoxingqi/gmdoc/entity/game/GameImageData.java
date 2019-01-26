package org.xiaoxingqi.gmdoc.entity.game;


import org.xiaoxingqi.gmdoc.entity.BaseSimpleData;

import java.util.List;

/**
 * Created by yzm on 2017/11/9.
 */

public class GameImageData {


    /**
     * pic : [{"name":"农村贵妇-泉","uid":38,"pic":"http://cdn.gmdoc.com/GMDOC_149935226750781.jpeg","title":"","id":175},{"name":"清凝仙子","uid":61,"pic":"http://cdn.gmdoc.com/GMDOC_150408554994261.jpg","title":"","id":617},{"name":"清凝仙子","uid":61,"pic":"http://cdn.gmdoc.com/GMDOC_150408554994321.jpg","title":"","id":618},{"name":"清凝仙子","uid":61,"pic":"http://cdn.gmdoc.com/GMDOC_150408554994439.jpeg","title":"","id":619},{"name":"清凝仙子","uid":61,"pic":"http://cdn.gmdoc.com/GMDOC_150408554994476.jpg","title":"","id":620},{"name":"清凝仙子","uid":61,"pic":"http://cdn.gmdoc.com/GMDOC_150408554994551.jpg","title":"","id":621},{"name":"清凝仙子","uid":61,"pic":"http://cdn.gmdoc.com/GMDOC_150408554994559.gif","title":"","id":622},{"name":"清凝仙子","uid":61,"pic":"http://cdn.gmdoc.com/GMDOC_150408554994657.jpg","title":"","id":623},{"name":"一朵小白云","uid":37,"pic":"http://cdn.gmdoc.com/GMDOC_150417915229735.gif","title":"","id":759},{"name":"一朵小白云","uid":37,"pic":"http://cdn.gmdoc.com/GMDOC_150417915229840.gif","title":"","id":760},{"name":"一朵小白云","uid":37,"pic":"http://cdn.gmdoc.com/GMDOC_150417915229877.gif","title":"","id":761},{"name":"一朵小白云","uid":37,"pic":"http://cdn.gmdoc.com/GMDOC_150417915229915.gif","title":"","id":762},{"name":"一朵小白云","uid":37,"pic":"http://cdn.gmdoc.com/GMDOC_150417915229916.gif","title":"","id":763},{"name":"一朵小白云","uid":37,"pic":"http://cdn.gmdoc.com/GMDOC_150417915230072.gif","title":"","id":764},{"name":"一朵小白云","uid":37,"pic":"http://cdn.gmdoc.com/GMDOC_150417915230064.gif","title":"","id":765}]
     * total : 39
     * currentPage : 1
     * lastPage : 3
     * state : 200
     */

    private int total;
    private int currentPage;
    private int lastPage;
    private int state;
    private List<BaseSimpleData> pic;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<BaseSimpleData> getPic() {
        return pic;
    }

    public void setPic(List<BaseSimpleData> pic) {
        this.pic = pic;
    }
}
