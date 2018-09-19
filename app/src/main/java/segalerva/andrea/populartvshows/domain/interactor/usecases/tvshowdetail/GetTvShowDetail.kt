package segalerva.andrea.populartvshows.domain.interactor.usecases.tvshowdetail

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.domain.injector.DomainDependencyInjector
import segalerva.andrea.populartvshows.domain.interactor.Interactor
import segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows.GetSimilarTvShows
import segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows.GetSimilarTvShowsParams
import segalerva.andrea.populartvshows.domain.model.PopularTvShows
import segalerva.andrea.populartvshows.domain.model.TvShowDetail

/**
 * Created by andrea on 19/9/18.
 * Use case to show all the show information in the detail screen
 */
class GetTvShowDetail(private val dataDependencyInjector: DataDependencyInjector, private val domainDependencyInjector: DomainDependencyInjector) : Interactor<TvShowDetail, GetSimilarTvShowsParams>() {


    override fun buildInteractorObservable(params: GetSimilarTvShowsParams): Observable<TvShowDetail> {

        return getTvShowById(params.showId).flatMap { showDetail -> getSimilarTvShows(showDetail, params) }
    }

    private fun getTvShowById(showId: Int): Observable<TvShowDetail> {

        return dataDependencyInjector.getTvShowsDataRepository().getShowById(showId).map {

            this.domainDependencyInjector.getTvShowDetailMapper().map(it)
        }
    }

    private fun getSimilarTvShows(showDetail: TvShowDetail, params: GetSimilarTvShowsParams): Observable<TvShowDetail> {

        val getSimilarTvShows = GetSimilarTvShows(dataDependencyInjector)

        return Observable.create { subscriber ->

            kotlin.run {

                getSimilarTvShows.execute(object : DisposableObserver<PopularTvShows>() {

                    override fun onNext(t: PopularTvShows) {

                        if (t.shows.isNotEmpty()) {
                            showDetail.addSimilarShows(t.shows)
                        }

                        subscriber.onNext(showDetail)
                    }

                    override fun onError(e: Throwable) {

                        subscriber.onNext(showDetail)
                    }

                    override fun onComplete() {

                        //Do nothing for the moment
                    }
                }, params)

            }
        }
    }
}