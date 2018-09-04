package org.xiaoxingqi.gmdoc.core

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSystem()
        setContent()
        initView()
        initData()
        initEvent()
    }

    private fun initSystem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //透明导航栏
            //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        }
    }

    abstract fun setContent()
    abstract fun initView()
    abstract fun initData()
    abstract fun initEvent()
    abstract fun request()

    fun setContent(view: View) {
        setContentView(view)
    }

    fun setContent(layoutId: Int) {
        setContentView(layoutId)
    }

}