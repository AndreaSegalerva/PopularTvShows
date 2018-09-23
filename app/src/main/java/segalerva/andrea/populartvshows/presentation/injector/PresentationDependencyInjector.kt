package segalerva.andrea.populartvshows.presentation.injector

import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.domain.injector.DomainDependencyInjector
import segalerva.andrea.populartvshows.domain.interactor.usecases.populartvshows.GetPopularTvShows
import segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows.GetSimilarTvShows
import segalerva.andrea.populartvshows.domain.interactor.usecases.tvshowdetail.GetTvShowDetail
import segalerva.andrea.populartvshows.presentation.mapper.TvShowDetailMapper
import segalerva.andrea.populartvshows.presentation.mapper.TvShowMapper

/**
 * Created by andrea on 16/9/18.
 * Initializing all the dependencies needed in the presentation layer
 */
class PresentationDependencyInjector(private val dataDependencyInjector: DataDependencyInjector, private val domainDependencyInjector: DomainDependencyInjector) {

    /**
     * Returns an instance of the use case [GetPopularTvShows]
     */
    fun getPopularTvShows(): GetPopularTvShows {

        return GetPopularTvShows(dataDependencyInjector)
    }

    /**
     * Returns an instance of the use case [GetTvShowDetail] use case
     */
    fun getTvShowDetail(): GetTvShowDetail {

        return GetTvShowDetail(dataDependencyInjector, domainDependencyInjector)
    }

    /**
     * Returns an instance of the use case [GetSimilarTvShows] use case
     */
    fun getSimilarTvShows(): GetSimilarTvShows {

        return GetSimilarTvShows(dataDependencyInjector)
    }

    /**
     * Returns an instance of [TvShowMapper]
     */
    fun getTvShowMapper(): TvShowMapper = TvShowMapper()


    /**
     * Returns an instance of [TvShowDetailMapper]
     */
    fun getTVShowDetailMapper(): TvShowDetailMapper {

        return TvShowDetailMapper()
    }
}