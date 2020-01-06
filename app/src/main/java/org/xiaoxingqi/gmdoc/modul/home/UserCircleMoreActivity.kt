package org.xiaoxingqi.gmdoc.modul.home

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_circle_recommend.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseAct
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.tools.PreferenceTools
import org.xiaoxingqi.gmdoc.tools.SPUtils

/**
 * 设置是否显示关注圈顶部显示
 */
class UserCircleMoreActivity : BaseAct() {

    override fun setContent() {
        setContent(R.layout.activity_circle_recommend)
    }

    override fun initView() {
        tv_Title.text = "关注圈顶部区域不显示"
        tv_Hint.text = "开启后将隐藏关注圈顶部的关注更多人和最近活跃的玩家模块"
        tv_visible_Title.text = "关注圈顶部区域不显示"
    }

    override fun initData() {
        val infoData = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
        toggle_Button.isChecked = SPUtils.getBoolean(this, infoData.data.uid + IConstant.ISCIRCLERECOMMEND, false)
    }

    override fun initEvent() {
        viewBack.setOnClickListener { finish() }
        toggle_Button.setOnCheckedChangeListener { view, isChecked ->
            val infoData = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
            SPUtils.setBoolean(this, infoData.data.uid + IConstant.ISCIRCLERECOMMEND, isChecked)
            if (isChecked) {
                Snackbar.make(linearBottomView, "已经隐藏关注圈活跃用户", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(linearBottomView, "已经显示关注圈活跃用户", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun request() {

    }
}