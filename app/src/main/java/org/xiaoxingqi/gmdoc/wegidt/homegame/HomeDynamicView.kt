package org.xiaoxingqi.gmdoc.wegidt.homegame

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.util.AttributeSet
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_dynamic.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.BaseImgBean
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.tools.SPUtils
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout

class HomeDynamicView : BaseLayout {

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet){
        dynamic_recycler.isNestedScrollingEnabled=false
        dynamic_recycler.isNestedScrollingEnabled=false

    }

    override fun getLayoutId(): Int {
        return R.layout.layout_dynamic
    }

    fun setData(bean: HomeUserShareData.ContributeBean) {
        /*val adapter = object : QuickAdapter<BaseImgBean>(context, R.layout.item_spoiler_img, bean.img) {
            override fun convert(helper: BaseAdapterHelper?, item: BaseImgBean?) {
                if (item!!.url.endsWith("gif")) {
                    helper!!.getView(R.id.iv_Gif).visibility = VISIBLE
                } else {
                    helper!!.getView(R.id.iv_Gif).visibility = GONE
                }
                if ("0" == item.spoiler) {//不包含剧透
                    helper.getView(R.id.viewSpoiler).visibility = GONE
                } else {
                    if (SPUtils.getBoolean(context, IConstant.IS_SPOLIER, false)) {
                        helper.getView(R.id.viewSpoiler).visibility = VISIBLE
                    } else {
                        helper.getView(R.id.viewSpoiler).visibility = GONE
                    }
                }
                Glide.with(context)
                        .load(item.url + "?imageMogr2/thumbnail/!240x240r/auto-orient")
                        .asBitmap()
                        .error(R.mipmap.btn_create_post)
                        .override(80, 80)
                        .placeholder(R.mipmap.btn_create_post)
                        .into(helper.getImageView(R.id.iv_img))
//                ImageLoader.getInstance().displayImage(item.url + "?imageMogr2/thumbnail/!240x240r/auto-orient", helper.getImageView(R.id.iv_img))
            }
        }
        dynamic_recycler.layoutManager = GridLayoutManager(context, 3)
        dynamic_recycler.adapter = adapter*/

    }

}