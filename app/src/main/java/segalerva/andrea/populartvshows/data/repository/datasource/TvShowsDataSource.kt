package segalerva.andrea.populartvshows.data.repository.datasource

import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.model.entity.TvShowEntity
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse

/**
 * Created by andrea on 15/9/18.
 */
interface TvShowsDataSource {

    fun getPopularTvShows(page: Int): Observable<PopularTvShowsResponse>
    fun getTvShowById(showId: Int):Observable<TvShowEntity>
    fun getSimilarTvShows(showId: Int,page: Int):Observable<PopularTvShowsResponse>
}