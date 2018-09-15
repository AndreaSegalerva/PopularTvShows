package segalerva.andrea.populartvshows.data.repository

import retrofit2.Call
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse
import segalerva.andrea.populartvshows.data.repository.datasource.RemoteTvShowsDataSource

/**
 * Created by andrea on 15/9/18.
 */
class TvShowsDataRepository(private val remoteTvShowsDataSource: RemoteTvShowsDataSource) : TvShowsRepository {

    override fun getPopularTVShows(page: Int): Call<PopularTvShowsResponse> {

        return remoteTvShowsDataSource.getPopularTvShows(page)
    }
}