package org.xiaoxingqi.gmdoc.modul.lifeCircle

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.frag_type_circle.view.*
import me.dkzwm.widget.srl.MaterialSmoothRefreshLayout
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.ThumbData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.home.TypeFragCallback
import org.xiaoxingqi.gmdoc.modul.dynamic.DynamicDetailsActivity
import org.xiaoxingqi.gmdoc.modul.home.UserHomeActivity
import org.xiaoxingqi.gmdoc.parsent.home.TypeFragPersenter
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.homegame.ArticleListView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeDynamicView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeLongCommentView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeShortCommentView

class TypeCircleFragment : BaseFrag<TypeFragPersenter>() {
    private var chooseType = ""
    private var userId: String = ""
    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshLayout: MaterialSmoothRefreshLayout
    private lateinit var adapter: QuickAdapter<HomeUserShareData.ContributeBean>
    private val mData by lazy { ArrayList<HomeUserShareData.ContributeBean>() }
    private var current = 1
    override fun createPresent(): TypeFragPersenter {
        return TypeFragPersenter(activity!!, object : TypeFragCallback() {

            override fun thumbCallback(data: ThumbData?, view: View?) {

            }

            override fun callTypeData(data: HomeUserShareData?) {
                if (data!!.data.data != null && data.data.data.size > 0) {
                    if (current == 0) {
                        mData.clear()
                        mData.addAll(data!!.data.data)
                        adapter.notifyDataSetChanged()
                    } else {
                        for (bean in data.data.data) {
                            mData.add(bean)
                            adapter.notifyItemInserted(adapter.itemCount - 1)
                        }
                    }
                }
                refreshLayout.refreshComplete()
            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_type_circle
    }

    override fun initView(view: View?) {
        recyclerView = view!!.recyclerView
        refreshLayout = view.swipeRefresh
        refreshLayout.setDisableLoadMore(false)
        refreshLayout.materialStyle()
        refreshLayout.setEnableAutoLoadMore(true)
        refreshLayout.setEnableSmoothRollbackWhenCompleted(true)
        refreshLayout.setDisableLoadMoreWhenContentNotFull(true)
        refreshLayout.autoRefresh(false)
    }

    override fun initData() {
        arguments?.getString("chooseType")?.let {
            chooseType = it
        }
        arguments?.getString("userId")?.let {
            userId = it
        }
        adapter = object : QuickAdapter<HomeUserShareData.ContributeBean>(activity, R.layout.item_dynamic, mData) {
            @SuppressLint("SetTextI18n")
            override fun convert(helper: BaseAdapterHelper?, item: HomeUserShareData.ContributeBean?) {
                Glide.with(this@TypeCircleFragment)
                        .load(item!!.avatar)
                        .override(80, 80)
                        .into(helper!!.getImageView(R.id.iv_UserLogo))
                helper.getTextView(R.id.tv_CreateTime).text = TimeUtils.getInstance().paserTime(item.created_at)
                helper.getTextView(R.id.tv_UserName).text = item.username
                helper.getTextView(R.id.tv_loveGame).text = "(${item.like_game.split(" ")[0]})"
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
                    if (userId != item.uid)
                        startActivity(Intent(activity, UserHomeActivity::class.java).putExtra("userId", item.uid))
                }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        persent?.queryData(current, userId, chooseType)
    }

    override fun bindEvent() {
        refreshLayout.setOnRefreshListener(object : RefreshingListenerAdapter() {
            override fun onRefreshing() {
                current = 0
                persent?.queryData(current, userId, chooseType)
            }

            override fun onLoadingMore() {
                current++
                persent?.queryData(current, userId, chooseType)
            }
        })
        adapter.setOnItemClickListener { _, position ->
            startActivity(Intent(activity, DynamicDetailsActivity::class.java).putExtra("dynamicId", mData[position].id))
        }
    }

    override fun request(flag: Int) {

    }


}