package org.xiaoxingqi.gmdoc.modul.lifeCircle

import android.view.View
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.home.TypeFragCallback
import org.xiaoxingqi.gmdoc.parsent.home.TypeFragPersenter

class TypeCircleFragment : BaseFrag<TypeFragPersenter>() {
    private var chooseType = ""
    private var userId: String = ""
    override fun createPresent(): TypeFragPersenter {
        return TypeFragPersenter(activity!!, object : TypeFragCallback {
            override fun callTypeData(data: HomeUserShareData?) {

            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_type_circle

    }

    override fun initView(view: View?) {

    }

    override fun initData() {
        arguments?.getString("chooseType")?.let {
            chooseType = it
        }
        arguments?.getString("userId")?.let {
            userId = it
        }
        persent?.queryData(0, userId!!, chooseType)
    }

    override fun bindEvent() {

    }

    override fun request(flag: Int) {

    }


}