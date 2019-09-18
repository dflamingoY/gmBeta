package org.xiaoxingqi.gmdoc.presenter.login

import android.content.Context
import org.xiaoxingqi.gmdoc.entity.login.LoginUserData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.login.LoginCallBack
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

class LoginPresenter : BasePresenter {
    private var callBack: LoginCallBack? = null

    constructor(context: Context, callBack: LoginCallBack) : super(context) {
        this.callBack = callBack
    }

    fun login(map: Map<String, String>) {
        addObserve(apiServer.postLogin("login${IConstant.GET_END}", map), object : Subscriber<LoginUserData>() {
            override fun onNext(t: LoginUserData?) {
                callBack?.loginCall(t)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }
}