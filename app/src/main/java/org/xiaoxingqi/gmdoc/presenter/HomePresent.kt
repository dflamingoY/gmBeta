package org.xiaoxingqi.gmdoc.presenter

import android.content.Context
import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData
import org.xiaoxingqi.gmdoc.entity.home.HomeGameData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.HomeTabCallback
import org.xiaoxingqi.gmdoc.rx.BaseSubscriber

/**
 * 获取热门游戏列表
 * 获取玩家的 贡献图
 */
class HomePresent : BasePresenter {

    private var tabCallback: HomeTabCallback? = null

    constructor(context: Context, tabCallback: HomeTabCallback) : super(context) {
        this.tabCallback = tabCallback
    }

    fun getGameData() {
        addObserve(apiServer.getHomeGame("home${IConstant.GET_END}"), object : BaseSubscriber<HomeGameData>() {
            override fun onNext(t: HomeGameData) {
                tabCallback?.gameSuccess(t)
            }
        })
    }

    fun getAttributeData(flag: Int) {
        addObserve(apiServer.getHomeContribute("home_contribute${IConstant.GET_END}&page=$flag"), object : BaseSubscriber<HomeUserShareData>() {
            override fun onNext(t: HomeUserShareData) {
                tabCallback?.contibuteSuccess(t)
            }
        })
    }

    fun getActionData() {
        addObserve(apiServer.getHomeActive("activity_list${IConstant.GET_END}"), object : BaseSubscriber<HomeActiveData>() {
            override fun onNext(t: HomeActiveData) {
                tabCallback?.activeSuccess(t)
            }
        })
    }

}