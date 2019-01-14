package org.xiaoxingqi.gmdoc.dialog

import android.content.ClipboardManager
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import org.xiaoxingqi.gmdoc.R

class DialogVideo(context: Context) : BaseDialog(context) {
    override fun getLayoutId(): Int {
        return R.layout.dialog_video
    }

    override fun initView() {
        val editText = findViewById<EditText>(R.id.et_link)
        findViewById<View>(R.id.tv_Cancel).setOnClickListener { dismiss() }
        findViewById<View>(R.id.tv_Commit).setOnClickListener {
            if (!TextUtils.isEmpty(editText.text.toString().trim())) {
                onResultListener?.result(editText.text.toString().trim())
                dismiss()
            } else {
                Toast.makeText(context, "请输入网络地址", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<View>(R.id.tv_Copy).setOnClickListener {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val text = clipboard.text
            editText.setText(text)
        }
    }

    interface OnClickResult {
        fun result(result: String)
    }

    private var onResultListener: OnClickResult? = null
    fun setOnResultListener(onResultListener: OnClickResult): DialogVideo {
        this.onResultListener = onResultListener
        return this
    }
}