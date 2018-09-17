package segalerva.andrea.populartvshows.domain.interactor.usecases

import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.data.repository.TvShowsDataRepository
import segalerva.andrea.populartvshows.domain.interactor.Interactor
import segalerva.andrea.populartvshows.domain.model.PopularTvShows

/**
 * Created by andrea on 16/9/18.
 * Use case to obtain the list of the most popular shows.
 * Connects with the repository [TvShowsDataRepository] defined in the data layer and
 * returns the PopularTvShows model obtained from the remote data source.
 */
class GetPopularTvShows(dataDependencyInjector: DataDependencyInjector) : Interactor<PopularTvShows, Int>() {

    private val tvShowDataRepository = TvShowsDataRepository(dataDependencyInjector.getRemoteTvShowsDataSource(), dataDependencyInjector.getPopularTvShowsMapper())

    override fun buildInteractorObservable(params: Int): Observable<PopularTvShows> {

        return tvShowDataRepository.getPopularTVShows(params)
    }
}