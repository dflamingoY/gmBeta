package org.xiaoxingqi.gmdoc.modul.home

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_love_game_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.user.LoveGameData
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

class LoveGameListActivity : BaseActivity<UserPresenter>() {
    private lateinit var adapter: QuickAdapter<LoveGameData.DataBean>
    private lateinit var userId: String
    private val mData by lazy { ArrayList<LoveGameData.DataBean>() }
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun loveGameList(data: LoveGameData) {
                transLayout.showContent()
                mData.clear()
                mData.addAll(data.data)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_love_game_list)
    }

    override fun initView() {
        tv_Edit.visibility = View.VISIBLE
    }

    override fun initData() {
        userId = intent.getStringExtra("userId")
        adapter = object : QuickAdapter<LoveGameData.DataBean>(this, R.layout.item_love_game_list, mData) {
            @SuppressLint("SetTextI18n")
            override fun convert(helper: BaseAdapterHelper?, item: LoveGameData.DataBean?) {

                helper!!.getTextView(R.id.tv_List).text = "No." + item!!.rank
                helper.getTextView(R.id.tv_GameName).text = item.game_name
                Glide.with(this@LoveGameListActivity)
                        .load(item.img)
                        .asBitmap()
                        .centerCrop()
                        .override(360, 180)
                        .error(R.drawable.img_empty_avatar_back)
                        .into(helper.getImageView(R.id.iv_Details))
                helper.getTextView(R.id.tv_Desc).text = item.desc
            }
        }
        recyclerGame.layoutManager = LinearLayoutManager(this)
        recyclerGame.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.notifyDataSetChanged()
        persent?.loveGame(userId)
    }

    override fun initEvent() {
        viewBack.setOnClickListener {
            finish()
        }
        tv_Edit.setOnClickListener {
            startActivity(Intent(this, EditGameListActivity::class.java).putExtra("userId", userId))
        }
    }
}