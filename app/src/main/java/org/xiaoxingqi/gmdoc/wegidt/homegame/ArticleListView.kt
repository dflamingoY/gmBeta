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
import kotlinx.android.synthetic.main.layout_long_comment.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.entity.other.SelectionBean
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.modul.dynamic.DynamicDetailsActivity
import org.xiaoxingqi.gmdoc.tools.AppConfig
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout
import org.xiaoxingqi.gmdoc.wegidt.imagespan.VerticalImageSpan

/**
 * 与长评公用一个布局文件
 */
class ArticleListView(context: Context) : BaseLayout(context) {
    private var bean: HomeUserShareData.ContributeBean? = null

    override fun getLayoutId(): Int {
        return R.layout.layout_long_comment
    }

    @SuppressLint("SetTextI18n")
    fun setData(bean: HomeUserShareData.ContributeBean) {
        this.bean = bean

        linearContent.visibility = VISIBLE
        if (bean.is_ori == 0) {//转发
            linear_Orignal.visibility = VISIBLE
            linearContent.setBackgroundColor(Color.parseColor("#f6f7f7"))
            tv_Orignal_User.text = "@" + bean.self.username
            if (!TextUtils.isEmpty(bean.title)) {
                tv_Title.setData(AppConfig.getImageHtml(bean.title))
            } else {
                tv_Title.text = "转发博文"
            }

            val ssb = SpannableStringBuilder("发表博文" + "   " + bean.self.title)
            ssb.setSpan(VerticalImageSpan(context, R.mipmap.img_doc_grey), 5, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            tv_GameInfo.text = ssb
            linearContent.setOnClickListener {
                //继续跳原创详情
                context.startActivity(Intent(context, DynamicDetailsActivity::class.java).putExtra("id", bean.self.id))
            }
            if (bean.self == null || TextUtils.isEmpty(bean.self.id)) {//原文被删除
                tvNullContent.visibility = VISIBLE
                linearContent.visibility = GONE
                return
            }
        } else {
            linear_Orignal.visibility = GONE
            linearContent.setBackgroundColor(Color.WHITE)
            val ssb = SpannableStringBuilder("发表博文" + "   " + bean.title)
            ssb.setSpan(VerticalImageSpan(context, R.mipmap.img_doc_blue), 5, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            ssb.setSpan(ForegroundColorSpan(Color.parseColor("#2d7dd2")), 7, ssb.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            val selectionBean = SelectionBean("", "", 5, ssb.length, 1)
            tv_Title.text = ssb
            val span = BackgroundColorSpan(Color.parseColor("#31000000"))
            val slop = ViewConfiguration.get(context).scaledTouchSlop
            tv_Title.setOnTouchListener(object : OnTouchListener {
                private var downX: Int = 0
                private var downY: Int = 0
                private var id: Int = 0

                @SuppressLint("ClickableViewAccessibility")
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
                            if (selectionBean != null && index >= selectionBean.start && index <= selectionBean.end) {
                                ssb.setSpan(span, selectionBean.start, selectionBean.end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                tv_Title.text = ssb
                                parent.requestDisallowInterceptTouchEvent(true)//不允许父view拦截
                                return true
                            }
                            return false
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val indexMove = event.findPointerIndex(id)
                            val currentX = event.getX(indexMove).toInt()
                            val currentY = event.getY(indexMove).toInt()
                            if (Math.abs(currentX - downX) < slop && Math.abs(currentY - downY) < slop) {
                                if (selectionBean == null) {
                                    parent.requestDisallowInterceptTouchEvent(false)//允许父view拦截
                                    return false
                                }
                            }

                            parent.requestDisallowInterceptTouchEvent(false)//允许父view拦截
                            val indexUp = event.findPointerIndex(id)
                            ssb.removeSpan(span)
                            tv_Title.text = ssb
                            val upX = event.getX(indexUp).toInt()
                            val upY = event.getY(indexUp).toInt()
                            if (Math.abs(upX - downX) < slop && Math.abs(upY - downY) < slop) {
                                //TODO startActivity or whatever
                                if (selectionBean != null) {//跳转详情
//                                    context.startActivity(Intent(context, ArticalDetailActivity::class.java).putExtra("id", bean.id))
                                } else {
                                    return false
                                }
                            } else {

                                return false
                            }
                        }

                        MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                            val indexUp = event.findPointerIndex(id)
                            ssb.removeSpan(span)
                            tv_Title.text = ssb
                            val upX = event.getX(indexUp).toInt()
                            val upY = event.getY(indexUp).toInt()
                            if (Math.abs(upX - downX) < slop && Math.abs(upY - downY) < slop) {
                                if (selectionBean != null) {
//                                    context.startActivity(Intent(context, ArticalDetailActivity::class.java).putExtra("id", bean.id))
                                } else {
                                    return false
                                }
                            } else {
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
                    .override(360, 180)
                    .centerCrop()
                    .into(iv_Details)
            tv_PhotoTitle.text = bean.self.title
        } else {
            appBarPhoto.visibility = GONE
        }
    }
}