package segalerva.andrea.populartvshows.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse

/**
 * Created by andrea on 15/9/18.
 */
interface ApiService {

    @GET("tv/popular?api_key=5496223e71ac936eda331298ec9bc5aa")
    fun getPopularTvShows(@Query("page") page: Int): Call<PopularTvShowsResponse>
}