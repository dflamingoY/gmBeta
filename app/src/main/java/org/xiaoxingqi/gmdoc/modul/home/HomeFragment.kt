package org.xiaoxingqi.gmdoc.modul.home

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
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

class HomeFragment : BaseFrag<HomePresent>() {
    private lateinit var gameAdapter: BaseHomeAdapter<BaseHomeBean, BaseAdapterHelper>

    private lateinit var adapter: QuickAdapter<HomeUserShareData.ContributeBean>
    private val gameData by lazy {
        ArrayList<BaseHomeBean>()
    }
    private lateinit var headView: View
    private val mData by lazy {
        ArrayList<HomeUserShareData.ContributeBean>()
    }

    override fun createPresent(): HomePresent {
        return HomePresent(activity!!, object : HomeTabCallback() {
            override fun gameSuccess(data: HomeGameData?) {
                data?.data?.dy_top_big

                Glide.with(this@HomeFragment)
                        .load(data?.data?.dy_top_big?.get(0)?.cover)
//                        .into(headView.)
            }

            override fun activeSuccess(data: HomeActiveData?) {

            }

            override fun contibuteSuccess(data: HomeUserShareData?) {

            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_home
    }

    override fun initView(view: View?) {
        mView!!.recyclerView.layoutManager = LinearLayoutManager(activity)
        headView = LayoutInflater.from(activity).inflate(R.layout.home_heard_layout, mView!!.recyclerView, false)
        mView!!.relativeAction.alpha = 0f
    }

    override fun initData() {
        persent?.let {
            it.getActionData()
            it.getAttributeData(0)
            it.getGameData()
        }
        gameAdapter = object : BaseHomeAdapter<BaseHomeBean, BaseAdapterHelper>(activity, gameData) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseHomeBean?) {

            }
        }
        adapter = object : QuickAdapter<HomeUserShareData.ContributeBean>(activity, R.layout.item_dynamic, mData, headView) {
            override fun convert(helper: BaseAdapterHelper?, item: HomeUserShareData.ContributeBean?) {

            }
        }
        mView!!.recyclerView.adapter = adapter
    }

    override fun bindEvent() {

    }

    override fun request(flag: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}