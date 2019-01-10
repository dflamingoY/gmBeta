package org.xiaoxingqi.gmdoc.modul.global

import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_write_dynamic.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.global.WriteDynamicCallback
import org.xiaoxingqi.gmdoc.listener.SoftkeyBoardManager
import org.xiaoxingqi.gmdoc.parsent.global.WriteDynamicPersenter
import org.xiaoxingqi.gmdoc.tools.AppTools

/**
 * 创建内容 视频 图片 文字
 */
class WriteDynamicActivity : BaseActivity<WriteDynamicPersenter>() {
    private lateinit var manager: SoftkeyBoardManager
    private var keyboardHegiht: Int = 0
    override fun createPresent(): WriteDynamicPersenter {
        return WriteDynamicPersenter(this, WriteDynamicCallback())
    }

    override fun setContent() {
        setContent(R.layout.activity_write_dynamic)
    }

    override fun initView() {

    }

    override fun initData() {
        manager = SoftkeyBoardManager(window.decorView, false)
    }

    override fun initEvent() {
        manager.addSoftKeyboardStateListener(keyboardListener)
        iv_Gif.setOnClickListener {
            if (manager.isSoftKeyboardOpened) {

            } else {
                emojiView.visibility = View.VISIBLE
                val params = emojiView.layoutParams
                params.height = AppTools.dp2px(this, 206)
                emojiView.layoutParams = params
            }
        }
    }

    private val keyboardListener = object : SoftkeyBoardManager.SoftKeyboardStateListener {
        override fun onSoftKeyboardOpened(keyboardHeightInPx: Int) {
            val statusBarHeight = AppTools.getStatusBarHeight(this@WriteDynamicActivity)
            keyboardHegiht = keyboardHeightInPx - statusBarHeight
            Log.d("Mozator", "open   $keyboardHeightInPx    $statusBarHeight  $keyboardHegiht")
            emojiView.visibility = View.INVISIBLE
            val params = emojiView.layoutParams
            if (params.height != keyboardHegiht) {
                params.height = keyboardHegiht
                emojiView.layoutParams = params
            }
        }

        override fun onSoftKeyboardClosed() {
            /**
             * 关闭的时候要判断是什么情况 是否打开表情键盘
             */
            if (emojiView.visibility == View.VISIBLE || emojiView.visibility == View.INVISIBLE) {
                emojiView.visibility = View.GONE
            }
            Log.d("Mozator", "close board")
        }
    }

    override fun request() {

    }

    override fun onDestroy() {
        super.onDestroy()
        manager.removeSoftKeyboardStateListener(keyboardListener)
    }
}