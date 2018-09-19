package segalerva.andrea.populartvshows.data.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import segalerva.andrea.populartvshows.data.model.entity.TvShowEntity
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse

/**
 * Created by andrea on 15/9/18.
 * Api Service with the definition of all the API REST requests being used
 */
interface ApiService {

    // Popular Tv Shows endPoint
    @GET("tv/popular?api_key=5496223e71ac936eda331298ec9bc5aa")
    fun getPopularTvShows(@Query("page") page: Int): Observable<PopularTvShowsResponse>

    //Tv Show by id endPoint
    @GET("tv/{tv_id}?api_key=5496223e71ac936eda331298ec9bc5aa")
    fun getTvShowById(@Path("tv_id") showId: Int): Observable<TvShowEntity>

    //Similar tv shows endPoint
    @GET("tv/{tv_id}/similar?api_key=5496223e71ac936eda331298ec9bc5aa")
    fun getSimilarTvShows(@Path("tv_id") showId: Int, @Query("page") page: Int): Observable<PopularTvShowsResponse>
}