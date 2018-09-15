package segalerva.andrea.populartvshows.data.repository

import retrofit2.Call
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse

/**
 * Created by andrea on 15/9/18.
 */
interface TvShowsRepository {

    fun getPopularTVShows(page:Int):Call<PopularTvShowsResponse>
}