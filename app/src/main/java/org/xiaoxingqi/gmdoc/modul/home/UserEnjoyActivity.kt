package org.xiaoxingqi.gmdoc.modul.home

import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

/**
 * 关于用户的赞/收藏/黑名单
 */
class UserEnjoyActivity : BaseActivity<UserPresenter>() {
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {})
    }

    override fun setContent() {
        setContent(R.layout.activity_enjoy)
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initEvent() {
    }
}