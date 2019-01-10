package org.xiaoxingqi.gmdoc.modul.global

import android.graphics.drawable.BitmapDrawable
import android.text.*
import android.util.Log
import android.view.KeyEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_write_dynamic.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.entity.emoji.EmojiEntity
import org.xiaoxingqi.gmdoc.impl.global.WriteDynamicCallback
import org.xiaoxingqi.gmdoc.listener.SoftkeyBoardManager
import org.xiaoxingqi.gmdoc.presenter.global.WriteDynamicPresenter
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.wegidt.imagespan.VerticalImageSpan
import java.util.regex.Pattern

/**
 * 创建内容 视频 图片 文字
 */
class WriteDynamicActivity : BaseActivity<WriteDynamicPresenter>() {
    private lateinit var manager: SoftkeyBoardManager
    private var keyboardHegiht: Int = 0
    override fun createPresent(): WriteDynamicPresenter {
        return WriteDynamicPresenter(this, WriteDynamicCallback())
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
        emojiView.setOnEmojiClicklistener {
            if (it.type == EmojiEntity.EmojiType.DEL) {
                val action = KeyEvent.ACTION_DOWN
                val event = KeyEvent(action, 0)
                et_Content.onKeyDown(KeyEvent.KEYCODE_DEL, event) //抛给系统处理
                return@setOnEmojiClicklistener
            }
            val text = when (it.type) {
                EmojiEntity.EmojiType.GIF -> ":" + it.iconName.replace("gif/", "").replace(".gif", "") + ":"
                EmojiEntity.EmojiType.EMOJI -> ":" + it.iconName.replace("emoji/", "").replace(".png", "") + ":"
                else -> null
            }
            if (!TextUtils.isEmpty(text)) {
                try {
                    val start = et_Content.selectionStart
                    val end = et_Content.selectionEnd
                    val editable = et_Content.editableText
                    val manager = assets
                    val spannableString = SpannableString(text)
                    val open = manager.open(it.iconName)
                    val drawable = BitmapDrawable(open)
                    drawable.setBounds(8, 0, AppTools.dp2px(this, 15) + 8, AppTools.dp2px(this, 15))
                    spannableString.setSpan(VerticalImageSpan(drawable), 0, text!!.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    if (start <= 0 || start > editable.length) {
                        editable.append(spannableString)
                    } else {
                        if (start == end) {
                            editable.insert(start, spannableString)
                        } else {
                            editable.replace(start, end, spannableString)
                        }
                    }
                } catch (e: Exception) {

                }
            }
        }
        et_Content.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                /**
                 * 把所有的表情长度 标记为2
                 */
                tv_Count.text = "${calcLength(s.toString())}/140"
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count == 1 && "@" == s?.get(start).toString()) {
//                    startActivityForResult(Intent(this@WriteDynamicActivity, Act_AT_List::class.java).putExtra("Uid", mInfoData.getData().getUid()), REQUEST_CODE_SELECT_AT_USER)
                }
            }
        })
    }

    private var pattern = Pattern.compile(":\\w+:")
    private fun calcLength(s: String): Int {
        val matcher = pattern.matcher(s)
        var length = 0
        var count = 0
        while (matcher.find()) {
            val at = matcher.group(0)
            if (at != null) {
                length += at!!.length
                count++
            }
        }
        return s.length - length + count * 2
    }


    private val keyboardListener = object : SoftkeyBoardManager.SoftKeyboardStateListener {
        override fun onSoftKeyboardOpened(keyboardHeightInPx: Int) {
            val statusBarHeight = AppTools.getStatusBarHeight(this@WriteDynamicActivity)
            keyboardHegiht = keyboardHeightInPx - statusBarHeight
            emojiView.visibility = View.INVISIBLE
            val params = emojiView.layoutParams
            if (params.height != keyboardHegiht) {
                params.height = keyboardHegiht
                emojiView.layoutParams = params
            }
        }

        override fun onSoftKeyboardClosed() {
            /**
             * 关闭的时候要判断是什么情况是否打开表情键盘
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