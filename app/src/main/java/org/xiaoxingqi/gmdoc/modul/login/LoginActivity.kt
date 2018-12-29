package org.xiaoxingqi.gmdoc.modul.login

import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_login.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.login.LoginCallBack
import org.xiaoxingqi.gmdoc.parsent.login.LoginPresenter
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.SPUtils

/**
 * 登录
 */
class LoginActivity : BaseActivity<LoginPresenter>() {
    private var isHidePwd = true
    private val map by lazy {
        HashMap<String, String>()
    }

    override fun createPresent(): LoginPresenter {
        return LoginPresenter(this, LoginCallBack {
            SPUtils.setString(this@LoginActivity, IConstant.CACHEEMAIL, et_Email.text.toString().trim())
            showToast("登录成功")

            finish()
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_login)
    }

    override fun initView() {

    }

    override fun initData() {
        val defauleEmail = SPUtils.getString(this, IConstant.CACHEEMAIL, null)
        defauleEmail?.let {
            et_Email.setText(it)
            et_Email.setSelection(it.length)
        }
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.group_5, iv_Bg, AppTools.options)
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.btn_qq_blue, Login_By_QQ, AppTools.options)
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.btn_wechat_green, Login_By_Wechat, AppTools.options)
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.img_logo_slogan, iv_app_Logo, AppTools.options)
    }

    override fun initEvent() {
        linear_Close.setOnClickListener {
            finish()
        }
        et_Email.setOnFocusChangeListener { v, hasFocus ->
            relative_Email.isSelected = hasFocus
        }

        et_Pwd.setOnFocusChangeListener { v, hasFocus ->
            relative_Pwd.isSelected = hasFocus
        }


        iv_hide_pwd.setOnClickListener {
            if (!isHidePwd) {
                et_Pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                iv_hide_pwd.setImageResource(R.drawable.eye_closed)
            } else {
                et_Pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()//显示
                iv_hide_pwd.setImageResource(R.drawable.btn_eye_open)
            }
            et_Pwd.setSelection(et_Pwd.text.toString().trim().length)
            isHidePwd = !isHidePwd
        }

        iv_Login.setOnClickListener {
            if (TextUtils.isEmpty(et_Email.text.toString().trim())) {
                showToast("请输入邮箱")
                return@setOnClickListener
            }
            if (!AppTools.isEmail(et_Email.text.toString().trim())) {
                showToast("请输入正确格式的邮箱")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(et_Pwd.text.toString().trim())) {
                showToast("请输入密码")
                return@setOnClickListener
            }
            map["email"] = et_Email.text.toString().trim()
            map["password"] = et_Pwd.text.toString().trim()
            map["_token"] = App.s_Token!!
            persent?.login(map)
        }
    }

    override fun request() {

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.act_exit_trans)
    }
}