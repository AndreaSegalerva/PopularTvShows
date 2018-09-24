package segalerva.andrea.populartvshows.data.mapper

import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.mockito.Mockito
import segalerva.andrea.populartvshows.data.model.entity.TvShowEntity
import kotlin.test.assertEquals

/**
 * Created by andrea on 23/9/18.
 * Unit Test of [TvShowEntityMapper]
 */

@RunWith(MockitoJUnitRunner::class)
class TvShowEntityMapperTest {

    private var tvShowEntityMapper: TvShowEntityMapper = mock()

    @Before
    fun setUp() {

        Mockito.`when`(tvShowEntityMapper.map(any())).thenCallRealMethod()
        Mockito.`when`(tvShowEntityMapper.mapList(any())).thenCallRealMethod()
    }

    @Test
    fun givenTvShowEntity_ThenMapToTvShow() {

        val tvShowEntity = TvShowEntity(892,
                "Agents of S.H.I.E.L.D",
                "2017-06-12",
                "Overview Description",
                5.2,
                234,
                "en",
                "backdropPath",
                "posterPath", 100, 4)

        val tvShow = tvShowEntityMapper.map(tvShowEntity)

        assertEquals(tvShowEntity.id, tvShow.id)
        assertEquals(tvShowEntity.originalName, tvShow.originalName)
        assertEquals(tvShowEntity.airDate, tvShow.airDate)
        assertEquals(tvShowEntity.overview, tvShow.overView)
        assertEquals(tvShowEntity.voteAverage, tvShow.voteAverage)
        assertEquals(tvShowEntity.voteCount, tvShow.voteCount)
        assertEquals(tvShowEntity.originalLanguage, tvShow.originalLanguage)
        assertEquals(tvShowEntity.backdropPath, tvShow.backdropPath)
        assertEquals(tvShowEntity.posterPath, tvShow.posterPath)
        assertEquals(tvShowEntity.numberEpisodes, tvShow.numberEpisodes)
        assertEquals(tvShowEntity.numberSeasons, tvShow.numberSeasons)
    }

    @Test
    fun givenTvShowEntityList_whenMapLisrThenMapToTvShowList() {

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

        val tvShowList = tvShowEntityMapper.mapList(tvShowEntityList)

        assertEquals(tvShowEntityList.size, tvShowList.size)
    }
}