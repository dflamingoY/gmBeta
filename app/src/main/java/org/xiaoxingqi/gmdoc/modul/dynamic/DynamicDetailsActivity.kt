package org.xiaoxingqi.gmdoc.modul.dynamic

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import kotlinx.android.synthetic.main.activity_dynamic_details.*
import kotlinx.android.synthetic.main.layout_dynamic_head.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.CommentData
import org.xiaoxingqi.gmdoc.entity.DynamicDetailsData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.global.DynamicDetailsCallback
import org.xiaoxingqi.gmdoc.presenter.global.DynamicDetailsPresenter
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.homegame.ArticleListView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeDynamicView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeLongCommentView
import org.xiaoxingqi.gmdoc.wegidt.homegame.HomeShortCommentView

class DynamicDetailsActivity : BaseActivity<DynamicDetailsPresenter>() {
    private lateinit var headView: View
    private lateinit var adapter: QuickAdapter<CommentData.CommentDataBean>
    private var dynamicId: String? = null
    private var mBean: HomeUserShareData.ContributeBean? = null
    private val mData by lazy { ArrayList<CommentData.CommentDataBean>() }

    @SuppressLint("SetTextI18n")
    override fun createPresent(): DynamicDetailsPresenter {
        return DynamicDetailsPresenter(this, object : DynamicDetailsCallback {
            override fun dynamicInfo(data: DynamicDetailsData) {
                transLayout.showContent()
                mBean = data.data[0]
                if (null != mBean) {
                    Glide.with(this@DynamicDetailsActivity)
                            .load(mBean!!.avatar)
                            .error(R.mipmap.img_avatar_default)
                            .override(80, 80)
                            .centerCrop()
                            .into(GlideDrawableImageViewTarget(headView.iv_UserLogo, 0))
                    if (mBean!!.no_forward == 1) {
                        linear_aircraft.isEnabled = false
                        linear_aircraft.alpha = 0.5f
                    }
                    if (mBean!!.no_comment == 1) {
                        linear_comment.isEnabled = false
                        linear_comment.alpha = 0.5f
                    }
                    headView.tv_Private.visibility = if (mBean!!.power == 3) View.VISIBLE else View.GONE
                    headView.tv_UserName.text = mBean!!.username
                    headView.tv_loveGame.text = "(" + mBean!!.like_game.split(" ")[0] + ")"
                    headView.tv_loveGame.text = TimeUtils.getInstance().parseTime(mBean!!.created_at)
                    if (!TextUtils.isEmpty(mBean!!.ranking)) {
                        headView.iv_Flag.setImageResource(if ("1" == mBean!!.ranking) R.mipmap.img_1st else if ("2" == mBean!!.ranking) R.mipmap.img_2nd else if ("3" == mBean!!.ranking) R.mipmap.img_3rd else 0)
                    } else {
                        headView.iv_Flag.setImageResource(0)
                    }
                    headView.iv_Flag.visibility = if (mBean!!.is_ori == 0) View.GONE else View.VISIBLE
                    ivLeg.isSelected = mBean!!.pay_status == 1
                    tvLeg.text = if (mBean!!.pay_status == 1) "已加" else "加鸡腿"
                    tvLeg.setTextColor(if (mBean!!.pay_status == 1) resources.getColor(R.color.color_shallow_yellow) else resources.getColor(R.color.color_text_color))
                    tv_aircraft.text = if (mBean!!.forward_num > 0) "" + mBean!!.forward_num else "转发"
                    if (mBean!!.like_status == 1) {
                        iv_Thumb.isSelected = true
                        tv_Thumb.setTextColor(resources.getColor(R.color.color_shallow_yellow))
                    } else {
                        iv_Thumb.isSelected = false
                        tv_Thumb.setTextColor(resources.getColor(R.color.color_text_color))
                    }
                    if (mBean!!.like_num > 0) {
                        tv_Thumb.text = "" + mBean!!.like_num
                    } else {
                        tv_Thumb.text = "赞"
                    }
                    if (mBean!!.comment_num > 0) {
                        tv_Comment.text = mBean!!.comment_num.toString()
                    } else {
                        tv_Comment.text = "评论"
                    }
                    headView.tv_Repeat.text = "转发 " + mBean!!.forward_num
                    headView.tvThumb.text = "赞 " + mBean!!.like_num
                    when (mBean!!.type) {
                        0//动态
                        -> {
                            val dyncmiaView = HomeDynamicView(this@DynamicDetailsActivity)
                            dyncmiaView.setData(mBean!!)
                            headView.frame_Container.addView(dyncmiaView)
                        }
                        1//短评
                        -> {
                            val shortView = HomeShortCommentView(this@DynamicDetailsActivity)
                            shortView.setData(mBean!!)
                            headView.frame_Container.addView(shortView)
                        }
                        2//长评
                        -> {
                            val view = HomeLongCommentView(this@DynamicDetailsActivity)
                            view.setData(mBean!!)
                            headView.frame_Container.addView(view)
                        }
                        3//博文
                        -> {
                            val article = ArticleListView(this@DynamicDetailsActivity)
                            article.setData(mBean!!)
                            headView.frame_Container.addView(article)
                        }
                    }
                }
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_dynamic_details)
    }

    override fun initView() {
        recyclerComment.layoutManager = LinearLayoutManager(this)
        headView = LayoutInflater.from(this).inflate(R.layout.layout_dynamic_head, recyclerComment, false)
    }

    override fun initData() {
        dynamicId = intent.getStringExtra("dynamicId")
        adapter = object : QuickAdapter<CommentData.CommentDataBean>(this, R.layout.layout_item_comment_list_details, mData, headView) {
            override fun convert(helper: BaseAdapterHelper?, item: CommentData.CommentDataBean?) {


            }
        }
        recyclerComment.adapter = adapter
        dynamicId?.let {
            persent?.getDetails(it)
        }
    }

    override fun initEvent() {

    }

    override fun request() {

    }
}