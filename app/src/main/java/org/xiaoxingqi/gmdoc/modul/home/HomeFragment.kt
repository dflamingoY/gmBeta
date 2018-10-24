package org.xiaoxingqi.gmdoc.modul.home

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.frag_home.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.BaseHomeAdapter
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.BaseHomeBean
import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData
import org.xiaoxingqi.gmdoc.entity.home.HomeGameData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.home.HomeTabCallback
import org.xiaoxingqi.gmdoc.parsent.HomePresent
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.wegidt.ItemHomeView

class HomeFragment : BaseFrag<HomePresent>() {
    private lateinit var gameAdapter: BaseHomeAdapter<List<BaseHomeBean>, BaseAdapterHelper>
    private lateinit var adapter: QuickAdapter<HomeUserShareData.ContributeBean>
    private lateinit var refresh: SwipeRefreshLayout
    private lateinit var gameRecycler: RecyclerView
    private val gameData by lazy {
        ArrayList<List<BaseHomeBean>>()
    }
    private lateinit var headView: View
    private val mData by lazy {
        ArrayList<HomeUserShareData.ContributeBean>()
    }
    private val gameTitleData by lazy {
        ArrayList<String>()
    }

    override fun createPresent(): HomePresent {
        return HomePresent(activity!!, object : HomeTabCallback() {
            override fun gameSuccess(data: HomeGameData?) {
                refresh.isRefreshing = false
                gameData.clear()
                data?.data?.dy_top_big
                Glide.with(this@HomeFragment)
                        .load(data?.data?.dy_top_big?.get(0)?.cover)
                        .into(headView.findViewById(R.id.iv_topImg))
                for (indices in data?.data?.game?.data!!.indices) {//遍歷游戏数据在不同的位置插入 新闻
                    if (indices == 2) {
                        gameData.add(data?.data?.dy_long!!)
                    } else if (indices == 5) {
                        gameData.add(data?.data?.dy_blog!!)
                    }
                    gameData.add(data?.data?.game?.data!![indices])
                }
                /**
                 * update recyclerView
                 */
                gameAdapter.notifyDataSetChanged()
            }

            override fun activeSuccess(data: HomeActiveData?) {//6个活动的type

            }

            override fun contibuteSuccess(data: HomeUserShareData?) {
                mData.addAll(data?.data?.data!!)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_home
    }

    override fun initView(view: View?) {
        mView!!.recyclerView.layoutManager = LinearLayoutManager(activity)
        mView!!.recyclerView.isNestedScrollingEnabled = false
        headView = LayoutInflater.from(activity).inflate(R.layout.home_heard_layout, mView!!.recyclerView, false)
        mView!!.relativeAction.alpha = 0f
        refresh = view!!.refresh
        gameRecycler = headView.findViewById(R.id.recyclerView)
    }

    override fun initData() {
        persent?.let {
            it.getActionData()
            it.getAttributeData(0)
            it.getGameData()
        }
        gameAdapter = object : BaseHomeAdapter<List<BaseHomeBean>, BaseAdapterHelper>(activity, gameData) {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AppTools.dp2px(activity, 152))
            override fun convert(helper: BaseAdapterHelper?, item: List<BaseHomeBean>?) {
                if (getItemViewType(helper!!.itemView.tag as Int) == 1) {//圖片

                } else {//游戏列表
                    val linearContainer = helper!!.itemView.findViewById<LinearLayout>(R.id.linearContainer)
                    linearContainer.removeAllViews()
                    if (item != null) {
                        for (bean in item) {
                            val gameItem = ItemHomeView(activity!!)
                            gameItem.setData(bean)
                            linearContainer.addView(gameItem, params)
                        }
                    }
                }
            }
        }
        gameRecycler.layoutManager = LinearLayoutManager(activity)
        gameRecycler.isNestedScrollingEnabled = false
//        gameRecycler.adapter = gameAdapter
        adapter = object : QuickAdapter<HomeUserShareData.ContributeBean>(activity, R.layout.item_dynamic, mData, headView) {
            override fun convert(helper: BaseAdapterHelper?, item: HomeUserShareData.ContributeBean?) {

            }
        }
        mView!!.recyclerView.adapter = adapter
    }

    override fun bindEvent() {
        refresh.setOnRefreshListener {
            persent?.let {
                it.getActionData()
                it.getAttributeData(0)
                it.getGameData()
            }
        }
    }

    override fun request(flag: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}