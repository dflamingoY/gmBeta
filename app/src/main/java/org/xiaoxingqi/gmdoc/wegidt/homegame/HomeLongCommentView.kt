package org.xiaoxingqi.gmdoc.wegidt.homegame

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_long_comment.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.entity.other.SelectionBean
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.modul.game.GameDetailsActivity
import org.xiaoxingqi.gmdoc.tools.AppConfig
import org.xiaoxingqi.gmdoc.tools.PreferenceTools
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout
import org.xiaoxingqi.gmdoc.wegidt.imagespan.VerticalImageSpan
import java.util.ArrayList

class HomeLongCommentView(context: Context) : BaseLayout(context) {
    private var bean: HomeUserShareData.ContributeBean? = null
    private var selects: MutableList<SelectionBean>? = null
    override fun getLayoutId(): Int {
        return R.layout.layout_long_comment
    }

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
            if (!TextUtils.isEmpty(bean.title)) {
                tv_Title.setData(AppConfig.getImageHtml(bean.title))
            } else {
                tv_Title.text = "转发长评"
            }
            val builder = SpannableStringBuilder("发表长评   " + bean.game.game_pla + bean.game.game_ver + " | " + bean.game.game_name)
            builder.setSpan(VerticalImageSpan(context, R.mipmap.btn_game_grey), 5, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            tv_GameInfo.text = builder
            val spannableBuilder = SpannableStringBuilder("   " + bean.self.title)
            spannableBuilder.setSpan(VerticalImageSpan(context, R.mipmap.img_doc_grey), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            tv_GameInfo.append(spannableBuilder)
        } else {
            if (null == selects)
                selects = ArrayList()
            linear_Orignal.visibility = GONE
            linearContent.setBackgroundColor(Color.WHITE)
            val builder = SpannableStringBuilder("发表长评   " + bean.game.game_name + " | " + bean.game.game_pla + " | " + bean.game.game_ver)
            builder.setSpan(VerticalImageSpan(context, R.mipmap.btn_game_blue), 5, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            val colorSpan = ForegroundColorSpan(Color.parseColor("#2d7dd2"))
            builder.setSpan(colorSpan, 7, 8 + bean.game.game_name.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            selects?.add(SelectionBean("", "", 5, 8 + bean.game.game_name.length, 1))
            val length = builder.length
            builder.append(" . " + bean.self.title)
            builder.setSpan(VerticalImageSpan(context, R.mipmap.img_doc_blue), length + 1, length + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            builder.setSpan(ForegroundColorSpan(Color.parseColor("#2d7dd2")), length + 3, builder.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            selects?.add(SelectionBean("", "", length, builder.length, 5))
            tv_Title.text = builder

            val span = BackgroundColorSpan(Color.parseColor("#31000000"))
            val slop = ViewConfiguration.get(context).scaledTouchSlop
            tv_Title.setOnTouchListener(object : OnTouchListener {
                private var downX: Int = 0
                private var downY: Int = 0
                private var id: Int = 0
                private var downSection: SelectionBean? = null

                override fun onTouch(v: View, event: MotionEvent): Boolean {
                    val action = event.actionMasked
                    val layout = tv_Title.layout ?: return false
                    val line: Int
                    val index: Int

                    when (action) {
                        MotionEvent.ACTION_DOWN//TODO 最后一行点击问题 网址链接
                        -> {
                            val actionIndex = event.actionIndex
                            id = event.getPointerId(actionIndex)
                            downX = event.getX(actionIndex).toInt()
                            downY = event.getY(actionIndex).toInt()
                            line = layout.getLineForVertical(scrollY + event.y.toInt())
                            index = layout.getOffsetForHorizontal(line, event.x.toInt().toFloat())
                            val lastRight = layout.getLineRight(line).toInt()
                            if (lastRight < event.x) {  //文字最后为话题时，如果点击在最后一行话题之后，也会造成话题被选中效果
                                return false
                            }
                            for (section in selects!!) {
                                if (index >= section.start && index <= section.end) {
                                    builder.setSpan(span, section.start, section.end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                    downSection = section
                                    tv_Title.text = builder
                                    parent.requestDisallowInterceptTouchEvent(true)//不允许父view拦截
                                    return true
                                }
                            }
                            return false
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val indexMove = event.findPointerIndex(id)
                            val currentX = event.getX(indexMove).toInt()
                            val currentY = event.getY(indexMove).toInt()
                            if (Math.abs(currentX - downX) < slop && Math.abs(currentY - downY) < slop) {
                                if (downSection == null) {
                                    parent.requestDisallowInterceptTouchEvent(false)//允许父view拦截
                                    return false
                                }
                            }
                            downSection = null
                            parent.requestDisallowInterceptTouchEvent(false)//允许父view拦截
                            val indexUp = event.findPointerIndex(id)
                            builder.removeSpan(span)
                            tv_Title.text = builder
                            val upX = event.getX(indexUp).toInt()
                            val upY = event.getY(indexUp).toInt()
                            if (Math.abs(upX - downX) < slop && Math.abs(upY - downY) < slop) {
                                //TODO startActivity or whatever
                                if (downSection != null) {
                                    if (downSection!!.type == 5) {
//                                        context.startActivity(Intent(context, LongCommentDetailsActivity::class.java).putExtra("id", bean.id))
                                    } else if (downSection!!.type == 1) {
                                        context.startActivity(Intent(context, GameDetailsActivity::class.java).putExtra("gameId", bean.game_id))
                                    }
                                    downSection = null
                                } else {
                                    return false
                                }
                            } else {
                                downSection = null
                                return false
                            }
                        }

                        MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                            val indexUp = event.findPointerIndex(id)
                            builder.removeSpan(span)
                            tv_Title.text = builder
                            val upX = event.getX(indexUp).toInt()
                            val upY = event.getY(indexUp).toInt()
                            if (Math.abs(upX - downX) < slop && Math.abs(upY - downY) < slop) {
                                if (downSection != null) {
                                    if (downSection!!.type == 5) {
//                                        context.startActivity(Intent(context, LongCommentDetailsActivity::class.java).putExtra("id", bean.id))
                                    } else if (downSection!!.type == 1) {
                                        context.startActivity(Intent(context, GameDetailsActivity::class.java).putExtra("gameId", bean.game_id))
                                    }
                                    downSection = null
                                } else {
                                    return false
                                }
                            } else {
                                downSection = null
                                return false
                            }
                        }
                    }
                    return true
                }
            })
        }
        if (!TextUtils.isEmpty(bean.cover)) {//有图片
            appBarPhoto.visibility = VISIBLE
            val url = if (bean.cover.startsWith("http")) {
                bean.cover
            } else {
                IConstant.PICSPORT + bean.cover
            }
            Glide.with(context)
                    .load(url)
                    .apply(RequestOptions() .override(360, 180)
                            .centerCrop()
                            .error(R.mipmap.img_avatar_default))
                    .into(iv_Details)

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

            tv_PhotoTitle.text = bean.self.title
        } else {
            appBarPhoto.visibility = GONE
        }
    }
}