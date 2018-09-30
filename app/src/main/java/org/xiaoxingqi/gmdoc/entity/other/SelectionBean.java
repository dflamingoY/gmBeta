package org.xiaoxingqi.gmdoc.entity.other;

/**
 * Created by yzm on 2017/12/4.
 */

public class SelectionBean {

    private String id;
    private String name;
    private int start;
    private int end;
    private int type;//1 游戏名字    2 @用户  3标签 都跳搜索  4 网址 跳webView    5长评详情 6 博文详情

    public SelectionBean(String id, String name, int start, int end, int type) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public SelectionBean(String name, int start, int end, int type) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
