package org.xiaoxingqi.gmdoc.modul.home

import android.view.View
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData
import org.xiaoxingqi.gmdoc.entity.home.HomeGameData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.home.HomeTabCallback
import org.xiaoxingqi.gmdoc.parsent.HomePresent

class HomeFragment : BaseFrag<HomePresent>() {

    override fun createPresent(): HomePresent {
        return HomePresent(activity!!, object : HomeTabCallback() {
            override fun gameSuccess(data: HomeGameData?) {

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

    }

    override fun initData() {
        persent?.let {
            it.getActionData()
            it.getAttributeData(0)
            it.getGameData()
        }
    }

    override fun bindEvent() {

    }

    override fun request(flag: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}