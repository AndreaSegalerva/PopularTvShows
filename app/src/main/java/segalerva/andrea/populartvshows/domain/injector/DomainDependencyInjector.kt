package segalerva.andrea.populartvshows.domain.injector

import segalerva.andrea.populartvshows.domain.mapper.TvShowDetailMapper
/**
 * Created by andrea on 19/9/18.
 * Injector of all the dependencies needed in the domain layer
 */
class DomainDependencyInjector {

    /**
     * Returns an instance of TvShowMapper
     */
    fun getTvShowDetailMapper(): TvShowDetailMapper = TvShowDetailMapper()

}