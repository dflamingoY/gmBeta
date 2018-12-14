package org.xiaoxingqi.gmdoc.entity.game;


import org.xiaoxingqi.gmdoc.entity.BaseRespData;

import java.util.List;

/**
 * Created by yzm on 2017/11/3.
 * 游戏列表
 */

public class GameListData extends BaseRespData {


    private DataBeanX data;
    private String version;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBeanX {
        /**
         * current_page : 1
         * data : [{"game_name":"人中北斗","id":109069,"introduce":"以经典漫画《北斗神拳》改编的游戏，类型为动作冒险。","version":"港中","sale_time":"2018年02月22日","cover":"image/573a3e621ca58c71e7980ccbbd38e6ad.jpeg","score":-1},{"game_name":"纷争 最终幻想NT","id":108031,"introduce":"《最终幻想纷争》街机版的制作组为开发过《忍龙》以及《DOA》系列等著名动作游戏的Team NINJA。游戏全程都将会为高流畅的60FPS，而且将于之后移植到PS4平台上。","version":"港中","sale_time":"2018年01月11日","cover":"image/10529989f67baab755140f3980ccf0b5.jpeg","score":-1},{"game_name":"如龙：极 2","id":108032,"introduce":"该作是如龙2重制版，暂无更多情报。","version":"港中","sale_time":"2017年12月07日","cover":"image/如龙2.jpg","score":-1},{"game_name":"最终幻想15 深海怪物","id":107976,"introduce":"《最终幻想15》的钓鱼元素拿出来制作成了单独的VR游戏。正如游戏名暗示的那样，游戏里不只有常规的普通鱼类，在波光粼粼的水面下还藏着一些体型巨大的怪兽鱼。","version":"港中","sale_time":"2017年11月21日","cover":"image/最终幻想15 深海怪物.jpg","score":-1},{"game_name":"模拟人生4","id":105326,"introduce":"在《模拟人生4》中，开发商让游戏角色开始肩负完全的情感包袱。一场争吵会使得一个角色生气，而专业上的失败会使他抑郁。黑暗房间被跳动的烛火点亮的场景则会让角色进入到\u201c浪漫\u201d状态。","version":"港中","sale_time":"2017年11月17日","cover":"image/PS4gangban00122.jpg","score":-1},{"game_name":"星球大战：前线2","id":105327,"introduce":"《星球大战：前线2》是一款FPS游戏，本作加入了原创剧情，讲述的是一个从未曝光过的星战故事，玩家将扮演一名精英风暴兵。本作多人模式包含陆地和空战。","version":"港中","sale_time":"2017年11月17日","cover":"image/PS4gangban00123.jpg","score":-1},{"game_name":"勇者斗恶龙11","id":107766,"introduce":"《勇者斗恶龙11》是一款动作冒险游戏。游戏中男主角在一座小村庄Ishi长大，16岁后村庄为他举办了传统的成人仪式，在仪式上他得知自己正是传说中拯救世界的\u201c勇者\u201d转生，而他也将背负着重大的使命。为了了解有关勇者的一切，主角离开家乡踏上了冒险之旅。\r\n　　然而出乎意料的是，国王却直指主角是\u201c恶魔之子\u201d，两边冲出士兵将主角团团围住。身为\u201c勇者\u201d转世的主角为何会被视为\u201c恶魔之子\u201d？","version":"港中","sale_time":"2017年11月11日","cover":"image/295fc89e7498fbbe38dcc4f04c242933.jpeg","score":-1},{"game_name":"极品飞车20：复仇","id":100134,"introduce":"本作依旧由Ghost Games制作，游戏改装玩法更加强悍。警匪追逐中将加入互动元素，赛道包括柏油路以及沙土路，还将加入\u201c特技飞车\u201d玩法，另外，前作争议的联网机制本作将取消，玩家可以离线游玩单机模式，而且可以随意暂停游戏。","version":"港中","sale_time":"2017年11月10日","cover":"image/PS4gangban00096.jpg","score":-1}]
         * first_page_url : http://api.gmdoc.com/game_list/PS4?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://api.gmdoc.com/game_list/PS4?page=1
         * next_page_url : null
         * path : http://api.gmdoc.com/game_list/PS4
         * per_page : 20
         * prev_page_url : null
         * to : 8
         * total : 8
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
        private List<BaseGameBean> data;

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

        public List<BaseGameBean> getData() {
            return data;
        }

        public void setData(List<BaseGameBean> data) {
            this.data = data;
        }
    }
}
