package org.xiaoxingqi.gmdoc

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.http.HttpServer
import org.xiaoxingqi.gmdoc.entity.TokenData
import org.xiaoxingqi.gmdoc.modul.home.HomeFragment
import android.view.WindowManager
import android.os.Build
import android.support.v4.widget.DrawerLayout
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.MainCallBack
import org.xiaoxingqi.gmdoc.modul.game.GameFragment
import org.xiaoxingqi.gmdoc.modul.global.WriteDynamicActivity
import org.xiaoxingqi.gmdoc.modul.home.UserHomeActivity
import org.xiaoxingqi.gmdoc.modul.login.LoginActivity
import org.xiaoxingqi.gmdoc.onEvent.LoginEvent
import org.xiaoxingqi.gmdoc.parsent.MainPersenter
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.PreferenceTools
import org.xiaoxingqi.gmdoc.tools.SPUtils

class MainActivity : BaseActivity<MainPersenter>() {

    private val map by lazy { HashMap<String, String>() }


    override fun createPresent(): MainPersenter {
        return MainPersenter(this, object : MainCallBack {

            override fun token(data: TokenData?) {
                App.s_Token = data?._token
                transLayout.showContent()
            }

            override fun loginOut(data: BaseRespData?) {
                /**
                 * 退出登录 检测是否退出成功
                 */
                drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

                persent?.post_token()
            }

            @SuppressLint("SetTextI18n")
            override fun userInfo(data: UserInfoData?) {
                /**
                 * 信息查询成功 表示用户已经登录
                 */
                data?.let {
                    if (it.data.jutou == 0) {//剧透打开
                        toggle_Button.isChecked = true
                        SPUtils.setBoolean(this@MainActivity, IConstant.IS_SPOLIER, true)
                    } else {//关闭
                        SPUtils.setBoolean(this@MainActivity, IConstant.IS_SPOLIER, false)
                        toggle_Button.isChecked = false
                    }
                    PreferenceTools.saveObj(this@MainActivity, IConstant.USERINFO, it)
                    drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    Glide.with(this@MainActivity)
                            .load(it.data.avatar)
                            .error(R.mipmap.img_avatar_default)
                            .centerCrop()
                            .into(GlideDrawableImageViewTarget(iv_UserLogo, 0))
                    Glide.with(this@MainActivity)
                            .load(it.data.top_image)
//                            .error(R.mipmap.img_mine_banner)
                            .centerCrop()
                            .into(user_Home_Bg)
                    tv_UserName.text = it.data.username
                    if (TextUtils.isEmpty(it.data.like_num)) {
                        tv_UserLike.text = "0 关注 · "
                    } else {
                        tv_UserLike.text = "${it.data.follow_num} 关注 · "
                    }
                    if (it.data.fans_switch == 0) {//切换是否隐藏读者数
                        if (TextUtils.isEmpty(it.data.fans_num)) {
                            tv_UserFans.text = "0 读者"
                        } else {
                            tv_UserFans.text = "${it.data.fans_num} 读者"
                        }
                    } else {
                        tv_UserFans.text = "未知 读者"
                    }
//                    updateFrag()

                    if (TextUtils.isEmpty(it.data.like_game) || TextUtils.isEmpty(it.data.username)) {
//                        startActivity(Intent(this@MainActivity, PerfectInfoActivity::class.java))
                    } else {
                        if (!TextUtils.isEmpty(it.data.like_game)) {
                            val split = it.data.like_game.split(",")
                            if (split.isEmpty()) {
//                                startActivity(Intent(this@MainActivity, PerfectInfoActivity::class.java))
                            }
                        }
                    }
                }

            }

            override fun onError(obj: Any?) {
                /**
                 * 错误的时候  未登录
                 */
                drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        })
    }

    private val homeFrag = HomeFragment()
    private val gameFrag = GameFragment()
    //    val lifeCircle = LifCircleFragment()
//    val msgFrag = MsgFragment()
    private var currentFrag: Fragment? = null

    override fun setContent() {
        setContent(R.layout.activity_main)
    }

    override fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
        drawerlayout.closeDrawers()
        val params = left_drawer.layoutParams as DrawerLayout.LayoutParams
        params.gravity = Gravity.START
        left_drawer.layoutParams = params
//        drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun initData() {
        switchFragment(TypeFragment.Home)
        persent?.post_token()
        persent?.queryInfo()
    }

    override fun initEvent() {
        actionbarView.setOnTabClickListener {
            when (it.id) {
                R.id.tab_01 -> {
                    switchFragment(TypeFragment.Home)
                    true
                }
                R.id.tab_02 -> {
                    switchFragment(TypeFragment.Echoe)
                    true
                }
                R.id.Iv_HomeButton -> {
                    if (AppTools.isLogin(this)) {
                        startActivity(Intent(this, WriteDynamicActivity::class.java))
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java))
                        overridePendingTransition(R.anim.act_enter_trans, 0)
                    }
                    true
                }
                R.id.tab_03 -> {
                    switchFragment(TypeFragment.Listen)
                    true
                }
                R.id.tab_04 -> {
                    switchFragment(TypeFragment.Me)
                    true
                }
                else -> {
                    false
                }
            }
        }

        iv_login_out.setOnClickListener {
            map["_token"] = App.s_Token!!
            persent?.loginOut(map)
        }
        relative_User_Home.setOnClickListener {
            val infoData = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
            startActivity(Intent(this, UserHomeActivity::class.java).putExtra("userId", infoData.data.uid))
        }
        relative_likeGame.setOnClickListener {

        }
        relative_Content.setOnClickListener {

        }
        relative_More.setOnClickListener {

        }
        relative_User_Wallet.setOnClickListener {

        }
        relative_User_Enjoy.setOnClickListener {

        }
        relative_Setting.setOnClickListener {

        }
        iv_Help.setOnClickListener {

        }
    }

    override fun request() {

    }

    private fun switchFragment(type: TypeFragment) {
        val transTemp = supportFragmentManager.beginTransaction()
        currentFrag?.let { transTemp.hide(currentFrag) }
        when (type) {
            TypeFragment.Home ->
                currentFrag = homeFrag
            TypeFragment.Echoe ->
                currentFrag = gameFrag
//            TypeCircleFragment.Listen ->
//                currentFrag = lifeCircle
//            TypeCircleFragment.Me ->
//                currentFrag = msgFrag
            else -> {

            }
        }
        currentFrag?.let {
            if (!currentFrag!!.isAdded) {
                transTemp.add(R.id.frame_Fragment, currentFrag)
            }
        }
        transTemp.show(currentFrag)
        transTemp.commit()
    }

    enum class TypeFragment(type: Int) {
        Home(0), Echoe(1), Listen(2), Me(3);

        var value = type
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun loginEvent(event: LoginEvent) {
        persent?.queryInfo()
    }

}
