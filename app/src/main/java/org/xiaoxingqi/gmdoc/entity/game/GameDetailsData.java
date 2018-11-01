package org.xiaoxingqi.gmdoc.entity.game;


import org.xiaoxingqi.gmdoc.entity.BaseSimpleData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzm on 2017/11/3.
 */

public class GameDetailsData implements Serializable {

    private AllBean all;
    private int all_pic_num;
    private BaseGameBean game;
    private String is_love;
    private MyBean my;
    private int pic_num;
    private ScoreTabBean score_tab;
    private int state;
    private String user_score;
    private ArrayList<BaseSimpleData> pic;
    private ArrayList<BaseSimpleData> video;
    private List<BaseSimpleData> my_con_pic;
    private int is_playing;//是否在玩中 1是0 否
    private int is_waiting;//是否待评分  1 是  0 否


    public int getIs_waiting() {
        return is_waiting;
    }

    public void setIs_waiting(int is_waiting) {
        this.is_waiting = is_waiting;
    }

    public int getIs_playing() {
        return is_playing;
    }

    public void setIs_playing(int is_playing) {
        this.is_playing = is_playing;
    }

    public List<BaseSimpleData> getMy_con_pic() {
        return my_con_pic;
    }

    public void setMy_con_pic(List<BaseSimpleData> my_con_pic) {
        this.my_con_pic = my_con_pic;
    }

    public AllBean getAll() {
        return all;
    }

    public void setAll(AllBean all) {
        this.all = all;
    }

    public int getAll_pic_num() {
        return all_pic_num;
    }

    public void setAll_pic_num(int all_pic_num) {
        this.all_pic_num = all_pic_num;
    }

    public BaseGameBean getGame() {
        return game;
    }

    public void setGame(BaseGameBean game) {
        this.game = game;
    }

    public String getIs_love() {
        return is_love;
    }

    public void setIs_love(String is_love) {
        this.is_love = is_love;
    }

    public MyBean getMy() {
        return my;
    }

    public void setMy(MyBean my) {
        this.my = my;
    }

    public int getPic_num() {
        return pic_num;
    }

    public void setPic_num(int pic_num) {
        this.pic_num = pic_num;
    }

    public ScoreTabBean getScore_tab() {
        return score_tab;
    }

    public void setScore_tab(ScoreTabBean score_tab) {
        this.score_tab = score_tab;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public ArrayList<BaseSimpleData> getPic() {
        return pic;
    }

    public void setPic(ArrayList<BaseSimpleData> pic) {
        this.pic = pic;
    }

    public ArrayList<BaseSimpleData> getVideo() {
        return video;
    }

    public void setVideo(ArrayList<BaseSimpleData> video) {
        this.video = video;
    }

    public static class AllBean implements Serializable {
        /**
         * count : 3
         * score : 10
         */

        private int count;
        private float score;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }
    }


    public static class MyBean implements Serializable {
        /**
         * count : 0
         * score : -1
         */

        private int count;
        private float score;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }
    }

    public static class ScoreTabBean implements Serializable {

        private AllBeanX all;
        private AllBeanX my;

        public AllBeanX getAll() {
            return all;
        }

        public void setAll(AllBeanX all) {
            this.all = all;
        }

        public AllBeanX getMy() {
            return my;
        }

        public void setMy(AllBeanX my) {
            this.my = my;
        }

        public static class AllBeanX implements Serializable {
            /**
             * all : 3
             * one : 3
             * three : 0
             * two : 0
             */

            private int all;
            private String one;
            private String three;
            private String two;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }

            public String getOne() {
                return one;
            }

            public void setOne(String one) {
                this.one = one;
            }

            public String getThree() {
                return three;
            }

            public void setThree(String three) {
                this.three = three;
            }

            public String getTwo() {
                return two;
            }

            public void setTwo(String two) {
                this.two = two;
            }
        }
    }
}
