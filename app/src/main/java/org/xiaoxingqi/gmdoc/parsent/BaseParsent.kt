package org.xiaoxingqi.gmdoc.parsent

import android.content.Context
import org.xiaoxingqi.gmdoc.core.http.HttpServer
import org.xiaoxingqi.gmdoc.impl.ApiServer
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * 控制网络请求, 控制视图的切换展示
 */
abstract class BaseParsent<T, H> {
    lateinit var apiServer: ApiServer
    var t: T//与主界面交互
    var composite: CompositeSubscription = CompositeSubscription()

    constructor(context: Context, t: T) {
        apiServer = HttpServer.getInstance(context).apiServer
        this.t = t
    }

    fun onAttach() {

    }

    fun addObaser(observable: Observable<H>, subscriber: Subscriber<H>) {
        composite.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber))
    }

    fun onDetach() {
        composite.clear()
    }
}