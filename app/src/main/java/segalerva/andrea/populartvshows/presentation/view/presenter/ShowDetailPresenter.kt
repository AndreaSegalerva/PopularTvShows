package segalerva.andrea.populartvshows.presentation.view.presenter

import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.domain.interactor.callback.BaseDisposableObserver
import segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows.GetSimilarTvShowsParams
import segalerva.andrea.populartvshows.domain.model.PopularTvShows
import segalerva.andrea.populartvshows.domain.model.TvShow
import segalerva.andrea.populartvshows.domain.model.TvShowDetail
import segalerva.andrea.populartvshows.presentation.injector.PresentationDependencyInjector
import segalerva.andrea.populartvshows.presentation.model.TvShowDetailView
import segalerva.andrea.populartvshows.presentation.model.TvShowView
import segalerva.andrea.populartvshows.presentation.view.base.BaseView
import segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail.ShowDetailView

/**
 * Created by andrea on 20/9/18.
 */
class ShowDetailPresenter(private val view: ShowDetailView, private val presentationDependencyInjector: PresentationDependencyInjector) : BasePresenter {

    override fun getView(): BaseView? = view

    private var currentSimilarTvShowsPage = 0
    private var totalSimilarTvShowsPages = 0
    private var showId = 0

    /**
     * Get tv show by Id information using the use case
     */
    fun getTvShowByIdData(showId: Int) {

        view.showLoading()
        this.showId = showId
        currentSimilarTvShowsPage = 1

        executeGetTvShowDetail(showId)
    }


    /**
     * Only load more similar tv shows if the device is connected to the internet
     * and there are more pages to load
     */
    fun onLoadMore() {

        if (isConnectedToInternet()) {

            if (currentSimilarTvShowsPage < totalSimilarTvShowsPages) {

                executeGetSimilarTvShows(currentSimilarTvShowsPage)
            } else {
                view.disableLoadMoreSimilarTvShows()
            }
        } else {

            showInternetConnectionMessage()
        }
    }

    /**
     * When similar tv show clicked
     * Navigate to tv show detail if device is connected to the internet
     * If not connected, show connection dialog
     */
    fun onTvShowClicked(tvShowView: TvShowView) {

        if (isConnectedToInternet()) {

            view.navigateToTvShowDetail(tvShowView.id, tvShowView.name)
        } else {
            view.showConnectionDialog()
        }
    }

    // ------------------------------------------------------------------------------------
    // Private methods
    // ------------------------------------------------------------------------------------

    /**
     * Execute use case getTvShowDetail
     */
    private fun executeGetTvShowDetail(showId: Int) {

        presentationDependencyInjector.getTvShowDetail().execute(object : BaseDisposableObserver<TvShowDetail>() {

            override fun onError(e: Throwable) {

                if (isSafeManipulateView()) {
                    view.hideLoading()
                    view.showToastMessage(R.string.something_went_wrong)
                }
            }

            override fun onNext(response: TvShowDetail) {

                val tvShowDetailView = presentationDependencyInjector.getTVShowDetailMapper().map(response)

                if (isSafeManipulateView()) {

                    view.hideLoading()
                    showPrincipalTvShowInformation(tvShowDetailView)

                    //If similar tv shows is not empty or null prepare to show the views
                    if (tvShowDetailView.similarShows != null && tvShowDetailView.similarShows!!.shows.isNotEmpty()) {

                        totalSimilarTvShowsPages = tvShowDetailView.similarShows!!.totalPages
                        prepareSimilarTvShowsViews(tvShowDetailView.similarShows!!.shows)

                    } else {
                        // inform the user that the tv show has no similar ones
                        view.showToastMessage(R.string.no_similar_tv_shows)
                    }
                }
            }

        }, GetSimilarTvShowsParams(showId, currentSimilarTvShowsPage))
    }

    /**
     * Show the principal tv show detail information
     */
    private fun showPrincipalTvShowInformation(tvShowDetailView: TvShowDetailView) {

        // Poster
        if (tvShowDetailView.posterPath.orEmpty().isNotEmpty()) {

            view.showPosterPicture(tvShowDetailView.posterPath!!)

        } else if (tvShowDetailView.backdropPath.orEmpty().isNotEmpty()) {

            view.showPosterPicture(tvShowDetailView.backdropPath!!)
        }

        // Vote average
        view.showVoteAverage(tvShowDetailView.voteAverage.toString())

        // Overview
        if (tvShowDetailView.overView != null) {
            view.showOverView(tvShowDetailView.overView)
        }

        // Air date
        if (tvShowDetailView.airDate != null) {
            view.showAirDate(tvShowDetailView.airDate)
        }

        // Number of episodes
        if (tvShowDetailView.numberEpisodes != null) {
            view.showNumberEpisodes(tvShowDetailView.numberEpisodes)
        }

        // Number of seasons
        if (tvShowDetailView.numberSeasons != null) {
            view.showNumberSeasons(tvShowDetailView.numberSeasons)
        }
    }


    /**
     * Execute use case getSimilarTvShows
     * Returns the list of similar tv shows by showId
     * @param page current page used to know which page to load
     */
    private fun executeGetSimilarTvShows(page: Int) {

        currentSimilarTvShowsPage = page + 1

        presentationDependencyInjector.getSimilarTvShows().execute(object : BaseDisposableObserver<PopularTvShows>() {

            override fun onError(e: Throwable) {

                if (isSafeManipulateView()) {
                    view.hideLoading()
                    view.hideLoadMore()
                    view.showToastMessage(R.string.something_went_wrong)
                }
            }

            override fun onNext(response: PopularTvShows) {

                if (isSafeManipulateView()) {

                    if (response.shows.isNotEmpty()) {

                        prepareSimilarTvShowsViews(response.shows)
                    } else {
                        view.disableLoadMoreSimilarTvShows()
                    }
                }
            }
        }, GetSimilarTvShowsParams(showId, currentSimilarTvShowsPage))
    }

    /**
     * Prepare similar tv shows views to populate in the view
     */
    private fun prepareSimilarTvShowsViews(similarTvShows: List<TvShow>) {

        val similarTvShowsViews = presentationDependencyInjector.getTvShowMapper().mapList(similarTvShows)
        view.hideErrorMessage()
        view.showSimilarTvShows(similarTvShowsViews)
    }

    /**
     * Show Internet connection message when device not connected
     */
    private fun showInternetConnectionMessage() {

        view.hideSimilarTvShows()
        view.showConnectionDialog()
        view.showErrorMessage(R.string.need_connection_to_continue)
    }
}

