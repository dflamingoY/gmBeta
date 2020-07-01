package org.xiaoxingqi.gmdoc.modul.home

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.frag_type_circle.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.RecommendData
import org.xiaoxingqi.gmdoc.impl.home.RecommendCallback
import org.xiaoxingqi.gmdoc.presenter.home.RecommendPresenter
import org.xiaoxingqi.gmdoc.wegidt.RoundScoreView

class RecommendArtFragment : BaseFrag<RecommendPresenter>() {
    private var page = 0
    private var type = 1
    private lateinit var adapter: QuickAdapter<RecommendData.RecommendBean>
    private val mData by lazy { ArrayList<RecommendData.RecommendBean>() }

    override fun createPresent(): RecommendPresenter {
        return RecommendPresenter(context, object : RecommendCallback {
            override fun recommendList(data: RecommendData) {
                data.data.data?.let {
                    mData.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_type_circle
    }

    override fun initView(view: View?) {
    }

    override fun initData() {
        arguments?.let {
            type = it.getInt("type")
        }
        adapter = object : QuickAdapter<RecommendData.RecommendBean>(activity, R.layout.item_recommend_article, mData) {
            @SuppressLint("SetTextI18n")
            override fun convert(helper: BaseAdapterHelper?, item: RecommendData.RecommendBean?) {

                if (type == 2) { //长评
                    helper!!.getView(R.id.scoreView).visibility = View.VISIBLE
                    helper.getView(R.id.tv_Game_Name).visibility = View.VISIBLE
                    helper.getTextView(R.id.tv_Game_Name).text = item!!.version.toString() + " | " + item.game_name
                    (helper.getView(R.id.scoreView) as RoundScoreView).setScore(item.score.toFloat())
                } else if (type == 3) {
                    helper!!.getView(R.id.scoreView).visibility = View.GONE
                    helper.getView(R.id.tv_Game_Name).visibility = View.GONE
                }
                Glide.with(activity)
                        .load(item!!.cover)
                        .apply(RequestOptions().error(R.drawable.img_empty_avatar_back)
                                .centerCrop())
                        .into(helper!!.getImageView(R.id.iv_Cover))
                helper.getTextView(R.id.tv_Title).text = item.title
            }
        }
        mView!!.recyclerView.layoutManager = LinearLayoutManager(activity)
        mView!!.recyclerView.adapter = adapter
        presenter?.getData(type, page)
    }

    override fun bindEvent() {
    }

    override fun request(flag: Int) {
    }
}