package org.xiaoxingqi.gmdoc.wegidt.gameDetails

import android.content.Context
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.layout_game_long_comment.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.entity.game.BaseScoreBean
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.tools.AppConfig
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.PreferenceTools
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout

class GameLongCommentView(context: Context) : BaseLayout(context) {
    private var bean: BaseScoreBean? = null

    override fun getLayoutId(): Int {
        return R.layout.layout_game_long_comment
    }

    fun setData(bean: BaseScoreBean) {
        this.bean = bean
        tv_Comment.text = if (bean.comment_num > 0) "${bean.comment_num}" else "评论"
        try {
            if (bean.is_sub === 1 || bean.is_sub === 2) {
                scoreView.setScore(bean.score.toFloat(), true)
            } else {
                val infoData = PreferenceTools.getObj(context, IConstant.USERINFO, UserInfoData::class.java)
                if (infoData != null && infoData!!.data != null) {
                    if (infoData!!.data.uid == bean.uid.toString() + "") {
                        scoreView.setScore(bean.score.toFloat(), true)
                    } else {
                        scoreView.setScore(bean.score.toFloat(), false)
                    }
                } else {
                    scoreView.setScore(bean.score.toFloat(), false)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (!TextUtils.isEmpty(bean.version) && !TextUtils.isEmpty(bean.platform_id)) {
            tv_CreateTime.text = bean.version + " | " + bean.platform_id
            iv_Flag.visibility = GONE
        } else {
            tv_CreateTime.text = TimeUtils.getInstance().paserTime(bean.created_at)
            iv_Flag.visibility = VISIBLE
        }
        tv_Title.text = bean.self_title
        Glide.with(context)
                .load(bean.img)
                .error(R.mipmap.img_avatar_default)
                .into(iv_UserLogo)
        tvLoveGame.text = bean.name + "( " + bean.like_game + " )"
        if (TextUtils.isEmpty(bean.cover)) {
            appBarPhoto.visibility = GONE
            tv_Content.visibility = VISIBLE
            tv_Content.setData(AppConfig.getImageHtml(bean.summary.replace("\n", "")))
        } else {
            appBarPhoto.visibility = VISIBLE
            tv_Content.visibility = GONE
            ImageLoader.getInstance().displayImage(bean.cover, iv_Details, AppTools.options)
            tv_PhotoTitle.text = bean.self_title
        }
        if (bean.ranking <= 3) {
            if (bean.ranking === 1)
                iv_Flag.setImageResource(R.mipmap.img_1st)
            else if (bean.ranking === 2)
                iv_Flag.setImageResource(R.mipmap.img_2nd)
            else if (bean.ranking === 3)
                iv_Flag.setImageResource(R.mipmap.img_3rd)
        } else {
            iv_Flag.visibility = GONE
        }
        if ("1" == bean.is_like) {//已经点赞 + 点赞id
            iv_Thumb.isSelected = true
            tv_Thumb.setTextColor(context.resources.getColor(R.color.color_shallow_yellow))
        } else {
            iv_Thumb.isSelected = false
            tv_Thumb.setTextColor(context.resources.getColor(R.color.color_text_color))
        }
        tv_Thumb.text = if (bean.like_num > 0) "" + bean.like_num else "赞"
        if (bean.pay_status === 1) {
            iv_pay_leg.isSelected = true
            tv_AddLeg.setTextColor(context.resources.getColor(R.color.color_shallow_yellow))
            tv_AddLeg.text = "已加"
        } else {
            tv_AddLeg.setTextColor(context.resources.getColor(R.color.color_text_color))
            iv_pay_leg.isSelected = false
            tv_AddLeg.text = "加鸡腿"
        }
    }

}