package org.xiaoxingqi.gmdoc.wegidt

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout

class SpoilerImageView : RelativeLayout {
    private lateinit var iv: ImageView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initView()
    }

    private fun initView() {
        iv = ImageView(context)
        iv.scaleType = ImageView.ScaleType.CENTER_CROP
        addView(iv, generateDefaultLayoutParams())
    }

    fun getImageView(): ImageView {

        return iv
    }
}