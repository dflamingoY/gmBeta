package org.xiaoxingqi.gmdoc.presenter.global

import android.content.Context
import android.util.Log
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.BowenDetailsData
import org.xiaoxingqi.gmdoc.entity.CommentData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.global.WebDetailsCallBack
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

class WebPresenter : BasePresenter {

    private var callBack: WebDetailsCallBack? = null

    constructor(context: Context, callback: WebDetailsCallBack) : super(context) {
        this.callBack = callback
    }

    fun getDetails(id: String) {
        addObserve(apiServer.baseGet("detail/$id/${IConstant.GET_END}"), object : Subscriber<String>() {
            override fun onNext(t: String) {
                callBack?.webDetails(JSON.parseObject(t, BowenDetailsData::class.java))
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    fun getComment(id: String, page: Int) {
        addObserve(apiServer.baseGet("comlist/$id${IConstant.GET_END}&page=$page"), object : Subscriber<String>() {
            override fun onNext(t: String) {
                Log.d("Mozator", "$t")
                callBack?.setData(JSON.parseObject(t, CommentData::class.java))
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

}