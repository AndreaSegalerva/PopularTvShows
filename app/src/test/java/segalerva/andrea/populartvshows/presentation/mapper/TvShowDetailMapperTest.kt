package segalerva.andrea.populartvshows.presentation.mapper

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import segalerva.andrea.populartvshows.domain.model.PopularTvShows
import segalerva.andrea.populartvshows.domain.model.TvShow
import segalerva.andrea.populartvshows.domain.model.TvShowDetail
import segalerva.andrea.populartvshows.presentation.mapper.TvShowDetailMapper
import kotlin.test.assertEquals

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
    fun givenTvShowDetail_WhenMap_ThenReturnTvShowDetailView() {

        val tvShowDetail = createTvShowDetail()

        val tvShowDetailView = tvShowDetailMapper.map(tvShowDetail)

        assertEquals(tvShowDetail.id, tvShowDetailView.id)
        assertEquals(tvShowDetail.originalName, tvShowDetailView.originalName)
        assertEquals(tvShowDetail.airDate, tvShowDetailView.airDate)
        assertEquals(tvShowDetail.overView, tvShowDetailView.overView)
        assertEquals(tvShowDetail.voteCount, tvShowDetailView.voteCount)
        assertEquals(tvShowDetail.voteAverage, tvShowDetailView.voteAverage)
        assertEquals(tvShowDetail.originalLanguage, tvShowDetailView.originalLanguage)
        assertEquals(tvShowDetail.backdropPath, tvShowDetailView.backdropPath)
        assertEquals(tvShowDetail.posterPath, tvShowDetailView.posterPath)
        assertEquals(tvShowDetail.numberEpisodes, tvShowDetailView.numberEpisodes)
        assertEquals(tvShowDetail.numberSeasons, tvShowDetailView.numberSeasons)
        assertEquals(tvShowDetail.similarTvShows, tvShowDetailView.similarShows)
    }

    /**
     * Create TvShowDetail Model
     */
    private fun createTvShowDetail(): TvShowDetail = TvShowDetail(129,
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
            8, createSimilarTvShows())


    /**
     * Create PopularTvShows model
     */
    private fun createSimilarTvShows(): PopularTvShows = PopularTvShows(1,
            20,
            createTvShows())

    /**
     * Create TvShow model
     */
    private fun createTvShow(): TvShow = TvShow(892,
            "Agents of S.H.I.E.L.D",
            "2017-06-12",
            "Overview Description",
            234,
            6.5,
            "en",
            "backdropPath",
            "posterPath", 100, 4)

    /**
     * Create list of TvShow model
     */
    private fun createTvShows(): List<TvShow> {

        val shows = mutableListOf<TvShow>()

        for (i in 1..4) {
            shows.add(createTvShow())
        }
        return shows
    }
}