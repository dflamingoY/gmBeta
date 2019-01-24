package org.xiaoxingqi.gmdoc.modul.global

import android.view.View
import kotlinx.android.synthetic.main.activity_write_blog.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.global.WriteCallback
import org.xiaoxingqi.gmdoc.listener.SoftkeyBoardManager
import org.xiaoxingqi.gmdoc.presenter.global.WritePresenter
import org.xiaoxingqi.gmdoc.tools.AppTools

class WriteBlogActivity : BaseActivity<WritePresenter>() {

    private lateinit var manager: SoftkeyBoardManager
    private var keyboardHeight: Int = 0
    private var focus = true

    override fun createPresent(): WritePresenter {
        return WritePresenter(this, object : WriteCallback() {

        })
    }

    override fun setContent() {
        setContent(R.layout.activity_write_blog)
    }

    override fun initView() {

    }

    override fun initData() {
        manager = SoftkeyBoardManager(window.decorView, false)
    }

    override fun initEvent() {
        cancel.setOnClickListener {
            finish()
        }
        manager.addSoftKeyboardStateListener(keyboardListener)
        et_Content.setOnFocusChangeListener { v, hasFocus ->
            focus = hasFocus
            if (hasFocus) {
                val params = emojiView.layoutParams
                if (params.height != keyboardHeight) {
                    params.height = keyboardHeight
                    emojiView.layoutParams = params
                }
            } else {

            }
        }
        et_Title.setOnFocusChangeListener { v, hasFocus ->
            if (manager.isSoftKeyboardOpened) {
                val params = emojiView.layoutParams
                if (params.height != 0) {
                    params.height = 0
                    emojiView.layoutParams = params
                }
            }
            focus = !hasFocus
        }
    }

    private val keyboardListener = object : SoftkeyBoardManager.SoftKeyboardStateListener {
        override fun onSoftKeyboardOpened(keyboardHeightInPx: Int) {
            val statusBarHeight = AppTools.getStatusBarHeight(this@WriteBlogActivity)
            val navigation = AppTools.getNavigationBarHeight(this@WriteBlogActivity)
            keyboardHeight = keyboardHeightInPx - statusBarHeight - navigation
            emojiView.visibility = View.INVISIBLE
            if (focus) {
                val params = emojiView.layoutParams
                if (params.height != keyboardHeight) {
                    params.height = keyboardHeight
                    emojiView.layoutParams = params
                }
            } else {
                val params = emojiView.layoutParams
                if (params.height != 0) {
                    params.height = 0
                    emojiView.layoutParams = params
                }
            }
        }

        override fun onSoftKeyboardClosed() {
            /**
             * 关闭的时候要判断是什么情况是否打开表情键盘
             */
            if (emojiView.visibility == View.VISIBLE || emojiView.visibility == View.INVISIBLE) {
                emojiView.visibility = View.GONE
            }
        }
    }

    override fun request() {

    }

}