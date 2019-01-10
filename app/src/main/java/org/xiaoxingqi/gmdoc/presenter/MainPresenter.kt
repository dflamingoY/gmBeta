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
        addObaser(apiServer.get_UserInfo("user_info/${IConstant.GET_END}"), object : Subscriber<UserInfoData>() {
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
        addObaser(apiServer.base_post("logout/${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.loginOut(JSON.parseObject(t, BaseRespData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    fun post_token() {
        addObaser(apiServer.post_token("post_token${IConstant.GET_END}"), object : Subscriber<TokenData>() {
            override fun onNext(t: TokenData?) {
                callback?.token(t)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

}