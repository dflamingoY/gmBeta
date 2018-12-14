package org.xiaoxingqi.gmdoc.modul.game

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_game_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.game.BaseGameBean
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.game.GameListCallBack
import org.xiaoxingqi.gmdoc.parsent.GameListPresent
import org.xiaoxingqi.gmdoc.wegidt.RoundScoreView

class GameListActicivty : BaseActivity<GameListPresent>() {
    private lateinit var adapter: QuickAdapter<BaseGameBean>
    private var current = 0
    private val mData by lazy {
        ArrayList<BaseGameBean>()
    }

    override fun createPresent(): GameListPresent {
        return GameListPresent(this, GameListCallBack {
            it?.let {
                if (it.data.data != null && it.data.data.size > 0) {
                    for (bean in it.data.data) {
                        mData.add(bean)
                        adapter.notifyItemInserted(adapter.itemCount - 1)
                    }
                }
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_game_list)
    }

    override fun initView() {

    }

    override fun initData() {
        val platName = intent.getStringExtra("platfromId")
        adapter = object : QuickAdapter<BaseGameBean>(this, R.layout.item_home_game_layout, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseGameBean?) {
                var url = if (null != item!!.cover && item!!.cover.startsWith("http")!!) {
                    item.cover
                } else {
                    IConstant.PICSPORT + item.cover
                }
                if ("ios".equals(platName, true)) {
                    helper!!.getView(R.id.linear_Time).visibility = View.GONE
                } else {
                    helper!!.getView(R.id.linear_Time).visibility = View.VISIBLE
                }
                Glide.with(this@GameListActicivty)
                        .load(url)
                        .error(R.mipmap.img_empty_square)
                        .placeholder(R.mipmap.img_empty_square)
                        .into(helper.getImageView(R.id.iv_Game_Logo))
                helper.getTextView(R.id.tv_Game_Name).text = item.game_name
                (helper.getView(R.id.relative_Score) as RoundScoreView).setScore(item.score)
                helper.getTextView(R.id.tv_Desc).text = item.introduce
                helper.getTextView(R.id.tv_Time).text = item.sale_time
                helper.getTextView(R.id.tv_Version).text = item.version
                if (!TextUtils.isEmpty(item.extra))
                    helper.getTextView(R.id.tv_Extre).text = item.extra
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        persent?.getGameList(platName, current)
    }

    override fun initEvent() {

    }

    override fun request() {

    }
}