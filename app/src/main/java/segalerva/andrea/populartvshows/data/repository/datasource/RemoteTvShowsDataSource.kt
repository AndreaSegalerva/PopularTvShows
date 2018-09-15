package segalerva.andrea.populartvshows.data.repository.datasource


import retrofit2.Call
import segalerva.andrea.populartvshows.data.network.RestClient
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse

/**
 * Created by andrea on 15/9/18.
 */
class RemoteTvShowsDataSource(private val restClient: RestClient) : TvShowsDataSource {

    override fun getPopularTvShows(page: Int): Call<PopularTvShowsResponse> {

        return restClient.apiService!!.getPopularTvShows(page)
    }
}