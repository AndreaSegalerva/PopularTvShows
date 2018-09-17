package segalerva.andrea.populartvshows.data.repository

import io.reactivex.Observable
import segalerva.andrea.populartvshows.domain.model.PopularTvShows

/**
 * Created by andrea on 15/9/18.
 */
interface TvShowsRepository {

    fun getPopularTVShows(page:Int):Observable<PopularTvShows>
}