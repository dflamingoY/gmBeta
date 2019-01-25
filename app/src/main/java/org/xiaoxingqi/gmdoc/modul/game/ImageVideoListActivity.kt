package org.xiaoxingqi.gmdoc.modul.game

import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.game.GameVideoCallback
import org.xiaoxingqi.gmdoc.presenter.game.GameImageVideoPresenter

/**
 * 玩家的贡献图和视频展示列表
 */
class ImageVideoListActivity : BaseActivity<GameImageVideoPresenter>() {
    override fun createPresent(): GameImageVideoPresenter {
        return GameImageVideoPresenter(this, object : GameVideoCallback() {})
    }

    override fun setContent() {
        setContent(R.layout.activity_img_video_list)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {

    }
}