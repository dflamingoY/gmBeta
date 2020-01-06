package org.xiaoxingqi.gmdoc.modul.game

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.frag_game.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.game.BaseGameBean
import org.xiaoxingqi.gmdoc.entity.game.GameListData
import org.xiaoxingqi.gmdoc.entity.game.GamePlatformData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.game.GameFragCallback
import org.xiaoxingqi.gmdoc.listener.OnGameTabClickListener
import org.xiaoxingqi.gmdoc.presenter.game.GameFragPersent
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.wegidt.RoundScoreView
import org.xiaoxingqi.gmdoc.wegidt.TransLayout
import org.xiaoxingqi.gmdoc.wegidt.gamefraglist.GameTabView

class GameFragment : BaseFrag<GameFragPersent>() {
    private lateinit var platRecycler: RecyclerView
    private lateinit var platAdapter: QuickAdapter<GamePlatformData.PlatformListData>
    private lateinit var headView: View
    private lateinit var gameListRecycler: RecyclerView
    private lateinit var transLayout: TransLayout
    private lateinit var gameTabView: GameTabView
    private val platData by lazy {
        ArrayList<GamePlatformData.PlatformListData>()
    }
    private val mData by lazy {
        ArrayList<BaseGameBean>()
    }

    private lateinit var adapter: QuickAdapter<BaseGameBean>
    /**
     * 平台名
     */
    private var name: String? = null
    /**
     * 版本号
     */
    private var version: String? = null
    /**
     * 发布状态
     */
    private var sell = ""
    /**
     * 默认类型
     */
    private var defaultType = ""

    override fun createPresent(): GameFragPersent {
        return GameFragPersent(activity!!, object : GameFragCallback {
            override fun gamePlatList(data: GamePlatformData?) {
                for (bean in data!!.pla_list) {
                    platData.add(bean)
                    platAdapter.notifyItemInserted(platAdapter.itemCount - 1)
                }
                gameTabView.setPlatData(data = data.pla_list)
            }

            override fun gameDetailsList(data: GameListData?) {
                if (platRecycler.visibility == View.VISIBLE) {
                    platRecycler.visibility = View.GONE
                    mView!!.linearGameList.visibility = View.VISIBLE
                }
                for (bean in data?.data?.data!!) {
                    mData.add(bean)
                    adapter.notifyItemInserted(adapter.itemCount - 1)
                }
                transLayout.showContent()
            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_game
    }

    override fun initView(view: View?) {
        platRecycler = view!!.platRecycler
        gameListRecycler = view!!.gameListRecycler
        transLayout = view!!.transLayout
        gameTabView = view!!.gameTabView
        val manager = GridLayoutManager(activity, 4)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) manager.spanCount else 1
            }
        }
        platRecycler.layoutManager = manager
        headView = LayoutInflater.from(activity).inflate(R.layout.layout_game_head_title, platRecycler, false)
    }

    override fun initData() {
        platAdapter = object : QuickAdapter<GamePlatformData.PlatformListData>(activity, R.layout.item_game_platform_list, platData, headView) {
            var width = ((AppTools.getWindowsWidth(activity) - AppTools.dp2px(activity, 56)) * 1f / 4 + 0.5f).toInt()
            var height = (width / 0.9f + 0.5f).toInt()
            override fun convert(helper: BaseAdapterHelper?, item: GamePlatformData.PlatformListData?) {
                val mParams = RelativeLayout.LayoutParams(width, height)
                if ((helper!!.itemView.tag as Int).rem(4) == 3) {
                    mParams.setMargins(AppTools.dp2px(activity, 12), 0, AppTools.dp2px(activity, 10), AppTools.dp2px(activity, 11))
                } else if (helper.itemView.tag as Int % 4 == 0) {
                    mParams.setMargins(AppTools.dp2px(activity, 12), 0, 0, AppTools.dp2px(activity, 11))
                } else {
                    mParams.setMargins(AppTools.dp2px(activity, 10), 0, 0, AppTools.dp2px(activity, 11))
                }
                helper.getImageView(R.id.iv_Platform).layoutParams = mParams
                Glide.with(activity)
                        .load(item!!.url)
                        .into(helper.getImageView(R.id.iv_Platform))
            }
        }
        platRecycler.adapter = platAdapter
        presenter?.getGamePlat()
        adapter = object : QuickAdapter<BaseGameBean>(activity, R.layout.item_game_list_layout, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseGameBean?) {

                var url = ""
                if (!TextUtils.isEmpty(item!!.cover)) {
                    if (item.cover.startsWith("http")) {
                        url = item.cover
                    } else {
                        url = IConstant.PICSPORT + item.cover
                    }
                }
                Glide.with(activity)
                        .load("$url?imageMogr2/auto-orient/thumbnail/!200x200r")
                        .override(200, 200)
                        .centerCrop()
                        .into(helper!!.getImageView(R.id.iv_Game_Logo))
                if ("ios".equals(name, ignoreCase = true)) {
                    helper.getView(R.id.linear_Time).visibility = View.GONE
                } else {
                    helper.getView(R.id.linear_Time).visibility = View.VISIBLE
                }
                helper.getTextView(R.id.tv_Game_Name).text = item.game_name
                (helper.getView(R.id.relative_Score) as RoundScoreView).setScore(item.score)
                /*if (AppTools.isLogin(activity)) {
                    (helper.getView(R.id.relative_Follow) as RoundScoreView).setScore(bean.getF_score())
                } else {
                    (helper.getView(R.id.relative_Follow) as RoundScoreView).setScore(-1)
                }*/
                helper.getTextView(R.id.tv_Desc).text = item.introduce
                helper.getTextView(R.id.tv_Time).text = item.sale_time
                helper.getTextView(R.id.tv_Version).text = item.version
                if (!TextUtils.isEmpty(item.extra))
                    helper.getTextView(R.id.tv_Extre).text = item.extra
                else {
                    helper.getTextView(R.id.tv_Extre).text = ""
                }
            }
        }
        gameListRecycler.layoutManager = LinearLayoutManager(activity)
        gameListRecycler.adapter = adapter
    }

    override fun bindEvent() {
        platAdapter.setOnItemClickListener { view, position ->
            gameTabView.setCurrentPlat(position)
            transLayout.showProgress()
            name = platData[position].name
            version = platData[position].version[0].id.toString()
            presenter?.getGameList(name!!, version = version!!, sell = sell!!, type = defaultType!!, page = 0)
        }
        adapter.setOnItemClickListener { view, position ->
            startActivity(Intent(activity, GameDetailsActivity::class.java).putExtra("gameId", mData[position].id))
        }
        gameTabView.setOnTabListener(object : OnGameTabClickListener {
            override fun onplatClick(name: String?, position: Int) {
                this@GameFragment.name = name
                transLayout.showProgress()
                version = platData[position].version[0].id.toString()
                mData.clear()
                adapter.notifyDataSetChanged()
                presenter?.getGameList(name!!, version = version!!, sell = sell!!, type = defaultType!!, page = 0)
            }

            override fun versionClick(id: Int) {
                version = id.toString()
                transLayout.showProgress()
                mData.clear()
                adapter.notifyDataSetChanged()
                presenter?.getGameList(name!!, version = version!!, sell = sell!!, type = defaultType!!, page = 0)
            }

            override fun sellClick(id: Int) {
                sell = id.toString()
                transLayout.showProgress()
                mData.clear()
                adapter.notifyDataSetChanged()
                presenter?.getGameList(name!!, version = version!!, sell = sell!!, type = defaultType!!, page = 0)
            }

            override fun releasClick(id: Int) {
                defaultType = id.toString()
                transLayout.showProgress()
                mData.clear()
                adapter.notifyDataSetChanged()
                presenter?.getGameList(name!!, version = version!!, sell = sell!!, type = defaultType!!, page = 0)
            }
        })
    }

    override fun request(flag: Int) {

    }
}