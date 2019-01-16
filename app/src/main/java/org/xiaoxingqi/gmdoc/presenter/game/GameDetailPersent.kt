package org.xiaoxingqi.gmdoc.presenter.game

import android.content.Context
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.ThumbData
import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData
import org.xiaoxingqi.gmdoc.entity.game.GameScoreAllData
import org.xiaoxingqi.gmdoc.entity.game.GameTabData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.game.GameDetailCallBack
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

/**
 *1 请求游戏详情数据
 * 2
 */
class GameDetailPersent : BasePresenter {
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

    /**
     *长评短评
     */
    fun getComment(map: Map<String, String>, gameId: String) {
        addObaser(apiServer.queryGameComment("scoreList/$gameId${IConstant.GET_END}", map), object : Subscriber<GameScoreAllData>() {
            override fun onNext(t: GameScoreAllData?) {
                callBack?.gameComment(t)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    /**
     * 添加心愿单
     */
    fun addWish(map: Map<String, String>, type: String) {
        addObaser(apiServer.base_post("$type${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callBack?.gameOperator(JSON.parseObject(t, ThumbData::class.java), type)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
                e?.stackTrace
                e?.printStackTrace()
            }
        })
    }

    fun getTab(gameId: String) {
        addObaser(apiServer.base_get("get_label${IConstant.GET_END}&game_id=$gameId"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callBack?.gameTab(JSON.parseObject(t, GameTabData::class.java))

            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }

        })
    }

}