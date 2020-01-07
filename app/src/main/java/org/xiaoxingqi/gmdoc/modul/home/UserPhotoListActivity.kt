package org.xiaoxingqi.gmdoc.modul.home

import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import com.alibaba.fastjson.JSON
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_photo_list.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.BaseDecorationAdapter
import org.xiaoxingqi.gmdoc.entity.BaseImgBean
import org.xiaoxingqi.gmdoc.entity.user.UserContentPhotoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter
import org.xiaoxingqi.gmdoc.tools.SPUtils

/**
 * 用户的相册
 */
class UserPhotoListActivity : BaseActivity<UserPresenter>() {
    private lateinit var userId: String
    private lateinit var adapter: BaseDecorationAdapter<BaseImgBean, BaseAdapterHelper>
    private val mData by lazy { ArrayList<BaseImgBean>() }
    private var current = 0
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun userPhoto(data: UserContentPhotoData) {
                if (null != data.data.data) {
                    for (bean in data.data.data) {
                        mData.add(BaseImgBean(true, bean.date.toString()))
                        adapter.notifyItemChanged(adapter.itemCount - 1)
                        for (s in bean.list) {
                            for (imgBean in parseData(s)!!) {
                                mData.add(imgBean)
//                                tempData.add(imgBean)
                                adapter.notifyItemChanged(adapter.itemCount - 1)
                            }
                        }
                    }
                }
                transLayout.showContent()
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_photo_list)
    }

    override fun initView() {
        val mManager = GridLayoutManager(this, 4)
        mManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.isHorzontal(position)) mManager.spanCount else 1
            }
        }
        recyclerView.layoutManager = mManager
    }

    override fun initData() {
        userId = intent.getStringExtra("userId")
        adapter = object : BaseDecorationAdapter<BaseImgBean, BaseAdapterHelper>(this, R.layout.item_title_text, R.layout.item_spoiler_img, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseImgBean?) {
                if (item!!.isSelected) {
                    helper!!.getTextView(R.id.tv_Title).text = item.horizontalTitle
                } else {
                    if (item.url.endsWith("gif")) {
                        helper!!.getView(R.id.iv_Gif).visibility = VISIBLE
                    } else {
                        helper!!.getView(R.id.iv_Gif).visibility = GONE
                    }
                    if ("0" == item.spoiler) {//不包含剧透
                        helper.getView(R.id.viewSpoiler).visibility = GONE
                    } else {
                        if (SPUtils.getBoolean(this@UserPhotoListActivity, IConstant.IS_SPOLIER, false)) {
                            helper.getView(R.id.viewSpoiler).visibility = VISIBLE
                        } else {
                            helper.getView(R.id.viewSpoiler).visibility = GONE
                        }
                    }
                    Glide.with(this@UserPhotoListActivity)
                            .asBitmap()
                            .load(item.url + "?imageMogr2/thumbnail/!240x240r/auto-orient")
                            .override(190, 190)
                            .placeholder(R.drawable.img_empty_avatar_back)
                            .centerCrop()
                            .error(R.drawable.img_empty_avatar_back)
                            .into(helper.getImageView(R.id.iv_img))
                }
            }
        }
        recyclerView.adapter = adapter
        adapter.setLoadMoreEnable(recyclerView, recyclerView.layoutManager, LayoutInflater.from(this).inflate(R.layout.layout_loadmore, recyclerView, false))
        persent?.getUserPhoto(userId, current)
    }

    override fun initEvent() {
        viewBack.setOnClickListener { finish() }
        adapter.setOnLoadListener {
            current++
            persent?.getUserPhoto(userId, current)
        }
    }

    private fun parseData(resp: String): List<BaseImgBean>? {
        return try {
            JSON.parseArray<BaseImgBean>(resp, BaseImgBean::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}