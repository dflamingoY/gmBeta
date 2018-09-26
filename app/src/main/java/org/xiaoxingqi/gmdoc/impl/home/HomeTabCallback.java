package org.xiaoxingqi.gmdoc.impl.home;

import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData;
import org.xiaoxingqi.gmdoc.entity.home.HomeGameData;
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData;

public abstract class HomeTabCallback {
    /**
     * 获取首页游戏的数据
     *
     * @param data
     */
    public abstract void gameSuccess(HomeGameData data);

    /**
     * 获取新活动
     *
     * @param data
     */
    public abstract void activeSuccess(HomeActiveData data);

    /**
     * 贡献图
     *
     * @param data
     */
    public abstract void contibuteSuccess(HomeUserShareData data);
}
