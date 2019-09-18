package org.xiaoxingqi.gmdoc.modul.global

import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.global.AtlistCallback
import org.xiaoxingqi.gmdoc.presenter.global.AtListPresenter

class AtListActivity : BaseActivity<AtListPresenter>() {
    override fun createPresent(): AtListPresenter {
        return AtListPresenter(this, object : AtlistCallback {})
    }

    override fun setContent() {
        setContent(R.layout.activity_at_list)
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