package segalerva.andrea.populartvshows.data.repository.datasource

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import segalerva.andrea.populartvshows.data.model.entity.TvShowEntity
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse
import segalerva.andrea.populartvshows.data.network.ApiService
import segalerva.andrea.populartvshows.data.network.RestClient

/**
 * Created by andrea on 23/9/18.
 * Unit test for [RemoteTvShowsDataSource]
 */
@RunWith(MockitoJUnitRunner::class)
class RemoteTvShowsDataSourceTest {

    private val restClient: RestClient = mock()
    val apiService: ApiService = mock()
    private lateinit var remoteTvShowsDataSource: TvShowsDataSource

    @Before
    fun setUp() {

        Mockito.`when`(restClient.apiService).thenReturn(apiService)
        Mockito.`when`(restClient.apiService!!.getPopularTvShows(any())).thenReturn(Observable.just(getPopularTvShowsResponse()))
        Mockito.`when`(restClient.apiService!!.getTvShowById(any())).thenReturn(Observable.just(getTvSHowEntity()))
        Mockito.`when`(restClient.apiService!!.getSimilarTvShows(any(), any())).thenReturn(Observable.just(getPopularTvShowsResponse()))
        remoteTvShowsDataSource = RemoteTvShowsDataSource(restClient)
    }

    @Test
    fun whenGetPopularTvShows_ThenGetPopularTvShowsFromApi() {

        val page = 1
        remoteTvShowsDataSource.getPopularTvShows(page)

        verify(apiService, times(1)).getPopularTvShows(page)
    }

    @Test
    fun whenGetTvShowById_ThenGetTvShowByIdFromApi() {

        val showId = 123
        remoteTvShowsDataSource.getTvShowById(showId)

        verify(apiService, times(1)).getTvShowById(showId)
    }

    @Test
    fun whenGetSimilarTvShows_ThenGetSimilarTvShowsFromApi() {

        val page = 1
        val showId=123
        remoteTvShowsDataSource.getSimilarTvShows(showId,page)

        verify(apiService, times(1)).getSimilarTvShows(showId,page)
    }

    /**
     * Get PopularTvShowsResponse when calling getPopularTVShows and getSimilarTvShows
     */
    private fun getPopularTvShowsResponse(): PopularTvShowsResponse {

        val tvShowEntityList = createTvShowEntityList()
        return PopularTvShowsResponse(1, 40, 2, tvShowEntityList)
    }

    /**
     * Get TvShowEntity
     */
    private fun getTvSHowEntity(): TvShowEntity = TvShowEntity(892,
            "Agents of S.H.I.E.L.D",
            "2017-06-12",
            "Overview Description",
            5.2,
            234,
            "en",
            "backdropPath",
            "posterPath", 100, 4)

    /**
     * Create TvShowEntity list
     */
    private fun createTvShowEntityList(): List<TvShowEntity> {

        val tvShowEntityList = mutableListOf<TvShowEntity>()

        for (i in 1..5) {

            tvShowEntityList.add(getTvSHowEntity())
        }

        return tvShowEntityList
    }

}