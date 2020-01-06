package org.xiaoxingqi.gmdoc.presenter.home

import android.content.Context
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.HomeTabActiveCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import org.xiaoxingqi.gmdoc.rx.BaseSubscriber

class HomeTabFragPresenter(context: Context?, val callBack: HomeTabActiveCallback? = null) : BasePresenter(context) {

    fun queryList(id: Int, type: Int, page: Int) {
        addObserve(apiServer.baseGet("activity_detail/$id${IConstant.GET_END}&type=$type&page=$page"), object : BaseSubscriber<String>() {
            override fun onNext(t: String) {
                callBack?.list(JSON.parseObject(t, HomeUserShareData::class.java))
            }
        })
    }

}