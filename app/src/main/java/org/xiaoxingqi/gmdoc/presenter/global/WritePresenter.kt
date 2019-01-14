package org.xiaoxingqi.gmdoc.presenter.global

import android.content.Context
import org.xiaoxingqi.gmdoc.impl.global.WriteCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter

class WritePresenter : BasePresenter {
    private var callback: WriteCallback? = null

    constructor(context: Context, callback: WriteCallback) : super(context) {
        this.callback = callback
    }

    fun send() {

    }

    fun sendPic() {

    }
}