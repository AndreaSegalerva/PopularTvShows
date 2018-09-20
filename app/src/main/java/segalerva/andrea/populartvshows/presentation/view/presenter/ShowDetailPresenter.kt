package segalerva.andrea.populartvshows.presentation.view.presenter

import segalerva.andrea.populartvshows.domain.interactor.callback.BaseDisposableObserver
import segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows.GetSimilarTvShowsParams
import segalerva.andrea.populartvshows.domain.model.TvShowDetail
import segalerva.andrea.populartvshows.presentation.injector.PresentationDependencyInjector
import segalerva.andrea.populartvshows.presentation.model.TvShowDetailView
import segalerva.andrea.populartvshows.presentation.view.base.BaseView
import segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail.ShowDetailView

/**
 * Created by andrea on 20/9/18.
 */
class ShowDetailPresenter(private val view: ShowDetailView, private val presentationDependencyInjector: PresentationDependencyInjector) : BasePresenter {

    override fun getView(): BaseView? = view

    private lateinit var tvShowDetailView: TvShowDetailView

    fun getTvShowByIdData(showId: Int) {

        view.showLoading()

        presentationDependencyInjector.getTvshowDetail().execute(object : BaseDisposableObserver<TvShowDetail>() {

            override fun onError(e: Throwable) {
                super.onError(e)

                if (isSafeManipulateView()) {
                    view.hideLoading()
                }
            }

            override fun onNext(response: TvShowDetail) {
                super.onNext(response)

                tvShowDetailView = presentationDependencyInjector.getTVShowDetailMapper().map(response)

                if (isSafeManipulateView()) {

                    view.hideLoading()
                    showPrincipalTvShowInformation()
                }
            }

        }, GetSimilarTvShowsParams(showId, 1))
    }

    // ------------------------------------------------------------------------------------
    // Private methods
    // ------------------------------------------------------------------------------------

    private fun showPrincipalTvShowInformation() {

        if (tvShowDetailView.posterPath.orEmpty().isNotEmpty()) {

            view.showPosterPicture(tvShowDetailView.posterPath!!)
        }

        view.showVoteAverage(tvShowDetailView.voteAverage.toString())
        view.showOverView(tvShowDetailView.overView)
        view.showAirDate(tvShowDetailView.airDate)

        if (tvShowDetailView.numberEpisodes != null) {
            view.showNumberEpisodes(tvShowDetailView.numberEpisodes!!)
        }
        if (tvShowDetailView.numberSeasons != null) {
            view.showNumberSeasons(tvShowDetailView.numberSeasons!!)
        }

    }
}
