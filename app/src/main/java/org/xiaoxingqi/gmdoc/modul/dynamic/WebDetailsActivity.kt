package org.xiaoxingqi.gmdoc.modul.dynamic

import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.global.WebDetailsCallBack
import org.xiaoxingqi.gmdoc.presenter.global.WebPresenter

/**
 * 文章详情
 */
class WebDetailsActivity : BaseActivity<WebPresenter>() {

    override fun createPresent(): WebPresenter {
        return  WebPresenter(this,object : WebDetailsCallBack(){
            override fun setData(data: Any) {

            }
        })
    }

    override fun setContent() {

    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {

    }
}