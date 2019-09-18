package org.xiaoxingqi.gmdoc.presenter.game

import android.content.Context
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.game.GameImageData
import org.xiaoxingqi.gmdoc.entity.game.GameVideoImgData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.game.GameVideoCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

class GameImageVideoPresenter : BasePresenter {
    private var callback: GameVideoCallback? = null

    constructor(context: Context, callback: GameVideoCallback) : super(context) {
        this.callback = callback
    }

    //GameImageData
    fun getGameImg(gameId: String, page: Int) {
        addObserve(apiServer.baseGet("propaganda/$gameId${IConstant.GET_END}&key=2&page=$page"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.getImgList(JSON.parseObject(t, GameImageData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    //GameVedioImgData
    fun getVideo(gameId: String) {
        addObserve(apiServer.baseGet("propaganda/$gameId${IConstant.GET_END}"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.getVideoList(JSON.parseObject(t, GameVideoImgData::class.java))
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

}