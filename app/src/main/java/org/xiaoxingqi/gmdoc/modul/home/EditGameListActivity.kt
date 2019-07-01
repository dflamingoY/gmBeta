package org.xiaoxingqi.gmdoc.modul.home

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit_game_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.user.LoveGameData
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

class EditGameListActivity : BaseActivity<UserPresenter>() {
    private lateinit var adapter: QuickAdapter<LoveGameData.DataBean>
    private lateinit var userId: String
    private val mData by lazy { ArrayList<LoveGameData.DataBean>() }

    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun loveGameList(data: LoveGameData) {
                mData.clear()
                mData.addAll(data.data)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_edit_game_list)
    }

    override fun initView() {

    }

    override fun initData() {
        userId = intent.getStringExtra("userId")
        adapter = object : QuickAdapter<LoveGameData.DataBean>(this, R.layout.item_suolve_lvoe_game, mData) {
            @SuppressLint("SetTextI18n")
            override fun convert(helper: BaseAdapterHelper?, item: LoveGameData.DataBean?) {
                helper!!.getTextView(R.id.tv_No).text = "No." + item!!.rank
                helper.getTextView(R.id.tv_gameName).text = item.game_name
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
        viewBack.setOnClickListener { finish() }
        adapter.setOnItemClickListener { view, position ->
            startActivity(Intent(this, UserEditGameActivity::class.java)
                    .putExtra("data", mData)
                    .putExtra("current", position)
            )
        }
        linear_AddGame.setOnClickListener {
            startActivity(Intent(this, UserEditGameActivity::class.java)
                    .putExtra("data", mData)
                    .putExtra("current", -1))
        }
    }
}