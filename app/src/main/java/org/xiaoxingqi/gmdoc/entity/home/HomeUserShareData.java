package org.xiaoxingqi.gmdoc.entity.home;


import org.xiaoxingqi.gmdoc.entity.BaseImgBean;
import org.xiaoxingqi.gmdoc.entity.BaseRespData;
import org.xiaoxingqi.gmdoc.entity.BaseSelfBean;
import org.xiaoxingqi.gmdoc.entity.DynamicGameBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzm on 2017/11/8.
 * 首页玩家贡献的游戏截图
 */

public class HomeUserShareData extends BaseRespData {

    private DataBeanX data;
    private DataBeanX com;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public DataBeanX getCom() {
        return com;
    }

    public void setCom(DataBeanX com) {
        this.com = com;
    }

    public static class DataBeanX {

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private String next_page_url;
        private String path;
        private int per_page;
        private String prev_page_url;
        private int to;
        private int total;
        private List<ContributeBean> data;
        private String money;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

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

        public String getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(String next_page_url) {
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

        public String getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(String prev_page_url) {
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

        public List<ContributeBean> getData() {
            return data;
        }

        public void setData(List<ContributeBean> data) {
            this.data = data;
        }
    }

    public static class ContributeBean implements Serializable {

        private String username;
        private String id;
        private String avatar;
        private String like_game;
        private String summary;
        private String cover;
        private String created_at;
        private String game_id;
        private String video;
        private int no_comment;//禁止评论 1 0
        private int no_forward;//禁止转发 1 0
        private int power;
        private String state;
        private int type;
        private String ranking;//排行榜
        private String uid;
        private String title;
        private int is_ori;//   0表示转发 1 原创
        private String ori_cover;
        private String path;
        private int is_video;
        private int is_photo;
        private int forward_num;
        private int comment_num;
        private int like_num;
        private int read_num;
        private String is_hot;
        private int self_type;
        private int like_status;
        private int col_status;//(1 代表是 0否)
        private int pay_status;//(1 代表是 0否)
        private int rep_status;//(1 代表是 0否)
        private int is_sub;
        private String is_help;//(1 代表是 0否)
        private BaseSelfBean self;
        private DynamicGameBean game;
        private ArrayList<BaseImgBean> img;
        private String pay_type;
        private String money;
        private int ctype; //悄悄话 ==2
        private List<BaseAtList> ate_list;
        private String com_content;
        private String from_id;
        private String from_user;
        private String from_content;
        private String from_username;
        private String cid;
        private String user_id;
        private String video_cover;
        private String name;
        private String env_id;

        public String getEnv_id() {
            return env_id;
        }

        public void setEnv_id(String env_id) {
            this.env_id = env_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVideo_cover() {
            return video_cover;
        }

        public void setVideo_cover(String video_cover) {
            this.video_cover = video_cover;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getFrom_id() {
            return from_id;
        }

        public void setFrom_id(String from_id) {
            this.from_id = from_id;
        }

        public String getFrom_user() {
            return from_user;
        }

        public void setFrom_user(String from_user) {
            this.from_user = from_user;
        }

        public String getFrom_content() {
            return from_content;
        }

        public void setFrom_content(String from_content) {
            this.from_content = from_content;
        }

        public String getFrom_username() {
            return from_username;
        }

        public void setFrom_username(String from_username) {
            this.from_username = from_username;
        }

        public String getCom_content() {
            return com_content;
        }

        public void setCom_content(String com_content) {
            this.com_content = com_content;
        }

        public List<BaseAtList> getAte_list() {
            return ate_list;
        }

        public void setAte_list(List<BaseAtList> ate_list) {
            this.ate_list = ate_list;
        }

        public int getCtype() {
            return ctype;
        }

        public void setCtype(int ctype) {
            this.ctype = ctype;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLike_game() {
            return like_game;
        }

        public void setLike_game(String like_game) {
            this.like_game = like_game;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getGame_id() {
            return game_id;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
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

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_ori() {
            return is_ori;
        }

        public void setIs_ori(int is_ori) {
            this.is_ori = is_ori;
        }

        public String getOri_cover() {
            return ori_cover;
        }

        public void setOri_cover(String ori_cover) {
            this.ori_cover = ori_cover;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getIs_video() {
            return is_video;
        }

        public void setIs_video(int is_video) {
            this.is_video = is_video;
        }

        public int getIs_photo() {
            return is_photo;
        }

        public void setIs_photo(int is_photo) {
            this.is_photo = is_photo;
        }

        public int getForward_num() {
            return forward_num;
        }

        public void setForward_num(int forward_num) {
            this.forward_num = forward_num;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public int getLike_num() {
            return like_num;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public int getRead_num() {
            return read_num;
        }

        public void setRead_num(int read_num) {
            this.read_num = read_num;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public int getSelf_type() {
            return self_type;
        }

        public void setSelf_type(int self_type) {
            this.self_type = self_type;
        }

        public int getLike_status() {
            return like_status;
        }

        public void setLike_status(int like_status) {
            this.like_status = like_status;
        }

        public int getCol_status() {
            return col_status;
        }

        public void setCol_status(int col_status) {
            this.col_status = col_status;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public int getRep_status() {
            return rep_status;
        }

        public void setRep_status(int rep_status) {
            this.rep_status = rep_status;
        }

        public int getIs_sub() {
            return is_sub;
        }

        public void setIs_sub(int is_sub) {
            this.is_sub = is_sub;
        }

        public String getIs_help() {
            return is_help;
        }

        public void setIs_help(String is_help) {
            this.is_help = is_help;
        }

        public BaseSelfBean getSelf() {
            return self;
        }

        public void setSelf(BaseSelfBean self) {
            this.self = self;
        }

        public DynamicGameBean getGame() {
            return game;
        }

        public void setGame(DynamicGameBean game) {
            this.game = game;
        }

        public ArrayList<BaseImgBean> getImg() {
            return img;
        }

        public void setImg(ArrayList<BaseImgBean> img) {
            this.img = img;
        }
    }
}
