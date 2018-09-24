package segalerva.andrea.populartvshows.presentation.presenter

import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.domain.interactor.callback.BaseDisposableObserver
import segalerva.andrea.populartvshows.domain.interactor.usecases.populartvshows.GetPopularTvShows
import segalerva.andrea.populartvshows.domain.model.PopularTvShows
import segalerva.andrea.populartvshows.domain.model.TvShow
import segalerva.andrea.populartvshows.presentation.injector.PresentationDependencyInjector
import segalerva.andrea.populartvshows.presentation.mapper.TvShowMapper
import segalerva.andrea.populartvshows.presentation.model.TvShowView
import segalerva.andrea.populartvshows.presentation.view.features.populartvshows.PopularTvShowsListView
import segalerva.andrea.populartvshows.presentation.view.presenter.PopularTvShowsListPresenter
import kotlin.test.assertEquals

/**
 * Created by andrea on 23/9/18.
 * Unit Test of the [PopularTvShowsListPresenter]
 */
@RunWith(MockitoJUnitRunner::class)
class PopularTvShowsListPresenterTest {

    lateinit var presenter: PopularTvShowsListPresenter
    private val view: PopularTvShowsListView = mock()

    private val presentationDependencyInjector: PresentationDependencyInjector = mock()
    private val getPopularTvShows: GetPopularTvShows = mock()
    private val tvShowMapper: TvShowMapper = mock()
    private val tvShowView: TvShowView = mock()

    @Before
    fun setUp() {

        Mockito.`when`(presentationDependencyInjector.getPopularTvShows()).thenReturn(getPopularTvShows)
        Mockito.`when`(presentationDependencyInjector.getTvShowMapper()).thenReturn(tvShowMapper)
        presenter = PopularTvShowsListPresenter(view, presentationDependencyInjector)
        Mockito.`when`(presenter.isSafeManipulateView()).thenReturn(true)
    }

    @Test
    fun givenDeviceNotConnectedToInternet_WhenGetPopularTvShows_ThenShowMessage() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(false)
        presenter.getPopularTvShowsData(true, false)

        verify(view, times(1)).disableSwipeRefreshLayout()
        verify(view, times(1)).disableLoadMore()
        verify(view, times(1)).hideTvShowsList()
        verify(view, times(1)).showConnectionDialog()
        verify(view, times(1)).showErrorMessage(R.string.no_connection_try_again)
    }

    @Test
    fun givenDeviceConnectedToInternet_WhenGetPopularTvShows_ThenShowTvShows() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(true)

        presenter.getPopularTvShowsData(true, true)

        getPopularTvShowsSuccessfully()
    }

    @Test
    fun givenDeviceConnectedToInternet_WhenGetPopularTvShowsError_ThenShowTvShows() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(true)

        presenter.getPopularTvShowsData(true, true)

        getPopularTvShowsError()
    }

    @Test
    fun givenInternetConnection_WhenOnTryAgainClicked_ThenRefresh() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(true)

        presenter.onTryAgainClicked()

        getPopularTvShowsSuccessfully()
    }

    @Test
    fun givenInternetConnection_WhenOnRefresh_ThenGetPopularTvShows() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(true)

        presenter.onRefresh()

        getPopularTvShowsSuccessfully()
    }

    @Test
    fun givenInternetConnection_WhenOnLoadMoreAndNoMorePages_ThenDisableLoadMore() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(true)

        presenter.onLoadMore()

        verify(view, times(1)).disableLoadMore()
    }

    @Test
    fun givenDeviceConnectedToInternet_WhenOnTvShowClicked_ThenNavigateToShowDetail() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(true)
        presenter.onTvShowClicked(tvShowView)

        val tvShowViewCaptor = argumentCaptor<TvShowView>()

        tvShowViewCaptor.apply {
            verify(view, times(1)).navigateToTvShowDetail(capture())
            assertEquals(tvShowView, firstValue)
        }
    }

    @Test
    fun givenDeviceNotConnectedToInternet_WhenOnTvShowClicked_ThenShowMessage() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(false)
        presenter.onTvShowClicked(tvShowView)

        verify(view, times(1)).showConnectionDialog()
    }


    /**
     * Get PopularTvShows model
     */
    private fun getPopularTvShow(): PopularTvShows = PopularTvShows(1, 3, createTvShows())


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


    /**
     * Create list of TvShow
     */
    private fun createTvShows(): List<TvShow> {

        val tvShows = mutableListOf<TvShow>()
        for (i in 1..3) {

            tvShows.add(createTvShow())
        }
        return tvShows
    }

    /**
     * Scenario when getPopularTvShows is a success
     */
    private fun getPopularTvShowsSuccessfully() {

        val popularTvShowsCaptor = argumentCaptor<BaseDisposableObserver<PopularTvShows>>()

        popularTvShowsCaptor.apply {

            verify(getPopularTvShows, times(1)).execute(capture(), eq(1))
            firstValue.onNext(getPopularTvShow())

            verify(view, times(1)).showLoading()
            verify(view, times(1)).hideLoading()
            verify(view, times(1)).enableSwipeRefreshLayout()
            verify(view, times(1)).enableLoadMore()
            verify(view, times(1)).cleanListTvShows()
            verify(view, times(1)).showTvShows(any())
        }
    }


    /**
     * Scenario when getPopularTvShows is not a success
     */
    private fun getPopularTvShowsError() {

        val popularTvShowsCaptor = argumentCaptor<BaseDisposableObserver<PopularTvShows>>()

        popularTvShowsCaptor.apply {

            verify(getPopularTvShows, times(1)).execute(capture(), eq(1))
            firstValue.onError(Throwable("Something went wrong getting popular tv shows"))

            verify(view, times(1)).showLoading()
            verify(view, times(1)).hideLoading()
            verify(view, times(0)).enableSwipeRefreshLayout()
            verify(view, times(0)).enableLoadMore()
            verify(view, times(1)).disableSwipeRefreshLayout()
            verify(view, times(1)).disableLoadMore()
            verify(view, times(1)).showErrorMessage(R.string.something_went_wrong)
            verify(view, times(0)).cleanListTvShows()
            verify(view, times(0)).showTvShows(any())
        }
    }
}