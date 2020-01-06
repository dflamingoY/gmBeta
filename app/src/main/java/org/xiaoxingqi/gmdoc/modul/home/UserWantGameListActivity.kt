package org.xiaoxingqi.gmdoc.modul.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_love_game_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.game.BaseGameBean
import org.xiaoxingqi.gmdoc.entity.game.GameListData
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.modul.game.GameDetailsActivity
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter
import org.xiaoxingqi.gmdoc.wegidt.RoundScoreView

class UserWantGameListActivity : BaseActivity<UserPresenter>() {
    private lateinit var userId: String
    private var current = 0
    private lateinit var adapter: QuickAdapter<BaseGameBean>
    private val mData by lazy { ArrayList<BaseGameBean>() }
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun otherGame(data: GameListData) {
                transLayout.showContent()
                for (bean in data.data.data) {
                    mData.add(bean)
                    adapter.notifyItemInserted(adapter.itemCount - 1)
                }
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_love_game_list)
    }

    override fun initView() {
        tv_Title.text = "想玩"
    }

    override fun initData() {
        userId = intent.getStringExtra("userId")
        adapter = object : QuickAdapter<BaseGameBean>(this, R.layout.item_other_game, mData) {
            @SuppressLint("SetTextI18n")
            override fun convert(helper: BaseAdapterHelper?, item: BaseGameBean?) {
                val scoreView = helper!!.getView(R.id.roundScore) as RoundScoreView
                scoreView.setScore(item!!.score)
                helper.getTextView(R.id.tv_GameName).text = item.game_name
                helper.getTextView(R.id.tv_stateInfo).text = item.sale_time
                helper.getTextView(R.id.tv_Title).text = item.platform + " | " + item.version
                helper.getView(R.id.tv_Edit).visibility = View.GONE
                helper.getView(R.id.tv_Delete).visibility = /*if (mIsEdit) View.VISIBLE else */View.GONE
                helper.getView(R.id.tv_Delete).setOnClickListener { v ->

                }
            }
        }
        recyclerGame.layoutManager = LinearLayoutManager(this)
        recyclerGame.adapter = adapter
        persent?.otherGameList("wish", userId, current)
    }

    override fun initEvent() {
        viewBack.setOnClickListener { finish() }
        adapter.setOnItemClickListener { view, position ->
            startActivity(Intent(this,GameDetailsActivity::class.java).putExtra("gameId",mData[position].game_id))
        }
    }
}