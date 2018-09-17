package segalerva.andrea.populartvshows.domain.interactor.usecases

import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.mapper.PopularTvShowsMapper
import segalerva.andrea.populartvshows.data.mapper.TvShowEntityMapper
import segalerva.andrea.populartvshows.data.network.RestClient
import segalerva.andrea.populartvshows.data.repository.TvShowsDataRepository
import segalerva.andrea.populartvshows.data.repository.datasource.RemoteTvShowsDataSource
import segalerva.andrea.populartvshows.domain.interactor.Interactor
import segalerva.andrea.populartvshows.domain.model.PopularTvShows

/**
 * Created by andrea on 16/9/18.
 * Use case to obtain the list of the most popular shows.
 * Connects with the repository TvShowDataRepository defined in the data layer and
 * returns the PopularTvShows model obtained from the remote data source.
 */
class GetPopularTvShows : Interactor<PopularTvShows, Int>() {

    private val restClient = RestClient()
    private val remoteTvShowsDataSource = RemoteTvShowsDataSource(restClient)
    private val tvShowEntityMapper = TvShowEntityMapper()
    private val popularTvShowsMapper = PopularTvShowsMapper(tvShowEntityMapper)
    private val tvShowDataRepository = TvShowsDataRepository(remoteTvShowsDataSource, popularTvShowsMapper)

    override fun buildInteractorObservable(params: Int): Observable<PopularTvShows> {

        return tvShowDataRepository.getPopularTVShows(params)
    }
}