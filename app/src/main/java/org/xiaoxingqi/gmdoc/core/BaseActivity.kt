package org.xiaoxingqi.gmdoc.core

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.xiaoxingqi.gmdoc.presenter.BasePresenter

abstract class BaseActivity<T : BasePresenter> : AppCompatActivity() {
    protected var isTopActivity = true
    var persent: T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSystem()
        persent = createPresent()
        setContent()
        EventBus.getDefault().register(this)
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

    abstract fun createPresent(): T
    abstract fun setContent()
    abstract fun initView()
    abstract fun initData()
    abstract fun initEvent()
    open fun request() {}

    fun setContent(view: View) {
        setContentView(view)
    }

    fun setContent(layoutId: Int) {
        setContentView(layoutId)
    }

    override fun onResume() {
        super.onResume()
        isTopActivity = true
    }

    override fun onPause() {
        super.onPause()
        isTopActivity = false
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNewViewEvent(event: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}