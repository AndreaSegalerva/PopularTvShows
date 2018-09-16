package segalerva.andrea.populartvshows.data.repository

import io.reactivex.Observable
import segalerva.andrea.populartvshows.domain.model.TvShow

/**
 * Created by andrea on 15/9/18.
 */
interface TvShowsRepository {

    fun getPopularTVShows(page:Int):Observable<List<TvShow>>
}