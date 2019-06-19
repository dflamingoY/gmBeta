package org.xiaoxingqi.gmdoc.presenter.global

import android.content.Context
import org.xiaoxingqi.gmdoc.impl.global.WebDetailsCallBack
import org.xiaoxingqi.gmdoc.presenter.BasePresenter

class WebPresenter : BasePresenter {

    private var callBack: WebDetailsCallBack? = null

    /**
     *回掉参数
     */
    constructor(context: Context, callback: WebDetailsCallBack) : super(context) {
        this.callBack = callBack
    }

}