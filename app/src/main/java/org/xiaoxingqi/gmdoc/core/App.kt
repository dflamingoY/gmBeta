package org.xiaoxingqi.gmdoc.core

import android.app.Application
import org.xiaoxingqi.gmdoc.tools.AppTools

class App : Application() {

    companion object {
        var s_Token: String? = null
    }

    override fun onCreate() {
        super.onCreate()
        AppTools.initImageLoader(this)
        AppTools.initDisplayImageOptions()
    }
}