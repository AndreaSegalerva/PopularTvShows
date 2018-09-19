package segalerva.andrea.populartvshows.presentation.injector

import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.domain.interactor.usecases.populartvshows.GetPopularTvShows
import segalerva.andrea.populartvshows.presentation.mapper.TvShowMapper

/**
 * Created by andrea on 16/9/18.
 * Initializing all the dependencies needed in the presentation layer
 */
class PresentationDependencyInjector(private val dataDependencyInjector: DataDependencyInjector) {

    /**
     * Returns an instance of the use case GetPopularTvShows
     */
    fun getPopularTvShows(): GetPopularTvShows {

        return GetPopularTvShows(dataDependencyInjector)
    }

    /**
     * Returns an instance of TvShowMapper
     */
    fun getTvShowMapper(): TvShowMapper = TvShowMapper()

}