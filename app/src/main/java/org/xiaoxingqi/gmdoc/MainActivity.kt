package org.xiaoxingqi.gmdoc

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.drawerlayout.widget.DrawerLayout
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.WebSocket
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.TokenData
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.eventbus.SocketEvent
import org.xiaoxingqi.gmdoc.eventbus.SocketOffline
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.MainCallBack
import org.xiaoxingqi.gmdoc.modul.game.GameFragment
import org.xiaoxingqi.gmdoc.modul.global.WriteDynamicActivity
import org.xiaoxingqi.gmdoc.modul.home.*
import org.xiaoxingqi.gmdoc.modul.lifeCircle.LifCircleFragment
import org.xiaoxingqi.gmdoc.modul.login.LoginActivity
import org.xiaoxingqi.gmdoc.modul.message.MsgFragment
import org.xiaoxingqi.gmdoc.onEvent.LoginEvent
import org.xiaoxingqi.gmdoc.presenter.MainPresenter
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.PreferenceTools
import org.xiaoxingqi.gmdoc.tools.SPUtils
import org.xiaoxingqi.gmdoc.tools.SocketUtils

class MainActivity : BaseActivity<MainPresenter>() {
    private var socket: WebSocket? = null

    companion object {
        private const val REQUEST_PERMISSION = 0x01
    }

    private val map by lazy { HashMap<String, String>() }

    override fun createPresent(): MainPresenter {
        return MainPresenter(this, object : MainCallBack {

            override fun token(data: TokenData?) {
                App.s_Token = data?._token
                transLayout.showContent()
            }

            override fun loginOut(data: BaseRespData?) {
                /**
                 * 退出登录 检测是否退出成功
                 */
                drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                PreferenceTools.clear(this@MainActivity, IConstant.USERINFO)
                persent?.postToken()
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
                            .applyDefaultRequestOptions(RequestOptions().error(R.mipmap.img_avatar_default)
                                    .centerCrop())
                            .asBitmap()
                            .load(it.data.avatar)
                            .into(iv_UserLogo)
                    Glide.with(this@MainActivity)
                            .applyDefaultRequestOptions(RequestOptions().centerCrop())
                            .load(it.data.top_image)
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
                    socket = SocketUtils.initSocket()
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
                transLayout.showContent()
            }
        })
    }

    private val homeFrag = HomeFragment()
    private val gameFrag = GameFragment()
    private val lifeCircle = LifCircleFragment()
    private val msgFrag = MsgFragment()
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_PERMISSION)
            }
        }
        switchFragment(TypeFragment.Home)
        persent?.postToken()
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
                    if (!AppTools.isLogin(this)) {
                        startActivity(Intent(this, LoginActivity::class.java))
                        overridePendingTransition(R.anim.act_enter_trans, 0)
                        false
                    } else {
                        switchFragment(TypeFragment.Listen)
                        true
                    }
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
        relative_love_game.setOnClickListener {
            val infoData = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
            startActivity(Intent(this, UserGameListActivity::class.java)
                    .putExtra("userId", infoData.data.uid))
        }
        relative_text.setOnClickListener {
            val infoData = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
            startActivity(Intent(this, UserEditTextActivity::class.java)
                    .putExtra("userId", infoData.data.uid))
        }
        relative_album.setOnClickListener {
            val infoData = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
            startActivity(Intent(this, UserAlbumActivity::class.java)
                    .putExtra("userId", infoData.data.uid)
            )
        }
        relative_User_Wallet.setOnClickListener {
            startActivity(Intent(this, UserWalletActivity::class.java))
        }
        relative_User_Enjoy.setOnClickListener {
            startActivity(Intent(this, UserEnjoyActivity::class.java))
        }
        relative_Setting.setOnClickListener {
            startActivity(Intent(this, UserSetActivity::class.java))
        }
        iv_Help.setOnClickListener {
            startActivity(Intent(this, InviteCodeActivity::class.java))
        }
        tv_UserLike.setOnClickListener {
            val infoData = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
            startActivity(Intent(this, LoveListActivity::class.java).putExtra("userId", infoData.data.uid))
        }
    }

    override fun request() {

    }

    private fun switchFragment(type: TypeFragment) {
        val transTemp = supportFragmentManager.beginTransaction()
        currentFrag?.let { transTemp.hide(currentFrag!!) }
        when (type) {
            TypeFragment.Home ->
                currentFrag = homeFrag
            TypeFragment.Echoe ->
                currentFrag = gameFrag
            TypeFragment.Listen ->
                currentFrag = lifeCircle
            TypeFragment.Me ->
                currentFrag = msgFrag
        }
        currentFrag?.let {
            if (!currentFrag!!.isAdded) {
                transTemp.add(R.id.frame_Fragment, currentFrag!!)
            }
        }
        transTemp.show(currentFrag!!)
        transTemp.commit()
    }

    enum class TypeFragment(type: Int) {
        Home(0), Echoe(1), Listen(2), Me(3);

        var value = type
    }

    override fun onDestroy() {
        super.onDestroy()
        socket?.let {
            it.close(1000, "")
            socket = null
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun loginEvent(event: LoginEvent) {
        persent?.queryInfo()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION) {

        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK// 注意
            intent.addCategory(Intent.CATEGORY_HOME)
            this.startActivity(intent)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun socketEvent(event: SocketEvent) {


        Log.d("Mozator", event.msg)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun offline(event: SocketOffline) {
        /**
         * 断线重连
         */
        socket?.let {
            socket = null
        }
    }
}
