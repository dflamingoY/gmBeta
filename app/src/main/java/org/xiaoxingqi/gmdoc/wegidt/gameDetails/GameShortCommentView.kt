package org.xiaoxingqi.gmdoc.wegidt.gameDetails

import android.content.Context
import android.text.TextUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_game_short_comment.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.entity.game.BaseScoreBean
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.tools.AppConfig
import org.xiaoxingqi.gmdoc.tools.PreferenceTools
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout

class GameShortCommentView(context: Context) : BaseLayout(context) {

    init {


    }

    override fun getLayoutId(): Int {
        return R.layout.layout_game_short_comment
    }

    fun setData(bean: BaseScoreBean) {
        try {
            if (bean.is_sub === 1 || bean.is_sub === 2) {
                scoreView.setScore(bean.score.toFloat(), true)
            } else {
                val infoData = PreferenceTools.getObj(context, IConstant.USERINFO, UserInfoData::class.java)
                if (infoData != null && infoData!!.getData() != null) {
                    if (infoData!!.data.uid == bean.uid.toString()) {
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
        tv_userName.text = bean.name
        tv_LoveGame.text = "(${bean.like_game})"
        if (!TextUtils.isEmpty(bean.version) && !TextUtils.isEmpty(bean.platform_id)) {
            tv_CreateTim.text = bean.version + " | " + bean.platform_id
            iv_Flag.visibility = GONE
        } else {
            tv_CreateTim.text = TimeUtils.getInstance().parseTime(bean.created_at)
            iv_Flag.visibility = VISIBLE
        }
        if (bean.ranking <= 3) {
            iv_Flag.setImageResource(if (bean.ranking == 1)
                R.mipmap.img_1st
            else if (bean.ranking == 2)
                R.mipmap.img_2nd
            else if (bean.ranking == 3)
                R.mipmap.img_3rd
            else
                0
            )
        } else {
            iv_Flag.visibility = GONE
        }
        Glide.with(context)
                .load(bean.img)
                .asBitmap()
                .error(R.mipmap.img_avatar_default)
                .into(iv_UserLogo)
        tv_GameDesc.setData(AppConfig.getImageHtml(bean.content))
        if (TextUtils.isEmpty(bean.good)) {
            linear_Good.visibility = GONE
        } else {
            linear_Good.visibility = VISIBLE
            tv_GoodWay.text = bean.good
        }
        if (TextUtils.isEmpty(bean.bad)) {
            linear_bad.visibility = GONE
        } else {
            linear_bad.visibility = VISIBLE
            tv_BadWay.text = bean.bad
        }
        if ("1" == bean.is_like) {//已经点赞 + 点赞id
            iv_Thumb.isSelected = true
            tv_Thumb.setTextColor(context.resources.getColor(R.color.color_shallow_yellow))
        } else {
            iv_Thumb.isSelected = false
            tv_Thumb.setTextColor(context.resources.getColor(R.color.color_text_color))
        }
        if (bean.like_num < 1) {
            tv_Thumb.text = "赞"
        } else {
            tv_Thumb.text = "" + bean.like_num
        }
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