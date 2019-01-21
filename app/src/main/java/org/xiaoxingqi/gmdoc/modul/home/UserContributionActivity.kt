package org.xiaoxingqi.gmdoc.modul.home

import kotlinx.android.synthetic.main.activity_photo_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

/**
 * 用户的贡献相册
 */
class UserContributionActivity : BaseActivity<UserPresenter>() {
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {})
    }

    override fun setContent() {
        setContent(R.layout.activity_photo_list)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {
        viewBack.setOnClickListener { finish() }
    }
}