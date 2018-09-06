package org.xiaoxingqi.gmdoc.core.http

import android.content.Context
import android.text.TextUtils
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.Response
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.tools.PreferenceTools

/**
 * 添加head中cookie
 * */
class RequestHeadInterceptot(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val proceed = chain.request().newBuilder()
        if (!TextUtils.isEmpty(PreferenceTools.getObj(context, IConstant.COOKIE, String::class.java))) {
            proceed.addHeader("Cookie", PreferenceTools.getObj(context, IConstant.COOKIE, String::class.java))
        }
//        Observable.just(PreferenceTools.getObj(context, IConstant.COOKIE, String::class.java)).subscribe()
        return chain.proceed(proceed.build())
    }
}