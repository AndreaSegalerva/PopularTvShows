package segalerva.andrea.populartvshows.presentation.view.presenter

import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.domain.interactor.callback.BaseDisposableObserver
import segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows.GetSimilarTvShowsParams
import segalerva.andrea.populartvshows.domain.model.PopularTvShows
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

    private lateinit var tvShowDetailView: TvShowDetailView
    private var currentSimilarTvShowsPage = 0
    private var totalSimilarTvShowsPages = 0

    fun getTvShowByIdData(showId: Int) {

        view.showLoading()

        currentSimilarTvShowsPage = 1

        presentationDependencyInjector.getTvShowDetail().execute(object : BaseDisposableObserver<TvShowDetail>() {

            override fun onError(e: Throwable) {
                super.onError(e)

                if (isSafeManipulateView()) {
                    view.hideLoading()
                    view.showToastMessage(R.string.something_went_wrong)
                }
            }

            override fun onNext(response: TvShowDetail) {
                super.onNext(response)

                tvShowDetailView = presentationDependencyInjector.getTVShowDetailMapper().map(response)

                if (isSafeManipulateView()) {

                    view.hideLoading()
                    showPrincipalTvShowInformation()
                    showSimilarTvShows()
                }
            }

        }, GetSimilarTvShowsParams(showId, currentSimilarTvShowsPage))
    }

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

    private fun showPrincipalTvShowInformation() {

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
            view.showOverView(tvShowDetailView.overView!!)
        }

        // Air date
        if (tvShowDetailView.airDate != null) {
            view.showAirDate(tvShowDetailView.airDate!!)
        }

        // Number of episodes
        if (tvShowDetailView.numberEpisodes != null) {
            view.showNumberEpisodes(tvShowDetailView.numberEpisodes!!)
        }

        // Number of seasons
        if (tvShowDetailView.numberSeasons != null) {
            view.showNumberSeasons(tvShowDetailView.numberSeasons!!)
        }
    }

    private fun showSimilarTvShows() {

        if (tvShowDetailView.similarShows != null) {

            if (tvShowDetailView.similarShows!!.shows.isNotEmpty()) {

                totalSimilarTvShowsPages = tvShowDetailView.similarShows!!.totalPages
                val similarTvShows = tvShowDetailView.similarShows!!.shows
                val similarTvShowsViews = presentationDependencyInjector.getTvShowMapper().mapList(similarTvShows)
                view.hideErrorMessage()
                view.showSimilarTvShows(similarTvShowsViews)

            } else {

                view.showToastMessage(R.string.no_similar_tv_shows)
            }

        } else {

            view.showToastMessage(R.string.no_similar_tv_shows)
        }
    }

    private fun executeGetSimilarTvShows(page: Int) {

        currentSimilarTvShowsPage = page + 1

        presentationDependencyInjector.getSimilarTvShows().execute(object : BaseDisposableObserver<PopularTvShows>() {

            override fun onError(e: Throwable) {
                super.onError(e)

                if (isSafeManipulateView()) {
                    view.hideLoading()
                    view.hideLoadMore()
                    view.showToastMessage(R.string.something_went_wrong)
                }
            }

            override fun onNext(response: PopularTvShows) {
                super.onNext(response)

                if (isSafeManipulateView()) {

                    if (response.shows.isNotEmpty()) {

                        val similarTvShowsViews = presentationDependencyInjector.getTvShowMapper().mapList(response.shows)
                        view.hideErrorMessage()
                        view.showSimilarTvShows(similarTvShowsViews)
                    } else {
                        view.disableLoadMoreSimilarTvShows()
                    }
                }
            }
        }, GetSimilarTvShowsParams(tvShowDetailView.id, currentSimilarTvShowsPage))
    }

    private fun showInternetConnectionMessage() {

        view.hideSimilarTvShows()
        view.showConnectionDialog()
        view.showErrorMessage(R.string.need_connection_to_continue)
    }
}

