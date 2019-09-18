package org.xiaoxingqi.gmdoc.presenter.msg

import android.content.Context
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.msg.MsgCommentData
import org.xiaoxingqi.gmdoc.entity.msg.MsgDetailsData
import org.xiaoxingqi.gmdoc.entity.msg.MsgInfoListData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.msg.MessageCallback
import org.xiaoxingqi.gmdoc.presenter.BasePresenter
import rx.Subscriber

class MessagePresenter : BasePresenter {
    private var callback: MessageCallback? = null

    constructor(context: Context, callback: MessageCallback) : super(context) {
        this.callback = callback
    }

    fun getMsgList() {
        addObserve(apiServer.baseGet("client_list${IConstant.GET_END}"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.msgList(JSON.parseObject(t, MsgInfoListData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }


    fun getChatList(userId: String, page: Int) {
        addObserve(apiServer.baseGet("get_msg${IConstant.GET_END}&uid=$userId&page=$page"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.chatList(JSON.parseObject(t, MsgDetailsData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    /**
     * 发送消息
     */
    fun sendMsg(map: Map<String, String>) {
        addObserve(apiServer.basePost("send_msg/${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.sendSuccess(JSON.parseObject(t, MsgCommentData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
                callback?.onError(e)
            }
        })
    }

}