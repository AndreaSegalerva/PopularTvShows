package segalerva.andrea.populartvshows.domain.interactor.usecases

import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.mapper.TvShowEntityMapper
import segalerva.andrea.populartvshows.data.network.RestClient
import segalerva.andrea.populartvshows.data.repository.TvShowsDataRepository
import segalerva.andrea.populartvshows.data.repository.datasource.RemoteTvShowsDataSource
import segalerva.andrea.populartvshows.domain.interactor.Interactor
import segalerva.andrea.populartvshows.domain.model.TvShow

/**
 * Created by andrea on 16/9/18.
 * Use case to obtain the list of the most popular shows.
 * Connects with the repository TvShowDataRepository defined in the data layer and
 * returns the list of movies obtained from the remote data source.
 */
class GetPopularTvShows : Interactor<List<TvShow>, Int>() {

    private val restClient = RestClient()
    private val remoteTvShowsDataSource = RemoteTvShowsDataSource(restClient)
    private val tvShowEntityMapper = TvShowEntityMapper()
    private val tvShowDataRepository = TvShowsDataRepository(remoteTvShowsDataSource, tvShowEntityMapper)

    override fun buildInteractorObservable(params: Int): Observable<List<TvShow>> {

        return tvShowDataRepository.getPopularTVShows(params)
    }
}