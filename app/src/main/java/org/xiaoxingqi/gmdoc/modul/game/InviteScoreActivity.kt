package org.xiaoxingqi.gmdoc.modul.game

import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_invate_score.*
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import me.dkzwm.widget.srl.config.Constants
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.game.BaseInvateBean
import org.xiaoxingqi.gmdoc.entity.game.InvateGameData
import org.xiaoxingqi.gmdoc.impl.game.GameInviteCallback
import org.xiaoxingqi.gmdoc.presenter.game.InviteScorePresenter

class InviteScoreActivity : BaseActivity<InviteScorePresenter>() {
    private val map by lazy { HashMap<String, String>() }
    private lateinit var adapter: QuickAdapter<BaseInvateBean>
    private val mData by lazy { ArrayList<BaseInvateBean>() }
    private var current = 1
    override fun createPresent(): InviteScorePresenter {
        return InviteScorePresenter(this, object : GameInviteCallback {
            override fun invating(data: BaseRespData?, bean: BaseInvateBean) {
                if (data?.state == 200) {
                    bean.state = 1
                    adapter.notifyItemChanged(mData.indexOf(bean))
                    Snackbar.make(refresh, "邀请已发出", Snackbar.LENGTH_SHORT).show()
                }
            }

            override fun invateUser(data: InvateGameData?) {
                refresh.refreshComplete()
                data?.let {
                    if (it.data != null) {
                        for (item in it.data) {
                            mData.add(item)
                            adapter.notifyItemInserted(adapter.itemCount - 1)
                        }
                    }
                }
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_invate_score)
    }

    override fun initView() {
        refresh.setDisableLoadMore(false)
        refresh.materialStyle()
        refresh.setEnableAutoLoadMore(true)
        refresh.setEnableSmoothRollbackWhenCompleted(true)
        refresh.setDisableLoadMoreWhenContentNotFull(true)
        refresh.autoRefresh(Constants.ACTION_NOTHING, false)
    }

    override fun initData() {
        val gameId = intent.getStringExtra("gameId")
        map["_token"] = App.s_Token!!
        map["gameId"] = gameId
        adapter = object : QuickAdapter<BaseInvateBean>(this, R.layout.item_invate_score, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseInvateBean?) {
                Glide.with(this@InviteScoreActivity)
                        .load(item!!.avatar)
                        .asBitmap()
                        .error(R.mipmap.img_avatar_default)
                        .override(80, 80)
                        .centerCrop()
                        .into(helper!!.getImageView(R.id.iv_UserLogo))
                helper.getTextView(R.id.tv_UserName).text = item.name
                helper.getTextView(R.id.tv_User_Desc).text = item.desc
                when (item.state) {
                    0 -> {
                        helper.getTextView(R.id.tv_addFollows).text = "发出邀请"
                        helper.getTextView(R.id.tv_addFollows).setTextColor(resources.getColor(R.color.color_shallow_yellow))
                        helper.getTextView(R.id.tv_addFollows).isClickable = true
                        helper.getTextView(R.id.tv_addFollows).setBackgroundResource(R.drawable.invalidate_game_score_sector)
                    }
                    1 -> {
                        helper.getTextView(R.id.tv_addFollows).isClickable = false
                        helper.getTextView(R.id.tv_addFollows).setTextColor(resources.getColor(R.color.color_888))
                        helper.getTextView(R.id.tv_addFollows).text = "已邀请"
                        helper.getTextView(R.id.tv_addFollows).setBackgroundResource(R.drawable.shape_grey_round_100)
                    }
                    2 -> {
                        helper.getTextView(R.id.tv_addFollows).isClickable = false
                        helper.getTextView(R.id.tv_addFollows).text = "已评分"
                        helper.getTextView(R.id.tv_addFollows).isSelected = true
                        helper.getTextView(R.id.tv_addFollows).setTextColor(resources.getColor(R.color.color_888))
                        helper.getTextView(R.id.tv_addFollows).setBackgroundResource(R.drawable.shape_grey_round_100)
                    }
                }
                helper.getView(R.id.tv_addFollows).setOnClickListener {
                    if (item.state == 0) {
                        map["uid"] = item.id.toString()
                        persent?.invating(map, item)
                    }
                }
            }
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        persent?.getFollowUser(map, current)
    }

    override fun initEvent() {
        refresh.setOnRefreshListener(object : RefreshingListenerAdapter() {
            override fun onRefreshing() {
                mData.clear()
                adapter.notifyDataSetChanged()
                current = 1
                persent?.getFollowUser(map, current)
            }

            override fun onLoadingMore() {
                current++
                persent?.getFollowUser(map, current)
            }
        })
    }

}