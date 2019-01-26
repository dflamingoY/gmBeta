package org.xiaoxingqi.gmdoc.modul.message

import kotlinx.android.synthetic.main.activity_chat.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.msg.MessageCallback
import org.xiaoxingqi.gmdoc.presenter.msg.MessagePresenter

class ChatActivity : BaseActivity<MessagePresenter>() {
    private lateinit var userId: String
    override fun createPresent(): MessagePresenter {
        return MessagePresenter(this, object : MessageCallback() {

        })
    }

    override fun setContent() {
        setContent(R.layout.activity_chat)
    }

    override fun initView() {

    }

    override fun initData() {
        userId = intent.getStringExtra("userId")
        tv_FromUser.text = "和 ${intent.getStringExtra("name")}聊天中..."
        intent.getIntExtra("type", 1)

    }

    override fun initEvent() {

    }
}