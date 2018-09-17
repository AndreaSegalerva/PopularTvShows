package segalerva.andrea.populartvshows.presentation.view.base

import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by andrea on 16/9/18.
 * Interface with common view methods implemented by [BaseFragment]
 */
interface BaseView {

    fun context(): Context?
    fun showLoading()
    fun hideLoading()
    fun isSafeManipulateView(): Boolean
    fun isConnectedToInternet(): Boolean
    fun showConnectionDialog()
    fun showErrorMessage(@StringRes message: Int)
    fun hideErrorMessage()
}