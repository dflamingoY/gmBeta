package org.xiaoxingqi.gmdoc.presenter.home

import android.content.Context
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.QINiuRespData
import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData
import org.xiaoxingqi.gmdoc.entity.game.GameListData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.entity.user.FollowData
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

    /**查询用户的钱包信息*/
    fun getWallet(type: Int, page: Int) {
        addObserve(apiServer.baseGet("wallet${IConstant.GET_END}&type=$type&page=$page"), object : Subscriber<String>() {
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
     * */
    fun getUserPhoto(userId: String, page: Int) {

        addObserve(apiServer.baseGet("photo/$userId${IConstant.GET_END}&page=$page"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.userPhoto(JSON.parseObject(t, UserContentPhotoData::class.java))
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    /**
     * 喜欢的游戏单*/
    fun loveGame(userId: String) {
        addObserve(apiServer.baseGet("like_game/$userId${IConstant.GET_END}"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.loveGameList(JSON.parseObject(t, LoveGameData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    /**
     *增删改游戏单*/
    fun changeGame(map: Map<String, String>) {
        addObserve(apiServer.basePost("updateinfo/${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.changeGame(JSON.parseObject(t, BaseRespData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    /**
     * 获取7牛的token*/
    fun getQiNiuToken() {
        addObserve(apiServer.baseGet("niu_token"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.qiniuToken(JSON.parseObject(t, QINiuRespData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    fun otherGameList(type: String, uid: String, page: Int) {
        addObserve(apiServer.baseGet("$type/$uid/${IConstant.GET_END}&page=$page"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.otherGame(JSON.parseObject(t, GameListData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    /**
     * 获取用户的点评列表
     */
    fun shortCommentList(userId: String, type: Int, key: Int, page: Int, order: String) {
        addObserve(apiServer.baseGet("dynamic/$userId${IConstant.GET_END}&type=$type&key=$key&page=$page&order=$order"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.userWordList(JSON.parseObject(t, HomeUserShareData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    /**
     * 更新用户信息
     */
    fun updateUserInfo(map: Map<String, String>) {
        addObserve(apiServer.basePost("updateinfo${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.updateInfo(JSON.parseObject(t, BaseRespData::class.java))
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    /**
     * 更新用户头像
     */
    fun updateAvatar(map: Map<String, String>) {

        addObserve(apiServer.basePost("contribution${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    /**
     * 获取用户的贡献图
     */
    fun getUserContribute(gameId: String) {
        addObserve(apiServer.baseGet("propaganda/$gameId${IConstant.GET_END}&page=2"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.userContribute(JSON.parseObject(t, GameDetailsData::class.java))
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    /**
     * 添加贡献图
     */
    fun addContribute(map: Map<String, String>) {
        addObserve(apiServer.basePost("contribution${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.addContributeSuccess(JSON.parseObject(t, BaseRespData::class.java))
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    /**
     * 发布
     */
    fun pushDynamic(map: Map<String, String>) {
        addObserve(apiServer.basePost("send/${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.pushSuccess(JSON.parseObject(t, BaseRespData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    fun getLoveList(uid: String, page: Int) {
        addObserve(apiServer.baseGet("follow/$uid${IConstant.GET_END}&page=$page"), object : Subscriber<String>() {
            override fun onNext(p0: String?) {
                callback?.loveList(JSONObject.parseObject(p0, FollowData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(p0: Throwable?) {

            }
        })
    }


}
