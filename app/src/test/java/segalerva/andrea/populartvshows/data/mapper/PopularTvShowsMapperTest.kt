package segalerva.andrea.populartvshows.data.mapper

import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import segalerva.andrea.populartvshows.data.model.entity.TvShowEntity
import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse
import kotlin.test.assertEquals

/**
 * Created by andrea on 23/9/18.
 * Unit Test for [PopularTvShowsMapper]
 */

@RunWith(MockitoJUnitRunner::class)
class PopularTvShowsMapperTest {


    private lateinit var popularTvShowsMapper: PopularTvShowsMapper
    private var tvShowEntityMapper: TvShowEntityMapper = mock()

    @Before
    fun setUp() {

        popularTvShowsMapper = PopularTvShowsMapper(tvShowEntityMapper)
    }

    @Test
    fun givenPopularTcShowsResponse_WhenMap_ThenReturnPopularTvShows() {


        val tvShowEntity = TvShowEntity(892,
                "Agents of S.H.I.E.L.D",
                "2017-06-12",
                "Overview Description",
                5.2,
                234,
                "en",
                "backdropPath",
                "posterPath", 100, 4)

        val tvShowEntityList: ArrayList<TvShowEntity> = ArrayList()
        tvShowEntityList.add(tvShowEntity)

        val popularTvShowsResponse = PopularTvShowsResponse(1, 40, 2, tvShowEntityList)

        val popularTvShows = popularTvShowsMapper.map(popularTvShowsResponse)

        assertEquals(popularTvShowsResponse.page, popularTvShows.page)
        assertEquals(popularTvShowsResponse.totalPages, popularTvShows.totalPages)
    }
}