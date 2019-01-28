package org.xiaoxingqi.gmdoc.modul.home

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_user_wallet.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.modul.dynamic.DynamicDetailsActivity
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter
import org.xiaoxingqi.gmdoc.tools.AppConfig
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.textView.WalletTextView

class UserWalletActivity : BaseActivity<UserPresenter>() {
    private var type = 1 //1 支出  2 收入
    private var current = 0
    private lateinit var adapter: QuickAdapter<HomeUserShareData.ContributeBean>
    private val mData by lazy { ArrayList<HomeUserShareData.ContributeBean>() }
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun walletData(data: HomeUserShareData) {
                if (current == 0) {
                    mData.clear()
                    adapter.notifyDataSetChanged()
                }
                if (data.data.data != null) {
                    for (bean in data.data.data) {
                        mData.add(bean)
                        adapter.notifyItemInserted(adapter.itemCount - 1)
                    }
                }
                tv_Balance.text = data.data.money
                transLayout.showContent()
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_user_wallet)
    }

    override fun initView() {

    }

    override fun initData() {
        adapter = object : QuickAdapter<HomeUserShareData.ContributeBean>(this, R.layout.item_wallet_details, mData) {
            @SuppressLint("SetTextI18n")
            override fun convert(helper: BaseAdapterHelper?, item: HomeUserShareData.ContributeBean?) {
                var typeName = ""
                var title = ""
                when (item!!.type) {
                    0//动态
                    -> {
                        typeName = "动态"
                        title = item.title
                    }
                    1//短评
                    -> {
                        typeName = "短评"
                        if (item.game != null) {
                            title = item.game.game_name + " | " + item.game.game_pla + " | " + item.game.game_ver
                        }
                    }
                    2//长评
                    -> {
                        typeName = "长评"
                        if (item.game != null) {
                            title = item.game.game_name + " | " + item.game.game_pla + " | " + item.game.game_ver
                        }
                    }
                    3//博文
                    -> {
                        typeName = "博文"
                        title = item.title
                    }
                }
                if ("app_wx_pay" == item.pay_type) {
                    helper!!.getTextView(R.id.tv_pay_Type).text = "微信支付"
                } else if ("balance_pay" == item.pay_type) {
                    helper!!.getTextView(R.id.tv_pay_Type).text = "余额支付"
                }
                helper!!.getTextView(R.id.tv_TIme).text = TimeUtils.getInstance().parseTime(item.created_at)
                val result: String
                if (type == 1) {//支出
                    result = item.username + " 的" + typeName + " " + title
                    helper.getTextView(R.id.tv_pay_info).text = "-￥" + item.money
                    helper.getImageView(R.id.iv_legType).setImageResource(R.mipmap.img_wallet_leg_out)
                    helper.getTextView(R.id.tv_LegState).text = "加鸡腿"
                } else {//收入
                    result = item.username + " 给我的" + typeName + " " + title
                    helper.getTextView(R.id.tv_pay_info).text = "+￥" + item.money
                    helper.getImageView(R.id.iv_legType).setImageResource(R.mipmap.img_wallet_leg_in)
                    helper.getTextView(R.id.tv_LegState).text = "收到鸡腿"
                }
                val tvTitle = helper.getTextView(R.id.tv_Info) as WalletTextView
                tvTitle.setData(AppConfig.getImageHtml(result), result.indexOf(title), item.username, true)
                tvTitle.setResultClicklistener(object : WalletTextView.OnResultClicklistener {
                    override fun titleClick() {
                        startActivity(Intent(this@UserWalletActivity, UserHomeActivity::class.java).putExtra("Uid", item.uid))
                    }

                    override fun contentClick() {
                        if (TextUtils.isEmpty(item.id)) {
                            showToast("抱歉，此内容已被作者删除")
                            return
                        }
                        startActivity(Intent(this@UserWalletActivity, DynamicDetailsActivity::class.java).putExtra("id", item.id))
                    }
                })
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        transLayout.showProgress()
        persent?.getWallet(type, current)
    }

    override fun initEvent() {
        tabTitleLayout.setOnClick {
            val ofChild = tabTitleLayout.indexOfChild(it)
            val clickType = if (ofChild == 0) {//支出
                1
            } else {//收入
                2
            }
            if (clickType == type) {
                return@setOnClick
            }
            current = 0
            type = clickType
            transLayout.showProgress()
            persent?.getWallet(type, current)
        }
        tv_Withdrawals.setOnClickListener {

        }
    }
}