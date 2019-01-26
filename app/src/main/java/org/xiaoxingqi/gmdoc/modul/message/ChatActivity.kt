package org.xiaoxingqi.gmdoc.modul.message

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.msg.BaseMsgDetailsBean
import org.xiaoxingqi.gmdoc.entity.msg.MsgDetailsData
import org.xiaoxingqi.gmdoc.impl.msg.MessageCallback
import org.xiaoxingqi.gmdoc.presenter.msg.MessagePresenter

class ChatActivity : BaseActivity<MessagePresenter>() {
    private lateinit var userId: String
    private var current = 1
    private lateinit var adapter: QuickAdapter<BaseMsgDetailsBean>
    private val mData by lazy { ArrayList<BaseMsgDetailsBean>() }
    override fun createPresent(): MessagePresenter {
        return MessagePresenter(this, object : MessageCallback() {
            override fun chatList(data: MsgDetailsData) {

            }
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
        adapter = object : QuickAdapter<BaseMsgDetailsBean>(this, R.layout.item_chat_msg, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseMsgDetailsBean?) {


            }
        }
        recyclerContent.layoutManager = LinearLayoutManager(this)
        recyclerContent.adapter = adapter
        persent?.getChatList(userId, current)
    }

    override fun initEvent() {
        viewBack.setOnClickListener {
            finish()
        }
    }
}