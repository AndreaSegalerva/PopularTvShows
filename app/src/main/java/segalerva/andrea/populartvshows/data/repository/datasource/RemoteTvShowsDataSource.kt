package segalerva.andrea.populartvshows.data.repository.datasource


import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.model.entity.TvShowEntity
import segalerva.andrea.populartvshows.data.network.RestClient
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse

/**
 * Created by andrea on 15/9/18.
 */
class RemoteTvShowsDataSource(private val restClient: RestClient) : TvShowsDataSource {

    override fun getPopularTvShows(page: Int): Observable<PopularTvShowsResponse> {

        return restClient.apiService!!.getPopularTvShows(page)
    }

    override fun getTvShowById(showId: Int): Observable<TvShowEntity> {

        return restClient.apiService!!.getTvShowById(showId)
    }

    override fun getSimilarTvShows(showId: Int, page: Int): Observable<PopularTvShowsResponse> {

        return restClient.apiService!!.getSimilarTvShows(showId, page)
    }
}