package org.xiaoxingqi.gmdoc.modul.home

import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

/**
 * 用户发过的 短 长 博文
 */
class UserEditTextActivity : BaseActivity<UserPresenter>() {
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {})
    }

    override fun setContent() {
        setContent(R.layout.activity_edit_text)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {

    }
}