package segalerva.andrea.populartvshows.domain.mapper

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import segalerva.andrea.populartvshows.domain.mapper.TvShowDetailMapper
import segalerva.andrea.populartvshows.domain.model.TvShow
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Created by andrea on 23/9/18.
 * Unit test of [TvShowDetailMapper]
 */
@RunWith(MockitoJUnitRunner::class)
class TvShowDetailMapperTest {

    private val tvShowDetailMapper: TvShowDetailMapper = mock()


    @Before
    fun setUp() {

        Mockito.`when`(tvShowDetailMapper.map(any())).thenCallRealMethod()
    }


    @Test
    fun givenTvShow_WhenMap_ThenReturnTvShowDetail() {

        val tvShow = createTvShow()

        val tvShowDetail = tvShowDetailMapper.map(tvShow)

        assertEquals(tvShow.id, tvShowDetail.id)
        assertEquals(tvShow.originalName, tvShowDetail.originalName)
        assertEquals(tvShow.airDate, tvShowDetail.airDate)
        assertEquals(tvShow.overView, tvShowDetail.overView)
        assertEquals(tvShow.voteCount, tvShowDetail.voteCount)
        assertEquals(tvShow.voteAverage, tvShowDetail.voteAverage)
        assertEquals(tvShow.originalLanguage, tvShowDetail.originalLanguage)
        assertEquals(tvShow.backdropPath, tvShowDetail.backdropPath)
        assertEquals(tvShow.posterPath, tvShowDetail.posterPath)
        assertEquals(tvShow.numberEpisodes, tvShowDetail.numberEpisodes)
        assertEquals(tvShow.numberSeasons, tvShowDetail.numberSeasons)
        assertNull(tvShowDetail.similarTvShows)
    }

    /**
     * Create TvShow model
     */
    private fun createTvShow(): TvShow = TvShow(132,
            "Sons of Anarchy",
            "2008-09-03",
            " Sons of Anarchy, aka SAMCRO, " +
                    "is a motorcycle club that operates " +
                    "both illegal and legal businesses " +
                    "in the small town of Charming.",
            13000,
            9.0,
            "en",
            "backddropPath",
            "posterPath",
            400,
            8)
}