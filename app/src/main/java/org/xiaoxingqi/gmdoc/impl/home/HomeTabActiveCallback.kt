package org.xiaoxingqi.gmdoc.impl.home

import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData

interface HomeTabActiveCallback {
    fun list(data: HomeUserShareData)
}