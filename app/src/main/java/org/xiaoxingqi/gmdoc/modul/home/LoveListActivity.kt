package org.xiaoxingqi.gmdoc.modul.home

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_love_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.BaseSimpleData
import org.xiaoxingqi.gmdoc.entity.user.FollowData
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.PreferenceTools

class LoveListActivity : BaseActivity<UserPresenter>() {
    private lateinit var uid: String
    private var currentPage = 1
    private lateinit var headView: View
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun loveList(data: FollowData) {
                for (bean in data.data.sub.data) {
                    mData.add(bean)
                    adapter.notifyItemInserted(adapter.itemCount - 1)
                }
                transLayout.showContent()
            }
        })
    }

    private lateinit var adapter: QuickAdapter<BaseSimpleData>
    private val mData by lazy { ArrayList<BaseSimpleData>() }
    override fun setContent() {
        setContent(R.layout.activity_love_list)
    }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        headView = LayoutInflater.from(this).inflate(R.layout.head_love_list, recyclerView, false)
    }

    override fun initData() {
        uid = intent.getStringExtra("userId")
        adapter = object : QuickAdapter<BaseSimpleData>(this, R.layout.item_love_user, mData, headView) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseSimpleData?) {
                Glide.with(this@LoveListActivity)
                        .load(item!!.avatar)
                        .apply(RequestOptions().error(R.mipmap.img_avatar_default)
                                .centerCrop())
                        .into(helper!!.getImageView(R.id.iv_UserLogo))
                helper.getTextView(R.id.tv_UserName).text = item.username
                helper.getTextView(R.id.tv_LoveGame).text = item.like_game
                helper.getTextView(R.id.tv_User_Desc).text = item.desc
                val infoData = PreferenceTools.getObj(this@LoveListActivity, IConstant.USERINFO, UserInfoData::class.java)
                if (infoData != null && infoData!!.data != null) {
                    if (item.id == infoData!!.data.uid) {
                        helper.getView(R.id.tv_addFollows).visibility = View.GONE
                    } else {
                        helper.getView(R.id.tv_addFollows).visibility = View.VISIBLE
                    }
                } else {
                    helper.getView(R.id.tv_addFollows).visibility = View.VISIBLE
                }
                if (item.is_sub == 0) {
                    helper.getTextView(R.id.tv_addFollows).text = "+关注"
                    helper.getTextView(R.id.tv_addFollows).isSelected = false
                    helper.getTextView(R.id.tv_addFollows).setTextColor(resources.getColor(R.color.color_shallow_yellow))
                } else if (item.is_sub == 1) {
                    helper.getTextView(R.id.tv_addFollows).text = "已关注"
                    helper.getTextView(R.id.tv_addFollows).isSelected = true
                    helper.getTextView(R.id.tv_addFollows).setTextColor(Color.WHITE)
                } else if (item.is_sub == 2) {
                    helper.getTextView(R.id.tv_addFollows).text = "互相关注"
                    helper.getTextView(R.id.tv_addFollows).setTextColor(Color.WHITE)
                    helper.getTextView(R.id.tv_addFollows).isSelected = true
                }
                helper.getTextView(R.id.tv_addFollows).setOnClickListener { v ->
                    if (AppTools.isLogin(this@LoveListActivity)) {
//                        sub(item, helper.getTextView(R.id.tv_addFollows))
                    } else {
                        AppTools.login(this@LoveListActivity)
                    }
                }
                helper.getView(R.id.iv_UserLogo).setOnClickListener {
                    UserHomeActivity.start(this@LoveListActivity, item.avatar, item.id, it as ImageView)
                }
            }
        }
        recyclerView.adapter = adapter
        persent?.getLoveList(uid, currentPage)
    }

    override fun initEvent() {
        viewBack.setOnClickListener { finish() }

    }
}