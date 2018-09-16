package segalerva.andrea.populartvshows.data.repository

import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.mapper.TvShowEntityMapper
import segalerva.andrea.populartvshows.data.repository.datasource.RemoteTvShowsDataSource
import segalerva.andrea.populartvshows.domain.model.TvShow

/**
 * Created by andrea on 15/9/18.
 */
class TvShowsDataRepository(private val remoteTvShowsDataSource: RemoteTvShowsDataSource, private val movieEntityMapper: TvShowEntityMapper) : TvShowsRepository {

    override fun getPopularTVShows(page: Int): Observable<List<TvShow>> {

        return remoteTvShowsDataSource.getPopularTvShows(page).map {

            this.movieEntityMapper.mapList(it.results)
        }
    }
}