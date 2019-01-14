package org.xiaoxingqi.gmdoc.modul.global

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.*
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_write_dynamic.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.core.http.UpQiNiuManager
import org.xiaoxingqi.gmdoc.dialog.DialogDToB
import org.xiaoxingqi.gmdoc.dialog.DialogVideo
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.ImgBean
import org.xiaoxingqi.gmdoc.entity.QINiuRespData
import org.xiaoxingqi.gmdoc.entity.emoji.EmojiEntity
import org.xiaoxingqi.gmdoc.impl.global.WriteDynamicCallback
import org.xiaoxingqi.gmdoc.listener.SoftkeyBoardManager
import org.xiaoxingqi.gmdoc.modul.album.AlbumActivity
import org.xiaoxingqi.gmdoc.presenter.global.WriteDynamicPresenter
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.CustomCheckImageView
import org.xiaoxingqi.gmdoc.wegidt.imagespan.VerticalImageSpan
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashMap

/**
 * 创建内容 视频 图片 文字
 */
class WriteDynamicActivity : BaseActivity<WriteDynamicPresenter>() {
    private val REQUEST_CODE_SELECT_AT_USER = 0x01
    private val REQUEST_PHOTO = 0x02

    private lateinit var manager: SoftkeyBoardManager
    private var keyboardHeight: Int = 0
    private var isEmojiMenu = false
    private lateinit var adapter: QuickAdapter<ImgBean>
    private val imgData by lazy { ArrayList<ImgBean>() }
    private val map by lazy { HashMap<String, String>() }
    private var videoPath = ""
    override fun createPresent(): WriteDynamicPresenter {
        return WriteDynamicPresenter(this, object : WriteDynamicCallback {
            override fun qiniuToken(data: QINiuRespData) {
                sendPic(data)
            }

            override fun pushSuccess(data: BaseRespData) {
                showToast("发布成功")
                finish()
            }
        })
    }


    override fun setContent() {
        setContent(R.layout.activity_write_dynamic)
    }

    override fun initView() {

    }

    override fun initData() {
        manager = SoftkeyBoardManager(window.decorView, false)
        adapter = object : QuickAdapter<ImgBean>(this, R.layout.item_spoiler_input, imgData) {
            override fun convert(helper: BaseAdapterHelper?, item: ImgBean?) {
                Glide.with(this@WriteDynamicActivity)
                        .load(item!!.key)
                        .asBitmap()
                        .centerCrop()
                        .into(helper!!.getImageView(R.id.iv_img))
                val checkImageView = helper.getView(R.id.checkbox) as CustomCheckImageView
                checkImageView.setOnStateChangeListener { selected ->
                    item.isSelected = selected
                    helper.getView(R.id.viewSpoiler).visibility = if (selected) View.VISIBLE else View.GONE
                }
                helper.getView(R.id.iv_Delete).setOnClickListener { v ->
                    imgData.remove(item)
                    adapter.notifyDataSetChanged()
                    if (imgData.size == 0) {
                        iv_Video.isClickable = true
                        iv_Video.alpha = 1f
                    }
                }
            }
        }
        recycler_img.layoutManager = GridLayoutManager(this, 3)
        recycler_img.adapter = adapter
    }

    override fun initEvent() {
        manager.addSoftKeyboardStateListener(keyboardListener)
        iv_Gif.setOnClickListener {
            if (manager.isSoftKeyboardOpened) {
                closeMenu()
            }
            if (emojiView.visibility != View.VISIBLE) {
                emojiView.visibility = View.VISIBLE
                val params = emojiView.layoutParams
                params.height = AppTools.dp2px(this, 206)
                emojiView.layoutParams = params
            } else {
                showMenu()
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
                    val drawable = BitmapDrawable(resources, open)
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
            @SuppressLint("SetTextI18n")
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
                    startActivityForResult(Intent(this@WriteDynamicActivity, AtListActivity::class.java), REQUEST_CODE_SELECT_AT_USER)
                }
            }
        })
        iv_Photo.setOnClickListener {
            startActivityForResult(Intent(this, AlbumActivity::class.java).putExtra("allowCount", 9 - imgData.size), REQUEST_PHOTO)
        }

        val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT //允许上下左右的拖动
                return makeMovementFlags(dragFlags, 0)
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                //                Collections.swap(photoList, (int) viewHolder.itemView.getTag(), (int) target.itemView.getTag());
                //                mAdapter.notifyItemMoved((int) viewHolder.itemView.getTag(), (int) target.itemView.getTag());
                // 真实的Position：通过ViewHolder拿到的position都需要减掉HeadView的数量。
                val fromPosition = viewHolder.layoutPosition
                val toPosition = target.layoutPosition
                if (fromPosition < toPosition)
                    for (i in fromPosition until toPosition)
                        Collections.swap(imgData, i, i + 1)
                else
                    for (i in fromPosition downTo toPosition + 1)
                        Collections.swap(imgData, i, i - 1)
                adapter.notifyItemMoved(fromPosition, toPosition)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }
        })
        helper.attachToRecyclerView(recycler_img)
        iv_Topic.setOnClickListener {
            val start = et_Content.selectionStart
            val end = et_Content.selectionEnd
            val editable = et_Content.editableText
            if (start <= 0 || start > editable.length) {
                editable.insert(0, "#输入相关游戏标签#")
            } else {
                if (start == end) {
                    editable.insert(start, "#输入相关游戏标签#")
                } else {
                    editable.replace(start, end, "#输入相关游戏标签#")
                }
            }
            et_Content.setSelection(start + 1, start + "#输入相关游戏标签#".length - 1)
            showMenu()
        }
        iv_Spoiler.setOnClickListener {
            val start = et_Content.selectionStart
            val end = et_Content.selectionEnd
            if (start == end) {
                showToast("请选中剧透文字")
                return@setOnClickListener
            }
            val editableText = et_Content.editableText
            if (start < 0 || start >= editableText.length) {

            } else {
                editableText.insert(start, "[剧透:")//光标所在位置插入文字
                editableText.insert(end + "[剧透:".length, "]")//光标所在位置插入文字
            }
            et_Content.setSelection(end + 5)
        }
        cancel.setOnClickListener {
            finish()
        }
        tv_Commit.setOnClickListener {
            /**
             * 上传资源
             */
            if (imgData.size > 0) {
                persent?.getQiNiuToken()
            } else {
                persent?.getQiNiuToken()
                map["type"] = "0"
                map["state"] = "1"
                map["title"] = "动态"
                map["editorValue"] = et_Content.text.toString()
                map["_token"] = App.s_Token!!
                if (!TextUtils.isEmpty(videoPath)) {
                    map["video"] = videoPath
                }
                persent?.pushDynamic(map)
            }
        }
        iv_Video.setOnClickListener {
            DialogVideo(this).setOnResultListener(object : DialogVideo.OnClickResult {
                override fun result(result: String) {
                    videoPath = result
                    iv_Photo.isEnabled = false
                    iv_Photo.alpha = 0.2f
                    iv_Video.isEnabled = false
                    iv_Video.alpha = 0.2f
                    relative_Video.visibility = View.VISIBLE
                }
            }).show()
        }
        iv_Close.setOnClickListener {
            relative_Video.visibility = View.GONE
            iv_Photo.isEnabled = true
            iv_Photo.alpha = 1f
            iv_Video.isEnabled = true
            iv_Video.alpha = 1f
        }
        iv_Other.setOnClickListener {
            DialogDToB(this).setOnClickListener(View.OnClickListener {

            }).show()
        }
    }

    private fun showMenu() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(et_Content, InputMethodManager.SHOW_FORCED)
    }

    private fun closeMenu() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(et_Content.windowToken, 0)
    }

    private var pattern = Pattern.compile(":\\w+:")

    private fun calcLength(s: String): Int {
        val matcher = pattern.matcher(s)
        var length = 0
        var count = 0
        while (matcher.find()) {
            val at = matcher.group(0)
            if (at != null) {
                length += at.length
                count++
            }
        }
        return s.length - length + count * 2
    }


    private val keyboardListener = object : SoftkeyBoardManager.SoftKeyboardStateListener {
        override fun onSoftKeyboardOpened(keyboardHeightInPx: Int) {
            val statusBarHeight = AppTools.getStatusBarHeight(this@WriteDynamicActivity)
            val navigation = AppTools.getNavigationBarHeight(this@WriteDynamicActivity)
            keyboardHeight = keyboardHeightInPx - statusBarHeight - navigation
            emojiView.visibility = View.INVISIBLE
            val params = emojiView.layoutParams
            if (params.height != keyboardHeight) {
                params.height = keyboardHeight
                emojiView.layoutParams = params
            }
            Log.d("Mozator", "keyboardHeightInPx  : $keyboardHeightInPx  statusBarHeight : $statusBarHeight   navigation : $navigation")
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

    fun sendPic(data: QINiuRespData) {

        UpQiNiuManager(data.token, object : UpQiNiuManager.LoadStateListener {
            var mJSONArray = JSONArray()
            override fun success() {
                map["type"] = "0"
                map["state"] = "1"
                map["title"] = "动态"
                map["editorValue"] = et_Content.text.toString()
                map["img"] = mJSONArray.toJSONString()
                map["_token"] = App.s_Token!!
                persent?.pushDynamic(map)
            }

            override fun fail() {

            }

            override fun oneFinish(endTag: String?, position: Int) {
                val `object` = JSONObject()
                `object`["url"] = data.url + endTag
                `object`["time"] = TimeUtils.getInstance().paserLong(System.currentTimeMillis())
                `object`["spoiler"] = if (imgData[imgData.size - 1 - position].isSelected) 1 else 0
                mJSONArray.add(`object`)
            }
        }, *parse()).next()
    }

    fun parse(): Array<String?> {
        val arrays = arrayOfNulls<String>(imgData.size)
        for (item in imgData.indices) {
            arrays[item] = imgData[item].key
        }
        return arrays
    }


    override fun request() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_PHOTO) {
                data?.let {
                    val result = data.getSerializableExtra("result") as ArrayList<String>
                    for (path in result) {
                        imgData.add(ImgBean(path))
                    }
                    adapter.notifyDataSetChanged()
                    if (imgData.size > 0) {
                        iv_Video.alpha = 0.2f
                        iv_Video.isClickable = false
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        manager.removeSoftKeyboardStateListener(keyboardListener)
    }
}