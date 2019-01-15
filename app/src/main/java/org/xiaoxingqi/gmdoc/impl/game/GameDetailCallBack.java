package org.xiaoxingqi.gmdoc.impl.game;

import org.xiaoxingqi.gmdoc.entity.ThumbData;
import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData;
import org.xiaoxingqi.gmdoc.entity.game.GameScoreAllData;
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData;

public interface GameDetailCallBack {
    /**
     * 获取游戏详情
     *
     * @param data
     */
    void gameDetails(GameDetailsData data);

    /**
     * 获取动态详情
     *
     * @param data
     */
    void gameDynamic(HomeUserShareData data);

    /**
     * 获取评论
     *
     * @param data
     */
    void gameComment(GameScoreAllData data);

    /**
     * 操作游戏  想玩 在玩  带评论
     *
     * @param data
     * @param type
     */
    void gameOperator(ThumbData data, String type);

}
