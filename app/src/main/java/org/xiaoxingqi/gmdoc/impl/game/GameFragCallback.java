package org.xiaoxingqi.gmdoc.impl.game;

import org.xiaoxingqi.gmdoc.entity.game.GameListData;
import org.xiaoxingqi.gmdoc.entity.game.GamePlatformData;

public interface GameFragCallback {
    /**
     * 获取所有游戏平台
     *
     * @param data
     */
    void gamePlatList(GamePlatformData data);

    /**
     * 获取某个平台的游戏列表
     *
     * @param data
     */
    void gameDetailsList(GameListData data);
}
