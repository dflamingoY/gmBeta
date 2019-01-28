package org.xiaoxingqi.gmdoc.modul.home

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.frag_home.view.*
import me.dkzwm.widget.srl.MaterialSmoothRefreshLayout
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import me.dkzwm.widget.srl.config.Constants
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.BaseHomeAdapter
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.BaseHomeBean
import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData
import org.xiaoxingqi.gmdoc.entity.home.HomeGameData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.home.HomeTabCallback
import org.xiaoxingqi.gmdoc.modul.dynamic.DynamicDetailsActivity
import org.xiaoxingqi.gmdoc.modul.game.GameDetailsActivity
import org.xiaoxingqi.gmdoc.modul.game.GameListActivity
import org.xiaoxingqi.gmdoc.presenter.HomePresent
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.ItemHomeView
import org.xiaoxingqi.gmdoc.wegidt.LinearScrollView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeDynamicView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeParentImg

class HomeFragment : BaseFrag<HomePresent>() {
    private lateinit var gameAdapter: BaseHomeAdapter<List<BaseHomeBean>, BaseAdapterHelper>
    private lateinit var adapter: QuickAdapter<HomeUserShareData.ContributeBean>
    private lateinit var gameRecycler: RecyclerView
    private lateinit var activeLinearContainer: LinearLayout
    private lateinit var swipeRefresh: MaterialSmoothRefreshLayout
    private lateinit var tv_TopDesc: TextView
    private lateinit var tv_GameCount: TextView
    private val gameData by lazy {
        ArrayList<List<BaseHomeBean>>()
    }
    private lateinit var headView: View
    private val mData by lazy {
        ArrayList<HomeUserShareData.ContributeBean>()
    }
    private val gameTitleData by lazy {
        ArrayList<String>()
    }
    private var current = 1

    override fun createPresent(): HomePresent {
        return HomePresent(activity!!, object : HomeTabCallback() {
            override fun gameSuccess(data: HomeGameData?) {
                gameData.clear()
                data?.data?.dy_top_big
                tv_TopDesc.text = data?.data?.dy_top_big?.get(0)?.title
                Glide.with(this@HomeFragment)
                        .load(data?.data?.dy_top_big?.get(0)?.cover)
                        .into(headView.findViewById(R.id.iv_topImg))
                for (indices in data?.data?.game?.data!!.indices) {//遍歷游戏数据在不同的位置插入 新闻
                    if (indices == 2) {
                        gameData.add(data.data?.dy_long!!)
                    } else if (indices == 5) {
                        gameData.add(data.data?.dy_blog!!)
                    }
                    gameData.add(data.data?.game?.data!![indices])
                }
                gameTitleData.addAll(data.data?.game?.pla_list!!)
                /**
                 * update recyclerView
                 */
                gameAdapter.notifyDataSetChanged()
                swipeRefresh.refreshComplete()
            }

            @SuppressLint("InflateParams")
            override fun activeSuccess(data: HomeActiveData?) {//6个活动的type
                if (data!!.data != null && data.data.data != null) {
                    activeLinearContainer.removeAllViews()
                    val width = ((AppTools.getWindowsWidth(activity) - AppTools.dp2px(activity, 52)) * 1f / 4.28f + 0.5f).toInt()
                    for (a in 0 until data.data.data.size) {
                        val params = LinearLayout.LayoutParams(width, width)
                        val view = LayoutInflater.from(activity).inflate(R.layout.layout_home_active_img, null)
                        if (a == 0) {
                            params.setMargins(AppTools.dp2px(activity, 12), AppTools.dp2px(activity, 13), 0, AppTools.dp2px(activity, 13))
                        } else if (a == data.data.data.size - 1) {
                            params.setMargins(AppTools.dp2px(activity, 10), AppTools.dp2px(activity, 13), AppTools.dp2px(activity, 12), AppTools.dp2px(activity, 13))
                        } else {
                            params.setMargins(AppTools.dp2px(activity, 10), AppTools.dp2px(activity, 13), 0, AppTools.dp2px(activity, 13))
                        }
                        (view.findViewById(R.id.tv_activeName) as TextView).text = data.data.data[a].activity_name
                        Glide.with(activity).load(data.data.data[a].activity_pic)
                                .centerCrop()
                                .into(view.findViewById(R.id.img_active) as ImageView)
                        activeLinearContainer.addView(view, params)
                    }
                }
                swipeRefresh.refreshComplete()
            }

            override fun contibuteSuccess(data: HomeUserShareData?) {
                if (data != null && data.data.data != null) {
                    for (bean in data.data.data) {
                        mData.add(bean)
                        adapter.notifyItemInserted(adapter.itemCount - 1)
                    }
                }
                swipeRefresh.refreshComplete()
            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_home
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initView(view: View?) {
        /**
         * 自动滚动
         */
        val layoutManager = object : LinearLayoutManager(activity) {
            override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
                return RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mView!!.recyclerView.layoutManager = layoutManager
        mView!!.recyclerView.isNestedScrollingEnabled = false
        headView = LayoutInflater.from(activity).inflate(R.layout.home_heard_layout, null, false)
        mView!!.relativeAction.alpha = 0f
        gameRecycler = headView.findViewById(R.id.recyclerView)
        activeLinearContainer = headView.findViewById(R.id.linear_Active)
        swipeRefresh = view!!.swipeRefresh
        val scrollview = headView.findViewById<LinearScrollView>(R.id.horizontalScrollview)
        tv_TopDesc = headView.findViewById(R.id.tv_TopDesc)
        tv_GameCount = headView.findViewById(R.id.tv_GameCount)
        tv_GameCount.text = Html.fromHtml(resources.getString(R.string.string_all_game_count))
        scrollview.setOnInterListener(object : LinearScrollView.OnInterListener {
            override fun intercept() {
                swipeRefresh.isEnabled = false
            }

            override fun cancle() {
                swipeRefresh.isEnabled = true
            }
        })
        scrollview.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    swipeRefresh.isEnabled = false
                }
                MotionEvent.ACTION_MOVE -> {
                    swipeRefresh.isEnabled = false
                }
                MotionEvent.ACTION_UP -> {
                    swipeRefresh.isEnabled = true
                }
                MotionEvent.ACTION_CANCEL -> {
                    swipeRefresh.isEnabled = true
                }
            }
            false
        }
        swipeRefresh.setDisableLoadMore(false)
        swipeRefresh.materialStyle()
        swipeRefresh.setEnableAutoLoadMore(true)
        swipeRefresh.setEnableSmoothRollbackWhenCompleted(true)
        swipeRefresh.setDisableLoadMoreWhenContentNotFull(true)
        swipeRefresh.autoRefresh(Constants.ACTION_NOTHING, false)
    }

    override fun initData() {
        gameAdapter = object : BaseHomeAdapter<List<BaseHomeBean>, BaseAdapterHelper>(activity, gameData) {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AppTools.dp2px(activity, 152))
            var width = ((AppTools.getWindowsWidth(activity) - AppTools.dp2px(activity, 14)) * 1f / 1.3 + 0.5f).toInt()
            @SuppressLint("SetTextI18n")
            override fun convert(helper: BaseAdapterHelper?, item: List<BaseHomeBean>?) {
                if (getItemViewType(helper!!.itemView.tag as Int) == 1) {//圖片
                    if (helper.itemView.tag as Int == 2) {
                        helper.getTextView(R.id.tv_SortName).text = "玩家们的游戏长评"
                    } else if (helper.itemView.tag as Int == 6) {
                        helper.getTextView(R.id.tv_SortName).text = "玩家们的博文"
                    }
                    val mLinearContainer = helper.getView(R.id.lineaContainer) as LinearLayout
                    mLinearContainer.removeAllViews()
                    for (a in item!!.indices) {
                        val params = LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT)
                        if (a == item.size - 1) {
                            params.setMargins(AppTools.dp2px(activity, 14), 0, AppTools.dp2px(activity, 14), 0)
                        } else {
                            params.setMargins(AppTools.dp2px(activity, 14), 0, 0, 0)
                        }
                        val homeImg = HomeParentImg(activity!!)
                        val bean = item[a]
                        homeImg.setData(bean)
                        mLinearContainer.addView(homeImg, params)

                    }
                } else {//游戏列表
                    val linearContainer = helper.itemView.findViewById<LinearLayout>(R.id.linearContainer)
                    val viewDivision = helper.getView(R.id.view_Indecator)
                    val mIvTypeGame = helper.getImageView(R.id.iv_SortType)
                    val mTvTypeName = helper.getTextView(R.id.tv_SortTypeName)
                    val position = helper.itemView.tag as Int
                    /**
                     * 分两段    ,  -2  ,  -3
                     */
                    var name = ""
                    if (position in 0..1) {//0 1
                        name = gameTitleData[position]
                    } else if (position in 3..5) {//3 4 5
                        name = gameTitleData[position - 1]
                    } else if (position in 7..9) {//7 8 9
                        name = gameTitleData[position - 2]
                    }
                    when (name) {
                        "ps4" -> {
                            mTvTypeName.text = "PlayStation 4 最新游戏"
                            viewDivision.setBackgroundColor(resources.getColor(R.color.color_ps4_))
                            mIvTypeGame.setImageResource(R.mipmap.img_mark_ps4)
                        }
                        "switch" -> {
                            mTvTypeName.text = "Switch 最新游戏"
                            viewDivision.setBackgroundColor(resources.getColor(R.color.color_Switch))
                            mIvTypeGame.setImageResource(R.mipmap.img_mark_switch)
                        }
                        "pc" -> {
                            mTvTypeName.text = "PC 最新游戏"
                            mIvTypeGame.setImageResource(R.mipmap.img_mark_pc)
                            viewDivision.setBackgroundColor(resources.getColor(R.color.color_PC))
                        }
                        "xboxone" -> {
                            mTvTypeName.text = "XboxOne 最新游戏"
                            mIvTypeGame.setImageResource(R.mipmap.img_mark_xboxone)
                            viewDivision.setBackgroundColor(resources.getColor(R.color.color_XBox))
                        }
                        "3ds" -> {
                            mTvTypeName.text = "3DS 最新游戏"
                            mIvTypeGame.setImageResource(R.mipmap.img_mark_3ds)
                            viewDivision.setBackgroundColor(resources.getColor(R.color.color_3DS))
                        }
                        "psvita" -> {
                            mIvTypeGame.setImageResource(R.mipmap.img_mark_psvota)
                            mTvTypeName.text = "PSVITA 最新游戏"
                            viewDivision.setBackgroundColor(resources.getColor(R.color.color_PSVITA))
                        }
                        "ios" -> {
                            mIvTypeGame.setImageResource(R.mipmap.img_mark_ios)
                            mTvTypeName.text = "iOS 热门游戏"
                            viewDivision.setBackgroundColor(resources.getColor(R.color.color_IOS))
                        }
                        "psvr" -> {
                            mIvTypeGame.setImageResource(R.mipmap.img_mark_psvr)
                            mTvTypeName.text = "PlayStation VR 最新游戏"
                            viewDivision.setBackgroundColor(resources.getColor(R.color.color_PS_VR))
                        }
                    }

                    helper.getView(R.id.tvfindAll).setOnClickListener {
                        startActivity(Intent(activity, GameListActivity::class.java)
                                .putExtra("platfromId", name))
                    }
                    linearContainer.removeAllViews()
                    if (item != null) {
                        for (bean in item) {
                            val gameItem = ItemHomeView(activity!!)
                            gameItem.setData(bean)
                            linearContainer.addView(gameItem, params)
                            gameItem.setOnClickListener {
                                startActivity(Intent(activity, GameDetailsActivity::class.java).putExtra("gameId", bean.id))
                            }
                        }
                    }
                }
            }
        }
        gameRecycler.layoutManager = LinearLayoutManager(activity)
        gameRecycler.isNestedScrollingEnabled = false
        gameRecycler.adapter = gameAdapter
        gameRecycler.isFocusableInTouchMode = false
        adapter = object : QuickAdapter<HomeUserShareData.ContributeBean>(activity, R.layout.item_dynamic, mData, headView) {

            @SuppressLint("SetTextI18n")
            override fun convert(helper: BaseAdapterHelper?, item: HomeUserShareData.ContributeBean?) {
                Glide.with(this@HomeFragment)
                        .load(item!!.avatar)
                        .override(80, 80)
                        .into(helper!!.getImageView(R.id.iv_UserLogo))
                helper.getTextView(R.id.tv_CreateTime).text = TimeUtils.getInstance().parseTime(item.created_at)
                helper.getTextView(R.id.tv_UserName).text = item.username
                helper.getTextView(R.id.tv_loveGame).text = "(" + item.like_game.split(" ")[0] + ")"
                val container = helper.getView(R.id.frameContainer) as FrameLayout
                container.removeAllViews()
                val dynamic = HomeDynamicView(activity!!)
                dynamic.setData(item)
                container.addView(dynamic)
                helper.getView(R.id.cardLogo).setOnClickListener {
                    startActivity(Intent(activity, UserHomeActivity::class.java)
                            .putExtra("userId", item.uid))
                }
            }
        }
        mView!!.recyclerView.adapter = adapter
        persent?.let {
            it.getActionData()
            it.getAttributeData(current)
            it.getGameData()
        }
    }

    override fun bindEvent() {
        swipeRefresh.setOnRefreshListener(object : RefreshingListenerAdapter() {
            override fun onRefreshing() {
                persent?.let {
                    current = 1
                    it.getActionData()
                    it.getAttributeData(current)
                    it.getGameData()
                }
            }

            override fun onLoadingMore() {
                current++
                persent?.getAttributeData(current)
            }
        })
        adapter.setOnItemClickListener { view, position ->
            startActivity(Intent(activity, DynamicDetailsActivity::class.java).putExtra("dynamicId", mData[position].id))
        }
    }

    override fun request(flag: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}