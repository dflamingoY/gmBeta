package org.xiaoxingqi.gmdoc.modul.home

import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.home.UserInfoCallBack
import org.xiaoxingqi.gmdoc.parsent.home.HomeUserInfoPersent

class UserHomeActivity : BaseActivity<HomeUserInfoPersent>() {

    override fun createPresent(): HomeUserInfoPersent {
        return HomeUserInfoPersent(this, object : UserInfoCallBack() {

        })
    }

    override fun setContent() {
        setContent(R.layout.activity_user_info)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {

    }

    override fun request() {

    }
}