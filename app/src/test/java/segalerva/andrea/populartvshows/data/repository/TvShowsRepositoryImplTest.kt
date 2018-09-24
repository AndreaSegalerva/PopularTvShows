package segalerva.andrea.populartvshows.data.repository

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
import segalerva.andrea.populartvshows.data.mapper.PopularTvShowsMapper
import segalerva.andrea.populartvshows.data.mapper.TvShowEntityMapper
import segalerva.andrea.populartvshows.data.model.entity.TvShowEntity
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse
import segalerva.andrea.populartvshows.data.repository.datasource.TvShowsDataSource

/**
 * Created by andrea on 23/9/18.
 * Unit tests for [TvShowsDataRepositoryImpl]
 */
@RunWith(MockitoJUnitRunner::class)
class TvShowsRepositoryImplTest {

    private val tvShowsDataSource: TvShowsDataSource = mock()
    private lateinit var popularTvShowsMapper: PopularTvShowsMapper
    private var tvShowEntityMapper: TvShowEntityMapper = mock()
    lateinit var tvShowsDataRepository: TvShowsDataRepositoryImpl

    @Before
    fun setUp() {

        popularTvShowsMapper = PopularTvShowsMapper(tvShowEntityMapper)
        tvShowsDataRepository = TvShowsDataRepositoryImpl(tvShowsDataSource, popularTvShowsMapper, tvShowEntityMapper)

        //Data source scenarios
        Mockito.`when`(tvShowsDataSource.getPopularTvShows(any())).thenReturn(Observable.just(getPopularTvShowsResponse()))
        Mockito.`when`(tvShowsDataSource.getTvShowById(any())).thenReturn(Observable.just(getTvSHowEntity()))
        Mockito.`when`(tvShowsDataSource.getSimilarTvShows(any(), any())).thenReturn(Observable.just(getPopularTvShowsResponse()))
    }

    @Test
    fun givenPopularTvShowsResponse_WhenGetPopularTvShows_InvokeDataSourceGetPopularTvShows() {

        val page = 1

        tvShowsDataRepository.getPopularTVShows(page)
        verify(tvShowsDataSource, times(1)).getPopularTvShows(page)
    }


    @Test
    fun givenTvShowResponse_WhenGetShowById_InvokeDataSourceGetShowById() {

        val showId = 134

        tvShowsDataRepository.getShowById(showId)
        verify(tvShowsDataSource, times(1)).getTvShowById(showId)
    }

    @Test
    fun givenPopularTvShowResponse_WhenGetSimilarTvShows_InvokeDataSourceGetSimilarTvShows() {

        val showId = 134
        val page = 1

        tvShowsDataRepository.getSimilarTvShows(showId, page)
        verify(tvShowsDataSource, times(1)).getSimilarTvShows(showId, page)
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