package org.xiaoxingqi.gmdoc.presenter.home

import android.content.Context
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.RecommendData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.RecommendCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import org.xiaoxingqi.gmdoc.rx.BaseSubscriber

class RecommendPresenter(context: Context?, val callback: RecommendCallback?) : BasePresenter(context) {

    fun getData(type: Int, page: Int) {
        addObserve(apiServer.baseGet("recommend_list${IConstant.GET_END}&type=$type&page=$page"), object : BaseSubscriber<String>() {
            override fun onNext(t: String) {
                callback?.recommendList(JSON.parseObject(t, RecommendData::class.java))
            }
        })
    }
}