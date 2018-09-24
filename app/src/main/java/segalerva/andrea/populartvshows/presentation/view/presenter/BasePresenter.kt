package segalerva.andrea.populartvshows.presentation.view.presenter

import segalerva.andrea.populartvshows.presentation.view.base.BaseView

/**
 * Created by andrea on 16/9/18.
 */
interface BasePresenter {

    fun getView(): BaseView?
    fun isSafeManipulateView(): Boolean {

        return getView() != null && getView()?.isSafeManipulateView()!!
    }

    fun isConnectedToInternet(): Boolean {
        return getView() != null && getView()!!.isConnectedToInternet()
    }
}