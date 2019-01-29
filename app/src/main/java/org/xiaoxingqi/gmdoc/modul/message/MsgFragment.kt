package org.xiaoxingqi.gmdoc.modul.message

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.frag_msg.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.msg.BaseMsgListBean
import org.xiaoxingqi.gmdoc.entity.msg.MsgInfoListData
import org.xiaoxingqi.gmdoc.impl.msg.MessageCallback
import org.xiaoxingqi.gmdoc.presenter.msg.MessagePresenter
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.textView.EmojiTextView

/**
 * 消息
 */
class MsgFragment : BaseFrag<MessagePresenter>() {
    private lateinit var headView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuickAdapter<BaseMsgListBean>
    private val mData by lazy { ArrayList<BaseMsgListBean>() }
    override fun createPresent(): MessagePresenter {
        return MessagePresenter(activity!!, object : MessageCallback() {
            override fun msgList(data: MsgInfoListData) {
                for (bean in data.data.service) {
                    bean.type = 2
                    mData.add(bean)
                }
                for (bean in data.data.list) {
                    mData.add(bean)
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_msg
    }

    override fun initView(view: View?) {
        recyclerView = view!!.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        headView = LayoutInflater.from(activity).inflate(R.layout.layout_msg_head, recyclerView, false)
    }

    override fun initData() {
        adapter = object : QuickAdapter<BaseMsgListBean>(activity, R.layout.item_msg, mData, headView) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseMsgListBean?) {
                Glide.with(activity)
                        .load(item!!.img)
                        .error(R.mipmap.img_avatar_default)
                        .into(helper!!.getImageView(R.id.iv_userLogo))
                helper.getView(R.id.viewUnRead).visibility = if (item.unread == 0) View.GONE else View.VISIBLE
                helper.getTextView(R.id.tv_name).text = item.name
                if (item.msg.is_pic == 1) {
                    helper.getTextView(R.id.tv_content).text = "[图片]"
                } else {
                    (helper.getTextView(R.id.tv_content) as EmojiTextView).setDataText(activity!!.assets, item.msg.content)
                }
                helper.getTextView(R.id.tv_Time).text = TimeUtils.getInstance().parseTime(item.msg.created_at)
            }
        }
        recyclerView.adapter = adapter
        persent?.getMsgList()
    }

    override fun bindEvent() {
        adapter.setOnItemClickListener { view, position ->
            startActivity(Intent(activity, ChatActivity::class.java)
                    .putExtra("userId", mData[position].id.toString())
                    .putExtra("type", mData[position].type)
                    .putExtra("name", mData[position].name)
            )
        }
    }

    override fun request(flag: Int) {

    }
}