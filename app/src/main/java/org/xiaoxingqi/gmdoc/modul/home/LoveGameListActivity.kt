package org.xiaoxingqi.gmdoc.modul.home

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

    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun loveGameList(data: LoveGameData) {

            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_love_game_list)
    }

    override fun initView() {

    }

    override fun initData() {
        adapter = object : QuickAdapter<LoveGameData.DataBean>(this, R.layout.item_love_game_list) {
            override fun convert(helper: BaseAdapterHelper?, item: LoveGameData.DataBean?) {
        
            }
        }
    }

    override fun initEvent() {
        viewBack.setOnClickListener {
            finish()
        }

    }
}