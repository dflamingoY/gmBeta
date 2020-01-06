package org.xiaoxingqi.gmdoc.impl.home

import org.xiaoxingqi.gmdoc.entity.RecommendData

interface RecommendCallback {
    fun recommendList(data: RecommendData)
}