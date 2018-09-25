package org.xiaoxingqi.gmdoc.core

import android.app.Application

class App : Application() {

    companion object {
        var s_Token: String? = null
    }

    override fun onCreate() {
        super.onCreate()
    }
}