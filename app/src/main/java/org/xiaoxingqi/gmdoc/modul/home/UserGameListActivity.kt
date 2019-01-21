package org.xiaoxingqi.gmdoc.modul.home

import android.content.Intent
import kotlinx.android.synthetic.main.activity_user_game_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

/**
 *
 * 用户的游戏列表
 */
class UserGameListActivity : BaseActivity<UserPresenter>() {
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {})
    }

    override fun setContent() {
        setContent(R.layout.activity_user_game_list)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {
        viewLoveGame.setOnClickListener {
            startActivity(Intent(this, LoveGameListActivity::class.java))
        }
        viewMoreWant.setOnClickListener {

        }
        viewMorePlaying.setOnClickListener {

        }
        viewWaitScore.setOnClickListener {

        }
    }
}