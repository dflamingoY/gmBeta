package org.xiaoxingqi.gmdoc.wegidt.homegame

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_short_comment.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.modul.dynamic.DynamicDetailsActivity
import org.xiaoxingqi.gmdoc.tools.AppConfig
import org.xiaoxingqi.gmdoc.tools.PreferenceTools
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout
import org.xiaoxingqi.gmdoc.wegidt.imagespan.VerticalImageSpan

/**
 * 短评展示的View
 */
class HomeShortCommentView(context: Context) : BaseLayout(context) {
    private var bean: HomeUserShareData.ContributeBean? = null

    override fun getLayoutId(): Int {
        return R.layout.layout_short_comment
    }

    init {

    }

    /**
     *
     */
    @SuppressLint("SetTextI18n")
    fun setData(bean: HomeUserShareData.ContributeBean) {
        this.bean = bean

        if (bean.game == null || TextUtils.isEmpty(bean.game_id)) {
            if (bean.is_ori == 0) {
                if (!TextUtils.isEmpty(bean.title)) {
                    tv_Title.setData(AppConfig.getImageHtml(bean.title))
                } else {
                    tv_Title.text = "转发短评"
                }
            } else {
                tv_Title.text = "发表短评"
            }
            tvNullContent.visibility = VISIBLE
            linearContent.visibility = GONE
            return
        }
        tvNullContent.visibility = GONE
        linearContent.visibility = VISIBLE
        if (bean.is_ori == 0) {//转发
            linear_Orignal.visibility = VISIBLE
            linearContent.setBackgroundColor(Color.parseColor("#f6f7f7"))
            tv_Orignal_User.text = "@${bean.self.username}"
            val builder = SpannableStringBuilder("发表短评   " + bean.game.game_pla + bean.game.game_ver + " | " + bean.game.game_name)
            val drawable = context.resources.getDrawable(R.mipmap.btn_game_grey)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            builder.setSpan(VerticalImageSpan(drawable), 5, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            tv_GameInfo.text = builder
            if (!TextUtils.isEmpty(bean.title)) {
                tv_Title.setData(AppConfig.getImageHtml(bean.title))
            } else {
                tv_Title.text = "转发短评"
            }
            linearContent.setOnClickListener {
                //继续跳原创详情
                context.startActivity(Intent(context, DynamicDetailsActivity::class.java).putExtra("id", bean.self.id))
            }
        } else {//原创
            linear_Orignal.visibility = GONE
            linearContent.setBackgroundColor(Color.WHITE)
            tv_Title.text = "发表短评"
        }
        tv_GameTitle.text = bean.game.game_name
        tv_Version.text = "${bean.game.game_pla} | ${bean.game.game_ver}"
        var url = ""
        if (!TextUtils.isEmpty(bean.game.game_cover)) {
            url = if (bean.game.game_cover.startsWith("http")) {
                bean.game.game_cover
            } else {
                IConstant.PICSPORT + bean.game.game_cover
            }
        }
        Glide.with(context)
                .load(url)
                .into(iv_GameLogo)
        try {
            try {
                if (bean.self.is_sub == 1 || bean.is_sub == 2) {
                    scoreView.setScore(bean.game.score.toFloat(), true)
                } else {
                    val infoData = PreferenceTools.getObj(context, IConstant.USERINFO, UserInfoData::class.java)
                    if (infoData != null && infoData.data != null) {
                        if (infoData.data.uid == bean.self.uid.toString() + "") {
                            scoreView.setScore(bean.game.score.toFloat(), true)
                        } else {
                            scoreView.setScore(bean.game.score.toFloat(), false)
                        }
                    } else {
                        scoreView.setScore(bean.game.score.toFloat(), false)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (TextUtils.isEmpty(bean.game.good)) {
            linear_Good.visibility = GONE
        } else {
            linear_Good.visibility = VISIBLE
            tv_GoodWay.text = bean.game.good
        }

        if (TextUtils.isEmpty(bean.game.bad)) {
            linear_bad.visibility = GONE
        } else {
            linear_bad.visibility = VISIBLE
            tv_BadWay.text = bean.game.bad
        }
        val content = bean.self.content
        tv_GameDesc.setData(AppConfig.getImageHtml(content))
    }

}