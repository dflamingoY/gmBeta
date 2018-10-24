package org.xiaoxingqi.gmdoc.entity.home;


import org.xiaoxingqi.gmdoc.entity.BaseHomeBean;
import org.xiaoxingqi.gmdoc.entity.BaseRespData;

import java.util.List;

/**
 * Created by yzm on 2017/10/31.
 */

public class HomeGameData extends BaseRespData {


    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        private GameBean game;//游戲數據
        private List<BaseHomeBean> dy_blog;//博文
        private List<BaseHomeBean> dy_long;//长评
        private List<BaseHomeBean> dy_top_big;//顶部轮播
        private List<BaseHomeBean> dy_top_small;//顶部每日精选

        public GameBean getGame() {
            return game;
        }

        public void setGame(GameBean game) {
            this.game = game;
        }

        public List<BaseHomeBean> getDy_blog() {
            return dy_blog;
        }

        public void setDy_blog(List<BaseHomeBean> dy_blog) {
            this.dy_blog = dy_blog;
        }

        public List<BaseHomeBean> getDy_long() {
            return dy_long;
        }

        public void setDy_long(List<BaseHomeBean> dy_long) {
            this.dy_long = dy_long;
        }

        public List<BaseHomeBean> getDy_top_big() {
            return dy_top_big;
        }

        public void setDy_top_big(List<BaseHomeBean> dy_top_big) {
            this.dy_top_big = dy_top_big;
        }

        public List<BaseHomeBean> getDy_top_small() {
            return dy_top_small;
        }

        public void setDy_top_small(List<BaseHomeBean> dy_top_small) {
            this.dy_top_small = dy_top_small;
        }

        public static class GameBean {
            private List<List<BaseHomeBean>> data;
            private List<String> pla_list;

            public List<List<BaseHomeBean>> getData() {
                return data;
            }

            public void setData(List<List<BaseHomeBean>> data) {
                this.data = data;
            }

            public List<String> getPla_list() {
                return pla_list;
            }

            public void setPla_list(List<String> pla_list) {
                this.pla_list = pla_list;
            }
        }
    }
}
