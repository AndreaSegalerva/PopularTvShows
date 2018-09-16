package segalerva.andrea.populartvshows.presentation.view.presenter.injector

import segalerva.andrea.populartvshows.domain.interactor.usecases.GetPopularTvShows
import segalerva.andrea.populartvshows.presentation.mapper.TvShowMapper

/**
 * Created by andrea on 16/9/18.
 * Dependency Injector used in the PopularTvShowsListPresenter
 * Initializing all the dependencies needed in the presenter
 */
class PresenterDependencyInjector {

    fun getPopularTvShows(): GetPopularTvShows {

        return GetPopularTvShows()
    }

    fun getTvShowMapper(): TvShowMapper {

        return TvShowMapper()
    }
}