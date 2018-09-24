package segalerva.andrea.populartvshows.domain.interactor.usecases.tvshowdetail

import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.domain.injector.DomainDependencyInjector
import segalerva.andrea.populartvshows.domain.interactor.Interactor
import segalerva.andrea.populartvshows.domain.interactor.callback.BaseDisposableObserver
import segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows.GetSimilarTvShows
import segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows.GetSimilarTvShowsParams
import segalerva.andrea.populartvshows.domain.model.PopularTvShows
import segalerva.andrea.populartvshows.domain.model.TvShowDetail

/**
 * Created by andrea on 19/9/18.
 * Use case to show all the tv show information in the detail screen ( tv show detail information & similar tv shows)
 */
class GetTvShowDetail(private val dataDependencyInjector: DataDependencyInjector, private val domainDependencyInjector: DomainDependencyInjector, private val getSimilarTvShows: GetSimilarTvShows) : Interactor<TvShowDetail, GetSimilarTvShowsParams>() {


    override fun buildInteractorObservable(params: GetSimilarTvShowsParams): Observable<TvShowDetail> {

        return getTvShowById(params.showId).flatMap { showDetail -> getSimilarTvShows(showDetail, params) }
    }

    /**
     * Call getShowById from repository with the detail of the tvShow
     * @param showId
     */
    private fun getTvShowById(showId: Int): Observable<TvShowDetail> {

        return dataDependencyInjector.getTvShowsRepository().getShowById(showId).map {

            this.domainDependencyInjector.getTvShowDetailMapper().map(it)
        }
    }

    /**
     * Execute use case getSimilarTvShows to response with a TvShowDetail model and it's similar tvShows
     * @param showDetail model in which adding similar tv shows
     * @param params used for the use case execution
     *
     * Motive: Executing the use case, allows us to have already TvShowDetail model
     * with all the needed information used in the presentation layer to show all the needed information
     * in the show detail screen (show detail information + similar tv shows)
     */
    private fun getSimilarTvShows(showDetail: TvShowDetail, params: GetSimilarTvShowsParams): Observable<TvShowDetail> {

        return Observable.create { subscriber ->

            kotlin.run {

                getSimilarTvShows.execute(object : BaseDisposableObserver<PopularTvShows>() {

                    override fun onNext(response: PopularTvShows) {

                        //Only add similar tv shows if there are any
                        if (response.shows.isNotEmpty()) {

                            showDetail.similarTvShows = response
                        }

                        subscriber.onNext(showDetail)
                    }

                    override fun onError(e: Throwable) {

                        // If something went wrong getting similar tv shows from repository, no similar tv shows added
                        subscriber.onNext(showDetail)
                    }

                }, params)

            }
        }
    }
}