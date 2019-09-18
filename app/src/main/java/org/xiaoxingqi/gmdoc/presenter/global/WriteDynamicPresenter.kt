package org.xiaoxingqi.gmdoc.presenter.global

import android.content.Context
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.QINiuRespData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.global.WriteDynamicCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

class WriteDynamicPresenter : BasePresenter {
    private var callback: WriteDynamicCallback? = null

    constructor(context: Context, callback: WriteDynamicCallback) : super(context) {
        this.callback = callback
    }

    /**
     * 获取7牛的token
     */
    fun getQiNiuToken() {
        addObserve(apiServer.baseGet("niu_token"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.qiniuToken(JSON.parseObject(t, QINiuRespData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    /**
     * 发布
     */
    fun pushDynamic(map: Map<String, String>) {
        addObserve(apiServer.basePost("send/${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.pushSuccess(JSON.parseObject(t, BaseRespData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }
}