package org.xiaoxingqi.gmdoc.presenter.game

import android.content.Context
import org.xiaoxingqi.gmdoc.entity.game.GameListData
import org.xiaoxingqi.gmdoc.entity.game.GamePlatformData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.game.GameFragCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

class GameFragPersent : BasePresenter {
    private var callBack: GameFragCallback? = null

    constructor(context: Context, callBack: GameFragCallback) : super(context) {
        this.callBack = callBack
    }

    fun getGamePlat() {
        addObaser(apiServer.get_GamePlat("platform_list${IConstant.GET_END}"), object : Subscriber<GamePlatformData>() {
            override fun onNext(t: GamePlatformData?) {
                callBack?.let {
                    it.gamePlatList(t)
                }
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    fun getGameList(name: String, version: String, sell: String, type: String, page: Int) {

        addObaser(apiServer.get_FragGameList("game_list/$name?version=$version&stime=$sell&sort=$type&page=$page&device=android"), object : Subscriber<GameListData>() {
            override fun onNext(t: GameListData?) {
                callBack?.let { it.gameDetailsList(t) }
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })

    }

}