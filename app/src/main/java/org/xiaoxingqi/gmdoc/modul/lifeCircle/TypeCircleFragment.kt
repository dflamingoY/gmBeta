package org.xiaoxingqi.gmdoc.modul.lifeCircle

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.frag_type_circle.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.home.TypeFragCallback
import org.xiaoxingqi.gmdoc.parsent.home.TypeFragPersenter

class TypeCircleFragment : BaseFrag<TypeFragPersenter>() {
    private var chooseType = ""
    private var userId: String = ""
    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var adapter: QuickAdapter<HomeUserShareData.ContributeBean>
    private val mData by lazy { ArrayList<HomeUserShareData.ContributeBean>() }
    override fun createPresent(): TypeFragPersenter {
        return TypeFragPersenter(activity!!, object : TypeFragCallback {
            override fun callTypeData(data: HomeUserShareData?) {
                mData.addAll(data!!.data.data)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_type_circle
    }

    override fun initView(view: View?) {
        recyclerView = view!!.recyclerView
        refreshLayout = view!!.swipeRefresh

    }

    override fun initData() {
        arguments?.getString("chooseType")?.let {
            chooseType = it
        }
        arguments?.getString("userId")?.let {
            userId = it
        }
        adapter = object : QuickAdapter<HomeUserShareData.ContributeBean>(activity, R.layout.item_dynamic, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: HomeUserShareData.ContributeBean?) {


            }
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        persent?.queryData(0, userId!!, chooseType)
    }

    override fun bindEvent() {

    }

    override fun request(flag: Int) {

    }


}