package segalerva.andrea.populartvshows.data.repository

import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.mapper.PopularTvShowsMapper
import segalerva.andrea.populartvshows.data.mapper.TvShowEntityMapper
import segalerva.andrea.populartvshows.data.repository.datasource.TvShowsDataSource
import segalerva.andrea.populartvshows.domain.model.PopularTvShows
import segalerva.andrea.populartvshows.domain.model.TvShow

/**
 * Created by andrea on 15/9/18.
 */
class TvShowsDataRepositoryImpl(private val remoteTvShowsDataSource: TvShowsDataSource, private val popularTvShowsMapper: PopularTvShowsMapper, private val tvShowEntityMapper: TvShowEntityMapper) : TvShowsRepository {

    override fun getPopularTVShows(page: Int): Observable<PopularTvShows> {

        return remoteTvShowsDataSource.getPopularTvShows(page).map {

            this.popularTvShowsMapper.map(it)
        }
    }

    override fun getShowById(showId: Int): Observable<TvShow> {

        return remoteTvShowsDataSource.getTvShowById(showId).map {

            this.tvShowEntityMapper.map(it)
        }
    }

    override fun getSimilarTvShows(showId: Int, page: Int): Observable<PopularTvShows> {

        return remoteTvShowsDataSource.getSimilarTvShows(showId, page).map {

            this.popularTvShowsMapper.map(it)
        }
    }
}