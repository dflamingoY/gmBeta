package org.xiaoxingqi.gmdoc.modul.home

import android.view.View
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.RecommendData
import org.xiaoxingqi.gmdoc.impl.home.RecommendCallback
import org.xiaoxingqi.gmdoc.presenter.home.RecommendPresenter

class RecommendArtFragment : BaseFrag<RecommendPresenter>() {
    private var page = 0
    private var type = 1
    private lateinit var adapter: QuickAdapter<RecommendData.RecommendBean>
    private val mData by lazy { ArrayList<RecommendData.RecommendBean>() }

    override fun createPresent(): RecommendPresenter {
        return RecommendPresenter(context, object : RecommendCallback {
            override fun recommendList(data: RecommendData) {

            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_type_circle
    }

    override fun initView(view: View?) {
    }

    override fun initData() {
        arguments?.let {
            type = it.getInt("type")
        }
        presenter?.getData(type, page)
    }

    override fun bindEvent() {
    }

    override fun request(flag: Int) {
    }
}