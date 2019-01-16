package org.xiaoxingqi.gmdoc.modul.global

import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.global.WriteCallback
import org.xiaoxingqi.gmdoc.presenter.global.WritePresenter

class WriteShortCommentActivity : BaseActivity<WritePresenter>() {
    override fun createPresent(): WritePresenter {
        return WritePresenter(this, object : WriteCallback() {})
    }

    override fun setContent() {
        setContent(R.layout.activity_write_short_comment)
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initEvent() {
    }
}