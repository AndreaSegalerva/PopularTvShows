package segalerva.andrea.populartvshows.data.repository

import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.mapper.PopularTvShowsMapper
import segalerva.andrea.populartvshows.data.repository.datasource.RemoteTvShowsDataSource
import segalerva.andrea.populartvshows.domain.model.PopularTvShows

/**
 * Created by andrea on 15/9/18.
 */
class TvShowsDataRepository(private val remoteTvShowsDataSource: RemoteTvShowsDataSource, private val popularTvShowsMapper: PopularTvShowsMapper):TvShowsRepository {

    override fun getPopularTVShows(page: Int): Observable<PopularTvShows> {

        return remoteTvShowsDataSource.getPopularTvShows(page).map {

            this.popularTvShowsMapper.map(it)
        }
    }
}