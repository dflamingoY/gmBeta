package org.xiaoxingqi.gmdoc.presenter.home

import android.app.Activity
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.UserInfoCallBack
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

class HomeUserInfoPersent : BasePresenter {
    private var callBack: UserInfoCallBack? = null

    constructor(activity: Activity, callback: UserInfoCallBack) : super(activity) {
        this.callBack = callback
    }

    fun getUserInfo(userId: String) {
        addObserve(apiServer.getUserInfo("user_info/$userId${IConstant.GET_END}"), object : Subscriber<UserInfoData>() {
            override fun onNext(t: UserInfoData?) {
                callBack?.userInfo(t)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }


}