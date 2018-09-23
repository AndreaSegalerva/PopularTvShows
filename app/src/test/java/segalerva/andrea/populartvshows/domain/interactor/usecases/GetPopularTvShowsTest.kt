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
import segalerva.andrea.populartvshows.data.repository.TvShowsDataRepositoryImpl
import segalerva.andrea.populartvshows.domain.interactor.usecases.populartvshows.GetPopularTvShows

/**
 * Created by andrea on 23/9/18.
 * Unit Test of use case [GetPopularTvShows]
 */

@RunWith(MockitoJUnitRunner::class)
class GetPopularTvShowsTest {

    private lateinit var getPopularTvShows: GetPopularTvShows
    private val dataDependencyInjector: DataDependencyInjector = mock()
    private val tvShowsDataRepository: TvShowsDataRepositoryImpl = mock()

    @Before
    fun setUp() {

        getPopularTvShows = GetPopularTvShows(dataDependencyInjector)
        Mockito.`when`(dataDependencyInjector.getTvShowsDataRepository()).thenReturn(tvShowsDataRepository)
    }

    @Test
    fun givenRepository_WhenBuildInteractorObservable_CallGetPopularTvShowsFromRepository() {

        getPopularTvShows.buildInteractorObservable(1)

        verify(tvShowsDataRepository, times(1)).getPopularTVShows(1)
    }
}