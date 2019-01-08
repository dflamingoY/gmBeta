package org.xiaoxingqi.gmdoc.wegidt.homegame

import android.content.Context
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout

class HomeLongCommentView(context: Context) : BaseLayout(context) {
    private var bean: HomeUserShareData.ContributeBean? = null
    override fun getLayoutId(): Int {
        return R.layout.layout_long_comment
    }


    fun setData( bean: HomeUserShareData.ContributeBean) {
        this.bean=bean




    }
}