package org.xiaoxingqi.gmdoc.presenter.home;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import org.xiaoxingqi.gmdoc.entity.BaseRespData;
import org.xiaoxingqi.gmdoc.entity.QINiuRespData;
import org.xiaoxingqi.gmdoc.entity.game.GameListData;
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData;
import org.xiaoxingqi.gmdoc.entity.user.LoveGameData;
import org.xiaoxingqi.gmdoc.entity.user.UserContentPhotoData;
import org.xiaoxingqi.gmdoc.impl.IConstant;
import org.xiaoxingqi.gmdoc.impl.home.UserCallback;
import org.xiaoxingqi.gmdoc.presenter.BasePresenter;

import java.util.Map;

import rx.Subscriber;

public class UserPresenter extends BasePresenter {
    private UserCallback callback;

    public UserPresenter(Context context, UserCallback callback) {
        super(context);
        this.callback = callback;
    }

    /**
     * 查询用户的钱包信息
     */
    public void getWallet(int type, int page) {
        addObaser(apiServer.base_get("wallet" + IConstant.GET_END + "&type=" + type + "&page=" + page), new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                callback.walletData(JSON.parseObject(s, HomeUserShareData.class));

            }
        });
    }


    /**
     * 获取用户的相册
     */

    public void getUserPhoto(String userId, int page) {
        addObaser(apiServer.base_get("photo/" + userId + IConstant.GET_END + "&page=" + page), new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                callback.userPhoto(JSON.parseObject(s, UserContentPhotoData.class));
            }
        });
    }


    /**
     * 喜欢的游戏单
     */

    public void loveGame(String userId) {
        addObaser(apiServer.base_get("like_game/" + userId + IConstant.GET_END), new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                callback.loveGameList(JSON.parseObject(s, LoveGameData.class));
            }
        });
    }


    /**
     * 增删改游戏单
     */

    public void changeGame(Map<String, String> map) {
        addObaser(apiServer.base_post("updateinfo/" + IConstant.GET_END, map), new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                callback.changeGame(JSON.parseObject(s, BaseRespData.class));
            }
        });
    }


    /**
     * 获取7牛的token
     */

    public void getQiNiuToken() {
        addObaser(apiServer.base_get("niu_token"), new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                callback.qiniuToken(JSON.parseObject(s, QINiuRespData.class));
            }
        });
    }

    public void otherGameList(String type, String uid, int page) {
        addObaser(apiServer.base_get(type + "/" + uid + IConstant.GET_END + "&page=" + page), new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                callback.otherGame(JSON.parseObject(s, GameListData.class));
            }
        });
    }

}
