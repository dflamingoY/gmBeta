package org.xiaoxingqi.gmdoc.modul.message

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_chat.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.backgroundResource
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.msg.BaseMsgDetailsBean
import org.xiaoxingqi.gmdoc.entity.msg.MsgCommentData
import org.xiaoxingqi.gmdoc.entity.msg.MsgDetailsData
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.eventbus.MsgUpdateEvent
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.msg.MessageCallback
import org.xiaoxingqi.gmdoc.listener.SoftkeyBoardManager
import org.xiaoxingqi.gmdoc.modul.common.ShowPicActivity
import org.xiaoxingqi.gmdoc.modul.home.UserHomeActivity
import org.xiaoxingqi.gmdoc.presenter.msg.MessagePresenter
import org.xiaoxingqi.gmdoc.tools.AppConfig
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.PreferenceTools
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.tools.time.DateUtils
import org.xiaoxingqi.gmdoc.wegidt.MsgThumbImageView
import org.xiaoxingqi.gmdoc.wegidt.textView.MsgTextView
import java.lang.Exception

class ChatActivity : BaseActivity<MessagePresenter>() {
    private lateinit var userId: String
    private var userInfo: UserInfoData? = null
    private var current = 1
    private var keyboardHeight: Int = 0
    private lateinit var manager: SoftkeyBoardManager
    private lateinit var adapter: QuickAdapter<BaseMsgDetailsBean>
    private val mData by lazy { ArrayList<BaseMsgDetailsBean>() }
    private val map by lazy { HashMap<String, String>() }
    override fun createPresent(): MessagePresenter {
        return MessagePresenter(this, object : MessageCallback() {
            override fun chatList(data: MsgDetailsData) {
                for (bean in data.data.data) {
                    mData.add(bean)
                    adapter.notifyItemInserted(adapter.itemCount - 1)
                }
                if (current == 1) {
                    recyclerContent.scrollToPosition(0)
                } else {
                    recyclerContent.scrollToPosition(mData.size - data.data.data.size)
                }
                swipeRefresh.isRefreshing = false
            }

            override fun sendSuccess(data: MsgCommentData) {
                et_Content.setText("")
                transLayout.showContent()
            }

            override fun onError(obj: Any?) {

                transLayout.showContent()
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_chat)
    }

    override fun initView() {

    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        userId = intent.getStringExtra("userId")
        userInfo = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
        tv_FromUser.text = "和 ${intent.getStringExtra("name")}聊天中..."
        manager = SoftkeyBoardManager(window.decorView, false)
        intent.getIntExtra("type", 1)
        adapter = object : QuickAdapter<BaseMsgDetailsBean>(this, R.layout.item_chat_msg, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseMsgDetailsBean?) {
                userInfo?.let {
                    val local = if (it.data.uid == item!!.from_uid) {//自己
                        helper!!.getView(R.id.cardLeft).visibility = View.GONE
                        helper.getView(R.id.cardRight).visibility = View.VISIBLE
                        Glide.with(this@ChatActivity)
                                .load(item.from_avatar)
                                .into(helper.getImageView(R.id.iv_rightimg))
                        helper.getView(R.id.frame_container).backgroundResource = R.drawable.shape_round_white_r_2
                        Gravity.RIGHT
                    } else {
                        helper!!.getView(R.id.cardLeft).visibility = View.VISIBLE
                        helper.getView(R.id.cardRight).visibility = View.GONE
                        Glide.with(this@ChatActivity)
                                .load(item.from_avatar)
                                .into(helper.getImageView(R.id.iv_leftimg))
                        helper.getView(R.id.frame_container).backgroundResource = R.drawable.shape_round_white_r_2
                        Gravity.LEFT
                    }
                    if (item.is_pic == 0) {
                        helper.getView(R.id.tvContent).visibility = View.VISIBLE
                        helper.getView(R.id.frame_ImgContainer).visibility = View.GONE
                        val params = helper.getView(R.id.tvContent).layoutParams as FrameLayout.LayoutParams
                        params.gravity = local
                        val msgTextView = helper.getView(R.id.tvContent) as MsgTextView
                        msgTextView.setTextData(AppConfig.getImageHtml(item.content))
                    } else {
                        helper.getView(R.id.tvContent).visibility = View.GONE
                        helper.getView(R.id.frame_ImgContainer).visibility = View.VISIBLE
                        val params = helper.getView(R.id.iv_showPic).layoutParams as FrameLayout.LayoutParams
                        params.gravity = local
                        val msgThumbImageView = helper.getView(R.id.iv_showPic) as MsgThumbImageView
                        msgThumbImageView.loadAsPath(item.content + "?imageMogr2/auto-orient/thumbnail/!200x200r", getImageMaxEdge(), getImageMinEdge(), R.drawable.message_item_round_bg, item.content + "?imageMogr2/auto-orient/thumbnail/!200x200r")
                    }
                }
                val position = helper!!.itemView.tag as Int
                try {
                    if (position != mData.size - 1) {
                        if (DateUtils.isCloseEnough(TimeUtils.getInstance().parseString(item!!.date), TimeUtils.getInstance().parseString(mData[position + 1].date))) {
                            helper.getTextView(R.id.tv_Time).visibility = View.GONE
                        } else {
                            helper.getTextView(R.id.tv_Time).visibility = View.VISIBLE
                            helper.getTextView(R.id.tv_Time).text = TimeUtils.getInstance().parseTime(item!!.date)
                        }
                    } else {
                        helper.getTextView(R.id.tv_Time).visibility = View.VISIBLE
                        helper.getTextView(R.id.tv_Time).text = TimeUtils.getInstance().parseTime(item!!.date)
                    }
                } catch (e: Exception) {
                    helper.getTextView(R.id.tv_Time).visibility = View.VISIBLE
                    helper.getTextView(R.id.tv_Time).text = TimeUtils.getInstance().parseTime(item!!.date)
                }
                helper.getView(R.id.iv_showPic).setOnClickListener {
                    startActivity(Intent(this@ChatActivity, ShowPicActivity::class.java).putExtra("path", item!!.content))
                    overridePendingTransition(0, 0)
                }
                helper.getImageView(R.id.iv_rightimg).setOnClickListener {
                    startActivity(Intent(this@ChatActivity, UserHomeActivity::class.java).putExtra("userId", item!!.from_uid))
                }
                helper.getImageView(R.id.iv_leftimg).setOnClickListener {
                    startActivity(Intent(this@ChatActivity, UserHomeActivity::class.java).putExtra("userId", item!!.from_uid))
                }
            }
        }
        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        manager.stackFromEnd = true//列表再底部开始展示，反转后由上面开始展示
        manager.reverseLayout = true//列表翻转
        recyclerContent.layoutManager = manager
        recyclerContent.requestDisallowInterceptTouchEvent(true)
        recyclerContent.overScrollMode = View.OVER_SCROLL_NEVER
        recyclerContent.adapter = adapter
        persent?.getChatList(userId, current)
    }

    override fun initEvent() {
        manager.addSoftKeyboardStateListener(keyboardListener)
        viewBack.setOnClickListener {
            finish()
        }
        swipeRefresh.setOnRefreshListener {
            current++
            persent?.getChatList(userId, current)
        }
        tv_Send.setOnClickListener {
            if (TextUtils.isEmpty(et_Content.text.toString().trim())) {
                showToast("消息不能为空")
                return@setOnClickListener
            }
            map["_token"] = App.s_Token!!
            map["uid"] = userId
            map["content"] = et_Content.text.toString().trim()
            map["is_pic"] = "0"
            transLayout.showProgress()
            persent?.sendMsg(map)
        }
    }

    private val keyboardListener = object : SoftkeyBoardManager.SoftKeyboardStateListener {
        override fun onSoftKeyboardOpened(keyboardHeightInPx: Int) {
            val statusBarHeight = AppTools.getStatusBarHeight(this@ChatActivity)
            val navigation = AppTools.getNavigationBarHeight(this@ChatActivity)
            keyboardHeight = keyboardHeightInPx - statusBarHeight - navigation
            recyclerContent.scrollToPosition(0)
            emojiView.visibility = View.INVISIBLE
            val params = emojiView.layoutParams
            if (params.height != keyboardHeight) {
                params.height = keyboardHeight
                emojiView.layoutParams = params
            }
        }

        override fun onSoftKeyboardClosed() {
            /**
             * 关闭的时候要判断是什么情况是否打开表情键盘
             */
            if (emojiView.visibility == View.VISIBLE || emojiView.visibility == View.INVISIBLE) {
                emojiView.visibility = View.GONE
            }
        }
    }


    private fun getImageMaxEdge(): Int {
        return (165.0 / 320.0 * AppTools.getWindowsWidth(this)).toInt()
    }

    private fun getImageMinEdge(): Int {
        return (76.0 / 320.0 * AppTools.getWindowsHeight(this)).toInt()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun msgEvent(event: MsgUpdateEvent) {
        /**
         * 解析信息
         * 收到新消息,
         */
        if (mData.size > 0) {

        }
    }
}