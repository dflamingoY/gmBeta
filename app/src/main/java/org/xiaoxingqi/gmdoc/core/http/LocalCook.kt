package org.xiaoxingqi.gmdoc.core.http

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class LocalCook : CookieJar {

    private val map: HashMap<String, List<Cookie>> = HashMap()
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return map[url.host] ?: ArrayList()
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        map[url.host] = cookies
    }
}