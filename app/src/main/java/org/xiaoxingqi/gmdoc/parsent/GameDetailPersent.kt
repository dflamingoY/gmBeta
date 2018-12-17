package org.xiaoxingqi.gmdoc.parsent

import android.content.Context
import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.game.GameDetailCallBack
import rx.Subscriber

/**
 *1 请求游戏详情数据
 * 2
 */
class GameDetailPersent : BasePresent {
    private var callBack: GameDetailCallBack? = null

    constructor(context: Context?, callBack: GameDetailCallBack) : super(context) {
        this.callBack = callBack
    }

    //获取游戏详情
    fun getGameDetail(gameId: String) {
        addObaser(apiServer.get_GameDetails("game_detail/$gameId${IConstant.GET_END}"), object : Subscriber<GameDetailsData>() {
            override fun onNext(t: GameDetailsData?) {
                callBack?.let {
                    it.gameDetails(t)
                }
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }
    //获取游戏的博文

    fun getAllDynamic(gameId: String, type: String, page: Int) {
        addObaser(apiServer.get_GameDynamic("label_dynamic/${IConstant.GET_END}&game_id=$gameId&type=$type&page=$page"), object : Subscriber<HomeUserShareData>() {
            override fun onNext(t: HomeUserShareData?) {
                callBack?.let {
                    it.gameDynamic(t)
                }
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    //添加心愿单

    //长评短评


}