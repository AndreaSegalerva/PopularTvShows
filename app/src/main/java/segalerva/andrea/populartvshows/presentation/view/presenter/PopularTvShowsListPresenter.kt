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
    private var tvShows = ArrayList<TvShowView>()

    fun getPopularTvShowsData(isLoadingMore: Boolean) {

        if (isConnectedToInternet()) {

            if (!isLoadingMore) {
                view.showLoading()
            }
            executeGetPopularTvShows(currentPage)

        } else {

            showInternetConnectionMessage()
        }
    }

    fun onTryAgainClicked() {

        getPopularTvShowsData(false)
    }

    fun onTvShowClicked(tvShowView: TvShowView) {

        view.navigateToTvShowDetail(tvShowView)
    }

    // ------------------------------------------------------------------------------------
    // Private methods
    // ------------------------------------------------------------------------------------

    private fun executeGetPopularTvShows(page: Int) {

        currentPage = page + 1
        presentationDependencyInjector.getPopularTvShows().execute(object : BaseDisposableObserver<PopularTvShows>() {

            override fun onError(e: Throwable) {

                if (isSafeManipulateView()) {

                    view.hideLoading()
                    view.showErrorMessage(R.string.something_went_wrong)
                }
            }

            override fun onNext(response: PopularTvShows) {

                if (isSafeManipulateView()) {
                    view.hideLoading()
                    view.hideErrorMessage()
                    totalPages = response.totalPages
                    view.populateTvShows(presentationDependencyInjector.getTvShowMapper().mapList(response.shows))
                }
            }
        }, currentPage)
    }

    /**
     * When scrolled and need to paginate
     * Load more tv shows until all the pages are loaded
     */
    fun onLoadMore() {

        if (currentPage < totalPages) {

            getPopularTvShowsData(true)

        } else {

            view.disableLoadMore()
        }
    }

    private fun showInternetConnectionMessage() {

        view.hideTvShowsList()
        view.showConnectionDialog()
        view.showErrorMessage(R.string.no_connection_try_again)
    }
}