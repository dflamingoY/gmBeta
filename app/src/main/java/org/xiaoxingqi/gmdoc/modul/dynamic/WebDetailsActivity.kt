package org.xiaoxingqi.gmdoc.modul.dynamic

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_recycler_details.*
import kotlinx.android.synthetic.main.head_web_details.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.BowenDetailsData
import org.xiaoxingqi.gmdoc.entity.CommentData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.global.WebDetailsCallBack
import org.xiaoxingqi.gmdoc.presenter.global.WebPresenter
import org.xiaoxingqi.gmdoc.tools.AppConfig
import org.xiaoxingqi.gmdoc.tools.TimeUtils
import org.xiaoxingqi.gmdoc.wegidt.textView.SpoilerTextView

/**
 * 文章详情
 */
class WebDetailsActivity : BaseActivity<WebPresenter>() {
    private lateinit var adapter: QuickAdapter<CommentData.CommentDataBean>
    private lateinit var headView: View
    private val mData by lazy { ArrayList<CommentData.CommentDataBean>() }
    private lateinit var id: String


    override fun createPresent(): WebPresenter {
        return WebPresenter(this, object : WebDetailsCallBack() {
            override fun setData(data: CommentData) {
                try {
                    headView.tvCommentCount.text = "评论 ${data.data.total}"
                    mData.addAll(data.data.data)
                    adapter.notifyDataSetChanged()
                } catch (e: Exception) {
                }
            }

            @SuppressLint("SetTextI18n")
            override fun webDetails(data: BowenDetailsData) {
                data.data?.let {
                    if (it.isNotEmpty()) {//show details
                        it[0].let { bean ->
                            headView.squareBg.visibility = if (TextUtils.isEmpty(bean.cover)) View.GONE else View.VISIBLE
                            Glide.with(this@WebDetailsActivity)
                                    .load(bean.cover)
                                    .apply(RequestOptions().error(R.drawable.img_empty_avatar_back)
                                            .centerCrop())
                                    .into(headView.squareBg)
                            Glide.with(this@WebDetailsActivity)
                                    .asBitmap()
                                    .load(bean.self.avatar)
                                    .apply(RequestOptions().error(R.mipmap.img_avatar_default)
                                            .centerCrop())
                                    .into(headView.ivUserLogo)
                            headView.tvUserName.text = bean.self.username
                            headView.tvLoveGame.text = "（${bean.self.like_game}）"
                            headView.tvCreateTime.text = TimeUtils.getInstance().parseTime(bean.created_at)
                            headView.tvFollow.text = when (bean.is_sub) {
                                1 -> "已关注"
                                2 -> "互相关注"
                                else -> "关注"
                            }
                            headView.tvFollow.isSelected = bean.is_sub == 1 || bean.is_sub == 2
                            headView.tvFollow.setTextColor(if (bean.is_sub == 1 || bean.is_sub == 2) Color.WHITE else ContextCompat.getColor(this@WebDetailsActivity, R.color.color_shallow_yellow))
                            headView.tvContentTitle.text = bean.self.title
                            headView.tvForward.text = "转发 ${bean.forward_num}"
                            headView.tvThumbHint.text = "赞 ${bean.like_num}"
                            headView.webContent.loadUrl("${IConstant.TEST_ART_DETAIL_SPORT}article_detail/$id${IConstant.GET_END}&key=${data.key}")
                        }
                    }
                }
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_recycler_details)
    }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        headView = LayoutInflater.from(this).inflate(R.layout.head_web_details, recyclerView, false)
        initActionBar(toolbar)
    }

    override fun initData() {
        id = intent.getStringExtra("id")!!
        adapter = object : QuickAdapter<CommentData.CommentDataBean>(this, R.layout.layout_item_comment_list_details, mData, headView) {
            override fun convert(helper: BaseAdapterHelper?, item: CommentData.CommentDataBean?) {
                Glide.with(this@WebDetailsActivity)
                        .asBitmap()
                        .load(item!!.avatar)
                        .into(helper!!.getImageView(R.id.iv_Logo))
                helper.getTextView(R.id.tv_FromUser).text = item.username
                helper.getTextView(R.id.tv_LoveGame).text = item.like_game
                helper.getTextView(R.id.tv_CreateTime).text = TimeUtils.getInstance().parseTime(item.creat_time)
                if (!TextUtils.isEmpty(item.from_name)) {
                    helper.getTextView(R.id.tv_toUserName).text = item.from_name
                    helper.getView(R.id.iv_IsTalk).visibility = View.VISIBLE
                    helper.getView(R.id.findTalk).visibility = View.VISIBLE
                    helper.getView(R.id.tv_toUserName).visibility = View.VISIBLE
                } else {
                    helper.getView(R.id.findTalk).visibility = View.GONE
                    helper.getView(R.id.iv_IsTalk).visibility = View.GONE
                    helper.getView(R.id.tv_toUserName).visibility = View.GONE
                }
                helper.getView(R.id.tv_Whisper).visibility = if (item.type === 1) View.GONE else View.VISIBLE
                helper.getImageView(R.id.iv_Thumb).isSelected = item.like_status === 1
                helper.getTextView(R.id.tv_Thumb).setTextColor(if (item.like_status === 1) ContextCompat.getColor(this@WebDetailsActivity, R.color.color_shallow_yellow) else ContextCompat.getColor(this@WebDetailsActivity, R.color.color_text_color))
                helper.getTextView(R.id.tv_Thumb).text = if (item.like_num > 0) "" + item.like_num else "赞"
                (helper.getTextView(R.id.tv_Content) as SpoilerTextView).setData(AppConfig.getImageHtml(item.content))

            }
        }
        recyclerView.adapter = adapter
        persent?.getDetails(id)
        persent?.getComment(id, 0)
    }

    override fun initEvent() {

    }
}