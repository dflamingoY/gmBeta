package org.xiaoxingqi.gmdoc.impl.game;

import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData;
import org.xiaoxingqi.gmdoc.entity.game.GameScoreAllData;
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData;

public interface GameDetailCallBack {

    void gameDetails(GameDetailsData data);

    void gameDynamic(HomeUserShareData data);

    void gameComment(GameScoreAllData data);
}
