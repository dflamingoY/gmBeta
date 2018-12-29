package org.xiaoxingqi.gmdoc.entity.game;

/**
 * Created by yzm on 2017/11/9.
 */

public class BaseScoreBean {

    /**
     * name : 小林克
     * uid : 34
     * img : http://image.gmdoc.com/FhUrtsI7aRY--U2nmxurElP-OgLl
     * like_game : 塞尔达传说 喷射战士2 最终生还者
     * self_title : 短评
     * type : 1
     * is_ori : 1
     * cover : null
     * content : cdscwv
     * created_at : 2017-08-18 20:14:14
     * good :
     * bad :
     * score : 10
     * like_num : 1
     * comment_num : 1
     * aid : 2385
     * no_comment : 0
     * no_forward : 0
     * summary :
     * ranking : 5
     * is_help : null
     * is_like : null
     * pay_status : 0
     * is_col : 0
     * is_sub : 0
     */

    private String name;
    private int uid;
    private String img;
    private String like_game;
    private String self_title;
    private int type;
    private int is_ori;
    private String cover;//详情使用的图片地址
    private String content;
    private String created_at;
    private String good;
    private String bad;
    private int score;
    private int like_num;
    private int comment_num;
    private int aid;
    private int no_comment;
    private int no_forward;
    private String summary;//列表中使用的图片地址
    private int ranking;
    private String is_help;
    private String is_like;
    private int pay_status;//0 未支付  1 支付
    private String is_col;
    private int is_sub;//1 是关注  0 未关注 2 是互相关注
    private String version;
    private String platform_id;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(String platform_id) {
        this.platform_id = platform_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLike_game() {
        return like_game;
    }

    public void setLike_game(String like_game) {
        this.like_game = like_game;
    }

    public String getSelf_title() {
        return self_title;
    }

    public void setSelf_title(String self_title) {
        this.self_title = self_title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIs_ori() {
        return is_ori;
    }

    public void setIs_ori(int is_ori) {
        this.is_ori = is_ori;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getNo_comment() {
        return no_comment;
    }

    public void setNo_comment(int no_comment) {
        this.no_comment = no_comment;
    }

    public int getNo_forward() {
        return no_forward;
    }

    public void setNo_forward(int no_forward) {
        this.no_forward = no_forward;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getIs_help() {
        return is_help;
    }

    public void setIs_help(String is_help) {
        this.is_help = is_help;
    }

    public String getIs_like() {
        return is_like;
    }

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public String getIs_col() {
        return is_col;
    }

    public void setIs_col(String is_col) {
        this.is_col = is_col;
    }

    public int getIs_sub() {
        return is_sub;
    }

    public void setIs_sub(int is_sub) {
        this.is_sub = is_sub;
    }
}
