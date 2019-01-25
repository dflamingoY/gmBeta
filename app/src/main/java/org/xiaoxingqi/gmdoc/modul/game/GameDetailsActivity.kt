package org.xiaoxingqi.gmdoc.modul.game

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import kotlinx.android.synthetic.main.activity_game_details.*
import kotlinx.android.synthetic.main.game_head.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.App
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.BaseSimpleData
import org.xiaoxingqi.gmdoc.entity.ThumbData
import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData
import org.xiaoxingqi.gmdoc.entity.game.GameScoreAllData
import org.xiaoxingqi.gmdoc.entity.game.GameTabData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.game.GameDetailCallBack
import org.xiaoxingqi.gmdoc.modul.global.WriteLongCommentActivity
import org.xiaoxingqi.gmdoc.modul.global.WriteShortCommentActivity
import org.xiaoxingqi.gmdoc.modul.home.UserHomeActivity
import org.xiaoxingqi.gmdoc.presenter.game.GameContributeActivity
import org.xiaoxingqi.gmdoc.presenter.game.GameDetailPersent
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.FastBlur
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.gameDetails.GameLongCommentView
import org.xiaoxingqi.gmdoc.wegidt.gameDetails.GameShortCommentView
import org.xiaoxingqi.gmdoc.wegidt.homegame.ArticleListView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeDynamicView

class GameDetailsActivity : BaseActivity<GameDetailPersent>() {
    private lateinit var adapter: QuickAdapter<HomeUserShareData.ContributeBean>
    private lateinit var headView: View
    private lateinit var gameId: String
    private var loadArray = IntArray(3)//0 游戏信息  1  动态  2   长短评
    private lateinit var footView: View
    private val mData by lazy {
        ArrayList<HomeUserShareData.ContributeBean>()
    }
    private val map = HashMap<String, String>()

    @SuppressLint("SetTextI18n")
    override fun createPresent(): GameDetailPersent {
        return GameDetailPersent(this, object : GameDetailCallBack {
            override fun gameTab(data: GameTabData?) {
                headView.gameTabView.setData(data!!.data.labels)
            }

            override fun gameOperator(data: ThumbData?, type: String?) {
                when (type) {
                    "wish" -> {
                        headView.iv_Wish.setImageResource(if (data?.data?.type == 1) R.mipmap.btn_fav_selected else R.mipmap.btn_fav_default)
                    }
                    "playing" -> {
                        headView.iv_Play.setImageResource(if (data?.data?.type == 1) R.mipmap.btn_fav_defaultcopy else R.mipmap.btn_playing_default)
                    }
                    "waiting_score" -> {
                        headView.iv_Score.setImageResource(if (data?.data?.type == 1) R.mipmap.btn_wait_selected else R.mipmap.btn_wait_default)
                    }
                }
                transLayout.showContent()
            }

            override fun gameComment(data: GameScoreAllData?) {
                headView.tv_ShortCommentCount.text = "短评 (${data!!.short_list_total})"
                headView.linearShortContainer.removeAllViews()
                headView.linearLongCommentContainer.removeAllViews()
                try {
                    for (bean in data.short_list) {
                        if (headView.linearShortContainer.childCount >= 3) {
                            break
                        }
                        val view = GameShortCommentView(this@GameDetailsActivity)
                        view.setData(bean)
                        headView.linearShortContainer.addView(view)
                    }
                } catch (e: Exception) {

                }
                headView.isHaveShort.visibility = if (headView.linearShortContainer.childCount > 0) View.GONE else View.VISIBLE
                headView.tv_FindMoreShort.visibility = if (headView.linearShortContainer.childCount >= 3) View.VISIBLE else View.GONE
                headView.tv_LongCommentCount.text = "长评( ${data.long_list_total})"
                try {
                    for (bean in data.long_list) {
                        if (headView.linearLongCommentContainer.childCount >= 3) {
                            break
                        }
                        val longView = GameLongCommentView(this@GameDetailsActivity)
                        longView.setData(bean)
                        headView.linearLongCommentContainer.addView(longView)
                    }
                } catch (e: Exception) {
                }
                headView.iv_IsLongComment.visibility = if (headView.linearLongCommentContainer.childCount > 0) View.GONE else View.VISIBLE
                headView.tv_FindMoreLong.visibility = if (headView.linearLongCommentContainer.childCount >= 3) View.VISIBLE else View.GONE
                loadArray[2] = 1
                checkStatus()
            }

            override fun gameDetails(data: GameDetailsData?) {
                Glide.with(this@GameDetailsActivity)
                        .load(data?.game?.cover)
                        .asBitmap()
                        .into(object : BitmapImageViewTarget(headView.iv_Game_Logo) {
                            override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                                super.onResourceReady(resource, glideAnimation)
                                headView.iv_Bluer_Bg.setImageBitmap(FastBlur().fastblur(resource, 30, headView.iv_Bluer_Bg))
                            }
                        })
                headView.expendView.setTvShowText(data!!.game.introduce + data.game.introduce_2)
                headView.tv_GameTitle.text = data.game.game_name
                if (!"ios".equals(data.game.platform, ignoreCase = true)) {
                    headView.tv_GameExtrl.text = data.game.platform + " | " + data.game.developer + " | " + data.game.type + " | " + data.game.sale_time
                } else {
                    headView.tv_GameExtrl.text = data.game.platform + " | " + data.game.developer + " | " + data.game.type
                }
                headView.iv_Wish.setImageResource(if (data.is_love == "1") R.mipmap.btn_fav_selected else R.mipmap.btn_fav_default)
                headView.iv_Play.setImageResource(if (data.is_playing == 1) R.mipmap.btn_fav_defaultcopy else R.mipmap.btn_playing_default)
                headView.iv_Score.setImageResource(if (data.is_waiting == 1) R.mipmap.btn_wait_selected else R.mipmap.btn_wait_default)
                headView.scoreView_Community.setScore(data.all.score)
                headView.scoreView_Follow.setScore(data.my.score)
                headView.linear_img_Details.removeAllViews()
                tv_Game_Name.text = data.game.game_name
                var descData: ArrayList<BaseSimpleData>? = null
                /**
                 * 组装视屏图片的集合
                 */
                data.video?.let {
                    if (it.size > 0) {
                        descData = it
                    }
                }
                data.pic?.let {
                    if (it.size > 0) {
                        if (null == descData) {
                            descData = it
                        } else {
                            descData?.addAll(it)
                        }
                    }
                }
                val width = ((AppTools.getWindowsWidth(this@GameDetailsActivity) - AppTools.dp2px(this@GameDetailsActivity, 50)) * 1f / 3.1f + 0.5f).toInt()
                descData?.let {
                    for (index in 0 until it.size) {
                        val params = LinearLayout.LayoutParams(width, width)
                        if (index > 4) {
                            break
                        }
                        if (it.size == 1) {
                            params.setMargins(AppTools.dp2px(this@GameDetailsActivity, 14), 0, AppTools.dp2px(this@GameDetailsActivity, 12), 0)
                        } else {
                            if (index == 0) {
                                params.setMargins(AppTools.dp2px(this@GameDetailsActivity, 14), 0, 0, 0)
                            } else if (index == it.size - 1) {
                                if (index == 4) {
                                    params.setMargins(AppTools.dp2px(this@GameDetailsActivity, 12), 0, AppTools.dp2px(this@GameDetailsActivity, 12), 0)
                                } else {
                                    params.setMargins(AppTools.dp2px(this@GameDetailsActivity, 12), 0, AppTools.dp2px(this@GameDetailsActivity, 12), 0)
                                }
                            } else {
                                params.setMargins(AppTools.dp2px(this@GameDetailsActivity, 12), 0, 0, 0)
                            }
                        }
                        val view = View.inflate(this@GameDetailsActivity, R.layout.layout_game_details_desc_img, null)
                        val isVeedio = view.findViewById<View>(R.id.viewIsVedio)
                        if (TextUtils.isEmpty(it[index].url) || !it[index].url.contains(".mp4")) {
                            isVeedio.visibility = View.GONE
                        } else {
                            isVeedio.visibility = View.VISIBLE
                        }
                        val mIvGameDesc = view.findViewById<ImageView>(R.id.iv_game_img)
                        val url = if (it[index].pic.contains("?")) {
                            it[index].pic + "&imageMogr2/auto-orient/thumbnail/!200x200r"
                        } else {
                            it[index].pic + "?imageMogr2/auto-orient/thumbnail/!200x200r"
                        }
                        Glide.with(this@GameDetailsActivity)
                                .load(url)
                                .into(mIvGameDesc)
                        view.layoutParams = params
                        headView.linear_img_Details.addView(view)
                        mIvGameDesc.setOnClickListener { v ->
                            //                            startActivity(Intent(this@GameDetailsActivity, ImageVedioDetailsActivity::class.java)
//                                    .putExtra("data", descData)
//                                    .putExtra("current", finalA)
//                                    .putExtra("title", mData.getGame().getGame_name() +
//                                            " | " + mData.getGame().getPlatform()
//                                            + mData.getGame().getVer_name()))
                        }
                    }
                }
                val params = LinearLayout.LayoutParams(width, width)
                if (!(descData != null && descData?.size != 0)) {
                    params.setMargins(AppTools.dp2px(this@GameDetailsActivity, 14), 0, 0, 0)
                } else params.setMargins(0, 0, AppTools.dp2px(this@GameDetailsActivity, 14), 0)
                if (descData == null || descData?.size!! < 5) {
                    val iv = ImageView(this@GameDetailsActivity)
                    iv.setImageResource(R.drawable.btn_post_pic)
                    headView.linear_img_Details.addView(iv, params)
                    iv.setOnClickListener { v ->
                        /*if (AppTools.isLogin(this)) {
                            startActivity(Intent(this, Act_AddShare::class.java).putExtra("gameId", mGameId))
                        } else {
                            AppTools.startAct(this)
                        }*/
                    }
                } else {
                    val view = View.inflate(this@GameDetailsActivity, R.layout.layout_game_desc_find_all, null)
                    val mTvCount = view.findViewById<TextView>(R.id.tv_AllCount)
                    mTvCount.text = "${data.all_pic_num}张"
                    headView.linear_img_Details.addView(view, params)
                    view.setOnClickListener { v -> startActivity(Intent(this@GameDetailsActivity, ImageVideoListActivity::class.java).putExtra("gameId", gameId)) }
                }
                loadArray[0] = 1
                checkStatus()
            }

            override fun gameDynamic(data: HomeUserShareData?) {
                for (bean in data?.data?.data!!) {
                    mData.add(bean)
                    adapter.notifyItemInserted(adapter.itemCount - 1)
                }
                if (mData.size == 0) {
                    adapter.setIsHaveFoot(true)
                    adapter.notifyDataSetChanged()
                } else {
                    adapter.notifyBFootView(false)
                }
                loadArray[1] = 1
                checkStatus()
            }
        })
    }

    private fun checkStatus() {
        for (status in loadArray) {
            if (status != 1) {
                return
            }
        }
        Handler().postDelayed({
            transLayout.showContent()
        }, 1000)
    }

    override fun setContent() {
        setContent(R.layout.activity_game_details)
    }

    override fun initView() {
        gameRecycler.layoutManager = LinearLayoutManager(this)
        headView = View.inflate(this, R.layout.game_head, null)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        headView.layoutParams = params
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }
        footView = LayoutInflater.from(this).inflate(R.layout.layout_game_details_foot, gameRecycler, false)

    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        window.decorView.overlay
        gameId = intent.getStringExtra("gameId")
        map["_token"] = App.s_Token!!
        map["game_id"] = gameId
        adapter = object : QuickAdapter<HomeUserShareData.ContributeBean>(this, R.layout.item_dynamic, mData, headView, footView) {
            override fun convert(helper: BaseAdapterHelper?, item: HomeUserShareData.ContributeBean?) {
                Glide.with(this@GameDetailsActivity)
                        .load(item!!.avatar)
                        .override(80, 80)
                        .into(helper!!.getImageView(R.id.iv_UserLogo))
                helper.getTextView(R.id.tv_CreateTime).text = TimeUtils.getInstance().paserTime(item.created_at)
                helper.getTextView(R.id.tv_UserName).text = item.username
                helper.getTextView(R.id.tv_loveGame).text = "(${item.like_game.split(" ")[0]})"
                val container = helper.getView(R.id.frameContainer) as FrameLayout
                container.removeAllViews()

                when (item.type) {
                    0 -> {
                        val dynamicView = HomeDynamicView(this@GameDetailsActivity)
                        dynamicView.setData(item)
                        container.addView(dynamicView)
                    }
                    3 -> {
                        val articleListView = ArticleListView(this@GameDetailsActivity)
                        articleListView.setData(item)
                        container.addView(articleListView)
                    }
                }
                helper.getImageView(R.id.iv_UserLogo).setOnClickListener {
                    startActivity(Intent(this@GameDetailsActivity, UserHomeActivity::class.java).putExtra("userId", item.uid))
                }
            }
        }
        gameRecycler.adapter = adapter
        transLayout.showProgress()
        /**
         * 獲取游戲詳情
         */
        persent?.getGameDetail(gameId)
        persent?.getComment(map, gameId)
        persent?.getAllDynamic(gameId, "3", 0)
        persent?.getTab(gameId)
    }

    private var allLength = 0
    private var commentLocation = 500f
    private var bowenLocation = 1080f
    override fun initEvent() {
        headView.tv_FindMoreLong.setOnClickListener {
            headView.linearLongCommentContainer.removeViewAt(0)
        }
        gameRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                allLength += dy
                if (allLength <= AppTools.dp2px(this@GameDetailsActivity, 238)) {
                    appbar.setBackgroundColor(Color.argb((allLength / AppTools.dp2px(this@GameDetailsActivity, 238).toFloat() * 255 + 0.5f).toInt(), 0, 0, 0))
                } else {
                    appbar.setBackgroundColor(Color.BLACK)
                }
                if (allLength + AppTools.dp2px(this@GameDetailsActivity, 80) >= commentLocation) {
                    commentTitle.visibility = View.VISIBLE
                } else {
                    commentTitle.visibility = View.GONE
                }
                val dy = allLength + AppTools.dp2px(this@GameDetailsActivity, 80) + AppTools.dp2px(this@GameDetailsActivity, 38)
                if (dy >= bowenLocation) {
                    var alpha = (-(bowenLocation - dy) / AppTools.dp2px(this@GameDetailsActivity, 38).toFloat())
                    if (alpha >= 1) {
                        alpha = 1f
                    }
                    val layoutComment = commentTitle.layoutParams
                    layoutComment.height = (AppTools.dp2px(this@GameDetailsActivity, 38) * (1 - alpha) + 0.5f).toInt()
                    commentTitle.layoutParams = layoutComment

                    val params = frameBowen.layoutParams as RelativeLayout.LayoutParams
                    params.setMargins(0, (AppTools.dp2px(this@GameDetailsActivity, 38) * (1 - alpha) + 0.5f).toInt(), 0, 0)
                    params.height = AppTools.dp2px(this@GameDetailsActivity, 38)
                    frameBowen.layoutParams = params
                } else {
                    val layoutComment = commentTitle.layoutParams
                    layoutComment.height = AppTools.dp2px(this@GameDetailsActivity, 38)
                    commentTitle.layoutParams = layoutComment

                    val params = frameBowen.layoutParams as RelativeLayout.LayoutParams
                    params.setMargins(0, AppTools.dp2px(this@GameDetailsActivity, 38), 0, 0)
                    params.height = AppTools.dp2px(this@GameDetailsActivity, 38)
                    frameBowen.layoutParams = params
                }
            }
        })
        headView.viewTreeObserver.addOnGlobalLayoutListener {
            commentLocation = headView.tabCommentTitle.y
            bowenLocation = headView.tabTitleDyncmia.y
        }
        commentTitle.setOnClick {
            val indexOfChild = commentTitle.indexOfChild(it)
            when (indexOfChild) {
                0 -> {

                    true
                }
                1 -> {

                    true
                }
                2 -> {

                    true
                }
                else -> {
                    false
                }
            }
        }
        headView.tabCommentTitle.setOnClick {
            val indexOfChild = headView.tabCommentTitle.indexOfChild(it)
            when (indexOfChild) {
                0 -> {
                    true
                }
                1 -> {
                    true
                }
                2 -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
        titleDyncmia.setOnClick {
            val indexOfChild = titleDyncmia.indexOfChild(it)
            when (indexOfChild) {
                0 -> {
                    true
                }
                1 -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
        headView.tabTitleDyncmia.setOnClick {
            val indexOfChild = headView.tabTitleDyncmia.indexOfChild(it)
            when (indexOfChild) {
                0 -> {
                    true
                }
                1 -> {
                    true
                }

                else -> {
                    false
                }
            }
        }
        headView.tv_HintUseTag.setOnClickListener {
            hintTagView.showMenu()
        }
        headView.iv_Wish.setOnClickListener {
            if (!AppTools.isLogin(this)) {
                AppTools.login(this)
            } else {
                transLayout.showProgress()
                persent?.addWish(map, "wish")
            }
        }
        headView.iv_Play.setOnClickListener {
            if (!AppTools.isLogin(this)) {
                AppTools.login(this)
            } else {
                transLayout.showProgress()
                persent?.addWish(map, "playing")
            }
        }
        headView.iv_Score.setOnClickListener {
            if (!AppTools.isLogin(this)) {
                AppTools.login(this)
            } else {
                transLayout.showProgress()
                persent?.addWish(map, "waiting_score")
            }
        }
        headView.linear_invalide.setOnClickListener {
            if (!AppTools.isLogin(this)) {
                AppTools.login(this)
            } else
                startActivity(Intent(this, InviteScoreActivity::class.java)
                        .putExtra("gameId", gameId)
                )
        }
        headView.tv_FindMoreShort.setOnClickListener {

        }
        headView.tv_AddShotComment.setOnClickListener {
            startActivity(Intent(this, WriteShortCommentActivity::class.java)
                    .putExtra("gameId", gameId))
        }
        headView.tv_addLongComment.setOnClickListener {
            startActivity(Intent(this, WriteLongCommentActivity::class.java).putExtra("gameId", gameId))
        }
        headView.tv_FindMoreLong.setOnClickListener {

        }
        headView.tv_AddTag.setOnClickListener {

        }
        headView.gameTabView.setOnItemClickListener {

        }
        headView.tv_Share_img.setOnClickListener {
            startActivity(Intent(this, GameContributeActivity::class.java).putExtra("gameId", gameId))
        }
    }

    override fun request() {

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (hintTagView.isShow) {
                hintTagView.showMenu()
                return true
            }
        }

        return super.onKeyDown(keyCode, event)
    }
}