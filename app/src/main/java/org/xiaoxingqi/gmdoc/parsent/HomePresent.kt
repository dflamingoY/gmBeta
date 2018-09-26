package org.xiaoxingqi.gmdoc.parsent

import android.content.Context
import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData
import org.xiaoxingqi.gmdoc.entity.home.HomeGameData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.HomeTabCallback
import rx.Subscriber

/**
 * 获取热门游戏列表
 * 获取玩家的 贡献图
 */
class HomePresent : BasePresent {

    private var tabCoallback: HomeTabCallback? = null

    constructor(context: Context, tabCoallback: HomeTabCallback) : super(context) {
        this.tabCoallback = tabCoallback
    }

    fun getGameData() {
        addObaser(apiServer.get_HomeGame("home${IConstant.GET_END}"), object : Subscriber<HomeGameData>() {
            override fun onNext(t: HomeGameData?) {
                tabCoallback?.let {
                    it.gameSuccess(t)
                }
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    fun getAttributeData(flag: Int) {
        addObaser(apiServer.get_HomeGame("home_contribute${IConstant.GET_END}&page=$flag"), object : Subscriber<HomeUserShareData>() {
            override fun onNext(t: HomeUserShareData?) {
                tabCoallback?.let {
                    it.contibuteSuccess(t)
                }
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    fun getActionData() {
        addObaser(apiServer.get_HomeGame("activity_list${IConstant.GET_END}"), object : Subscriber<HomeActiveData>() {
            override fun onNext(t: HomeActiveData?) {
                tabCoallback?.let {
                    it.activeSuccess(t)
                }
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

}