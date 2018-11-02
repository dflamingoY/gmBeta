package org.xiaoxingqi.gmdoc.wegidt

import android.content.Context
import android.util.AttributeSet
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData

/**
 * 展示内容
 */
class DynamicView : BaseLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun getLayoutId(): Int {
        return R.layout.layout_dynamic
    }

    fun setData(bean : HomeUserShareData.ContributeBean) {



    }

}