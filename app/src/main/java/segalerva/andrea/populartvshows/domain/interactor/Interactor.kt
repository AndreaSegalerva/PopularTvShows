package segalerva.andrea.populartvshows.domain.interactor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by andrea on 16/9/18.
 * Interactor to execute every needed use case
 */
abstract class Interactor<T, in Params> {

    private val disposables = CompositeDisposable()

    /**
     * Build an Observable to be used when the current interactor is executed
     */
    abstract fun buildInteractorObservable(params: Params): Observable<T>


    /**
     * Executes the current interactor which will be used in every use case
     * @param observer will be the one listening to the observable buildInteractorObservable
     * @param params parameters used to execute and build this interactor
     */
    fun execute(observer: DisposableObserver<T>, params: Params) {

        val observable = this.buildInteractorObservable(params).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())

        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Add the current disposable of the execution
     */
    private fun addDisposable(disposable: Disposable) {

        disposables.add(disposable)
    }

    fun dispose() {

        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}