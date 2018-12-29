package org.xiaoxingqi.gmdoc.parsent.home

import android.app.Activity
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.UserInfoCallBack
import org.xiaoxingqi.gmdoc.parsent.BasePresent
import rx.Subscriber

class HomeUserInfoPersent : BasePresent {
    private var callBack: UserInfoCallBack? = null

    constructor(activity: Activity, callback: UserInfoCallBack) : super(activity) {
        this.callBack = callBack
    }

    fun getUserInfo(userId: String) {
        addObaser(apiServer.get_UserInfo("user_info/$userId${IConstant.GET_END}"), object : Subscriber<UserInfoData>() {
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