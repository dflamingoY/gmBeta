package org.xiaoxingqi.gmdoc.entity.msg;


import org.xiaoxingqi.gmdoc.entity.BaseRespData;

import java.util.List;

/**
 * Created by yzm on 2017/12/5.
 */

public class MsgInfoListData extends BaseRespData {

    /**
     * state : 200
     * data : {"service":[{"id":1,"name":"游戏档案GM","img":"http://cdn.gmdoc.com/GMDOC_149933564400557.jpeg","motto":"有问题找我，我是您的客服雪泉","unread":0,"created_at":"2017-11-04 15:59:15","msg":{"content":"你好，欢迎来到游戏档案GMDOC！我是你的GMDOC小助手，在使用本站的过程中遇到任何问题都可以跟我说哦~现在开始你的游戏档案之旅吧!","created_at":"2017-12-01 15:16:33","is_pic":0}}],"list":[{"id":59,"name":"拉面新闻","img":"http://image.gmdoc.com/GMDOC_8339915811433497.jpg","motto":"爱火影，爱拉面，爱新闻","unread":0,"created_at":"2017-11-30 16:47:21","msg":{"content":":despise::mua::heihei::mua::heihei::mua:年代开始看","created_at":"2017-11-30 17:06:53","is_pic":0}},{"id":40,"name":"戏子","img":"http://image.gmdoc.com/GMDOC_4130796569028392.jpg","motto":"就算是咸鱼，也要做最咸的那一条","unread":0,"created_at":"2017-11-27 18:26:31","msg":{"content":"点击顶焦度计","created_at":"2017-11-30 16:47:21","is_pic":0}},{"id":38,"name":"农村贵妇-泉","img":"http://cdn.gmdoc.com/GMDOC_149923581134326.jpg","motto":"一曲终了，繁花散尽，伊人已逝，只余一声空","unread":0,"created_at":"2017-11-04 16:00:16","msg":{"content":"点击顶焦度计","created_at":"2017-11-30 16:47:21","is_pic":0}},{"id":89,"name":"拉面","img":"/images/avatar_default.png","motto":null,"unread":0,"created_at":"2017-10-26 18:51:12","msg":{"content":"点击顶焦度计","created_at":"2017-11-30 16:47:21","is_pic":0}},{"id":39,"name":"上班想要床上压鬼","img":"http://image.gmdoc.com/FtVHEj6D5-70gTkvzRUvCRQBWl_C","motto":null,"unread":0,"created_at":"2017-09-28 17:53:47","msg":{"content":"点击顶焦度计","created_at":"2017-11-30 16:47:21","is_pic":0}},{"id":61,"name":"清凝仙子","img":"http://cdn.gmdoc.com/GMDOC_150164729092157.jpg","motto":null,"unread":0,"created_at":"2017-08-28 18:47:13","msg":{"content":"点击顶焦度计","created_at":"2017-11-30 16:47:21","is_pic":0}},{"id":55,"name":"殷吹斯挺啊考虑考虑同","img":"http://cdn.gmdoc.com/GMDOC_150027633379584.png","motto":null,"unread":0,"created_at":"2017-08-11 16:35:27","msg":{"content":"点击顶焦度计","created_at":"2017-11-30 16:47:21","is_pic":0}},{"id":34,"name":"小林克","img":"http://image.gmdoc.com/FhUrtsI7aRY--U2nmxurElP-OgLl","motto":"我要去找公主！","unread":0,"created_at":"2017-08-11 16:33:43","msg":{"content":"点击顶焦度计","created_at":"2017-11-30 16:47:21","is_pic":0}}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BaseMsgListBean> service;
        private List<BaseMsgListBean> list;

        public List<BaseMsgListBean> getService() {
            return service;
        }

        public void setService(List<BaseMsgListBean> service) {
            this.service = service;
        }

        public List<BaseMsgListBean> getList() {
            return list;
        }

        public void setList(List<BaseMsgListBean> list) {
            this.list = list;
        }
    }
}
