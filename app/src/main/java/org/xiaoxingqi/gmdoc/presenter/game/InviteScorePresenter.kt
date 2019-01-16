package org.xiaoxingqi.gmdoc.presenter.game

import android.content.Context
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.game.BaseInvateBean
import org.xiaoxingqi.gmdoc.entity.game.InvateGameData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.game.GameInviteCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

class InviteScorePresenter : BasePresenter {
    private var callback: GameInviteCallback? = null

    constructor(context: Context, callback: GameInviteCallback) : super(context) {
        this.callback = callback
    }

    fun getFollowUser(map: Map<String, String>, page: Int) {
        addObaser(apiServer.base_post("invite_users${IConstant.GET_END}&page=$page", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.invateUser(JSON.parseObject(t, InvateGameData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
            }

        })
    }

    fun invating(map: Map<String, String>, bean: BaseInvateBean) {
        addObaser(apiServer.base_post("invite_score", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.invating(JSON.parseObject(t, BaseRespData::class.java), bean)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }
}