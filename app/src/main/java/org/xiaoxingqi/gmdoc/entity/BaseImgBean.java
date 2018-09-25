package org.xiaoxingqi.gmdoc.entity;


import java.io.Serializable;

/**
 * Created by yzm on 2017/11/13.
 */

public class BaseImgBean extends BaseTitleData implements Serializable {
    private String game_id;
    private String spoiler;
    private String url;
    private String pic;
    private String title;
    private String currentPage;//动态浏览 总的动态条数
    private String time;
    private String indexPage;//一条动态的第几页

    public BaseImgBean(boolean isSelected, String horizontalTitle) {
        super(isSelected, horizontalTitle);
    }

    public BaseImgBean(String pic) {
        this.pic = pic;
        this.url = pic;
    }


    public BaseImgBean() {

    }

    public String getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(String spoiler) {
        this.spoiler = spoiler;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
