package org.xiaoxingqi.gmdoc.modul.temp

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import com.nineoldandroids.animation.ObjectAnimator
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.eventbus.OnNewHintViewEvent
import org.xiaoxingqi.gmdoc.tools.AppTools

class TempAct2 : BaseActivity() {
    override fun setContent() {
        setContent(R.layout.activity_temp)
    }

    override fun initView() {

    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun request() {

    }

    fun openAct(view: View) {
        startActivity(Intent(this, TempAct3::class.java))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNewViewEvent(event: OnNewHintViewEvent) {
        /**
         * 收到新通知时窗体弹出View
         */
        if (isTopActivity) {
            /*val group = (window.decorView as ViewGroup)
            if (group != null) {
                val view = View.inflate(this, R.layout.layout_toast, null)
                val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppTools.dp2px(this, 50))
                group.addView(view, params)
                doAnim(view)
            }*/
            PopNewHintView.attach(this,"")
        }
    }

    /**
     * 执行加载出来的动画
     */
    private fun doAnim(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationY", -AppTools.dp2px(this, 50).toFloat(), 0f)
        animator.duration = 500
        animator.start()
    }

}