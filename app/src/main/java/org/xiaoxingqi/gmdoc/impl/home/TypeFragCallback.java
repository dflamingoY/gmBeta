package org.xiaoxingqi.gmdoc.impl.home;

import android.view.View;

import org.xiaoxingqi.gmdoc.entity.RespFansData;
import org.xiaoxingqi.gmdoc.entity.ThumbData;
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData;

public abstract class TypeFragCallback {
    /**
     * 获取动态数据
     *
     * @param data
     */
    public abstract void callTypeData(HomeUserShareData data);

    /**
     * 获取最新活跃度的三十位用户
     *
     * @param data
     */
    public void callHotUser(RespFansData data) {

    }

    public abstract void thumbCallback(ThumbData data, View view);
}
