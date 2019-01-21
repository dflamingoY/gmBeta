package org.xiaoxingqi.gmdoc.presenter.home

import android.content.Context
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.entity.user.LoveGameData
import org.xiaoxingqi.gmdoc.entity.user.UserContentPhotoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

class UserPresenter : BasePresenter {
    private var callback: UserCallback? = null

    constructor(context: Context, callback: UserCallback) : super(context) {
        this.callback = callback
    }

    /**
     * 查询用户的钱包信息
     */
    fun getWallet(type: Int, page: Int) {
        addObaser(apiServer.base_get("wallet${IConstant.GET_END}&type=$type&page=$page"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.walletData(JSON.parseObject(t, HomeUserShareData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    /**
     * 获取用户的相册
     */
    fun getUserPhoto(userId: String, page: Int) {

        addObaser(apiServer.base_get("photo/$userId${IConstant.GET_END}&page=$page"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.userPhoto(JSON.parseObject(t, UserContentPhotoData::class.java))
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    fun loveGame(userId: String) {
        addObaser(apiServer.base_get("like_game/$userId${IConstant.GET_END}"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.loveGameList(JSON.parseObject(t, LoveGameData::class.java))
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

}