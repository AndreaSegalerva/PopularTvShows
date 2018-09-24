package segalerva.andrea.populartvshows.domain.interactor.usecases.populartvshows

import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.data.repository.TvShowsDataRepositoryImpl
import segalerva.andrea.populartvshows.domain.interactor.Interactor
import segalerva.andrea.populartvshows.domain.model.PopularTvShows

/**
 * Created by andrea on 16/9/18.
 * Use case to obtain the list of the most popular shows.
 * Connects with the repository [TvShowsDataRepositoryImpl] defined in the data layer and
 * returns the PopularTvShows model obtained from the remote data source.
 */
class GetPopularTvShows(private val dataDependencyInjector: DataDependencyInjector) : Interactor<PopularTvShows, Int>() {

    override fun buildInteractorObservable(params: Int): Observable<PopularTvShows> {

        return dataDependencyInjector.getTvShowsRepository().getPopularTVShows(params)
    }
}