package segalerva.andrea.populartvshows.presentation.mapper

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import segalerva.andrea.populartvshows.domain.model.TvShow
import segalerva.andrea.populartvshows.presentation.mapper.TvShowMapper
import kotlin.test.assertEquals

/**
 * Created by andrea on 23/9/18.
 * Unit Tests of [TvShowMapper]
 */
@RunWith(MockitoJUnitRunner::class)
class TvShowMapperTest {

    private val tvShowMapper: TvShowMapper = mock()

    @Before
    fun setUp() {

        Mockito.`when`(tvShowMapper.map(any())).thenCallRealMethod()
        Mockito.`when`(tvShowMapper.mapList(any())).thenCallRealMethod()
    }

    @Test
    fun givenTvShow_whenMap_ThenReturnTvShowView() {

        val tvShow = createTvShow()
        val tvShowView = tvShowMapper.map(tvShow)

        assertEquals(tvShow.id, tvShowView.id)
        assertEquals(tvShow.originalName, tvShowView.name)
        assertEquals(tvShow.posterPath, tvShowView.posterPath)
        assertEquals(tvShow.overView, tvShowView.overview)
        assertEquals(tvShow.voteCount, tvShowView.voteCount)
        assertEquals(tvShow.voteAverage, tvShowView.voteAverage)
        assertEquals(tvShow.originalLanguage, tvShowView.originalLanguage)
    }

    @Test
    fun givenTvShows_WhenMapList_ThenReturnTvShowsView() {

        val tvShows = createTvShows()
        val tvShowsView = tvShowMapper.mapList(tvShows)

        assertEquals(tvShows.size,tvShowsView.size)
    }

    /**
     * Creation of TvShow model
     */
    private fun createTvShow(): TvShow = TvShow(129,
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

    /**
     * Create list of TvShow model
     */
    private fun createTvShows(): List<TvShow> {


        val tvShow = createTvShow()
        val tvShows = mutableListOf<TvShow>()

        for (i in 1..7) {
            tvShows.add(tvShow)
        }

        return tvShows
    }
}