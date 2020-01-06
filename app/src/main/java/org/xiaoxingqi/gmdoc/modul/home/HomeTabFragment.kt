package org.xiaoxingqi.gmdoc.modul.home

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.frag_type_circle.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.home.HomeTabActiveCallback
import org.xiaoxingqi.gmdoc.presenter.home.HomeTabFragPresenter

class HomeTabFragment : BaseFrag<HomeTabFragPresenter>() {
    private var page = 0
    private var userId = -1
    private var type = 1
    private lateinit var adapter: QuickAdapter<HomeUserShareData.ContributeBean>

    private val mData by lazy { ArrayList<HomeUserShareData.ContributeBean>() }
    override fun createPresent(): HomeTabFragPresenter {
        return HomeTabFragPresenter(context, object : HomeTabActiveCallback {
            override fun list(any: HomeUserShareData) {
                any.data.data?.let {
                    mData.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_type_circle
    }

    override fun initView(view: View?) {
        view!!.swipeRefresh.setDisableRefresh(true)
        view.swipeRefresh.setEnableOverScroll(false)
    }

    override fun initData() {
        arguments?.let {
            userId = it.getInt("id")
            type = it.getInt("type")
        }
        adapter = object : QuickAdapter<HomeUserShareData.ContributeBean>(context, R.layout.item_dynamic, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: HomeUserShareData.ContributeBean?) {

            }
        }
        mView!!.recyclerView.layoutManager = LinearLayoutManager(context)
        mView!!.recyclerView.adapter = adapter
        presenter?.queryList(id = userId, type = type, page = page)
    }

    override fun bindEvent() {

    }


    override fun request(flag: Int) {

    }
}