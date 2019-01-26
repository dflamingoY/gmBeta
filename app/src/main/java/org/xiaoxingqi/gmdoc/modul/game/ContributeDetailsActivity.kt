package org.xiaoxingqi.gmdoc.modul.game

import kotlinx.android.synthetic.main.activity_contibute_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.game.GameVideoCallback
import org.xiaoxingqi.gmdoc.presenter.game.GameImageVideoPresenter

/**
 * 贡献图, 视频的展示详情,大图
 */
class ContributeDetailsActivity : BaseActivity<GameImageVideoPresenter>() {
    override fun createPresent(): GameImageVideoPresenter {
        return GameImageVideoPresenter(this, object : GameVideoCallback() {})
    }

    override fun setContent() {
        setContent(R.layout.activity_contibute_list)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {
        viewBack.setOnClickListener {
            finish()
        }


    }
}