package org.xiaoxingqi.gmdoc.modul.home

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_user_edit_game.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.dialog.SelectLoveGameIndexDialog
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.user.LoveGameData
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.modul.global.CropRectActivity
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter
import org.xiaoxingqi.gmdoc.tools.AppTools
import java.util.*

class UserEditGameActivity : BaseActivity<UserPresenter>() {
    private var current = 0
    private val mData by lazy { ArrayList<LoveGameData.DataBean>() }
    private val REQUEST_GALLERY = 0x01
    private val REQUEST_CROP = 0x02
    private var imgPath: String? = null
    private val map by lazy { HashMap<String, String>() }
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {

            override fun changeGame(data: BaseRespData) {
                finish()
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_user_edit_game)
    }

    override fun initView() {

    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        (intent.getSerializableExtra("data") as ArrayList<LoveGameData.DataBean>)?.let {
            mData.addAll(it)
        }
        current = intent.getIntExtra("current", -1)
        if (mData.size > 0 && current >= 0) {
            tv_No.text = "No.${current + 1}"
            et_Name.setText(mData[current].game_name)
            Glide.with(this)
                    .load(mData[current].img)
                    .into(iv_Game_Logo)
            et_Content.setText(mData[current].desc)
            tv_Count.text = "${mData[current].desc?.length}/140"
        } else {
            tv_No.text = "No.${mData.size + 1}"
        }
        map["_token"] = App.s_Token!!

        if (mData.size > 3) {
            tv_Delete.visibility = View.VISIBLE
        }
    }

    override fun initEvent() {
        et_Content.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                tv_Count.text = "${s?.length}/140"
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        tv_Save.setOnClickListener {
            map["like_game"] = parse().toJSONString()
            persent?.changeGame(map)
        }
        iv_Game_Logo.setOnClickListener {
            /**
             * 打开相册选择图片
             */
            AppTools.openGallery(this, REQUEST_GALLERY)
        }
        tv_Delete.setOnClickListener {
            mData.removeAt(current)
            current = -2
            map["like_game"] = parse().toJSONString()
            persent?.changeGame(map)
        }
        tv_No.setOnClickListener {
            if (current >= 0) {
                val location = IntArray(2)
                tv_No.getLocationOnScreen(location)
                val indexDialog = SelectLoveGameIndexDialog(this)
                indexDialog.setData(mData, location[0], location[1], current)
                indexDialog.setItemSelectedListener {
                    tv_No.text = "No.${it + 1}"
                    Collections.swap(mData, it, current)
                    current = it
                }
            }
        }
        viewBack.setOnClickListener { finish() }
    }

    /**
     *
     */
    private fun parse(): JSONArray {
        val arrays = JSONArray()
        if (mData.size > 0) {
            for (index in mData.indices) {
                val json = JSONObject()
                if (index == current) {
                    json["game_name"] = et_Name.text.toString().trim()
                    if (!TextUtils.isEmpty(imgPath)) {
                        json["img"] = imgPath
                    } else {
                        json["img"] = if (TextUtils.isEmpty(mData[index].img)) "" else mData[index].img
                    }
                    json["desc"] = et_Content.text.toString().trim()
                    json["rank"] = index + 1
                } else {
                    json["game_name"] = mData[index].game_name
                    json["img"] = mData[index].img
                    json["desc"] = mData[index].desc
                    json["rank"] = index + 1
                }
                arrays.add(json)
            }
        }
        if (current == -1) {
            val json = JSONObject()
            json["game_name"] = et_Name.text.toString().trim()
            json["img"] = if (!TextUtils.isEmpty(imgPath)) imgPath else ""
            json["desc"] = et_Content.text.toString().trim()
            json["rank"] = 1
            arrays.add(json)
        }
        return arrays
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY) {
                val path = AppTools.getPath(data!!.data, this)
                startActivityForResult(Intent(this, CropRectActivity::class.java).putExtra("path", path), REQUEST_CROP)
            } else if (requestCode == REQUEST_CROP) {
                val path = data?.getStringExtra("path")
                imgPath = path
                Glide.with(this)
                        .load(path)
                        .into(iv_Game_Logo)
            }
        }
    }

}