package org.xiaoxingqi.gmdoc

import android.support.v4.app.Fragment
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.ApiServer
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.modul.game.GameFragment
import org.xiaoxingqi.gmdoc.modul.home.HomeFragment
import org.xiaoxingqi.gmdoc.modul.lifeCircle.LifCircleFragment
import org.xiaoxingqi.gmdoc.modul.message.MsgFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : BaseActivity() {
    val homeFrag = HomeFragment()
    val gameFrag = GameFragment()
    val lifeCircle = LifCircleFragment()
    val msgFrag = MsgFragment()
    var currentFrag: Fragment? = null

    override fun setContent() {
        setContent(R.layout.activity_main)
    }

    override fun initView() {

    }

    override fun initData() {
        switchFragment(TypeFragment.Home)
        val client = OkHttpClient.Builder()

        client.addInterceptor {
            /**
             *网络拦截器 添加cookie
             */
            val origin = it.request()
            val request = origin.newBuilder().addHeader("cookie", "")
                    .method(origin.method(), origin.body())
                    .build()
            it.proceed(request)
        }
        val retrofit = Retrofit.Builder()
                .baseUrl(IConstant.SPORT)
                .client(client.build())
                .build()
        val apiServer = retrofit.create(ApiServer::class.java)


        val call = apiServer.post("post_token")


        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("Mozator", "获取数据失败")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val cookie = response.headers().get("Set-Cookie")
                Log.d("Mozator", "cookie : $cookie  ${response.body()?.string()}")

            }
        })

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

    }

    private fun switchFragment(type: TypeFragment) {
        val transTemp = supportFragmentManager.beginTransaction()
        currentFrag?.let { transTemp.hide(currentFrag) }
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
