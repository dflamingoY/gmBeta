package org.xiaoxingqi.gmdoc.modul.global

import android.content.Intent
import android.os.Handler
import org.xiaoxingqi.gmdoc.MainActivity
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseAct

class SplashActivity : BaseAct() {

    override fun setContent() {
//        setContent(R.layout.activity_splash)
    }

    override fun initView() {
    }

    override fun initData() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 1000L)
    }

    override fun initEvent() {
    }

    override fun request() {
    }
}