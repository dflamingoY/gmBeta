package org.xiaoxingqi.gmdoc.core.http

import android.content.Context
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.Response
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.tools.PreferenceTools

/**
 * 解析响应头中cookie 本地保存
 */
class ReciveIntercept(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val proceed = chain.proceed(chain.request())
        val cookie = proceed.header("Set-Cookie")
        cookie?.let {
            PreferenceTools.saveObj(context, IConstant.COOKIE, cookie)
        }
        return proceed
    }
}