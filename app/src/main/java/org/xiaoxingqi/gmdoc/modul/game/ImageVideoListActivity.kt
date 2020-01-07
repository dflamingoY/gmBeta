package org.xiaoxingqi.gmdoc.modul.game

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_img_video_list.*
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.BaseSimpleData
import org.xiaoxingqi.gmdoc.entity.game.GameImageData
import org.xiaoxingqi.gmdoc.entity.game.GameVideoImgData
import org.xiaoxingqi.gmdoc.impl.game.GameVideoCallback
import org.xiaoxingqi.gmdoc.presenter.game.GameImageVideoPresenter
import org.xiaoxingqi.gmdoc.tools.AppTools

/**
 * 玩家的贡献图和视频展示列表
 */
class ImageVideoListActivity : BaseActivity<GameImageVideoPresenter>() {
    private lateinit var gameId: String
    private lateinit var adapter: QuickAdapter<BaseSimpleData>
    private val mData by lazy { ArrayList<BaseSimpleData>() }
    private var current = 1
    private lateinit var bottomView: ImageView

    override fun createPresent(): GameImageVideoPresenter {
        return GameImageVideoPresenter(this, object : GameVideoCallback() {
            override fun getVideoList(data: GameVideoImgData?) {
                if (null != data?.video) {
                    mData.addAll(data.video)
                }
                if (null != data?.pic) {
                    mData.addAll(data?.pic)
                }
                adapter.notifyDataSetChanged()
                persent?.getGameImg(gameId, current)
            }

            override fun getImgList(data: GameImageData?) {
                refresh.refreshComplete()
                if (null != data!!.pic) {
                    for (bean in data.pic) {
                        mData.add(bean)
                        adapter.notifyItemInserted(adapter.itemCount - 1)
                    }
                }
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_img_video_list)
    }

    override fun initView() {
        refresh.setDisableLoadMore(false)
        refresh.materialStyle()
        refresh.setEnableAutoLoadMore(false)
        refresh.setEnableSmoothRollbackWhenCompleted(true)
        refresh.setDisableLoadMoreWhenContentNotFull(true)
        refresh.setDisableRefresh(true)
        bottomView = ImageView(this)
        bottomView.setImageResource(R.drawable.btn_post_pic)
    }

    override fun initData() {
        gameId = intent.getStringExtra("gameId")
        adapter = object : QuickAdapter<BaseSimpleData>(this, R.layout.item_game_vedio_img_details, mData, null, bottomView) {
            private var height = ((AppTools.getWindowsWidth(this@ImageVideoListActivity) - AppTools.dp2px(this@ImageVideoListActivity, 24)) * 1f / 3 + 0.5f).toInt()
            private var width = (AppTools.getWindowsWidth(this@ImageVideoListActivity) * 1f / 3 + 0.5f).toInt()
            private var margin = AppTools.dp2px(this@ImageVideoListActivity, 6)
            override fun convert(helper: BaseAdapterHelper?, item: BaseSimpleData?) {
                val mParams = RelativeLayout.LayoutParams(height, height)
                if (helper!!.itemView.tag as Int % 3 == 0) {
                    mParams.setMargins(margin, margin, width - height - margin, 0)
                } else if (helper.itemView.tag as Int % 3 == 1) {
                    mParams.setMargins(margin - (width - height - margin), margin, width - height - margin, 0)
                } else if (helper.itemView.tag as Int % 3 == 2) {
                    mParams.setMargins(width - height - margin, margin, margin, 0)
                }

                val data = item as BaseSimpleData
                if (null == data) {
                    helper.itemView.layoutParams = mParams
                    return
                }
                if (TextUtils.isEmpty(data.url)) {
                    helper.getView(R.id.viewIsVedio).visibility = View.GONE
                } else {
                    helper.getView(R.id.viewIsVedio).visibility = View.VISIBLE
                }
                Glide.with(this@ImageVideoListActivity)
                        .asBitmap()
                        .load(data.pic)
                        .error(R.mipmap.img_empty_square)
                        .centerCrop()
                        .into(helper.getImageView(R.id.iv_game_img))
                helper.getImageView(R.id.iv_game_img).layoutParams = mParams
            }
        }
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter
        persent?.getVideo(gameId)
    }

    override fun initEvent() {
        viewBack.setOnClickListener {
            finish()
        }
        refresh.setOnRefreshListener(object : RefreshingListenerAdapter() {
            override fun onRefreshing() {

            }

            override fun onLoadingMore() {
                current++
                persent?.getGameImg(gameId, current)
            }
        })
        bottomView.setOnClickListener {
            startActivityForResult(Intent(this, GameContributeActivity::class.java)
                    .putExtra("gameId", gameId)
                    , 0)
        }
        adapter.setOnItemClickListener { view, position ->
            startActivity(Intent(this, ContributeDetailsActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

        }
    }
}