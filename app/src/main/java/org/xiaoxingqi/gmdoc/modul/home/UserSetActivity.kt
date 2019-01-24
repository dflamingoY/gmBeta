package org.xiaoxingqi.gmdoc.modul.home

import android.content.Intent
import kotlinx.android.synthetic.main.activity_user_set.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseAct

class UserSetActivity : BaseAct() {

    override fun setContent() {
        setContent(R.layout.activity_user_set)
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initEvent() {
        relative_Recommend.setOnClickListener {
            startActivity(Intent(this, UserCircleMoreActivity::class.java))
        }
        relative_OpenReaderCount.setOnClickListener {
            startActivity(Intent(this, UserVisibleReaderActivity::class.java))
        }
        viewBack.setOnClickListener {
            finish()
        }
        relativeUserInfo.setOnClickListener {
            startActivity(Intent(this, EditUserInfoActivity::class.java))
        }

    }

    override fun request() {
    }
}