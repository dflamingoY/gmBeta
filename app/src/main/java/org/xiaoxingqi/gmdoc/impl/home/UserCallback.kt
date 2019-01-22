package org.xiaoxingqi.gmdoc.impl.home

import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.QINiuRespData
import org.xiaoxingqi.gmdoc.entity.game.GameListData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.entity.user.LoveGameData
import org.xiaoxingqi.gmdoc.entity.user.UserContentPhotoData

open class UserCallback {
    /**
     * 查询钱包的信息
     */
    open fun walletData(data: HomeUserShareData) {}

    /**
     * 用户的相册
     */
    open fun userPhoto(data: UserContentPhotoData) {}

    /**
     * 喜欢的游戏列表
     */
    open fun loveGameList(data: LoveGameData) {}

    /**
     * 获取七牛
     */
    open fun qiniuToken(data: QINiuRespData) {}

    /**
     * 编辑游戏单
     */
    open fun changeGame(data: BaseRespData) {}

    /**
     * 想玩 在玩 待评分
     */
    open fun otherGame(data: GameListData) {}
}