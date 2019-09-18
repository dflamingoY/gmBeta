package org.xiaoxingqi.gmdoc.presenter.game;

import android.content.Context;

import org.xiaoxingqi.gmdoc.entity.game.GameListData;
import org.xiaoxingqi.gmdoc.impl.IConstant;
import org.xiaoxingqi.gmdoc.impl.game.GameListCallBack;
import org.xiaoxingqi.gmdoc.presenter.BasePresenter;

import rx.Subscriber;

public class GameListPresent extends BasePresenter {
    private GameListCallBack callBack;

    public GameListPresent(Context context, GameListCallBack callBack) {
        super(context);
        this.callBack = callBack;
    }


    public void getGameList(String platfrom, int page) {
        addObserve(apiServer.getGameList("game_list/" + platfrom + IConstant.GET_END + "&page=" + page + "&sort=1"), new Subscriber<GameListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GameListData gameListData) {
                if (callBack != null) {
                    callBack.getGameList(gameListData);
                }
            }
        });
    }
}
