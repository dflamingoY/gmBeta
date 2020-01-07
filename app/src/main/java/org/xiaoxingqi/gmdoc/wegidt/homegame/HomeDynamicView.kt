package org.xiaoxingqi.gmdoc.wegidt.homegame

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.layout_dynamic.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.entity.BaseImgBean
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.modul.common.ShowPicActivity
import org.xiaoxingqi.gmdoc.modul.game.GameDetailsActivity
import org.xiaoxingqi.gmdoc.tools.AppConfig
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout
import org.xiaoxingqi.gmdoc.wegidt.ninegridView.GridImageView
import org.xiaoxingqi.gmdoc.wegidt.ninegridView.NineGridImageView
import org.xiaoxingqi.gmdoc.wegidt.ninegridView.NineGridImageViewAdapter

class HomeDynamicView : BaseLayout {
    private var nigenineGridView: NineGridImageView<BaseImgBean> = findViewById(R.id.nineGridView)
    private var bean: HomeUserShareData.ContributeBean? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        tv_Content_Title.setOnTextTouchListener {
            when (it.type) {
                1 -> {
                    context.startActivity(Intent(context, GameDetailsActivity::class.java).putExtra("gameId", bean?.game_id))
                }
                2 -> {

                }
            }
        }
        nigenineGridView.setItemImageClickListener { context, _, index, _ ->
            context.startActivity(Intent(context, ShowPicActivity::class.java)
                    .putExtra("imgs", bean?.img)
                    .putExtra("index", index)
            )
            (context as Activity).overridePendingTransition(R.anim.act_enter_scale, 0)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_dynamic
    }

    fun setData(bean: HomeUserShareData.ContributeBean) {
        this.bean = bean
        if (!TextUtils.isEmpty(bean.title)) {
            tv_Content_Title.setData(AppConfig.getImageHtml(bean.title))
        }
        if (bean.is_ori == 0) {

        } else {
            linear_Repeat.visibility = GONE
            linearContent.setBackgroundColor(Color.WHITE)
            if (bean.game_id != null) {
                val text = "为 " + bean.game.game_name + "(" + bean.game.game_pla + "|" + bean.game.game_ver + ") " + "贡献了游戏截图" + "#" + bean.game.game_name + "#"
                tv_Content_Title.setData(text, bean.game.game_name + "(" + bean.game.game_pla + "|" + bean.game.game_ver + ") ")
            }
        }
        if (bean.is_video == 1) {
            relativeVedio.visibility = VISIBLE
            nigenineGridView.visibility = GONE
            ImageLoader.getInstance().displayImage(bean.video_cover, iv_Details, AppTools.options)
        } else if (bean.is_photo == 1) {
            relativeVedio.visibility = GONE
            nigenineGridView.visibility = VISIBLE
            val adapter = object : NineGridImageViewAdapter<BaseImgBean>() {
                override fun onDisplayImage(context: Context?, imageView: View?, t: BaseImgBean?) {
                    (imageView as GridImageView).loadPic(t!!.url + "?imageMogr2/thumbnail/!240x240r/auto-orient", t.spoiler == "1")
                }
            }
            nigenineGridView.setAdapter(adapter)
            nigenineGridView.setImagesData(bean.img)
        }
    }
}