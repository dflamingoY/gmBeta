package org.xiaoxingqi.gmdoc.parsent.home

import android.content.Context
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.TypeFragCallback
import org.xiaoxingqi.gmdoc.parsent.BasePresent
import rx.Subscriber

class TypeFragPersenter : BasePresent {
    private var callback: TypeFragCallback? = null

    constructor(context: Context, callback: TypeFragCallback) : super(context) {
        this.callback = callback
    }

    fun queryData(flag: Int, uid: String, type: String) {

        addObaser(apiServer.base_get("${IConstant.SPORT}user/$uid${IConstant.GET_END}&page=$flag&choose_type=$type"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.callTypeData(JSON.parseObject(t, HomeUserShareData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })

    }
}