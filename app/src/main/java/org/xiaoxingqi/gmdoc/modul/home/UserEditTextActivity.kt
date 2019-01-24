package org.xiaoxingqi.gmdoc.modul.home

import android.content.Intent
import kotlinx.android.synthetic.main.activity_edit_text.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

/**
 * 用户发过的 短 长 博文
 */
class UserEditTextActivity : BaseActivity<UserPresenter>() {
    private var userId: String? = null
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {})
    }

    override fun setContent() {
        setContent(R.layout.activity_edit_text)
    }

    override fun initView() {

    }

    override fun initData() {
        userId = intent.getStringExtra("userId")
    }

    override fun initEvent() {
        viewMoreShort.setOnClickListener {
            startActivity(Intent(this, UserShortListActivity::class.java).putExtra("userId", userId))
        }
        viewMoreLong.setOnClickListener {
            startActivity(Intent(this, UserLongListActivity::class.java).putExtra("userId", userId))
        }
        viewMoreBowen.setOnClickListener {
            startActivity(Intent(this, UserBlogListActivity::class.java).putExtra("userId", userId))
        }
        viewMorePhoto.setOnClickListener {

        }
        viewMoreShare.setOnClickListener {

        }
    }
}