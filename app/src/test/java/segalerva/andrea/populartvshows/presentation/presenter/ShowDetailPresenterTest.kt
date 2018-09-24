package segalerva.andrea.populartvshows.presentation.presenter

import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.domain.model.PopularTvShows
import segalerva.andrea.populartvshows.domain.model.TvShow
import segalerva.andrea.populartvshows.domain.model.TvShowDetail
import segalerva.andrea.populartvshows.presentation.injector.PresentationDependencyInjector
import segalerva.andrea.populartvshows.presentation.model.TvShowView
import segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail.ShowDetailView
import segalerva.andrea.populartvshows.presentation.view.presenter.ShowDetailPresenter

/**
 * Created by andrea on 23/9/18.
 * Unit Test of the [ShowDetailPresenter]
 */
@RunWith(MockitoJUnitRunner::class)

class ShowDetailPresenterTest {

    private val view: ShowDetailView = mock()
    private val presentationDependencyInjector: PresentationDependencyInjector = mock()
    private val tvShowView: TvShowView = mock()

    lateinit var presenter: ShowDetailPresenter
    
    @Before
    fun setUp() {

        presenter = ShowDetailPresenter(view, presentationDependencyInjector)
    }

    @Test
    fun givenDipositiveConnectedAndNoMoreSimilarToShow_WhenOnLoadMore_DisableLoadMoreTvSHows() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(true)

        presenter.onLoadMore()

        verify(view, times(1)).disableLoadMoreSimilarTvShows()
    }

    @Test
    fun givenDipositiveNotConnected_WhenOnLoadMore_ShowInformativeMessage() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(false)

        presenter.onLoadMore()

        verify(view, times(1)).hideSimilarTvShows()
        verify(view, times(1)).showConnectionDialog()
        verify(view, times(1)).showErrorMessage(R.string.need_connection_to_continue)
    }

    @Test
    fun givenDispositiveConnected_whenOnTvShowClicked_ThenNavigateToShowDetail() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(true)
        Mockito.`when`(tvShowView.id).thenReturn(123)
        Mockito.`when`(tvShowView.name).thenReturn("Sons of Anarchy")

        presenter.onTvShowClicked(tvShowView)

        verify(view, times(1)).navigateToTvShowDetail(123, "Sons of Anarchy")
    }

    @Test
    fun givenDispositiveNotConnected_whenOnTvShowClicked_ThenShowInformativeMessage() {

        Mockito.`when`(presenter.isConnectedToInternet()).thenReturn(false)

        presenter.onTvShowClicked(tvShowView)

        verify(view, times(1)).showConnectionDialog()
    }

    /**
     * Get TvShowDetail with similar tvShows
     */
    private fun getTvShowDetail(): TvShowDetail = TvShowDetail(132,
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
            8, getSimilarTvShows())

    /**
     * Get PopularTvShows model
     */
    private fun getSimilarTvShows(): PopularTvShows = PopularTvShows(1, 3, createTvShows())


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
}