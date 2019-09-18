package org.xiaoxingqi.gmdoc.rx

import android.util.Log
import rx.Subscriber

open class BaseSubscriber<T> : Subscriber<T>() {
    override fun onNext(t: T) {

    }

    override fun onCompleted() {

    }

    override fun onError(e: Throwable?) {
        e?.printStackTrace()
        Log.e("error", e?.message)
    }
}