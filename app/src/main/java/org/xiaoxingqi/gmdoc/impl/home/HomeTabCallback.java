package org.xiaoxingqi.gmdoc.impl.home;

import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData;
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData;

public abstract class HomeTabCallback {
    /**
     * 新活动的图
     *
     * @param data
     */
    public abstract void otherSuccess(HomeActiveData data);

    public abstract void activeSuccess(HomeActiveData data);

    public abstract void contibuteSuccess(HomeUserShareData data);
}
