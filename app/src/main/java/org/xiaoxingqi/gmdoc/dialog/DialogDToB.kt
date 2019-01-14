package org.xiaoxingqi.gmdoc.dialog

import android.content.Context
import android.view.View
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.tools.AppTools

/**
 * 动态转博文
 */
class DialogDToB(context: Context) : BaseDialog(context) {
    override fun getLayoutId(): Int {
        return R.layout.dialog_dynamic_to_b
    }

    override fun initView() {
        findViewById<View>(R.id.tv_Cancel).setOnClickListener {
            dismiss()
        }
        findViewById<View>(R.id.tv_Commit).setOnClickListener {
            onClickListener?.onClick(it)
            dismiss()
        }
        val p = window!!.attributes
        p.width = AppTools.getWindowsWidth(context)
        window!!.attributes = p
    }

    private var onClickListener: View.OnClickListener? = null
    fun setOnClickListener(onClickListener: View.OnClickListener): DialogDToB {
        this.onClickListener = onClickListener
        return this
    }
}