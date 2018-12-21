package org.xiaoxingqi.gmdoc.parsent.home

import android.app.Activity
import org.xiaoxingqi.gmdoc.impl.home.UserInfoCallBack
import org.xiaoxingqi.gmdoc.parsent.BasePresent

class HomeUserInfoPersent : BasePresent {
    private var callBack: UserInfoCallBack? = null

    constructor(activity: Activity, callback: UserInfoCallBack) : super(activity) {
        this.callBack = callBack
    }


}