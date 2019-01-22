package org.xiaoxingqi.gmdoc.modul.home

import kotlinx.android.synthetic.main.activity_love_game_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.game.BaseGameBean
import org.xiaoxingqi.gmdoc.entity.game.GameListData
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter

class UserWantGameListActivity : BaseActivity<UserPresenter>() {
    private lateinit var userId: String
    private var current = 0
    private lateinit var adapter: QuickAdapter<BaseGameBean>
    private val mData by lazy { ArrayList<BaseGameBean>() }
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun otherGame(data: GameListData) {

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
            override fun convert(helper: BaseAdapterHelper?, item: BaseGameBean?) {

            }
        }
        persent?.otherGameList("wish", userId, current)
    }

    override fun initEvent() {
        viewBack.setOnClickListener { finish() }
    }
}