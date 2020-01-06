package org.xiaoxingqi.gmdoc.impl.global

import org.xiaoxingqi.gmdoc.entity.BowenDetailsData
import org.xiaoxingqi.gmdoc.entity.CommentData

abstract class WebDetailsCallBack {

    abstract fun setData(data: CommentData)

    abstract fun webDetails(data: BowenDetailsData)
}