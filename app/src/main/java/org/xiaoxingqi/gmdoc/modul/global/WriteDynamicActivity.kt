package org.xiaoxingqi.gmdoc.modul.global

import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.impl.global.WriteDynamicCallback
import org.xiaoxingqi.gmdoc.parsent.global.WriteDynamicPersenter

/**
 * 创建内容 视频 图片 文字
 */
class WriteDynamicActivity : BaseActivity<WriteDynamicPersenter>() {
    override fun createPresent(): WriteDynamicPersenter {
        return WriteDynamicPersenter(this, WriteDynamicCallback())
    }

    override fun setContent() {
        setContent(R.layout.activity_write_dynamic)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {

    }

    override fun request() {

    }
}