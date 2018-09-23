package segalerva.andrea.populartvshows.presentation.view.presenter

import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.domain.interactor.callback.BaseDisposableObserver
import segalerva.andrea.populartvshows.domain.model.PopularTvShows
import segalerva.andrea.populartvshows.presentation.view.base.BaseView
import segalerva.andrea.populartvshows.presentation.view.features.populartvshows.PopularTvShowsListView
import segalerva.andrea.populartvshows.presentation.injector.PresentationDependencyInjector
import segalerva.andrea.populartvshows.presentation.model.TvShowView

/**
 * Created by andrea on 16/9/18.
 * Presenter of tv shows list view,containing all the business logic of the view
 * @param view
 * @param presentationDependencyInjector
 */

class PopularTvShowsListPresenter(private val view: PopularTvShowsListView, private val presentationDependencyInjector: PresentationDependencyInjector) : BasePresenter {

    override fun getView(): BaseView = view
    private var totalPages = 0
    private var currentPage = 0

    /**
     * Get popular tvShows data, only if device is connected to internet
     * @param showNormalLoader used to know if the normal progress bar should be visible
     * @param isRefreshing  used to know if the list is being refreshed and need to clean
     * the actual adapter and it's items
     */
    fun getPopularTvShowsData(showNormalLoader: Boolean, isRefreshing: Boolean) {

        if (isConnectedToInternet()) {

            if (showNormalLoader) {
                view.showLoading()
            }
            executeGetPopularTvShows(currentPage, isRefreshing)

        } else {

            disableLoaders()
            showInternetConnectionMessage()
        }
    }

    /**
     * When error message is shown and user tries again
     * Refresh all the information
     */
    fun onTryAgainClicked() {

        onRefresh()
    }

    /**
     * Refresh all the tv show list
     */
    fun onRefresh() {

        currentPage = 0
        getPopularTvShowsData(true, true)
    }

    /**
     * When scrolled and need to paginate
     * Load more tv shows only if there are missing pages to load
     */
    fun onLoadMore() {

        //If there are more pages to load, continue loading tv shows
        if (currentPage < totalPages) {

            getPopularTvShowsData(false, false)

        } else {

            view.disableLoadMore()
        }
    }

    /**
     * When TvShow is clicked from the list
     * Navigate to show detail only if device has connection
     */
    fun onTvShowClicked(tvShowView: TvShowView) {

        if (isConnectedToInternet()) {
            view.navigateToTvShowDetail(tvShowView)
        } else {
            view.showConnectionDialog()
        }
    }

    // ------------------------------------------------------------------------------------
    // Private methods
    // ------------------------------------------------------------------------------------

    /**
     * Execute the use case of getPopularTvShows
     * Show the list of popular tvShows
     */
    private fun executeGetPopularTvShows(page: Int, isRefreshing: Boolean) {

        currentPage = page + 1
        presentationDependencyInjector.getPopularTvShows().execute(object : BaseDisposableObserver<PopularTvShows>() {

            override fun onError(e: Throwable) {

                if (isSafeManipulateView()) {

                    view.hideLoading()
                    disableLoaders()
                    view.showErrorMessage(R.string.something_went_wrong)
                }
            }

            override fun onNext(response: PopularTvShows) {

                if (isSafeManipulateView()) {
                    view.hideLoading()
                    view.hideErrorMessage()
                    enableLoaders()
                    totalPages = response.totalPages

                    //If is pulling to refreshing we clear the adapter list items
                    if (isRefreshing) {
                        view.cleanListTvShows()
                    }

                    view.showTvShows(presentationDependencyInjector.getTvShowMapper().mapList(response.shows))
                }
            }
        }, currentPage)
    }


    /**
     * Show message when device not connected to internet
     */
    private fun showInternetConnectionMessage() {

        view.hideTvShowsList()
        view.showConnectionDialog()
        view.showErrorMessage(R.string.no_connection_try_again)
    }

    /**
     * Enable loaders view components( Swipe Refresh Layout & Load More)
     */
    fun enableLoaders() {

        view.enableSwipeRefreshLayout()
        view.enableLoadMore()
    }

    /**
     * Disable loaders view components (Swipe Refresh Layout & Load More)
     */
    private fun disableLoaders() {

        view.disableSwipeRefreshLayout()
        view.disableLoadMore()
    }
}