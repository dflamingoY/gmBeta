package org.xiaoxingqi.gmdoc.modul.home

import android.content.Intent
import kotlinx.android.synthetic.main.activity_user_album.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

class UserAlbumActivity : BaseActivity<UserPresenter>() {
    private lateinit var userId: String
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {})
    }

    override fun setContent() {
        setContent(R.layout.activity_user_album)
    }

    override fun initView() {

    }

    override fun initData() {
        userId = intent.getStringExtra("userId")
    }

    override fun initEvent() {
        viewMorePhoto.setOnClickListener {
            startActivity(Intent(this, UserPhotoListActivity::class.java)
                    .putExtra("userId", userId))
        }
        viewMoreShare.setOnClickListener {
            startActivity(Intent(this, UserContributionActivity::class.java)
                    .putExtra("userId", userId))
        }
    }
}