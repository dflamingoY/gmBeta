package org.xiaoxingqi.gmdoc.impl.home

import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.entity.user.LoveGameData
import org.xiaoxingqi.gmdoc.entity.user.UserContentPhotoData

open class UserCallback {
    /**
     * 查询钱包的信息
     */
    open fun walletData(data: HomeUserShareData) {}

    open fun userPhoto(data: UserContentPhotoData) {}

    open fun loveGameList(data: LoveGameData) {}
}