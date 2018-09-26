package org.xiaoxingqi.gmdoc

import android.graphics.Color
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.http.HttpServer
import org.xiaoxingqi.gmdoc.entity.TokenData
import org.xiaoxingqi.gmdoc.modul.home.HomeFragment
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import android.view.WindowManager
import android.os.Build
import android.view.View


class MainActivity : BaseActivity() {
    val homeFrag = HomeFragment()
//    val gameFrag = GameFragment()
//    val lifeCircle = LifCircleFragment()
//    val msgFrag = MsgFragment()
    var currentFrag: Fragment? = null

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
    }

    override fun initData() {
        switchFragment(TypeFragment.Home)
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
    }

    override fun request() {
        HttpServer.getInstance(this).apiServer.post_token("post_token")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<TokenData>() {
                    override fun onNext(t: TokenData?) {
                        App.s_Token = t?._token
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })


    }

    private fun switchFragment(type: TypeFragment) {
        val transTemp = supportFragmentManager.beginTransaction()
        currentFrag?.let { transTemp.hide(currentFrag) }
        when (type) {
            TypeFragment.Home ->
                currentFrag = homeFrag
//            TypeFragment.Echoe ->
//                currentFrag = gameFrag
//            TypeFragment.Listen ->
//                currentFrag = lifeCircle
//            TypeFragment.Me ->
//                currentFrag = msgFrag
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


}
