package org.xiaoxingqi.gmdoc.impl.msg

import org.xiaoxingqi.gmdoc.entity.msg.MsgCommentData
import org.xiaoxingqi.gmdoc.entity.msg.MsgDetailsData
import org.xiaoxingqi.gmdoc.entity.msg.MsgInfoListData
import org.xiaoxingqi.gmdoc.impl.BaseInterceptor

open class MessageCallback : BaseInterceptor {
    override fun onError(obj: Any?) {

    }

    open fun msgList(data: MsgInfoListData) {}
    open fun chatList(data: MsgDetailsData) {}
    /**
     * 消息发送成功
     */
    open fun sendSuccess(data: MsgCommentData) {}
}