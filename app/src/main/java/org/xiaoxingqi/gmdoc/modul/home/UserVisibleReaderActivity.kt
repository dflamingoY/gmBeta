package org.xiaoxingqi.gmdoc.modul.home

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_circle_recommend.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseAct
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.tools.PreferenceTools
import org.xiaoxingqi.gmdoc.tools.SPUtils

class UserVisibleReaderActivity : BaseAct() {

    override fun setContent() {
        setContent(R.layout.activity_circle_recommend)
    }

    override fun initView() {

    }

    override fun initData() {
        val infoData = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
        toggle_Button.isChecked = SPUtils.getBoolean(this, infoData.data.uid + IConstant.ISVISIBLEREADER, false)
    }

    override fun initEvent() {
        viewBack.setOnClickListener { finish() }

        toggle_Button.setOnCheckedChangeListener { view, isChecked ->
            val infoData = PreferenceTools.getObj(this, IConstant.USERINFO, UserInfoData::class.java)
            SPUtils.setBoolean(this, infoData.data.uid + IConstant.ISVISIBLEREADER, isChecked)
            if (isChecked) {
                Snackbar.make(linearBottomView, "已开启，已经隐藏自己的读者数量了", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(linearBottomView, "已关闭，现在您可以看到自己的读者数量了", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun request() {
    }
}