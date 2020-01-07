package org.xiaoxingqi.gmdoc.modul.home

import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_edit_user_info.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.modul.global.CropUserAvatarActivity
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.PreferenceTools

class EditUserInfoActivity : BaseActivity<UserPresenter>() {
    private val REQUEST_GALLERY = 0x01
    private val REQUEST_CROP = 0x02
    private var isEdit = false
    private val map by lazy { HashMap<String, String>() }
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun updateInfo(data: BaseRespData) {
                finish()
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_edit_user_info)
    }

    override fun initView() {
        et_Name.isFocusable = false
        et_Name.isFocusableInTouchMode = false
        et_Desc.isFocusable = false
        et_Desc.isFocusableInTouchMode = false
    }

    override fun initData() {
        val infoData = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
        et_Name.setText(infoData.data.username)
        et_Desc.setText(infoData.data.desc)
        Glide.with(this)
                .asBitmap()
                .load(infoData.data.avatar)
                .into(iv_Logo)
        map["_token"] = App.s_Token!!
        if ("1" == infoData.data.sex) {
            tv_Sex.text = "女"
        } else {
            tv_Sex.text = "男"
        }
    }

    override fun initEvent() {
        viewBack.setOnClickListener { finish() }
        edit.setOnClickListener {
            if (!isEdit) {
                ivEdit.visibility = View.VISIBLE
                et_Name.isFocusable = true
                et_Name.isFocusableInTouchMode = true
                et_Name.requestFocus()
                et_Desc.isFocusable = true
                et_Desc.isFocusableInTouchMode = true
                linear_Select.visibility = View.VISIBLE
                edit.text = "完成"
            } else {
                if (TextUtils.isEmpty(et_Name.text.toString().trim())) {
                    showToast("昵称不能为空")
                    return@setOnClickListener
                }
                map["name"] = et_Name.text.toString().trim()
                map["sex"] = if (check_Man.isSelected) "2" else "1"
                map["desc"] = et_Desc.text.toString().trim()
                ivEdit.visibility = View.GONE
                et_Name.isFocusable = false
                et_Name.isFocusableInTouchMode = false
                et_Desc.isFocusable = false
                et_Desc.isFocusableInTouchMode = false
                linear_Select.visibility = View.GONE
                edit.text = "编辑"
                persent?.updateUserInfo(map)
            }
            isEdit = !isEdit
        }
        ivEdit.setOnClickListener {
            AppTools.openGallery(this, REQUEST_GALLERY)
        }
        check_Man.setOnStateChangeListener {
            checkWomen.isSelected = !it
        }
        checkWomen.setOnStateChangeListener {
            check_Man.isSelected = !it
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY) {
                startActivityForResult(Intent(this, CropUserAvatarActivity::class.java).putExtra("path", AppTools.getPath(data!!.data, this)), REQUEST_CROP)
            } else if (requestCode == REQUEST_CROP) {
                val path = data?.getStringExtra("path")
                Glide.with(this)
                        .asBitmap()
                        .load(path)
                        .into(iv_Logo)
                map["type"] = "2"
                map["url"] = path!!
                persent?.updateAvatar(map)
            }
        }
    }
}