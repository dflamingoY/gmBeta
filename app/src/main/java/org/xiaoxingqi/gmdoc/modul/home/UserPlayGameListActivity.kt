package org.xiaoxingqi.gmdoc.modul.home

import kotlinx.android.synthetic.main.activity_love_game_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

class UserPlayGameListActivity : BaseActivity<UserPresenter>() {
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {})
    }

    override fun setContent() {
        setContent(R.layout.activity_love_game_list)
    }

    override fun initView() {
        tv_Title.text = "在玩"
    }

    override fun initData() {
    }

    override fun initEvent() {
        viewBack.setOnClickListener { finish() }
    }
}