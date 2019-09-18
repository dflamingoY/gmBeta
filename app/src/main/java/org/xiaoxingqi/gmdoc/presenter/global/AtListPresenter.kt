package org.xiaoxingqi.gmdoc.presenter.global

import android.content.Context
import org.xiaoxingqi.gmdoc.impl.global.AtlistCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter

class AtListPresenter : BasePresenter {
    private var callback: AtlistCallback? = null

    constructor(context: Context, callback: AtlistCallback) : super(context) {
        this.callback = callback
    }

}