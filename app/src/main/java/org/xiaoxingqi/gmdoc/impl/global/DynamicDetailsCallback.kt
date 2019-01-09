package org.xiaoxingqi.gmdoc.impl.global

import org.xiaoxingqi.gmdoc.entity.DynamicDetailsData

interface DynamicDetailsCallback {

    fun dynamicInfo(data: DynamicDetailsData)

}