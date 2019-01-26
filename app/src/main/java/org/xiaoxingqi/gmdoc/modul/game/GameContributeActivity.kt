package org.xiaoxingqi.gmdoc.modul.game

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.SparseArray
import android.view.View
import android.webkit.URLUtil
import android.widget.EditText
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_contibute_photo.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.core.http.UpQiNiuManager
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.BaseSimpleData
import org.xiaoxingqi.gmdoc.entity.QINiuRespData
import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.modul.album.AlbumActivity
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

/**
 * 用户添加贡献图
 */
class GameContributeActivity : BaseActivity<UserPresenter>() {
    private val PHOTO = 0x01

    private lateinit var gameId: String
    private val mData by lazy { ArrayList<BaseSimpleData>() }
    private lateinit var adapter: QuickAdapter<BaseSimpleData>
    private var upLoadData: ArrayList<BaseSimpleData>? = null
    private val map by lazy { HashMap<String, String>() }

    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            @SuppressLint("SetTextI18n")
            override fun userContribute(data: GameDetailsData) {
                for (bean in data.my_con_pic) {
                    mData.add(bean)
                    adapter.notifyItemInserted(adapter.itemCount - 1)
                }
                tv_currentCount.text = Html.fromHtml(String.format(resources.getString(R.string.text_attle_count), (9 - mData.size).toString() + ""))
                tv_GameInfo.text = "${data.game.game_name} | ${data.game.platform} | ${data.game.ver_name}"
                if (mData.size == 9) {
                    relative_AddImg.visibility = View.GONE
                } else {
                    relative_AddImg.visibility = View.VISIBLE
                }
                transLayout.showContent()
            }

            override fun qiniuToken(data: QINiuRespData) {
                /**
                 * 上傳
                 */
                UpQiNiuManager(data.token, object : UpQiNiuManager.LoadStateListener {

                    private val cache = SparseArray<String>()
                    private var count = 0
                    override fun success() {
                        /**
                         * 图片上传完成 整理数据 上传
                         */
                        for (index in upLoadData!!.indices) {
                            upLoadData!![index].pic = cache[index]
                        }
                        map["pic"] = sortData()
                        transLayout.showProgress()
                        persent?.addContribute(map)
                    }

                    override fun fail() {
                        Thread(Runnable { runOnUiThread { transLayout.showContent() } }).start()
                    }

                    override fun oneFinish(endTag: String, position: Int) {
                        cache.put(count++, data.url + endTag)
                    }
                }, *parseArrays()).next()
            }

            override fun addContributeSuccess(data: BaseRespData) {
                transLayout.showContent()
                if (mData.size > 0) {//
                    map.clear()
                    map["_token"] = App.s_Token!!
                    map["type"] = "0"
                    map["state"] = "1"
                    map["title"] = "贡献图"
                    map["editorValue"] = "贡献图"
                    map["img"] = parsePush()
                    transLayout.showProgress()
                    persent?.pushDynamic(map)
                } else {
                    finish()
                }
            }

            override fun pushSuccess(data: BaseRespData) {
                finish()
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_contibute_photo)
    }

    override fun initView() {
        recyclerView.isNestedScrollingEnabled = false
    }

    override fun initData() {
        gameId = intent.getStringExtra("gameId")
        adapter = object : QuickAdapter<BaseSimpleData>(this, R.layout.item_add_share_img, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseSimpleData?) {

                Glide.with(this@GameContributeActivity)
                        .load(if (URLUtil.isValidUrl(item!!.pic)) item.pic + "?imageMogr2/auto-orient/thumbnail/!200x200r" else item.pic)
                        .centerCrop()
                        .override(180, 180)
                        .into(helper!!.getImageView(R.id.iv_Img))
                val editText = helper.getTextView(R.id.et_Title) as EditText
                editText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                    }

                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                    }

                    override fun afterTextChanged(s: Editable) {
                        item.title = s.toString().trim()
                    }
                })
                editText.setOnFocusChangeListener { v, hasFocus ->
                    if (!hasFocus) {
                        editText.isFocusable = false
                        editText.isFocusableInTouchMode = false
                    } else {
                        editText.setSelection(editText.text.toString().trim { it <= ' ' }.length)
                    }
                }
                editText.setOnClickListener { v ->
                    editText.isFocusableInTouchMode = true
                    editText.isFocusable = true
                    editText.requestFocus()
                }
                editText.setText(item.title)
                helper.getView(R.id.iv_Close).setOnClickListener { v ->
                    mData.remove(item)
                    adapter.notifyDataSetChanged()
                    tv_currentCount.text = Html.fromHtml(String.format(resources.getString(R.string.text_attle_count), (9 - mData.size).toString() + ""))
                    if (mData.size < 9) {
                        relative_AddImg.visibility = View.VISIBLE
                    } else {
                        relative_AddImg.visibility = View.GONE
                    }
                }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        persent?.getUserContribute(gameId)
        map["_token"] = App.s_Token!!
        map["game_id"] = gameId
    }

    override fun initEvent() {
        tv_Cancel.setOnClickListener {
            finish()
        }
        tv_Commit.setOnClickListener {
            persent?.getQiNiuToken()
        }
        relative_AddImg.setOnClickListener {
            startActivityForResult(Intent(this, AlbumActivity::class.java).putExtra("allowCount", 9 - mData.size), PHOTO)
        }
    }

    /**
     * 获取上传的数据
     */
    private fun parseArrays(): Array<String?> {
        if (null == upLoadData) {
            upLoadData = ArrayList()
        }
        val data = ArrayList<BaseSimpleData>()
        for (index in mData.indices) {
            if (URLUtil.isValidUrl(mData[index].pic)) {
                data.add(mData[index])
            }
        }
        val ofNulls = arrayOfNulls<String>(upLoadData!!.size)
        for (index in upLoadData!!.indices) {
            ofNulls[index] = upLoadData!![index].pic
        }
        return ofNulls
    }

    /**
     * 整理上传的数据
     */
    private fun sortData(): String {
        val arrays = JSONArray()
        for (bean in mData) {
            val json = JSONObject()
            json["pic"] = bean.pic
            json["title"] = bean.title
            arrays.add(json)
        }
        return arrays.toJSONString()
    }

    /**
     * 整理发布的数据
     */
    private fun parsePush(): String {
        val arrays = JSONArray()
        for (bean in mData) {
            val json = JSONObject()
            json["url"] = bean.pic
            json["time"] = System.currentTimeMillis().toString()
            json["spoiler"] = "0"
            arrays.add(json)
        }
        return arrays.toJSONString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO) {
                data?.let {
                    val extra = it.getSerializableExtra("result") as ArrayList<String>
                    for (path in extra) {
                        val bean = BaseSimpleData()
                        bean.pic = path
                        mData.add(bean)
                        adapter.notifyItemInserted(adapter.itemCount - 1)
                    }
                    tv_currentCount.text = Html.fromHtml(String.format(resources.getString(R.string.text_attle_count), (9 - mData.size).toString() + ""))
                }
            }
        }
    }

}