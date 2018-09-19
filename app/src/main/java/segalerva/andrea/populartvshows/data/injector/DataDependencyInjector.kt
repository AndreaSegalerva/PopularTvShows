package segalerva.andrea.populartvshows.data.injector

import segalerva.andrea.populartvshows.data.mapper.PopularTvShowsMapper
import segalerva.andrea.populartvshows.data.mapper.TvShowEntityMapper
import segalerva.andrea.populartvshows.data.network.RestClient
import segalerva.andrea.populartvshows.data.repository.TvShowsDataRepository
import segalerva.andrea.populartvshows.data.repository.datasource.RemoteTvShowsDataSource

/**
 * Created by andrea on 17/9/18.
 * Injector of all the dependencies needed in the data layer
 */
class DataDependencyInjector {

    fun getRemoteTvShowsDataSource() = RemoteTvShowsDataSource(getRestClient())

    /**
     * Returns an instance of tvShowEntityMapper needed in [PopularTvShowsMapper]
     */
    fun getTvShowEntityMapper(): TvShowEntityMapper = TvShowEntityMapper()

    /**
     * Returns an instance of PopularTvShowsMapper needed in [TvShowsDataRepository]
     */
    fun getPopularTvShowsMapper(): PopularTvShowsMapper {

        return PopularTvShowsMapper(getTvShowEntityMapper())
    }

    /**
     * Returns an instance of TVShowsDataRepository needed in every use case defined in the domain layer
     */
    fun getTvShowsDataRepository(): TvShowsDataRepository {

        return TvShowsDataRepository(getRemoteTvShowsDataSource(), getPopularTvShowsMapper(), getTvShowEntityMapper())
    }

    /**
     * Returns an instance of RestClient used in [RemoteTvShowsDataSource]
     */
    private fun getRestClient(): RestClient {
        return RestClient()
    }
}