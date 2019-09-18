package org.xiaoxingqi.gmdoc.presenter

import android.content.Context
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.BaseRespData
import org.xiaoxingqi.gmdoc.entity.TokenData
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.MainCallBack
import rx.Subscriber

class MainPresenter : BasePresenter {
    private var callback: MainCallBack? = null

    constructor(context: Context, callback: MainCallBack) : super(context) {
        this.callback = callback
    }


    fun queryInfo() {
        addObserve(apiServer.getUserInfo("user_info/${IConstant.GET_END}"), object : Subscriber<UserInfoData>() {
            override fun onNext(t: UserInfoData?) {
                callback?.userInfo(t)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
                if (e?.cause is IllegalStateException) {
                    callback?.onError(e?.cause)
                }
            }
        })
    }


    fun loginOut(map: Map<String, String>) {
        addObserve(apiServer.basePost("logout/${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.loginOut(JSON.parseObject(t, BaseRespData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    fun postToken() {
        addObserve(apiServer.postToken("post_token${IConstant.GET_END}"), object : Subscriber<TokenData>() {
            override fun onNext(t: TokenData?) {
                callback?.token(t)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
                callback?.onError(e?.cause)
            }
        })
    }

}