package org.xiaoxingqi.gmdoc.dialog

import android.content.Context
import android.view.View
import org.xiaoxingqi.gmdoc.R

class DialogSavePic : BaseDialog {
    constructor(context: Context) : super(context, R.style.FullDialogTheme)

    override fun getLayoutId(): Int {
        return R.layout.dialog_save
    }

    override fun initView() {
        findViewById<View>(R.id.tv_Save).setOnClickListener {
            onClickListener?.onClick(it)
            dismiss()
        }
        findViewById<View>(R.id.tvCancel).setOnClickListener { dismiss() }

        initSystem()
    }

    private var onClickListener: View.OnClickListener? = null
    fun setOnClickListener(onClickListener: View.OnClickListener): DialogSavePic {
        this.onClickListener = onClickListener
        return this
    }
}