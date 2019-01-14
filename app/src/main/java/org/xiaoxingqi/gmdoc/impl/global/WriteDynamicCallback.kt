package org.xiaoxingqi.gmdoc.impl.global

import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.QINiuRespData

interface WriteDynamicCallback {

    fun pushSuccess(data: BaseRespData)

    fun qiniuToken(data: QINiuRespData)
}