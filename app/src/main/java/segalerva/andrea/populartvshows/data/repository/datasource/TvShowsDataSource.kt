package segalerva.andrea.populartvshows.data.repository.datasource

import retrofit2.Call
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse

/**
 * Created by andrea on 15/9/18.
 */
interface TvShowsDataSource {

    fun getPopularTvShows(page: Int): Call<PopularTvShowsResponse>
}