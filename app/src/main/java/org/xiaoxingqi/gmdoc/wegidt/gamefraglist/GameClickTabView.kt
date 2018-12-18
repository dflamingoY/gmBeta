package org.xiaoxingqi.gmdoc.wegidt.gamefraglist

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.layout_click_tab_view.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout

class GameClickTabView(context: Context, attrs: AttributeSet?) : BaseLayout(context, attrs) {
    init {
        linearTab1.setName("哈哈")
        linearTab2.setName("哈哈")
        linearTab3.setName("已发售")
        linearTab4.setName("默认排序")
        linearTab1.setOnClickListener { view ->
            onClickListener?.onClick(view)
            if (view.isSelected) {
                linearTab1.clearSelected()
            } else {
                linearTab1.setSelected()
                clear(view)
            }
        }
        linearTab2.setOnClickListener { view ->
            onClickListener?.onClick(view)
            if (view.isSelected) {
                linearTab2.clearSelected()
            } else {
                linearTab2.setSelected()
                clear(view)
            }
        }
        linearTab3.setOnClickListener { view ->
            onClickListener?.onClick(view)
            if (view.isSelected) {
                linearTab3.clearSelected()
            } else {
                linearTab3.setSelected()
                clear(view)
            }
        }
        linearTab4.setOnClickListener { view ->
            onClickListener?.onClick(view)
            if (view.isSelected) {
                linearTab4.clearSelected()
            } else {
                linearTab4.setSelected()
                clear(view)
            }
        }
    }

    private fun clear(view: View) {
        when (view.id) {
            R.id.linearTab1 -> {
                linearTab2.clearSelected()
                linearTab3.clearSelected()
                linearTab4.clearSelected()
            }
            R.id.linearTab2 -> {
                linearTab1.clearSelected()
                linearTab3.clearSelected()
                linearTab4.clearSelected()
            }
            R.id.linearTab3 -> {
                linearTab2.clearSelected()
                linearTab1.clearSelected()
                linearTab4.clearSelected()
            }
            R.id.linearTab4 -> {
                linearTab2.clearSelected()
                linearTab3.clearSelected()
                linearTab1.clearSelected()
            }
            else -> {
                linearTab1.clearSelected()
                linearTab2.clearSelected()
                linearTab3.clearSelected()
                linearTab4.clearSelected()
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_click_tab_view
    }

    fun setTabName(name1: String, name2: String, name3: String, name4: String) {
        clear(this)
        if (!TextUtils.isEmpty(name1)) {
            linearTab1.setName(name1)
        }
        if (!TextUtils.isEmpty(name2)) {
            linearTab2.setName(name2)
        }
        if (!TextUtils.isEmpty(name3)) {
            linearTab3.setName(name3)
        }
        if (!TextUtils.isEmpty(name4)) {
            linearTab4.setName(name4)
        }
    }

    private var onClickListener: OnClickListener? = null

    override fun setOnClickListener(l: OnClickListener?) {
        onClickListener = l
    }

}