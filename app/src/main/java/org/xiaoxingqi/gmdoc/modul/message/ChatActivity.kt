package org.xiaoxingqi.gmdoc.modul.message

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_chat.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.backgroundResource
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.msg.BaseMsgDetailsBean
import org.xiaoxingqi.gmdoc.entity.msg.MsgDetailsData
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.eventbus.MsgUpdateEvent
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.msg.MessageCallback
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
    private lateinit var adapter: QuickAdapter<BaseMsgDetailsBean>
    private val mData by lazy { ArrayList<BaseMsgDetailsBean>() }
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
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_chat)
    }

    override fun initView() {

    }

    override fun initData() {
        userId = intent.getStringExtra("userId")
        userInfo = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
        tv_FromUser.text = "和 ${intent.getStringExtra("name")}聊天中..."
        intent.getIntExtra("type", 1)
        adapter = object : QuickAdapter<BaseMsgDetailsBean>(this, R.layout.item_chat_msg, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseMsgDetailsBean?) {
                userInfo?.let {
                    val localtion = if (it.data.uid == item!!.from_uid) {//自己
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
                        params.gravity = localtion
                        val msgTextView = helper.getView(R.id.tvContent) as MsgTextView
                        msgTextView.setTextData(AppConfig.getImageHtml(item.content))
                    } else {
                        helper.getView(R.id.tvContent).visibility = View.GONE
                        helper.getView(R.id.frame_ImgContainer).visibility = View.VISIBLE
                        val params = helper.getView(R.id.iv_showPic).layoutParams as FrameLayout.LayoutParams
                        params.gravity = localtion
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
        viewBack.setOnClickListener {
            finish()
        }
        swipeRefresh.setOnRefreshListener {
            current++
            persent?.getChatList(userId, current)
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
         */


    }

}