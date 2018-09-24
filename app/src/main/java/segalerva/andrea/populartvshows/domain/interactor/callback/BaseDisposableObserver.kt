package segalerva.andrea.populartvshows.domain.interactor.callback

import android.util.Log
import io.reactivex.observers.DisposableObserver

/**
 * Created by andrea on 19/9/18.
 * Base Disposable Observer implementation.
 * Define here all the default error, complete or on next control
 *@param T
 */
open class BaseDisposableObserver<T> : DisposableObserver<T>() {

    override fun onComplete() {
        Log.d(javaClass.name, "OnComplete")
    }

    override fun onError(e: Throwable) {
        Log.d(javaClass.name, "OnError")
    }

    override fun onNext(response: T) {
        Log.d(javaClass.name, "OnNext")
    }
}