package org.xiaoxingqi.gmdoc.wegidt

import android.content.Context
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_home_game_layout.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.entity.BaseHomeBean

class ItemHomeView(context: Context) : BaseLayout(context) {

    init {
        setBackgroundResource(R.drawable.game_click_drawable)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_home_game_layout
    }

    fun setData(bean: BaseHomeBean?) {
        bean?.let {
            var url = ""
            if (bean.cover.contains("?")) {
                url = bean.cover + "&imageMogr2/auto-orient/thumbnail/!200x200r"
            } else {
                url = bean.cover + "?imageMogr2/auto-orient/thumbnail/!200x200r"
            }
            Glide.with(context)
                    .load(url)
                    .into(iv_Game_Logo)
            tv_Game_Name.text = bean.game_name
            relative_Score.setScore(bean.score)
            tv_Desc.text = bean.introduce
            tv_Time.text = bean.sale_time
            tv_Version.text = bean.version
            if (!TextUtils.isEmpty(bean.extra))
                tv_Extre.text = bean.extra
        }
    }

    fun setName(name: String) {
        try {
            if ("ios".equals(name, ignoreCase = true)) {
                linear_Time.visibility = View.GONE
            } else {
                linear_Time.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}