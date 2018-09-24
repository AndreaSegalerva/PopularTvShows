package segalerva.andrea.populartvshows.domain.interactor.usecases

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.data.repository.TvShowsRepository
import segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows.GetSimilarTvShows
import segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows.GetSimilarTvShowsParams

/**
 * Created by andrea on 23/9/18.
 * Unit Test for use case [GetSimilarTvShows]
 */

@RunWith(MockitoJUnitRunner::class)
class GetSimilarTvShowsTest {

    private lateinit var getSimilarTvShows: GetSimilarTvShows
    private val dataDependencyInjector: DataDependencyInjector = mock()
    private val tvShowsRepository: TvShowsRepository = mock()

    @Before
    fun setUp() {

        getSimilarTvShows = GetSimilarTvShows(dataDependencyInjector)
        Mockito.`when`(dataDependencyInjector.getTvShowsRepository()).thenReturn(tvShowsRepository)
    }


    @Test
    fun givenRepository_WhenBuildInteractorObservable_CallGetSimilarTvShowsRepository() {

        val getSimilarTvShowsParams=GetSimilarTvShowsParams(134,3)

        getSimilarTvShows.buildInteractorObservable(getSimilarTvShowsParams)

        verify(tvShowsRepository, times(1)).getSimilarTvShows(getSimilarTvShowsParams.showId,getSimilarTvShowsParams.page)
    }
}
