package org.xiaoxingqi.gmdoc.wegidt.gamefraglist

import android.content.Context
import android.util.AttributeSet
import kotlinx.android.synthetic.main.layout_click_tab_view.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout

class GameClickTabView(context: Context, attrs: AttributeSet?) : BaseLayout(context, attrs) {

    init {
        linearTab1.setName("哈哈")
        linearTab2.setName("哈哈")
        linearTab3.setName("哈哈")
        linearTab4.setName("哈哈")
        linearTab1.setOnClickListener {
            if (it.isSelected) {
                linearTab1.clearSelected()
            } else
                linearTab1.setSelected()
        }
        linearTab2.setOnClickListener {
            if (it.isSelected) {
                linearTab2.clearSelected()
            } else
                linearTab2.setSelected()
        }
        linearTab3.setOnClickListener {
            if (it.isSelected) {
                linearTab3.clearSelected()
            } else
                linearTab3.setSelected()
        }
        linearTab4.setOnClickListener {
            if (it.isSelected) {
                linearTab4.clearSelected()
            } else
                linearTab4.setSelected()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_click_tab_view
    }


}