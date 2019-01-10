package org.xiaoxingqi.gmdoc.parsent.home

import android.content.Context
import android.view.View
import com.alibaba.fastjson.JSON
import org.xiaoxingqi.gmdoc.entity.RespFansData
import org.xiaoxingqi.gmdoc.entity.ThumbData
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

    /**
     * 个人中心的列表
     */
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

    /**
     * 关注圈的列表
     */
    fun getData(flag: Int, type: String, groupId: String) {
        addObaser(apiServer.base_get("web${IConstant.GET_END}&choose_type=$type&group=$groupId&page=$flag"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.callTypeData(JSON.parseObject(t, HomeUserShareData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    fun getHotUser() {
        addObaser(apiServer.base_get("active_user${IConstant.GET_END}"), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.callHotUser(JSON.parseObject(t, RespFansData::class.java))
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }

    fun thumb(map: Map<String, String>, view: View) {
        addObaser(apiServer.base_post("like${IConstant.GET_END}", map), object : Subscriber<String>() {
            override fun onNext(t: String?) {
                callback?.thumbCallback(JSON.parseObject(t, ThumbData::class.java), view)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {

            }
        })
    }
}
