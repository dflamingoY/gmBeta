package org.xiaoxingqi.gmdoc.presenter.global

import android.content.Context
import org.xiaoxingqi.gmdoc.impl.global.WriteDynamicCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter

class WriteDynamicPresenter : BasePresenter {
    private var callback: WriteDynamicCallback? = null

    constructor(context: Context, callback: WriteDynamicCallback) : super(context) {
        this.callback = callback
    }

    /**
     * 获取7牛的token
     */
    fun getQiNiuToken() {

    }

    /**
     * 发布
     */
    fun pushDynamic() {

    }
}