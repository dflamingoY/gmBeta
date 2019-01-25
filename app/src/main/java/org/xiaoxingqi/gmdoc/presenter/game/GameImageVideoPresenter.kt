package org.xiaoxingqi.gmdoc.presenter.game

import android.content.Context
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.game.GameVideoCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

class GameImageVideoPresenter : BasePresenter {
    private var callback: GameVideoCallback? = null

    constructor(context: Context, callback: GameVideoCallback) : super(context) {
        this.callback = callback
    }

    fun getGameImg(gameId: String, page: Int) {
        addObaser(apiServer.base_get("propaganda/$gameId${IConstant.GET_END}"), object : Subscriber<String>() {
            override fun onNext(t: String?) {

            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }
}