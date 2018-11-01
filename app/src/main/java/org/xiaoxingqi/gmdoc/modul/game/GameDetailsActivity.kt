package org.xiaoxingqi.gmdoc.modul.game

import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.parsent.GameDetailPersent

class GameDetailsActivity : BaseActivity<GameDetailPersent>() {


    override fun createPresent(): GameDetailPersent {
        return GameDetailPersent(this)
    }

    override fun setContent() {
        setContent(R.layout.activity_game_details)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {

    }

    override fun request() {

    }
}