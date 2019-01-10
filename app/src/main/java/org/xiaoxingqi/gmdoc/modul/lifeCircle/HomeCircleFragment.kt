package org.xiaoxingqi.gmdoc.modul.lifeCircle

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.frag_type_circle.view.*
import me.dkzwm.widget.srl.MaterialSmoothRefreshLayout
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import me.dkzwm.widget.srl.config.Constants
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.BaseSimpleData
import org.xiaoxingqi.gmdoc.entity.RespFansData
import org.xiaoxingqi.gmdoc.entity.ThumbData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.home.TypeFragCallback
import org.xiaoxingqi.gmdoc.modul.home.UserHomeActivity
import org.xiaoxingqi.gmdoc.parsent.home.TypeFragPersenter
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.homegame.ArticleListView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeDynamicView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeLongCommentView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeShortCommentView
import org.xiaoxingqi.gmdoc.wegidt.smallHeart.SmallBang

/**
 * 首頁關注圈的界面
 */
class HomeCircleFragment : BaseFrag<TypeFragPersenter>() {
    private var chooseType = "0"
    private var groupId = "0"
    private var current = 1
    private var operatBean: HomeUserShareData.ContributeBean? = null
    private lateinit var recycler: RecyclerView
    private lateinit var refresh: MaterialSmoothRefreshLayout
    private lateinit var headView: RecyclerView
    private lateinit var adapter: QuickAdapter<HomeUserShareData.ContributeBean>
    private val mData by lazy { ArrayList<HomeUserShareData.ContributeBean>() }
    private lateinit var headAdapter: QuickAdapter<BaseSimpleData>
    private val headData by lazy { ArrayList<BaseSimpleData>() }
    private val map by lazy { HashMap<String, String>() }

    override fun createPresent(): TypeFragPersenter {
        return TypeFragPersenter(activity!!, object : TypeFragCallback() {
            override fun thumbCallback(data: ThumbData?, view: View?) {
                data?.let {
                    if (it.state == 200) {
                        SmallBang.attach2Window(activity).bang(view!!.findViewById<View>(R.id.iv_Thumb), 60f, null)
                        operatBean?.like_status = data.data.like_status
                        view.findViewById<View>(R.id.iv_Thumb).isSelected = data.data.like_status == 1
                        (view.findViewById<View>(R.id.tv_Thumb) as TextView).setTextColor(if (data.data.like_status == 1) resources.getColor(R.color.color_shallow_yellow) else resources.getColor(R.color.color_text_color))
                        (view.findViewById<View>(R.id.tv_Thumb) as TextView).text = if (data.data.like_count > 0) "" + data.data.like_count else "赞"
                    }
                }
            }

            override fun callTypeData(data: HomeUserShareData?) {

                if (data != null && data.data.data != null) {
                    if (current == 0) {
                        mData.clear()
                        mData.addAll(data.data.data)
                        adapter.notifyDataSetChanged()
                    } else {
                        for (bean in data.data.data) {
                            mData.add(bean)
                            adapter.notifyItemInserted(adapter.itemCount - 1)
                        }
                    }
                }
                refresh.refreshComplete()
            }

            override fun callHotUser(data: RespFansData?) {
                if (data != null && data.data.data != null) {
                    headData.add(BaseSimpleData())
                    headData.addAll(data.data.data)
                    headAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_type_circle
    }

    override fun initView(view: View?) {
        recycler = view!!.recyclerView
        refresh = view.swipeRefresh
        recycler.layoutManager = LinearLayoutManager(activity)
        headView = LayoutInflater.from(activity).inflate(R.layout.layout_circle_head, recycler, false) as RecyclerView
        refresh.setDisableLoadMore(false)
        refresh.materialStyle()
        refresh.setEnableAutoLoadMore(true)
        refresh.setEnableSmoothRollbackWhenCompleted(true)
        refresh.setDisableLoadMoreWhenContentNotFull(true)
        refresh.autoRefresh(Constants.ACTION_NOTIFY, true)
    }

    override fun initData() {
        arguments?.getString("chooseType")?.let {
            chooseType = it
        }
        arguments?.getString("groupId")?.let {
            groupId = it
        }
        headAdapter = object : QuickAdapter<BaseSimpleData>(activity, R.layout.item_circle_top_user, headData) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseSimpleData?) {
                val data = item as BaseSimpleData
                if (TextUtils.isEmpty(data.avatar) && TextUtils.isEmpty(data.name)) {
                    helper!!.getView(R.id.cardLogo).setBackgroundResource(0)
                    helper.getTextView(R.id.tv_UserName).text = "关注更多人"
                    helper.getTextView(R.id.tv_UserName).setTextColor(resources.getColor(R.color.color_shallow_yellow))
                    Glide.with(activity)
                            .load(R.mipmap.btn_user_more)
                            .error(R.mipmap.btn_user_more)
                            .placeholder(R.mipmap.btn_user_more)
                            .into(helper.getImageView(R.id.iv_userLogo))
                } else {
                    helper!!.getView(R.id.cardLogo).setBackgroundResource(R.drawable.shape_red_circle)
                    helper.getTextView(R.id.tv_UserName).setTextColor(Color.parseColor("#4a4a4a"))
                    Glide.with(activity)
                            .load(data.avatar)
                            .error(R.mipmap.img_avatar_default)
                            .into(helper.getImageView(R.id.iv_userLogo))
                    helper.getTextView(R.id.tv_UserName).text = data.name
                }
            }
        }
        headView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        headView.adapter = headAdapter
        adapter = object : QuickAdapter<HomeUserShareData.ContributeBean>(activity, R.layout.item_dynamic, mData, headView) {
            override fun convert(helper: BaseAdapterHelper?, item: HomeUserShareData.ContributeBean?) {
                Glide.with(this@HomeCircleFragment)
                        .load(item!!.avatar)
                        .override(80, 80)
                        .into(helper!!.getImageView(R.id.iv_UserLogo))
                helper.getTextView(R.id.tv_CreateTime).text = TimeUtils.getInstance().paserTime(item.created_at)
                helper.getTextView(R.id.tv_UserName).text = item.username
                helper.getTextView(R.id.tv_loveGame).text = "(${item.like_game.split(" ")[0]})"
                helper.getImageView(R.id.iv_Flag).visibility = if (item.is_ori == 0) View.GONE else View.VISIBLE
                if (item.type == 1 || item.type == 2) {
                    helper.getImageView(R.id.iv_Flag).setImageResource(if ("1" == item.ranking) R.mipmap.img_1st else if ("2" == item.ranking) R.mipmap.img_2nd else if ("3" == item.ranking) R.mipmap.img_3rd else 0)
                } else {
                    helper.getImageView(R.id.iv_Flag).setImageResource(0)
                }
                helper.getView(R.id.tv_Private).visibility = if (item.power == 3) View.VISIBLE else View.GONE
                helper.getImageView(R.id.iv_Thumb).isSelected = item.like_status == 1
                helper.getTextView(R.id.tv_Thumb).setTextColor(if (item.like_status == 1) resources.getColor(R.color.color_shallow_yellow) else resources.getColor(R.color.color_text_color))
                helper.getTextView(R.id.tv_Thumb).text = if (item.like_num > 0) "" + item.like_num else "赞"
                helper.getImageView(R.id.iv_pay_leg).isSelected = item.pay_status == 1
                helper.getTextView(R.id.tv_AddLeg).text = if (item.pay_status == 1) "已加" else "加鸡腿"
                helper.getTextView(R.id.tv_AddLeg).setTextColor(if (item.pay_status == 1) resources.getColor(R.color.color_shallow_yellow) else resources.getColor(R.color.color_text_color))
                helper.getTextView(R.id.tv_CommentCount).text = if (item.comment_num > 0) "${item.comment_num}" else "评论"
                helper.getTextView(R.id.tv_Report).text = if (item.forward_num > 0) item.forward_num.toString() else "转发"
                helper.getView(R.id.linear_comment).isEnabled = item.no_comment != 1
                helper.getView(R.id.linear_comment).alpha = if (item.no_comment == 1) 0.5f else 1f
                helper.getView(R.id.linear_aircraft).isEnabled = item.no_forward != 1
                helper.getView(R.id.linear_aircraft).alpha = if (item.no_forward == 1) 0.5f else 1f
                val container = helper.getView(R.id.frameContainer) as FrameLayout
                container.removeAllViews()
                when (item.type) {
                    0 -> {
                        val dynamicView = HomeDynamicView(activity!!)
                        dynamicView.setData(item)
                        container.addView(dynamicView)
                    }
                    1 -> {
                        val shortCommentView = HomeShortCommentView(context)
                        shortCommentView.setData(item)
                        container.addView(shortCommentView)
                    }
                    2 -> {
                        val longCommentView = HomeLongCommentView(context)
                        longCommentView.setData(item)
                        container.addView(longCommentView)
                    }
                    3 -> {
                        val articleListView = ArticleListView(context)
                        articleListView.setData(item)
                        container.addView(articleListView)
                    }
                }
                helper.getImageView(R.id.iv_UserLogo).setOnClickListener {
                    startActivity(Intent(activity, UserHomeActivity::class.java).putExtra("userId", item.uid))
                }
                helper.getView(R.id.linear_thumb).setOnClickListener {
                    operatBean = item
                    map.clear()
                    map["_token"] = App.s_Token!!
                    map["type"] = "1"
                    map["from_id"] = item.id
                    persent?.thumb(map, it)
                }
            }
        }
        recycler.adapter = adapter
        persent?.getHotUser()
        persent?.getData(current, chooseType, groupId)
    }

    override fun bindEvent() {
        refresh.setOnRefreshListener(object : RefreshingListenerAdapter() {
            override fun onRefreshing() {
                current = 0
                persent?.getData(current, chooseType, groupId)
            }

            override fun onLoadingMore() {
                current++
                persent?.getData(current, chooseType, groupId)
            }
        })
    }

    override fun request(flag: Int) {

    }

    override fun onPause() {
        super.onPause()
        if (refresh.isRefreshing) {
            persent?.onDetach()
        }
    }

}