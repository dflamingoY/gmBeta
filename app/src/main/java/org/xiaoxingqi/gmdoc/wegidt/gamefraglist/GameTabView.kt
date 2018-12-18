package org.xiaoxingqi.gmdoc.wegidt.gamefraglist

import android.content.Context
import android.util.AttributeSet
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout

class GameTabView : BaseLayout {

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

    }

    override fun getLayoutId(): Int {
        return R.layout.layout_frag_game_tab
    }


}